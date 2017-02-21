package org.appops.infra.appserver;

import org.appops.infra.appserver.provision.ProvisionSettingsService;
import org.appops.infra.appserver.provision.ProvisionSettingsServiceImpl;
import org.appops.infra.appserver.provision.ProvisionSettingsStore;
import org.appops.infra.core.AppOpsApplication;
import org.appops.infra.core.annotations.ServiceModule;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

@ServiceModule
public class AppServerModule extends AbstractModule {

	@Override
	protected void configure() {

		bind(AppServerEntryPoint.class).in(Singleton.class);
		bind(AppOpsApplication.class).to(AppOpsAppServer.class).in(Singleton.class);
		
		bind(ProvisionSettingsService.class).to(ProvisionSettingsServiceImpl.class) ;
		bind(ProvisionSettingsStore.class).in(Singleton.class);

	}

}
