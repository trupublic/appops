package pyramid.infra.appserver;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

import pyramid.infra.appserver.provision.ProvisionSettingsService;
import pyramid.infra.appserver.provision.ProvisionSettingsServiceImpl;
import pyramid.infra.appserver.provision.ProvisionSettingsStore;
import pyramid.infra.core.PyramidApplication;
import pyramid.infra.core.annotations.ServiceModule;

@ServiceModule
public class AppServerModule extends AbstractModule {

	@Override
	protected void configure() {

		bind(SocketEntryPoint.class).in(Singleton.class);
		bind(PyramidApplication.class).to(PyramidServerApplication.class).in(Singleton.class);
		
		bind(ProvisionSettingsService.class).to(ProvisionSettingsServiceImpl.class) ;
		bind(ProvisionSettingsStore.class).in(Singleton.class);

	}

}
