package org.appops.infra.appserver.provision;

import org.appops.infra.core.PyramidInfraException;

public interface ServiceProvider {
	
	public Object getServiceInstance(Class<?> c) throws PyramidInfraException; 
	
}
