package org.jsystem.perfecto.browser;

import org.jsystem.perfecto.PerfectoProperties;
import org.jsystem.perfecto.browser.functions.LabBrowserFunction;

public class PerfectoLabBrowser extends PerfectoBrowser {
    
	public PerfectoLabBrowser() {
		this.perfectoUrl = "https://" + PerfectoProperties.getCloud() + "/nexperience/selenium.jsp?container=eclipse";
		this.className = "PerfectoLabBrowser";
		this.windowTitle = "Perfecto Lab";
	}
	
    @Override
	protected void initBrowser() {
		
        new LabBrowserFunction(browser, "recordLineCallback");
        new LabBrowserFunction(browser, "insertLineCallback");
        new LabBrowserFunction(browser, "readyToRunPluginCallback");
        new LabBrowserFunction(browser, "logOutPluginCallback");
        new LabBrowserFunction(browser, "isInEclipsePluginCallback");
        new LabBrowserFunction(browser, "getContainerJspNameCallback");
        new LabBrowserFunction(browser, "checkVersionCompatibilityCallback");
        new LabBrowserFunction(browser, "openLinkInBrowserCallback");
		new LabBrowserFunction(browser, "startRemoteAccessCallback");
        new LabBrowserFunction(browser, "getApplicationContainerConfigurationCallback");
	}
}