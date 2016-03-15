package pyramid.infra.core;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.pa.tsgen.scanner.AnnotatedClassScanner;

import pyramid.infra.core.annotations.ServiceModule;

public class PyramidAppStarter {

	private static Injector injector = null ;

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException, PyramidInfraException {
		
		injector = Guice.createInjector(PyramidAppStarter.getApplicationModules());
		
		injector.getInstance(PyramidApplication.class).runApplication(args);
		
	}
	
	public static void start(String[] args) throws InstantiationException, IllegalAccessException, IOException, PyramidInfraException{
		injector = Guice.createInjector(PyramidAppStarter.getApplicationModules());
		
		injector.getInstance(PyramidApplication.class).runApplication(args);
	}
	
	public static void stop(String[] args){
		injector.getInstance(PyramidApplication.class).stopApplication(args) ;
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
