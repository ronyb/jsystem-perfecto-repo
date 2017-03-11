package jsystem.perfecto.browser.connector;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PerfectoLabConnectorServer implements Runnable {

	private static final int SERVER_PORT = 3456;

	private static String host;
	private static String executionId;

	private static ExecutorService executorService;
	
	private static volatile boolean keepRunning = true;

	private static ServerSocket serverSocket;
	private static DataOutputStream outToClient;
	
	private PerfectoLabConnectorServer() {}

	public static void startServer(String perfectoHost) throws IOException {
		
		if (executorService == null) {
			
			PerfectoLabConnectorServer.host = perfectoHost;
			
			serverSocket = new ServerSocket(SERVER_PORT);
			print("Server is listening on TCP port " + SERVER_PORT);
			
			executorService = Executors.newSingleThreadExecutor();
			executorService.submit(new PerfectoLabConnectorServer());
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

		Thread.currentThread().setName("PerfectoLabConnectorServer");
		
		try {
			
			while (keepRunning) {

				Socket socket = serverSocket.accept();
				
				BufferedReader inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				outToClient = new DataOutputStream(socket.getOutputStream());
				String requestStr = inFromClient.readLine();
				
				print("Received request: \"" + requestStr + "\"");

				if (requestStr.equals("getHost")) {
					writeToClient(host);
				}
				else if (requestStr.equals("getExecutionId")) {
					writeToClient(executionId);
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
		System.out.println("[PerfectoLabConnectorServer]: " + str);
	}
	
	public static int getServerPort() {
		return SERVER_PORT;
	}

	public static String getHost() {
		return host;
	}

	public static void setHost(String host) {
		PerfectoLabConnectorServer.host = host;
	}

	public static String getExecutionId() {
		return executionId;
	}

	public static void setExecutionId(String executionId) {
		PerfectoLabConnectorServer.executionId = executionId;
	}
}
