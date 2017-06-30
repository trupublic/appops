package org.appops.webserver;

import javax.inject.Singleton;

import com.google.inject.servlet.ServletModule;

public class InfraWebAppModule extends ServletModule {

	@Override
	protected void configureServlets() {
		serve("/infraEntryPoint").with(WebAppEntryPoint.class);
		bind(WebAppEntryPoint.class).in(Singleton.class);
		bind(InfraEntryPoint.class).in(Singleton.class);
	}

}
