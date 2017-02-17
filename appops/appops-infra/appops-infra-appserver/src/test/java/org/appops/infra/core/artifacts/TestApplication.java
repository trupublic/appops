package org.appops.infra.core.artifacts;


import org.appops.infra.core.PyramidApplication;
import org.appops.infra.core.PyramidInfraException;


public class TestApplication implements PyramidApplication {

	
	@Override
	public void runApplication(String[] args) throws PyramidInfraException {
		System.out.println("App Started");
	}

	@Override
	public void stopApplication(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
