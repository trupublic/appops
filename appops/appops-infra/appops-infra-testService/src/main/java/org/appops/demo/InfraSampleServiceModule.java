package org.appops.demo;

import com.google.inject.AbstractModule;

import pyramid.infra.core.annotations.ServiceModule;

@ServiceModule
public class InfraSampleServiceModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(CountryService.class).to(CountryServiceImpl.class);
	}
}
