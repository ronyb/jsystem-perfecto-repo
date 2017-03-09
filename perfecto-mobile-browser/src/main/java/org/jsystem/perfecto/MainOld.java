package org.jsystem.perfecto;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jsystem.perfecto.browser.PerfectoDashboardBrowser;
import org.jsystem.perfecto.browser.PerfectoLabBrowser;
import org.jsystem.perfecto.connector.PerfectoDashboardConnectorServer;
import org.jsystem.perfecto.connector.PerfectoLabConnectorServer;

public class MainOld {

	private static ExecutorService executorService;
	
	public static void main(String[] args) throws Exception {

		executorService = Executors.newCachedThreadPool();
		
		PerfectoProperties.loadProperties();
		
		if (args.length > 0) {
			
			if (args[0].toLowerCase().equals("lab")) {
				launchPerfectoLab();
				executorService.shutdown();
			}
			else if (args[0].toLowerCase().equals("dashboard")) {
				launchPerfectoDashboard();
				executorService.shutdown();
			}
			else {
				System.err.println("Command line argument can only be: 'lab' or 'dashboard'");
				executorService.shutdown();
				System.exit(1);
			}
		}
		else {
			launchPerfectoLab();
			Thread.sleep(2000);
			launchPerfectoDashboard();
			executorService.shutdown();
		}
	}
	
	private static void launchPerfectoLab() throws InterruptedException {
		
		try {
			PerfectoLabConnectorServer.startServer(PerfectoProperties.getHost());
		}
		catch (IOException e) {
			print("Perfecto Lab is already running. (TCP port " + PerfectoLabConnectorServer.getServerPort() + " is in use).");
			return;
		}
		
		PerfectoLabBrowser perfectoLab = new PerfectoLabBrowser();
		executorService.execute(perfectoLab);
		
		Thread.sleep(3000);
		perfectoLab.doExternalLogin();
	}
	
	private static void launchPerfectoDashboard() throws InterruptedException {
		
		try {
			PerfectoDashboardConnectorServer.startServer(PerfectoProperties.getHost());
		}
		catch (IOException e) {
			print("Perfecto Dashboard is already running. (TCP port " + PerfectoDashboardConnectorServer.getServerPort() + " is in use).");
			return;
		}
		
		PerfectoDashboardBrowser perfectoDashboard = new PerfectoDashboardBrowser();
		executorService.execute(perfectoDashboard);
		
		Thread.sleep(3000);
		perfectoDashboard.doExternalLogin();
	}
	
	private static void print(String str) {
		System.out.println("[Main]: " + str);
	}
}