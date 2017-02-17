package org.appops.infra.appserver;

import org.appops.infra.appserver.provision.ProvisionSettingsService;
import org.appops.infra.appserver.provision.ProvisionSettingsServiceImpl;
import org.appops.infra.appserver.provision.ProvisionSettingsStore;
import org.appops.infra.core.PyramidApplication;
import org.appops.infra.core.annotations.ServiceModule;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

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
