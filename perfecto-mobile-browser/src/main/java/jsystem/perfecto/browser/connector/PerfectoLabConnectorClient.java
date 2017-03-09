package jsystem.perfecto.browser.connector;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class PerfectoLabConnectorClient {

	private static final int SERVER_PORT = 3456;

	public static String getHost() throws Exception {
		return requestToServer("getHost");
	}
	
	public static String getExecutionId() throws Exception {
		return requestToServer("getExecutionId");
	}
	
	private static String requestToServer(String request) throws Exception {
		
		Socket clientSocket = new Socket("localhost", SERVER_PORT);
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		
		outToServer.writeBytes(request + "\n");
		String response = inFromServer.readLine();
		clientSocket.close();
		
		return response;
	}
}
