package org.appops.infra.dispatcher;

import java.io.IOException;
import java.net.URL;
import java.util.Set;

import org.appops.infra.core.annotations.Service;
import org.appops.scanner.AnnotatedClassScanner;

public class InvocableScanner {
	
	public Set<Class<?>> getServiceInterfaceSet() throws IOException{
		AnnotatedClassScanner scanner = new AnnotatedClassScanner() ;
		return scanner.getInterfacesAnnotatedWith(Service.class);
		
	}

	public Set<Class<?>> getServiceInterfaceSet(URL[] urlsToScan) throws IOException {
		AnnotatedClassScanner scanner = new AnnotatedClassScanner() ;
		scanner.setUrlsToScan(urlsToScan);
		return scanner.getInterfacesAnnotatedWith(Service.class);
	}
	
	
	
	

}
