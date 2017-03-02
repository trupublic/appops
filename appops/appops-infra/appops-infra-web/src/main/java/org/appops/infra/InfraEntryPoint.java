package org.appops.infra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InfraEntryPoint. Use this class when you want to use a standalone app server and you want to connect to it 
 * remotely using sockets 
 */
//@WebServlet("/infraEntryPoint/*")
public class InfraEntryPoint extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SERVER_IP = "localhost";
	private static final int SERVER_PORT = 1234;

	private static Logger logger = Logger.getLogger(InfraEntryPoint.class.getCanonicalName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InfraEntryPoint() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		logger.log(Level.FINE, "Request Url - " + request.getRequestURL().toString());
		logger.log(Level.FINE, "Query String in request - " + request.getQueryString());

		String paramName = "q";
		String paramValue = request.getParameter(paramName);

		String result = getOperationResult(paramValue);

		response.getWriter().write(result);
		response.getWriter().flush();

	}

	public String getOperationResult(String query) {
		String result = null;
		try (Socket currentClientSocket = new Socket(InetAddress.getByName(SERVER_IP), InfraEntryPoint.SERVER_PORT)) {

			if (currentClientSocket.isConnected()) {
				PrintWriter writer = new PrintWriter(currentClientSocket.getOutputStream(), true);
				BufferedReader reader = new BufferedReader(new InputStreamReader(currentClientSocket.getInputStream()));
				writer.println(query);
				result = reader.readLine();
			} else {
				result = "The socket is closed!";
			}
		} catch (Exception ex) {
			logger.log(Level.WARNING, ex.getMessage(), ex);
		}

		return result;

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 * 
	 *      Needs to be tested
	 * 
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String requestData = null;
		if (request.getContentType().equalsIgnoreCase("application/x-www-form-urlencoded")) {
			StringBuffer jb = new StringBuffer();
			String line = null;
			try {

				BufferedReader reader = request.getReader();

				while ((line = reader.readLine()) != null)
					jb.append(line);

				requestData = reader.toString();

			} catch (Exception e) {
				logger.log(Level.ALL, jb.toString());
			}

			logger.log(Level.FINE, jb.toString());
		} else {
			String paramName = "q";
			requestData = request.getParameter(paramName);
		}

		String result = getOperationResult(requestData);

		response.getWriter().write(result);
		response.getWriter().flush();

	}

}
