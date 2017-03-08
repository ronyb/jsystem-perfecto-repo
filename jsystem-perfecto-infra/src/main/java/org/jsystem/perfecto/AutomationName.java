package org.jsystem.perfecto;

/**
 * This enum should be used to specify the "automationName" capability
 * @author Rony Byalsky
 */
public enum AutomationName {

	APPIUM("Appium"),
	PERFECTO_MOBILE("PerfectoMobile");
	
	public String str;
	
	AutomationName(String str) {
		this.str = str;
	}
}
