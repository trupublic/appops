package tsgen;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.appops.infra.core.annotations.Service;
import org.junit.Assert;
import org.junit.Test;

import tsgen.ClassPathScanner.MyAnnotationDiscoveryListener;

/**
 * 
 * Responsibility: This class is used to test getClassesAnnotatedWith and
 * getInterfacesAnnotatedWith methods from classPathScanner class.
 *
 */

@Service
public class ClassPathScannerTestCase {
	
	/** These are the annotations for which we test the methods. */
	private static final String SERVICETYPE = "org.appops.infra.core.annotations.Service";
	private static final String JSTYPE = "jsinterop.annotations.JsType";

	@Test
	public void testMethods() throws ClassNotFoundException, IOException {

		Class<?> serviceTypeAnnotationClass = null;
		Class<?> jsTypeAnnotationClass = null;

		/**
		 * Here it gives class objects associated with SERVICETYPE and JSTYPE.
		 */
		serviceTypeAnnotationClass = Class.forName(SERVICETYPE);
		jsTypeAnnotationClass = Class.forName(JSTYPE);
		

		ClassPathScanner scanner = new ClassPathScanner();

		/**
		 * here class objects are send to getClassesAnnotatedWith method as a
		 * parameter to get all clases and interface annotated with Service.
		 */
		Collection<Class<?>> serviceTypeClasses = scanner.getClassesAnnotatedWith(serviceTypeAnnotationClass);
		assertNotNull(serviceTypeAnnotationClass);
		System.out.println(serviceTypeClasses);
		
		Collection<Class<?>> serviceTypeInterfaces=scanner.getInterfacesAnnotatedWith(serviceTypeAnnotationClass);
		assertNotNull(serviceTypeInterfaces);
		System.out.println(serviceTypeInterfaces);

		/**
		 * here class objects are send to getClassesAnnotatedWith method as a
		 * parameter to get all clases annotated with JsType.
		 */
		Collection<Class<?>> jsTypeAnnotationClasses = scanner.getClassesAnnotatedWith(jsTypeAnnotationClass);
		assertNotNull(jsTypeAnnotationClasses);
		System.out.println(jsTypeAnnotationClasses);

	}
}
