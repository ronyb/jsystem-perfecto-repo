package jsystem.perfecto.browser;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;

public class PerfectoDashboardBrowserFunction extends BrowserFunction {

	private static final String DASHBOARD_URL = "dashboard.jsp?autoWatch=true&container=eclipse";
	
	private Browser browser = null;
	private String functionName = null;

	public PerfectoDashboardBrowserFunction(Browser browser, String name) {
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
			browser.setUrl((String) args[0]);
		}
		
		return "";
	}
	
	private void print(String str) {
		System.out.println("[PerfectoDashboardBrowserFunction]: " + str);
	}
}
