package org.appops.infra.dispatcher;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.appops.infra.appserver.AppServerModule;
import org.appops.infra.benchmarking.BenchmarkingModule;
import org.appops.infra.core.artifacts.CountryService;
import org.appops.infra.core.artifacts.TestModule;
import org.appops.infra.dispatcher.InfraDispatcherModule;
import org.appops.infra.dispatcher.InvocableStore;
import org.appops.infra.dispatcher.InvocationDispatcher;
import org.appops.infra.dispatcher.ServiceMethod;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class InvocationDispatcherTest {

	@Test
	public void testInvokeRest() throws IOException, InstantiationException, IllegalAccessException,
			NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {

		ServiceMethod method = new ServiceMethod();
		
		method.setService("testService");
		method.setMethod("sayHello");
		method.setRef("testString");
		method.setArguments(new String[] { "Debasish" });
		

		Injector injector = Guice.createInjector(new TestModule(), new InfraDispatcherModule() , new AppServerModule());

		InvocableStore store = injector.getInstance(InvocableStore.class);

		store.buildServiceGrammar();

		InvocationDispatcher dispatcher = injector.getInstance(InvocationDispatcher.class);

		Gson gson = new Gson();

		Object serializable = dispatcher.invokeRestMethod(gson.toJson(method));

		assertNotNull(serializable);
		assertTrue(serializable instanceof String);
		assertTrue(serializable.equals("hello Debasish"));

	}
	
	@Test
	public void testInvokeBenchmarking() {
		
		Injector injector = Guice.createInjector(new TestModule(), new InfraDispatcherModule() , new AppServerModule(), new BenchmarkingModule());
		
		CountryService serv = injector.getInstance(CountryService.class);
		
		serv.getAllCountries() ;
		

	}
	
	
	
}
