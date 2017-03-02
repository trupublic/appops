package org.appops.infra.core;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.appops.infra.core.annotations.ServiceModule;
import org.appops.scanner.AnnotatedClassScanner;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

public class AppOpsAppStarter {

	private static Injector injector = null ;

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException, AppOpsInfraException {
		
		injector = Guice.createInjector(AppOpsAppStarter.getApplicationModules());
		
		injector.getInstance(AppOpsApplication.class).runApplication(args);
		
	}
	
	public static void start(String[] args) throws InstantiationException, IllegalAccessException, IOException, AppOpsInfraException{
		injector = Guice.createInjector(AppOpsAppStarter.getApplicationModules());
		
		injector.getInstance(AppOpsApplication.class).runApplication(args);
	}
	
	public static void stop(String[] args){
		injector.getInstance(AppOpsApplication.class).stopApplication(args) ;
	}
	
	static Set<Module> getApplicationModules() throws IOException, InstantiationException, IllegalAccessException{
		
		HashSet<Module> moduleSet = new HashSet<Module>();
		
		AnnotatedClassScanner scanner = new AnnotatedClassScanner() ;
		
		Set<Class<?>> moduleClassSet = scanner.getClassesAnnotatedWith(ServiceModule.class);
		
		for (Class<?> mod : moduleClassSet){
			
			moduleSet.add((Module)mod.newInstance()) ;
			
			System.out.println("Using module ->>>> " + mod.getCanonicalName() );

		}
		
		return moduleSet ;
		
	}

}
