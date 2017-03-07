package org.jsystem.perfecto.localSocket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class PerfectoLabClient {

	private static final int SERVER_PORT = 3456;

	public static String getCloud() throws Exception {
		return requestToServer("getCloud");
	}
	
	public static String getExecutionId() throws Exception {
		return requestToServer("getExecutionId");
	}
	
	public static String getCloudAndExecutionId() throws Exception {
		return requestToServer("getCloudAndExecutionId");
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
