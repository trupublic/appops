package org.appops.infra.core.artifacts;

import java.util.List;

public class TestServiceImpl implements TestServiceInterface{

	public String sayHello(String name){
		return "hello " + name ;
	}

	@Override
	public String sayHelloTwo(String name, int age, List<String> emails) {
		return null;
	}

}
