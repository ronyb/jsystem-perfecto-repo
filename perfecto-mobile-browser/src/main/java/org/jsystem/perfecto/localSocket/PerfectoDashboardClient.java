package org.jsystem.perfecto.localSocket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class PerfectoDashboardClient {

	private static final int SERVER_PORT = 3457;

	public static String getCloud() throws Exception {
		return requestToServer("getCloud");
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
