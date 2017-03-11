package jsystem.perfecto.browser;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;

import jsystem.perfecto.browser.connector.PerfectoLabConnectorServer;

public class PerfectoLabBrowserFunction extends BrowserFunction {

	private static final String SELENIUM_URL = "selenium.jsp?container=eclipse";
	
	private static double COMPATIBILITY_VERSION = 7;
	private static double REQUIRED_WEB_APP_VERSION = 6;

	private Browser browser = null;
	private String functionName = null;

	public PerfectoLabBrowserFunction(Browser browser, String name) {
		super(browser, name);
		this.browser = browser;
		this.functionName = name;
	}

	@Override
	public Object function(Object[] args) {

		print(functionName);
		
		if (functionName == "readyToRunPluginCallback") {
			
			String executionId = (String) args[0];
			print("executionId = " + executionId);
			PerfectoLabConnectorServer.setExecutionId(executionId);
		}

		else if (functionName == "logOutPluginCallback") {
			
			print("After log out: setting executionId=null");
			PerfectoLabConnectorServer.setExecutionId(null);
		}

		else if (functionName == "getContainerJspNameCallback") {
			return SELENIUM_URL;
		}

		else if (functionName == "isInEclipsePluginCallback") {
			return true;
		}

		else if (functionName == "checkVersionCompatibilityCallback") {
			return checkVersionCompatibilityHandler(args);
		}

		else if (functionName == "openLinkInBrowserCallback") {
			browser.setUrl((String) args[0]);
		}

		else if (functionName == "startRemoteAccessCallback") {
			startRemoteAccessHandler(args);
		}

		else if (functionName == "getApplicationContainerConfigurationCallback") {
			return getApplicationContainerConfiguration();
		}
		
		/*
		if (functionName == "recordLineCallback") {
		}

		else if (functionName == "insertLineCallback") {
		}
		*/

		return "";
	}

	private Boolean checkVersionCompatibilityHandler(Object[] args) {

		double webAppVersion = (double) args[0];
		double requiredPluginVersion = (double)(args[1]);

		print("checkVersionCompatibilityHandler(): webAppVersion=" + webAppVersion + ", requiredPluginVersion=" + requiredPluginVersion);
		print("checkVersionCompatibilityHandler(): Plugin version: " + COMPATIBILITY_VERSION + ", required webApp version: " + REQUIRED_WEB_APP_VERSION);

		return (webAppVersion >= REQUIRED_WEB_APP_VERSION && COMPATIBILITY_VERSION >= requiredPluginVersion);
	}

	private void startRemoteAccessHandler(Object[] args) {
		
		String url = (String)args[0];

		try {
			Boolean isMac = System.getProperty("os.name").toLowerCase().startsWith("mac");
			ProcessBuilder pb = isMac ? new ProcessBuilder("open", url) :
				new ProcessBuilder("cmd.exe", "/c", "start", "\"\"", "\"" + url + "\"");
			pb.start();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

	private String getApplicationContainerConfiguration() {
		String jsonString = "Eclipse";
		return jsonString;
	}
	
	private void print(String str) {
		System.out.println("[PerfectoLabBrowserFunction]: " + str);
	}
	
	/*
	private void readyToRunHandler(String executionId) {
		System.out.println("readyToRunHandler - executionId: " + executionId);
		initExecutor(executionId);
	}
	*/
	
	/*
	private void initExecutor(String executionId) {
		//in any case if the Executor  is in use - release it
		releaseExecutor();

		serverSocket = new EclipseServerSocket( 
				PerfectoProperties.getCloud(),
				PerfectoProperties.getUsername(),
				PerfectoProperties.getPassword(),
				executionId);
		Executors.newCachedThreadPool().execute(serverSocket);
	}
	*/

	/*
	private void logOutHandler() {
		releaseExecutor();
		MobileCloudUtil.executeStopRecordCommand();
		MobileCloudUtil.setApplicationReady(false);
	}
	*/

	/*
	//release when the URL gets reloaded
	private void releaseExecutor() {
		if (serverSocket != null) {
			serverSocket.stop();
			serverSocket = null;
		}
	}
	*/
}
