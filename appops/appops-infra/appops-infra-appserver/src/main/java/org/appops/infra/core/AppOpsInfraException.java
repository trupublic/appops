package org.appops.infra.core;

@SuppressWarnings("serial")
public class AppOpsInfraException extends RuntimeException {
	
	public AppOpsInfraException(String msg){
		super(msg) ;
	}

	public AppOpsInfraException(String msg , Exception e) {
		super(msg , e);
	}

}
