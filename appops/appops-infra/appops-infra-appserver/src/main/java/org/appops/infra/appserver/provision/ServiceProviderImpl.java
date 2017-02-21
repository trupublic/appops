package org.appops.infra.appserver.provision;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.appops.infra.core.AppOpsInfraException;

import com.google.common.util.concurrent.Service;
import com.google.inject.Inject;
import com.google.inject.Injector;

public class ServiceProviderImpl implements ServiceProvider {

	private Injector injector = null ;
	
	@Inject
	private Logger logger = null ;
	
	private ProvisionSettingsService provisionService = null ;
	
	@Inject
	public ServiceProviderImpl(Injector inj, ProvisionSettingsService provision) {
		injector = inj ;
		provisionService = provision ;
	}
	
	@Override
	public Object getServiceInstance(Class<?> cls) throws AppOpsInfraException {
		Object service = null ;
		
		try{
			Annotation[] annts = cls.getAnnotations() ;
			Iterator<Annotation> list = Arrays.asList(annts).stream().filter( value -> value.annotationType().equals(Service.class)).iterator() ;
			if (list.hasNext())
				throw new AppOpsInfraException("To use an interface as Service it must be annotated with Service from org.appops.infra.core.annotations");
			service = injector.getInstance(cls) ;
		}catch(Exception ex){
			logger.log(Level.SEVERE, "Error trying to located service instance for inteface " + cls.getCanonicalName(), ex);
			throw new AppOpsInfraException("Couldn't locate Service class for interface - " + cls.getCanonicalName() ) ;
		}
		return service ;
	}

}
