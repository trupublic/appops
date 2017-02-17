package org.appops.infra.core.artifacts;

import java.util.List;

import org.appops.infra.core.annotations.Operation;
import org.appops.infra.core.annotations.Param;
import org.appops.infra.core.annotations.Service;

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
