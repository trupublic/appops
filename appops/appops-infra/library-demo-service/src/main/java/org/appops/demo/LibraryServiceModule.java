package org.appops.demo;

import org.appops.infra.core.annotations.ServiceModule;
import org.appops.infra.core.provision.NoopImpl;

import com.google.inject.AbstractModule;

@ServiceModule
public class LibraryServiceModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(LibraryService.class).to(LibraryServiceImpl.class);
		bind(LibraryService.class).annotatedWith(NoopImpl.class).to(LibraryServiceNoopImpl.class);
	}

}
