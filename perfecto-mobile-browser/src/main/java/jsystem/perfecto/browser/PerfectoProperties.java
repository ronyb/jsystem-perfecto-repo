package jsystem.perfecto.browser;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PerfectoProperties {

	private static String host;
	private static String username;
	private static String password;

	public static void loadProperties() throws Exception {
		Properties prop = new Properties();

		if (new File("perfecto/perfecto.properties").exists()) {
			prop.load(new FileInputStream("perfecto/perfecto.properties"));
		}
		else if (new File("perfecto.properties").exists()) {
			prop.load(new FileInputStream("perfecto.properties"));
		}
		else {
			System.out.println("WARNING: Couldn't find perfecto.properties file");
			return;
		}

		host = prop.getProperty("perfectoHost");
		username = prop.getProperty("perfectoUsername");
		password = prop.getProperty("perfectoPassword");
	}

	public static String getHost() {
		return host;
	}

	public static String getUsername() {
		return username;
	}

	public static String getPassword() {
		return password;
	}
}