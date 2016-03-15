package pyramid.infra.appserver;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;

import pyramid.infra.core.PyramidAppStarter;
import pyramid.infra.core.artifacts.Country;
import pyramid.infra.dispatcher.ServiceMethod;

import static org.junit.Assert.*;

import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.Socket;

public class PyramidServerApplicationTest {

	@Test
	public void testServiceMethodInvocation() throws IOException, InterruptedException {

		ServiceMethod method = new ServiceMethod();
		
		method.setService("countries");
		method.setMethod("getAllCountries");
		method.setArguments(null);
		method.setRef("countries/getAllCountries");
		
		Gson gson = new Gson();
		String query = gson.toJson(method);

		String result = null;

		Thread thread = new Thread( new Runnable(){
			
			@Override
			public void run() {
				try {
					PyramidAppStarter.start(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}});
		
		thread.start();
		Thread.sleep(12000); 
		
		try (Socket currentClientSocket = new Socket(InetAddress.getByName("localhost"), 1234)) {

			if (currentClientSocket.isConnected()) {
				PrintWriter writer = new PrintWriter(currentClientSocket.getOutputStream(), true);
				BufferedReader reader = new BufferedReader(new InputStreamReader(currentClientSocket.getInputStream()));
				writer.println(query);
				result = reader.readLine();
			}
		}

		Type listType = new TypeToken<List<Country>>() {}.getType();

		List<Country> obj = gson.fromJson(result, listType);

		Country testCountry = new Country("MH", "West", 50.0, 80.0);
		assertTrue(obj instanceof List);
		assertTrue(obj.get(0).equals(testCountry));

	}
}
