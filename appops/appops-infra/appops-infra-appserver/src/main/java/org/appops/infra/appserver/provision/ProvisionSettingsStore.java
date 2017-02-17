package org.appops.infra.appserver.provision;

import java.util.HashMap;

import org.appops.infra.core.PyramidInfraException;
import org.appops.infra.dispatcher.InvocableStore;

import com.google.inject.Inject;

public class ProvisionSettingsStore {
	
	private ProvisionMode currentGlobal = ProvisionMode.DEFAULT; // Always starts in test mode 
	private InvocableStore invStore = null ;
	
	@Inject
	public ProvisionSettingsStore(InvocableStore store){
		invStore = store ;
	}
	
	public ProvisionMode getCurrentGlobal() {
		return currentGlobal;
	}

	public void setCurrentGlobal(ProvisionMode cg) throws PyramidInfraException {
		if (cg == ProvisionMode.NOOP)
			throw new PyramidInfraException("Cannot set global provisioning to NOOP. Choose among DEVELPOMENT, PRODUCTION or TEST instead");
		this.currentGlobal = cg;
	}

	private HashMap<String, ProvisionMode> pathProvisionMap = new HashMap<String, ProvisionMode>() ;
	
	public void setPathProvisionMode(String path , ProvisionMode mode){
		pathProvisionMap.put(path,mode);
	}
	
	public ProvisionMode getPathProvisionMode(String path){
		return pathProvisionMap.get(path) ;
	}

}
