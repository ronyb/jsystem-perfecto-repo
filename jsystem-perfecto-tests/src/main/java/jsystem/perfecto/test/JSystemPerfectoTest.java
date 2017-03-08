package jsystem.perfecto.test;

import java.io.FileInputStream;
import java.util.Properties;

import org.jsystem.perfecto.AutomationName;
import org.jsystem.perfecto.PerfectoLabUtils;
import org.junit.Before;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import jsystem.framework.ParameterProperties;
import junit.framework.SystemTestCase4;

/**
 * @author Rony Byalsky
 */
public abstract class JSystemPerfectoTest extends SystemTestCase4 {

	// Perfecto Cloud properties - loaded from perfecto/perfecto.properties file
	protected String perfectoHost;
	protected String perfectoUsername;
	protected String perfectoPassword;

	// DesiredCapabilities - configurable as JSystem test parameters
	protected String browser = "mobileOS";
	protected String version = "";
	protected Platform platform = Platform.ANY;
	protected AutomationName automationName = AutomationName.APPIUM;
	
	protected String deviceId = "";
	protected String app = "";
	protected String appPackage = ""; // for Android
	protected String appActivity = ""; // for Android
	protected String bundleId = ""; // for iOS
	protected String scriptName = "";
	
	protected DesiredCapabilities capabilities;
	
	@Before
	public void before() throws Exception {
		loadPerfectoProperties();
		initDesiredCapabilities();
	}

	private void loadPerfectoProperties() throws Exception {

		Properties prop = new Properties();
		prop.load(new FileInputStream("perfecto/perfecto.properties"));

		perfectoHost = prop.getProperty("perfectoHost");
		perfectoUsername = prop.getProperty("perfectoUsername");
		perfectoPassword = prop.getProperty("perfectoPassword");
	}

	protected void initDesiredCapabilities() throws Exception {
		
		capabilities = new DesiredCapabilities(browser, version, platform);
        
		capabilities.setCapability("user", perfectoUsername);
        capabilities.setCapability("password", perfectoPassword);
        capabilities.setCapability("automationName", automationName);
        
        if (!deviceId.equals("")) capabilities.setCapability("deviceId", deviceId);
        if (!app.equals(""))  capabilities.setCapability("app", app);
        if (!appPackage.equals("")) capabilities.setCapability("appPackage", appPackage);
        if (!appActivity.equals("")) capabilities.setCapability("appActivity", appActivity);
        if (!bundleId.equals("")) capabilities.setCapability("bundleId", bundleId);
        if (!scriptName.equals("")) capabilities.setCapability("scriptName", scriptName);
        
        PerfectoLabUtils.setExecutionIdCapability(capabilities, perfectoHost);
	}
	
	/* GETTERS & SETTERS */
	public String getDeviceId() {
		return deviceId;
	}

	@ParameterProperties(section = "Perfecto", description = "add description here")
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getBrowser() {
		return browser;
	}

	@ParameterProperties(section = "Perfecto", description = "add description here")
	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getVersion() {
		return version;
	}

	@ParameterProperties(section = "Perfecto", description = "add description here")
	public void setVersion(String version) {
		this.version = version;
	}

	public Platform getPlatform() {
		return platform;
	}

	@ParameterProperties(section = "Perfecto", description = "add description here")
	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

	public AutomationName getAutomationName() {
		return automationName;
	}

	@ParameterProperties(section = "Perfecto", description = "add description here")
	public void setAutomationName(AutomationName automationName) {
		this.automationName = automationName;
	}

	public String getApp() {
		return app;
	}

	@ParameterProperties(section = "Perfecto", description = "add description here")
	public void setApp(String app) {
		this.app = app;
	}

	public String getAppPackage() {
		return appPackage;
	}

	@ParameterProperties(section = "Perfecto", description = "add description here")
	public void setAppPackage(String appPackage) {
		this.appPackage = appPackage;
	}

	public String getAppActivity() {
		return appActivity;
	}

	@ParameterProperties(section = "Perfecto", description = "add description here")
	public void setAppActivity(String appActivity) {
		this.appActivity = appActivity;
	}

	public String getBundleId() {
		return bundleId;
	}

	@ParameterProperties(section = "Perfecto", description = "add description here")
	public void setBundleId(String bundleId) {
		this.bundleId = bundleId;
	}

	public String getScriptName() {
		return scriptName;
	}

	@ParameterProperties(section = "Perfecto", description = "add description here")
	public void setScriptName(String scriptName) {
		this.scriptName = scriptName;
	}
}
