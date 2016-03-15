package pyramid.infra.core.artifacts;


import pyramid.infra.core.PyramidApplication;
import pyramid.infra.core.PyramidInfraException;


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
