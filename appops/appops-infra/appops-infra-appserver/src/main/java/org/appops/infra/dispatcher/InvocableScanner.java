package org.appops.infra.dispatcher;

import java.io.IOException;
import java.util.Set;

import org.appops.infra.core.annotations.Service;

import com.pa.tsgen.scanner.AnnotatedClassScanner;

public class InvocableScanner {
	
	public Set<Class<?>> getServiceInterfaceSet() throws IOException{
		
		AnnotatedClassScanner scanner = new AnnotatedClassScanner() ;
		
		return scanner.getInterfacesAnnotatedWith(Service.class);
		
	}
	
	
	
	

}
