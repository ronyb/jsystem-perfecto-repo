package org.jsystem.perfecto;

import java.io.File;
import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.jsystem.perfecto.browser.functions.PerfectoDashboardBrowserFunction;
import org.jsystem.perfecto.browser.functions.PerfectoLabBrowserFunction;
import org.jsystem.perfecto.connector.PerfectoDashboardConnectorServer;
import org.jsystem.perfecto.connector.PerfectoLabConnectorServer;

public class Main {

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
			System.err.println("Command line argument 'lab' or 'dashboard' must be provided");
			System.exit(1);
		}
	}
	
	private static void launchPerfectoBrowser(String labOrDashboard) throws InterruptedException {
		
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
	
		shell.setSize(800, 600);
		shell.open();
		
		startConnectorServer(labOrDashboard);
		

		while (!shell.isDisposed()) {
			
			doExternalLogin();
			
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
				print("Perfecto Dashboard is already running. (TCP port " + PerfectoDashboardConnectorServer.getServerPort() + " is in use).");
				System.exit(1);
			}
		}
	}
	
	private static void doExternalLogin() {
		
		String currentUrl = browser.getUrl();

		if (currentUrl.contains("np-cas/login") || currentUrl.contains("login.jsp")) {

			print("Current URL: " + currentUrl);
			print("Calling doExternalLogin");
			
			if (PerfectoProperties.getUsername() != null && PerfectoProperties.getPassword() != null) {
				print("Performing external login");
				browser.execute("doExternalLogin('" + PerfectoProperties.getUsername() + "','" + PerfectoProperties.getPassword() + "',false);");
			}
			else {
				System.out.println("WARNING: perfecto.properties file wasn't found. Can't auto-login");
			}
		}
	}
	
	private static void print(String str) {
		System.out.println("[PerfectoBrowser]: " + str);
	}
}