package org.jsystem.perfecto.browser;

import java.io.File;

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
import org.jsystem.perfecto.PerfectoProperties;
import org.jsystem.perfecto.localSocket.PerfectoDashboardServer;
import org.jsystem.perfecto.localSocket.PerfectoLabServer;

public abstract class PerfectoBrowser implements Runnable {

	protected String className;
	protected String perfectoUrl;
	protected String windowTitle;

	protected Display display;
	protected Shell shell;
	protected Browser browser;

	@Override
	public void run() {

		display = new Display();
		shell = new Shell(display);

		shell.setText(windowTitle);

		if (new File("perfecto_icon.png").exists()) {
			shell.setImage(new Image(display, "perfecto_icon.png"));
		}
		else if (new File("perfecto/perfecto_icon.png").exists()) {
			shell.setImage(new Image(display, "perfecto/perfecto_icon.png"));
		}

		shell.setLayout(new GridLayout());

		shell.addListener(SWT.Close, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				if (className.equals("PerfectoLabBrowser")) {
					PerfectoLabServer.stopServer();
				}
				else if (className.equals("PerfectoDashboardBrowser")) {
					PerfectoDashboardServer.stopServer();
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

		shell.setSize(800, 600);
		shell.open();

		initBrowser();

		print("Opening URL: " + perfectoUrl);
		browser.setUrl(perfectoUrl);

		// Set up the event loop.
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

		display.dispose();
	}

	public synchronized void doExternalLogin() {

		if (!display.isDisposed()) {
			
			display.asyncExec(new Runnable() {

				@Override
				public void run() {

					String currentUrl = browser.getUrl();
					print("Current URL: " + currentUrl);

					if (currentUrl.contains("np-cas/login") || currentUrl.contains("login.jsp")) {

						if (PerfectoProperties.getUsername() != null && PerfectoProperties.getPassword() != null) {
							print("Performing external login");
							browser.execute("doExternalLogin('" + PerfectoProperties.getUsername() + "','" + PerfectoProperties.getPassword() + "',false);");
						}
						else {
							System.out.println("WARNING: perfecto.properties file wasn't found. Can't auto-login");
						}
					}
				}
			});
		}
	}

	protected abstract void initBrowser();

	protected void print(String str) {
		System.out.println("[" + className + "]: " + str);
	}
}
