package pyramid.infra.dispatcher;

import java.io.IOException;
import java.util.Set;

import com.pa.tsgen.scanner.AnnotatedClassScanner;

import pyramid.infra.core.annotations.Service;

public class InvocableScanner {
	
	public Set<Class<?>> getServiceInterfaceSet() throws IOException{
		
		AnnotatedClassScanner scanner = new AnnotatedClassScanner() ;
		
		return scanner.getInterfacesAnnotatedWith(Service.class);
		
	}
	
	
	
	

}
