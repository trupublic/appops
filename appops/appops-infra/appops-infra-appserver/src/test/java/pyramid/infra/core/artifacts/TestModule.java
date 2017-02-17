package pyramid.infra.core.artifacts;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

import pyramid.infra.core.annotations.ServiceModule;
import pyramid.infra.dispatcher.InvocableStore;

@ServiceModule
public class TestModule extends AbstractModule {

	@Override
	protected void configure() {
		this.bind(CountryService.class).to(CountryServiceImpl.class);
		this.bind(TestServiceInterface.class).to(TestServiceImpl.class);
		this.bind(InvocableStore.class).in(Singleton.class);
	}

}
