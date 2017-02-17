package org.appops.infra.core;

@SuppressWarnings("serial")
public class PyramidInfraException extends RuntimeException {
	
	public PyramidInfraException(String msg){
		super(msg) ;
	}

	public PyramidInfraException(String msg , Exception e) {
		super(msg , e);
	}

}
