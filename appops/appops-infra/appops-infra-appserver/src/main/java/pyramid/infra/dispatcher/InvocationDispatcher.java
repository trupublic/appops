package pyramid.infra.dispatcher;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.common.collect.Collections2;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Injector;
import com.google.inject.Key;

import pyramid.infra.appserver.provision.ProvisionMode;
import pyramid.infra.appserver.provision.ProvisionSettingsService;
import pyramid.infra.benchmarking.Ref;
import pyramid.infra.core.PyramidInfraException;
import pyramid.infra.core.provision.DevelopmentImpl;
import pyramid.infra.core.provision.NoopImpl;
import pyramid.infra.core.provision.ProductionImpl;
import pyramid.infra.core.provision.TestImpl;

public class InvocationDispatcher {

	private Injector injector;
	private InvocableStore invocableStore;
	private ProvisionSettingsService provisionSettings ;
	
	private static Logger logger = Logger.getLogger(InvocationDispatcher.class.getName());
	
	@Inject
	private Ref ref = null;
	
	@Inject
	public InvocationDispatcher(Injector inj, InvocableStore store, ProvisionSettingsService provisionService) {
		injector = inj;
		invocableStore = store;
		provisionSettings = provisionService ;
	}

	public Object invokeRest(String clientString) throws PyramidInfraException {

		try {
			logger.log(Level.INFO, "Service invocation request received with client Request - " + clientString);
			return invokeRestMethod(clientString);
			
		} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			throw new PyramidInfraException(
					"Exception trying to invoke Service method using request string - " + clientString, e);
		}

	}
	
	Object invokeRestMethod(String client ) throws InstantiationException, IllegalAccessException,
			NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {

		ServiceMethodDeserializer smd = new ServiceMethodDeserializer() ;
		
		Gson gson = new GsonBuilder().registerTypeAdapter(ServiceMethod.class,smd).create();
		
		@SuppressWarnings("unused") // intentially unused since all the data needed is present in the ServiceMethodDeserializer above
		ServiceMethod smethod = gson.fromJson(client, ServiceMethod.class) ;
		
		String serviceName = smd.getServiceName() ;
		String method = smd.getMethodName() ;
		String refStr = smd.getRef();
		ref.setRefString(refStr) ;
		
		Invocable inv = invocableStore.getInvocable(serviceName) ;
		
		if (inv == null)
			throw new PyramidInfraException("Error invoking service at - " + serviceName + " Specified service doesn't exists "   );
		
		Class<?> service = inv.getInvocable();
		
		if (service == null){
			throw new InvocationTargetException(new PyramidInfraException("Error trying to find matching service interface for service -" + serviceName));
		}else{
			logger.log(Level.INFO , "found matching service interface --- >" + service.getCanonicalName());
		}
		
		Object serviceInstance = null ;
		
		try{
			serviceInstance = getServiceInstance(inv);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new InstantiationException("Error trying to find matching service interface for service -" + serviceName + "----" + service.getCanonicalName());
		}

		Method[] methods = serviceInstance.getClass().getMethods();

		Collection<Method> filter = Collections2.filter(Arrays.asList(methods),
				o -> o.getName().equals(method));

		if (filter.size() > 1) {
			throw new RuntimeException(
					String.format("Exception trying to invoke method %s, found more than candidate", method));
		}
		
		Method methodToInvoke = filter.iterator().next();
		logger.log(Level.INFO , "found matching method on interface --- >" + methodToInvoke.getName());		
		
		Object result = methodToInvoke.invoke(serviceInstance, smd.getArguments(methodToInvoke.getParameterTypes()));
		logger.log(Level.INFO , "invoked method --- >" + methodToInvoke.getName() + " with result " + result);		

		return result ;
	}

	private Object getServiceInstance(Invocable inv) {
		
		ProvisionMode mode = inv.getProvisionMode();
		Object service = null ;
		
		if ( mode == null)
			mode = provisionSettings.getGlobalProvisionMode("dummy");
		
		Key<?> key = null ;
		
		switch (mode ){
		case DEVELOPMENT : 
			key = Key.get(inv.getInvocable() , DevelopmentImpl.class );
			break;
		case NOOP:
			key = Key.get(inv.getInvocable() , NoopImpl.class );
			break;
		case PRODUCTION:
			key = Key.get(inv.getInvocable() , ProductionImpl.class );
			break;
		case TEST:
			key = Key.get(inv.getInvocable() , TestImpl.class );
			break;
		case DEFAULT:
			key = Key.get(inv.getInvocable());
			break;
		default:
			throw new PyramidInfraException("Unknown provision mode - service cannot be invoked") ;
		}
		
		return service = injector.getInstance(key);
	}
	

}
