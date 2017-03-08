package org.jsystem.perfecto.browser;

import org.jsystem.perfecto.PerfectoProperties;
import org.jsystem.perfecto.browser.functions.PerfectoDashboardBrowserFunction;

public class PerfectoDashboardBrowser extends PerfectoBrowser {

	public PerfectoDashboardBrowser() {
		this.perfectoUrl = "https://" + PerfectoProperties.getHost() + "/nexperience/dashboard.jsp";
		this.className = "PerfectoDashboardBrowser";
		this.windowTitle = "Perfecto Dashboard";
	}
	
	@Override
	protected void initBrowser() {
		new PerfectoDashboardBrowserFunction(browser, "isInEclipsePluginCallback");
		new PerfectoDashboardBrowserFunction(browser, "getContainerJspNameCallback");
		new PerfectoDashboardBrowserFunction(browser, "openLinkInBrowserCallback");
	}
}