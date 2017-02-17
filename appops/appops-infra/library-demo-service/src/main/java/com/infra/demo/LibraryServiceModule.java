package com.infra.demo;

import com.google.inject.AbstractModule;

import pyramid.infra.core.annotations.ServiceModule;
import pyramid.infra.core.provision.NoopImpl;

@ServiceModule
public class LibraryServiceModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(LibraryService.class).to(LibraryServiceImpl.class);
		bind(LibraryService.class).annotatedWith(NoopImpl.class).to(LibraryServiceNoopImpl.class);
	}

}
