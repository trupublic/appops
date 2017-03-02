package org.appops.infra.appserver.provision;

import org.appops.infra.core.AppOpsInfraException;

public interface ServiceProvider {
	
	public Object getServiceInstance(Class<?> c) throws AppOpsInfraException; 
	
}
