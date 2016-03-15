package pyramid.infra.dispatcher;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.inject.Guice;
import com.google.inject.Injector;

import pyramid.infra.appserver.AppServerModule;
import pyramid.infra.benchmarking.BenchmarkingModule;
import pyramid.infra.core.artifacts.CountryService;
import pyramid.infra.core.artifacts.TestModule;

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
