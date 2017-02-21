package org.appops.infra.core.artifacts;


import org.appops.infra.core.AppOpsApplication;
import org.appops.infra.core.AppOpsInfraException;


public class TestApplication implements AppOpsApplication {

	
	@Override
	public void runApplication(String[] args) throws AppOpsInfraException {
		System.out.println("App Started");
	}

	@Override
	public void stopApplication(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
