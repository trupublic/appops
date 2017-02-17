package pyramid.infra.dispatcher;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

import pyramid.infra.core.annotations.ServiceModule;

@ServiceModule
public class InfraDispatcherModule extends AbstractModule {

	@Override
	protected void configure() {
		bind (InvocableStore.class).in(Singleton.class);
	}

}
