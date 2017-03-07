package org.jsystem.perfecto.browser.functions;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;

public class LabBrowserFunction extends BrowserFunction {

	private static final String SELENIUM_URL = "selenium.jsp?container=eclipse";
	private static final String className = "LabBrowserFunction";
	
	private static double COMPATIBILITY_VERSION = 7;
	private static double REQUIRED_WEB_APP_VERSION = 6;

	private Browser browser = null;
	private String functionName = null;

	public LabBrowserFunction(Browser browser, String name) {
		super(browser, name);
		this.browser = browser;
		this.functionName = name;
	}

	@Override
	public Object function(Object[] args) {

		print(functionName);
		
		if (functionName == "recordLineCallback") {
			//MobileCloudRecordingView.this.recordLineHandler(args);
		}

		else if (functionName == "insertLineCallback") {
			//MobileCloudRecordingView.this.insertLineHandler(args);
		}

		else if (functionName == "readyToRunPluginCallback") {
			readyToRunHandler((String)args[0]);
		}

		else if (functionName == "logOutPluginCallback") {
			logOutHandler();
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
			openLinkInBrowserHandler(args);
		}

		else if (functionName == "startRemoteAccessCallback") {
			startRemoteAccessHandler(args);
		}

		else if (functionName == "getApplicationContainerConfigurationCallback") {
			return getApplicationContainerConfiguration();
		}

		return "";
	}

	private Boolean checkVersionCompatibilityHandler(Object[] args) {

		double webAppVersion = (double) args[0];
		double requiredPluginVersion = (double)(args[1]);

		System.out.println("checkVersionCompatibilityHandler(): webAppVersion=" + webAppVersion + ", requiredPluginVersion=" + requiredPluginVersion);
		System.out.println("checkVersionCompatibilityHandler(): Plugin version: " + COMPATIBILITY_VERSION + ", required webApp version: " + REQUIRED_WEB_APP_VERSION);

		return (webAppVersion >= REQUIRED_WEB_APP_VERSION && COMPATIBILITY_VERSION >= requiredPluginVersion);
	}

	private void readyToRunHandler(String executionId) {
		System.out.println("readyToRunHandler - executionId: " + executionId);
//		initExecutor(executionId);
	}

	private void startRemoteAccessHandler(Object[] args) {
		String url = (String)args[0];

		try {
			Boolean isMac = System.getProperty("os.name").toLowerCase().startsWith("mac");
			ProcessBuilder pb = isMac ? new ProcessBuilder("open", url) :
				new ProcessBuilder("cmd.exe", "/c", "start", "\"\"", "\"" + url + "\"");
			Process process = pb.start();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

	private void openLinkInBrowserHandler(Object[] args) {
		String linkToOpen = (String)args[0];
		try {
			browser.setUrl(linkToOpen);
			//MobileCloudBrowserView viewPart =  (MobileCloudBrowserView)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(MobileCloudBrowserView.ID);
			//viewPart.loadURL(linkToOpen);
		}
		catch (Exception e) {
			System.out.print(e);
			//printToDebugConsole("openLinkInBrowserHandler(): " + e.toString());
		}
	}
	
	private void initExecutor(String executionId) {
		//in any case if the Executor  is in use - release it
		releaseExecutor();

//		serverSocket = new EclipseServerSocket( 
//				PerfectoProperties.getCloud(),
//				PerfectoProperties.getUsername(),
//				PerfectoProperties.getPassword(),
//				executionId);
//		Executors.newCachedThreadPool().execute(serverSocket);
	}

	private void logOutHandler() {

		releaseExecutor();
		//MobileCloudUtil.executeStopRecordCommand();
		//MobileCloudUtil.setApplicationReady(false);
	}
	
	//release when the URL gets reloaded
	private void releaseExecutor() {

//		if (serverSocket != null) {
//			serverSocket.stop();
//			serverSocket = null;
//		}
	}

	private String getApplicationContainerConfiguration() {
		String jsonString = "Eclipse";
		return jsonString;
	}
	
	private void print(String str) {
		System.out.println("[" + className + "]: " + str);
	}
}
