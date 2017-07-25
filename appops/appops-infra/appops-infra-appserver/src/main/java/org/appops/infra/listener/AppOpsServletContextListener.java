package org.appops.infra.listener;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.appops.infra.core.AppOpsAppStarter;
import org.appops.infra.dispatcher.InvocableStore;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.servlet.GuiceServletContextListener;

public class AppOpsServletContextListener extends GuiceServletContextListener {

	private static Logger logger = Logger.getLogger(AppOpsServletContextListener.class.getCanonicalName());

	private URL[] urlsToScan = null;

	@Override
	protected Injector getInjector() {

		URLClassLoader classLoader = (URLClassLoader) this.getClass().getClassLoader();
		urlsToScan = classLoader.getURLs();

		for (URL url : urlsToScan) {
			logger.log(Level.WARNING, "tomcat classloader url - " + url.toExternalForm());
		}

		try {

			Set<Module> moduleSet = AppOpsAppStarter.getApplicationModules(urlsToScan);

			moduleSet.add(new InfraWebAppModule());

			Injector injector = Guice.createInjector(moduleSet);
			
			InvocableStore store = injector.getInstance(InvocableStore.class);
			
			store.setUrlsToScan(urlsToScan);
			
			store.buildServiceGrammar();
			
			return injector;

		} catch (InstantiationException e) {
			logger.log(Level.SEVERE, "Couldn't instantiate application modules while trying to create injector", e);
		} catch (IllegalAccessException e) {
			logger.log(Level.SEVERE, "Couldn't instantiate injector", e);
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Couldn't locate application modules while trying to create injector", e);
		}

		return null;
	}
}