package jsystem.perfecto.test;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.jsystem.perfecto.PerfectoLabUtils;
import org.jsystem.perfecto.WindTunnelUtils;
import org.junit.Before;
import org.junit.Test;

import io.appium.java_client.android.AndroidDriver;
import jsystem.framework.TestProperties;

/**
 * @author Rony Byalsky
 */
public class JSystemPerfectoTestDemo extends JSystemPerfectoTest {

	protected AndroidDriver driver;
	private String urlOnMobileBrowser = "www.google.com";
	
	@Before
	public void before() throws Exception {
		
		super.before(); // if overriding the before() method, don't forget to execute this!
		
		driver = new AndroidDriver(new URL("https://" + perfectoHost + "/nexperience/perfectomobile/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
	
	@Test
	@TestProperties(name="Perfecto Demo Test")
	public void perfectoDemoTest() throws Exception {

		try {
			
			driver.get(urlOnMobileBrowser);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Retrieve the URL of the Single Test Report, can be saved to your execution summary and used to download the report at a later point
				String reportURL = (String)(driver.getCapabilities().getCapability(WindTunnelUtils.SINGLE_TEST_REPORT_URL_CAPABILITY));
				report.report("Report URL: " + reportURL);
				driver.close();

				// In case you want to download the report or the report attachments, do it here.
				PerfectoLabUtils.downloadReport(driver, "pdf", "C:\\test\\report");
				PerfectoLabUtils.downloadAttachment(driver, "video", "C:\\test\\report\\video", "flv");
				PerfectoLabUtils.downloadAttachment(driver, "image", "C:\\test\\report\\images", "jpg");

			} catch (Exception e) {
				e.printStackTrace();
			}

			driver.quit();
		}
	}

	/* GETTERS & SETTERS */
	public String getUrlOnMobileBrowser() {
		return urlOnMobileBrowser;
	}

	public void setUrlOnMobileBrowser(String urlOnMobileBrowser) {
		this.urlOnMobileBrowser = urlOnMobileBrowser;
	}
}
