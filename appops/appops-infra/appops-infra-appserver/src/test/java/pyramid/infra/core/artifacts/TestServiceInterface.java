package pyramid.infra.core.artifacts;

import java.util.List;

import pyramid.infra.core.annotations.Operation;
import pyramid.infra.core.annotations.Param;
import pyramid.infra.core.annotations.Service;

// path is test/testService
@Service(name="testService")
public interface TestServiceInterface {

	//test/testService/sayHello?name=DFASDF
	@Operation (name = "sayHello")
	public String sayHello( @Param("name") String name);
	
	//test/testService/sayHello?name=DFASDF
	@Operation (name = "sayHelloTwo")
	public String sayHelloTwo( @Param("name") String name , @Param int age , @Param List<String> emails);
}
