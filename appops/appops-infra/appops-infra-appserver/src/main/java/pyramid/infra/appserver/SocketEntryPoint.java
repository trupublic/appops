package pyramid.infra.appserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.inject.Inject;

import com.google.gson.Gson;

import pyramid.infra.core.PyramidInfraException;
import pyramid.infra.dispatcher.InvocationDispatcher;


public class SocketEntryPoint {

	private static ServerSocket serverSocket;
	private static final int INCOMING_PORT = 1234;
	private static boolean isRunning;
	private InvocationDispatcher invocationDispatcher;

	@Inject
	public SocketEntryPoint(InvocationDispatcher dispatcher ) {
		isRunning = false;
		invocationDispatcher = dispatcher ;
	}

	public void start(String[] args) {
		if (!SocketEntryPoint.isRunning) {
			try {
				SocketEntryPoint.serverSocket = new ServerSocket(SocketEntryPoint.INCOMING_PORT, 10,
						InetAddress.getByName("localhost"));
				SocketEntryPoint.isRunning = true;
				System.out.println("My app server is running on port number ----> " + INCOMING_PORT) ;
				
				while (SocketEntryPoint.isRunning) {
					try (Socket connectedSocket = serverSocket.accept()) {
						long startTime = System.nanoTime();
						
						System.out.println("AS : Started - " + (System.nanoTime() - startTime) / 1000000);
						System.out.println("My Server socket accepted client request ");

						PrintWriter toClient = new PrintWriter(connectedSocket.getOutputStream());
						BufferedReader reader = new BufferedReader(
								new InputStreamReader(connectedSocket.getInputStream()));

						String str = reader.readLine() ;
						
						Object result = invocationDispatcher.invokeRest(str);

						Gson gson = new Gson() ;
						
						String jsonResult  = gson.toJson(result);
						
						toClient.write(jsonResult);
						toClient.flush();

						System.out.println("AS : Response Sent - " + (System.nanoTime() - startTime) / 1000000);
					} catch (PyramidInfraException e) {
						e.printStackTrace();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();

			}
		}

	}

	public void stop(String[] args) {
		System.out.println("Stop service");

		if (SocketEntryPoint.isRunning) {
			try {
				SocketEntryPoint.serverSocket.close();
				SocketEntryPoint.isRunning = false;
			} catch (IOException e) {

			}
		}
	}

}
