package org.appops.demo;

import org.appops.infra.core.annotations.ServiceModule;

import com.google.inject.AbstractModule;

@ServiceModule
public class InfraSampleServiceModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(CountryService.class).to(CountryServiceImpl.class);
	}
}
