package tsgen;

import org.appops.infra.core.annotations.Service;

@Service
public interface MyService {
	
	public String sayHello(String hi, String bye) ;

	public void sayBye();
}
