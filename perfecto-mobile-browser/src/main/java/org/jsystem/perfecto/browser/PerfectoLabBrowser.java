package org.jsystem.perfecto.browser;

import org.jsystem.perfecto.PerfectoProperties;
import org.jsystem.perfecto.browser.functions.PerfectoLabBrowserFunction;

public class PerfectoLabBrowser extends PerfectoBrowser {
    
	public PerfectoLabBrowser() {
		this.perfectoUrl = "https://" + PerfectoProperties.getHost() + "/nexperience/selenium.jsp?container=eclipse";
		this.className = "PerfectoLabBrowser";
		this.windowTitle = "Perfecto Lab";
	}
	
    @Override
	protected void initBrowser() {
		
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
}