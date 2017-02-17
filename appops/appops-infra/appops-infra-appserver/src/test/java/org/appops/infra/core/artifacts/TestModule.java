package org.appops.infra.core.artifacts;

import org.appops.infra.core.annotations.ServiceModule;
import org.appops.infra.dispatcher.InvocableStore;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

@ServiceModule
public class TestModule extends AbstractModule {

	@Override
	protected void configure() {
		this.bind(CountryService.class).to(CountryServiceImpl.class);
		this.bind(TestServiceInterface.class).to(TestServiceImpl.class);
		this.bind(InvocableStore.class).in(Singleton.class);
	}

}
