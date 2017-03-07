package org.jsystem.perfecto.browser.functions;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;

public class DashboardBrowserFunction extends BrowserFunction {

	private static final String DASHBOARD_URL = "dashboard.jsp?autoWatch=true&container=eclipse";
	private static final String className = "DashboardBrowserFunction";
	
	private Browser browser = null;
	private String functionName = null;

	public DashboardBrowserFunction(Browser browser, String name) {
		super(browser, name);
		this.browser = browser;
		this.functionName = name;
	}
	
	@Override
	public Object function (Object[] args) {
		
		print(functionName);
		
		if (functionName == "getContainerJspNameCallback") {
			return DASHBOARD_URL;
		}
		
		else if (functionName == "isInEclipsePluginCallback") {
			return true;
		}
		
		else if (functionName == "openLinkInBrowserCallback") {
			openLinkInBrowserHandler(args);
		}
		
		return "";
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
	
	private void print(String str) {
		System.out.println("[" + className + "]: " + str);
	}
}
