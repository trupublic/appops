package tsgen;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;

import tsgen.jackson.module.DefinitionGenerator;
import tsgen.jackson.module.grammar.Module;

public class TypeScriptGenerator {

	private static final String JSTYPE = "jsinterop.annotations.JsType";
	
	private static final String SERVICETYPE = "org.appops.infra.core.annotations.Service" ;
	/**
	 * arg[0] fully qualified annotation class name
	 * arg[1] path to folder where ts files will be placed
	 * arg[2] name of ts namespace 
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
		System.out.println("TypeScriptGenerator executing with arg " + args[0] );
		
		if (args == null || args[0] == null || args[0].isEmpty())
			throw new IOException("Need to have annotation class name as a parameter to do conversion ") ;
		
		ObjectMapper mapper = new ObjectMapper();

		DefinitionGenerator dg = new DefinitionGenerator(mapper);
		
		String genPath ;
		String moduleName ; 

		if ( args[0] != null || !args[0].isEmpty())
			genPath = args[0];
		else throw new IOException("Java to TypeScript generation path cannot be null or empty") ; 
		
		if (args[1] != null || !args[1].isEmpty())
			moduleName = args[1] ;
		else throw new IOException("Java to TypeScript generation module name cannot be null or empty") ; 
		
		Class<?> antClass = null, serviceAnnotationClass = null ;

		try {
			antClass = Class.forName(JSTYPE) ;
		} catch (ClassNotFoundException e) {
			throw new IOException(e);
		} 
		
		try{
			serviceAnnotationClass = Class.forName(SERVICETYPE);
		}catch (ClassNotFoundException e) {
			throw new IOException(e);
		} 
		
		// retrieve the set of classes annotated with JSTYPE
		ClassPathScanner scanner = new ClassPathScanner() ;
		Collection<Class<?>> jsTypeClasses = scanner.getClassesAnnotatedWith(antClass );
		
		scanner = new ClassPathScanner() ;
		Collection<Class<?>> serviceInterfaces = scanner.getInterfacesAnnotatedWith(serviceAnnotationClass);
		
		Set<Class<?>> classes = new HashSet<Class<?>>();
		
		classes.addAll(jsTypeClasses);
		classes.addAll(serviceInterfaces);
		
		FileWriter fw = new FileWriter(genPath + moduleName + ".d.ts"); 
		
		Module m = dg.generateTypeScript(moduleName, classes, null);
		
		m.write(fw);

	}

}