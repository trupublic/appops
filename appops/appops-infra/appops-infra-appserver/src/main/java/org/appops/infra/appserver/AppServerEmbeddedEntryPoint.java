package org.appops.infra.appserver;

import org.appops.infra.core.AppOpsInfraException;
import org.appops.infra.dispatcher.InvocationDispatcher;

import com.google.inject.Inject;

public class AppServerEmbeddedEntryPoint {

	InvocationDispatcher invocationDispatcher = null ;
	
	@Inject
	public AppServerEmbeddedEntryPoint(InvocationDispatcher dispatcher){
		invocationDispatcher = dispatcher ;
	}
	
	public Object dispatchRestRequest(String rest) throws AppOpsInfraException{
		
		return invocationDispatcher.invokeRest(rest);

	}
}
