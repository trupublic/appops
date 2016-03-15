package pyramid.infra.appserver.provision;

import pyramid.infra.core.annotations.Service;

@Service
public interface ProvisionSettingsService {
	
	public void setGlobalProvisionMode(ProvisionMode mode , String dummy) ;
	
	public ProvisionMode getGlobalProvisionMode(String dummy) ;
	
	public void setPathProvision(String path, ProvisionMode pro) ;
	
	public ProvisionMode getPathProvision(String path) ;
	
}
