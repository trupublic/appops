package org.appops.infra.dispatcher;

import org.appops.infra.core.annotations.ServiceModule;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

@ServiceModule
public class InfraDispatcherModule extends AbstractModule {

	@Override
	protected void configure() {
		bind (InvocableStore.class).in(Singleton.class);
	}

}
