package org.appops.scanner;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;

public class UrlDiscovererTest {
	Logger logger = Logger.getLogger(this.getClass().getCanonicalName());

	@Test
	public void testResources() throws Exception {

		URL[] url = new URL[3];
		File[] file = new File[3];

		/**
		 * URLClassLoader is used to load classes and resources from a search
		 * path of URLs, referring to both JAR files and directories.
		 */
		URLClassLoader loader = new URLClassLoader(url, ClassLoader.getSystemClassLoader());

		/**
		 * getResource method finds resource of given name.
		 */

		file[0] = new File(loader.getResource("testclasses/FirstTestClass.class").getFile());
		file[1] = new File(loader.getResource("testclasses/SecondTestClass.class").getFile());
		file[2] = new File(loader.getResource("testclasses/Testing.jar").getFile());

		/**
		 * this toURI() converts file to abstract path and then toURL() method
		 * converts that path to standard url.
		 */
		url[0] = file[0].toURI().toURL();
		url[1] = file[1].toURI().toURL();
		url[2] = file[2].toURI().toURL();

		UrlDiscoverer urlDiscoverer = new UrlDiscoverer(url);
		URL[] urlList = urlDiscoverer.findResources();

		/**
		 * This asserts checks whether findResource() is returning null or array
		 * of urls.
		 */
		Assert.assertNotNull(urlList);

		/**
		 * checks whether urls returning from findResource() method and urls
		 * from test case are equal.
		 */

		for (int i = 0; i < 3; i++) {
			Assert.assertEquals(url[i].toString(), urlList[i].toString());

		}
		/**
		 * Checks whether urls returning from findResource method are ends with
		 * .class or .jars.
		 */
		for (URL ur : urlList) {
			Assert.assertTrue((ur.toString().endsWith(".class")) || (ur.toString().endsWith(".jar")));
			logger.log(Level.INFO, "" + ur.toString());
		}
	}
}