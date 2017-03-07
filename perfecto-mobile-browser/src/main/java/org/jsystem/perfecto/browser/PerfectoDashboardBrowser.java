package org.jsystem.perfecto.browser;

import org.jsystem.perfecto.PerfectoProperties;
import org.jsystem.perfecto.browser.functions.DashboardBrowserFunction;

public class PerfectoDashboardBrowser extends PerfectoBrowser {

	public PerfectoDashboardBrowser() {
		this.perfectoUrl = "https://" + PerfectoProperties.getCloud() + "/nexperience/dashboard.jsp";
		this.className = "PerfectoDashboardBrowser";
		this.windowTitle = "Perfecto Dashboard";
	}
	
	@Override
	protected void initBrowser() {
		new DashboardBrowserFunction(browser, "isInEclipsePluginCallback");
		new DashboardBrowserFunction(browser, "getContainerJspNameCallback");
		new DashboardBrowserFunction(browser, "openLinkInBrowserCallback");
	}
}