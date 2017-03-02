package org.appops.infra.dispatcher;

public class ServiceMethod {
	
	public String getService() {
		return service;
	}
	
	public void setService(String service) {
		this.service = service;
	}
	
	public String getMethod() {
		return method;
	}
	
	public void setMethod(String method) {
		this.method = method;
	}
	
	public Object[] getArguments() {
		return arguments;
	}
	
	public void setArguments(Object[] arguments) {
		this.arguments = arguments;
	}
	
	public ServiceMethod(){
		
	}

	public ServiceMethod(String s, String m) {
		service = s ;
		method = m ;
	}

	private String ref ;
	
	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	private String service ;
	
	private String method ; 
	
	private Object[] arguments; 
	
}
