package pyramid.infra.appserver;

import java.io.IOException;

import com.google.inject.Inject;

import pyramid.infra.core.PyramidApplication;
import pyramid.infra.core.PyramidInfraException;
import pyramid.infra.dispatcher.InvocableStore;

public class PyramidServerApplication implements PyramidApplication {
	
	private InvocableStore invocableStore = null ;
	private SocketEntryPoint entryPoint = null ;
	
	@Inject
	public void setInvocableStore(InvocableStore store){
		invocableStore = store ;
	}
	
	@Inject
	public void setSocketEntryPoint(SocketEntryPoint entry){
		entryPoint = entry ; 
	}
	
	@Override
	public void runApplication(String[] args) throws PyramidInfraException{
		
		try {
			invocableStore.buildServiceGrammar();
		} catch (IOException e) {
			throw new PyramidInfraException("Unable to build application grammar" , e);
		}
		
		entryPoint.start(args);
	}

	@Override
	public void stopApplication(String[] args) {

	}

}
