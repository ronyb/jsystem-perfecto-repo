package org.jsystem.perfecto.localSocket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PerfectoDashboardServer implements Runnable {

	private static final int SERVER_PORT = 3457;
	private static final PerfectoDashboardServer INSTANCE = new PerfectoDashboardServer();

	private static String perfectoCloud;

	private static ExecutorService executorService;
	
	private static volatile boolean keepRunning;

	private static ServerSocket serverSocket;
	private static DataOutputStream outToClient;
	
	private PerfectoDashboardServer() {}

	public static void startServer(String perfectoCloud) throws IOException {
		
		if (executorService == null) {
			
			PerfectoDashboardServer.perfectoCloud = perfectoCloud;
			keepRunning = true;
			
			serverSocket = new ServerSocket(SERVER_PORT);
			print("server is listening on TCP port " + SERVER_PORT);
			
			executorService = Executors.newSingleThreadExecutor();
			executorService.submit(INSTANCE);
			executorService.shutdown();
		}
	}
	
	public static void stopServer() {
		
		keepRunning = false;
		
		if (serverSocket != null) {
			print("killing server");
			try {
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			serverSocket = null;
		}

		if (executorService != null) {
			executorService.shutdownNow();
			executorService = null;
		}
	}
	
	@Override
	public void run() {

		Thread.currentThread().setName("Perfecto_Dashboard_Server");
		
		try {
			
			while (keepRunning) {

				Socket socket = serverSocket.accept();
				
				BufferedReader inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				outToClient = new DataOutputStream(socket.getOutputStream());
				String requestStr = inFromClient.readLine();
				
				print("Received request: \"" + requestStr + "\"");

				if (requestStr.equals("getCloud")) {
					writeToClient("perfectoCloud=" + perfectoCloud);
				}
				else {
					writeToClient(requestStr + " - unsupported request type");
				}
			}
			
			if (serverSocket != null) {
				serverSocket.close();
			}
			
			print("server stopped");
		}
		catch (Exception e) {
			if (!keepRunning) {
				print("server stopped");
			}
			else {
				e.printStackTrace();
			}
		}
	}

	private static void writeToClient(String response) throws Exception {
		print("Responding to client: \"" + response + "\"");
		outToClient.writeBytes(response + "\n");
	}
	
	private static void print(String str) {
		System.out.println("[PerfectoDashboardServer]: " + str);
	}
	
	public static String getPerfectoCloud() {
		return perfectoCloud;
	}

	public static void setPerfectoCloud(String perfectoCloud) {
		PerfectoDashboardServer.perfectoCloud = perfectoCloud;
	}

	public static int getServerPort() {
		return SERVER_PORT;
	}
}
