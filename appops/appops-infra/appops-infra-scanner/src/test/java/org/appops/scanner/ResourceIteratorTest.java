package org.appops.scanner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.appops.scanner.resource.ClassFileIterator;
import org.appops.scanner.resource.JarFileIterator;
import org.appops.scanner.resource.ResourceIterator;
import org.junit.Test;

public class ResourceIteratorTest {

	/*
	 * Checks getResourceIterator for a resource containing url of class folder
	 */

	@Test
	public void getResourceIteratorClassTest() throws IOException {

		Logger logger = Logger.getLogger(this.getClass().getCanonicalName());
		
		Filter filter = new FilterImpl();

		ResourceIterator iterator;

		URL[] classUrls;

		ClassLoader classLoader = getClass().getClassLoader();

		// get path of folder containing folder of .class files
		File file1 = new File(classLoader.getResource("result-class").getPath());

		// gets path of parent folder containing .class files
		File file2 = new File(classLoader.getResource("result-class1/").getPath());

		String[] path = new String[2];
		path[0] = file1.toString();
		path[1] = file2.toString();

		Discoverer discover = new PathDiscoverer(path, true);

		classUrls = discover.findResources();

	
		
		// Checks if url is null
		assertTrue(classUrls != null);

		for (URL url : classUrls) {

			logger.log(Level.INFO,""+ url);
			iterator = discover.getResourceIterator(url, filter);

			// checks if Null
			assertNotNull(iterator);

			// checks if the iterator is instanceof ClassFileIterator
			assertTrue(iterator instanceof ClassFileIterator);

		}

	}

	/*
	 * Checks getResourceIterator for a resource containing url of format jar: url !/
	 */
	
	
	@Test
	public void getResourceIteratorJarsTest() throws IOException {
		
		Logger logger = Logger.getLogger(this.getClass().getCanonicalName());
		
		URL[] jarUrls;

		Filter filter = new FilterImpl();
		ResourceIterator iterator;

		ClassLoader loader = getClass().getClassLoader();

		// gets the relative path of the jar file
		File file = new File(loader.getResource("result-jar/a.jar").getFile());

		// converts to url format
		URL url1 = file.toURI().toURL();
		URL[] url2 = new URL[1];

		url2[0] = new URL("jar", "", url1 + "!/");

		Discoverer discover = new UrlDiscoverer(url2);

		jarUrls = discover.findResources();

		for (URL url3: jarUrls) {
			
			logger.log(Level.INFO,""+ url3);
			
			iterator = discover.getResourceIterator(url3, filter);

			// checks if the iterator is null or not
			assertTrue(iterator != null);

			// checks if the iterator is an instanceOf JarFileIterator
			assertTrue(iterator instanceof JarFileIterator);

		}

	}

	/*
	 * Checks getResourceIterator for a resource containing url of jar file
	 */
	
	@Test
	public void getResourceIteratorJarTest() throws IOException {

		Logger logger = Logger.getLogger(this.getClass().getCanonicalName());
		
		
		URL[] jarFiles;

		Filter filter = new FilterImpl();

		ResourceIterator iterator;

		// used to load a class at runtime
		ClassLoader loader = getClass().getClassLoader();

		// gets the path of jar file
		File file1 = new File(loader.getResource("result-jar/a.jar").getPath());

		String[] path = new String[1];
		path[0] = file1.toString();

		Discoverer discover = new PathDiscoverer(path, true);

		jarFiles = discover.findResources();

		assertTrue(jarFiles != null);
		for (URL url : jarFiles) {
			
			logger.log(Level.INFO,""+ url);
			iterator = discover.getResourceIterator(url, filter);

			// checks if the iterator is null or not
			assertTrue(iterator != null);

			// check if the iterator ia an instanceOf JarFileIterator
			assertTrue(iterator instanceof JarFileIterator);

		}

	}
}
