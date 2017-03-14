package jsystem.perfecto.browser;

import java.io.File;
import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.browser.ProgressListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import jsystem.perfecto.browser.connector.PerfectoDashboardConnectorServer;
import jsystem.perfecto.browser.connector.PerfectoLabConnectorServer;

/**
 * @author Rony Byalsky
 */
public class PerfectoMobileBrowser {

	private static Display display;
	private static Shell shell;
	private static Browser browser;
	
	public static void main(String[] args) throws Exception {

		PerfectoProperties.loadProperties();
		
		if (args.length > 0) {
			
			if (args[0].equalsIgnoreCase("lab") || args[0].equalsIgnoreCase("dashboard")) {
				launchPerfectoBrowser(args[0]);
			}
			else {
				System.err.println("Command line argument can only be 'lab' or 'dashboard'");
				System.exit(1);
			}
		}
		else {
			launchPerfectoBrowser("lab");
		}
	}
	
	private static void launchPerfectoBrowser(final String labOrDashboard) throws InterruptedException {
		
		display = new Display();
		shell = new Shell(display);

		if (new File("perfecto_icon.png").exists()) {
			shell.setImage(new Image(display, "perfecto_icon.png"));
		}
		else if (new File("perfecto/perfecto_icon.png").exists()) {
			shell.setImage(new Image(display, "perfecto/perfecto_icon.png"));
		}

		shell.setLayout(new GridLayout());

		// we want to kill the connector server thread when the window is closed
		shell.addListener(SWT.Close, new Listener() {
			@Override
			public void handleEvent(Event event) {
				if (labOrDashboard.equalsIgnoreCase("lab")) {
					PerfectoLabConnectorServer.stopServer();
				}
				else if (labOrDashboard.equalsIgnoreCase("dashboard")) {
					PerfectoDashboardConnectorServer.stopServer();
				}
			}
		});

		Composite compositeLocation = new Composite(shell, SWT.NULL);
		compositeLocation.setLayout(new GridLayout(3, false));
		compositeLocation.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		browser = new Browser(shell, SWT.BORDER);
		browser.setLayoutData(new GridData(GridData.FILL_BOTH));

		Composite compositeStatus = new Composite(shell, SWT.NULL);
		compositeStatus.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		compositeStatus.setLayout(new GridLayout(2, false));

		String perfectoUrl = null;
		
		if (labOrDashboard.equalsIgnoreCase("lab")) {
			shell.setText("Perfecto Lab");
			initPerfectoLabBrowserFunctions();
			perfectoUrl = "https://" + PerfectoProperties.getHost() + "/nexperience/selenium.jsp?container=eclipse";
		}
		else if (labOrDashboard.equalsIgnoreCase("dashboard")) {
			shell.setText("Perfecto Dashboard");
			initPerfectoDashboardBrowserFunctions();
			perfectoUrl = "https://" + PerfectoProperties.getHost() + "/nexperience/dashboard.jsp";
		}
		
		print("Opening URL: " + perfectoUrl);
		browser.setUrl(perfectoUrl);
	
		// adding a "progress listener" to the browser to detect when finished loading a page
		// and check if there's a need to log-in using the doExternalLogin() JavaScript function
		browser.addProgressListener(new ProgressListener() {
			
			@Override
			public void completed(ProgressEvent progressEvent) {
				
				String currentUrl = browser.getUrl();
				print("current URL: " + currentUrl);
				
				if (currentUrl.contains("np-cas/login") || currentUrl.contains("login.jsp")) {

					if (PerfectoProperties.getUsername() != null && PerfectoProperties.getPassword() != null) {
						print("invoking doExternalLogin() JavaScript function");
						browser.execute("doExternalLogin('" + PerfectoProperties.getUsername() + "','" + PerfectoProperties.getPassword() + "',false);");
					}
					else {
						System.out.println("WARNING: perfecto.properties file wasn't found. Can't auto-login");
					}
				}
			}
			
			@Override
			public void changed(ProgressEvent progressEvent) {
			}
		});
		
		shell.setSize(800, 600);
		shell.open();
		
		startConnectorServer(labOrDashboard);
		
		while (!shell.isDisposed()) {
			
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

		display.dispose();
	}
	
	private static void initPerfectoLabBrowserFunctions() {
        new PerfectoLabBrowserFunction(browser, "recordLineCallback");
        new PerfectoLabBrowserFunction(browser, "insertLineCallback");
        new PerfectoLabBrowserFunction(browser, "readyToRunPluginCallback");
        new PerfectoLabBrowserFunction(browser, "logOutPluginCallback");
        new PerfectoLabBrowserFunction(browser, "isInEclipsePluginCallback");
        new PerfectoLabBrowserFunction(browser, "getContainerJspNameCallback");
        new PerfectoLabBrowserFunction(browser, "checkVersionCompatibilityCallback");
        new PerfectoLabBrowserFunction(browser, "openLinkInBrowserCallback");
		new PerfectoLabBrowserFunction(browser, "startRemoteAccessCallback");
        new PerfectoLabBrowserFunction(browser, "getApplicationContainerConfigurationCallback");
	}
	
	private static void initPerfectoDashboardBrowserFunctions() {
		new PerfectoDashboardBrowserFunction(browser, "isInEclipsePluginCallback");
		new PerfectoDashboardBrowserFunction(browser, "getContainerJspNameCallback");
		new PerfectoDashboardBrowserFunction(browser, "openLinkInBrowserCallback");
	}
	
	private static void startConnectorServer(String labOrDashboard) {
		
		if (labOrDashboard.equalsIgnoreCase("lab")) {
			try {
				PerfectoLabConnectorServer.startServer(PerfectoProperties.getHost());
			}
			catch (IOException e) {
				print("Terminating. Perfecto Lab is already running. (TCP port " + PerfectoLabConnectorServer.getServerPort() + " is in use).");
				System.exit(1);
			}
		}
		else if (labOrDashboard.equalsIgnoreCase("dashboard")) {
			try {
				PerfectoDashboardConnectorServer.startServer(PerfectoProperties.getHost());
			}
			catch (IOException e) {
				print("Terminating. Perfecto Dashboard is already running. (TCP port " + PerfectoDashboardConnectorServer.getServerPort() + " is in use).");
				System.exit(1);
			}
		}
	}
	
	private static void print(String str) {
		System.out.println("[PerfectoMobileBrowser]: " + str);
	}
}