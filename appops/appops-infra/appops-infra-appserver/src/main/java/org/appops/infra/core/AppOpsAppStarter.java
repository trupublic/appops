package org.appops.infra.core;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.appops.infra.core.annotations.ServiceModule;
import org.appops.scanner.AnnotatedClassScanner;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

public class AppOpsAppStarter {

	private static Injector injector = null;
	private static Logger logger = Logger.getLogger(AppOpsAppStarter.class.getCanonicalName());

	public static void main(String[] args) throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, IOException, AppOpsInfraException {

		String[] path  = null ;
		injector = Guice.createInjector(AppOpsAppStarter.getApplicationModules(path));

		injector.getInstance(AppOpsApplication.class).runApplication(args);

	}

	public static void start(String[] args)
			throws InstantiationException, IllegalAccessException, IOException, AppOpsInfraException {
		String[] path  = null ;
		injector = Guice.createInjector(AppOpsAppStarter.getApplicationModules(path));

		injector.getInstance(AppOpsApplication.class).runApplication(args);
	}

	public static void stop(String[] args) {
		injector.getInstance(AppOpsApplication.class).stopApplication(args);
	}

	static public Set<Module> getApplicationModules(URL[] urlsToScan)
			throws IOException, InstantiationException, IllegalAccessException {
		HashSet<Module> moduleSet = new HashSet<Module>();

		AnnotatedClassScanner scanner = new AnnotatedClassScanner();

		scanner.setUrlsToScan(urlsToScan);

		Set<Class<?>> moduleClassSet = scanner.getClassesAnnotatedWith(ServiceModule.class);

		for (Class<?> mod : moduleClassSet) {

			moduleSet.add((Module) mod.newInstance());

			logger.log(Level.INFO, "Found service module ->>>>     " + mod.getCanonicalName());

		}

		return moduleSet;
	}

	static public Set<Module> getApplicationModules(String[] paths)
			throws IOException, InstantiationException, IllegalAccessException {

		HashSet<Module> moduleSet = new HashSet<Module>();

		AnnotatedClassScanner scanner = new AnnotatedClassScanner();

		scanner.setPathsToScan(paths);

		Set<Class<?>> moduleClassSet = scanner.getClassesAnnotatedWith(ServiceModule.class);

		for (Class<?> mod : moduleClassSet) {

			moduleSet.add((Module) mod.newInstance());

			logger.log(Level.INFO, "Found service module ->>>>     " + mod.getCanonicalName());

		}

		return moduleSet;
	}

}
