package pyramid.infra.appserver.provision;

import pyramid.infra.core.PyramidInfraException;

public interface ServiceProvider {
	
	public Object getServiceInstance(Class<?> c) throws PyramidInfraException; 
	
}
