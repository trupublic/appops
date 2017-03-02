package org.appops.infra.appserver;

import java.io.IOException;

import org.appops.infra.core.AppOpsApplication;
import org.appops.infra.core.AppOpsInfraException;
import org.appops.infra.dispatcher.InvocableStore;

import com.google.inject.Inject;

public class AppOpsAppServer implements AppOpsApplication {
	
	private InvocableStore invocableStore = null ;
	private AppServerSocketEntryPoint entryPoint = null ;
	
	@Inject
	public void setInvocableStore(InvocableStore store){
		invocableStore = store ;
	}
	
	@Inject
	public void setSocketEntryPoint(AppServerSocketEntryPoint entry){
		entryPoint = entry ; 
	}
	
	@Override
	public void runApplication(String[] args) throws AppOpsInfraException{
		
		try {
			invocableStore.buildServiceGrammar();
		} catch (IOException e) {
			throw new AppOpsInfraException("Unable to build application grammar" , e);
		}
		
		entryPoint.start(args);
	}

	@Override
	public void stopApplication(String[] args) {

	}

}
