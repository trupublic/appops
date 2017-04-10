package org.appops.scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

public class UrlDiscovererTest {

	@Test
	public void testResources() {

		URL[] url = new URL[4];
		URL[] folderURL = new URL[1];
		File[] file = new File[4];
		File folder[] = new File[1];
		Logger logger = Logger.getLogger(this.getClass().getCanonicalName());

		try {

			/**
			 * URLClassLoader is used to load classes and resources from a search
			 * path of URLs, referring to both JAR files and directories.
			 */
			URLClassLoader loader = new URLClassLoader(url, ClassLoader.getSystemClassLoader());

			file[0] = new File(loader.getResource("com/assets/A.class").getFile());
			file[1] = new File(loader.getResource("com/assets/B.class").getFile());
			file[2] = new File(loader.getResource("com/assets/C.class").getFile());
			file[3] = new File(loader.getResource("com/assets/Demo.jar").getFile());

			/**
			 * this toURI() converts file to abstract path and then toURL() method
			 * converts that path to standard url.
			 */

			url[0] = file[0].toURI().toURL();

			url[1] = file[1].toURI().toURL();
			url[2] = file[2].toURI().toURL();
			url[3] = file[3].toURI().toURL();

			UrlDiscoverer urlDiscoverer = new UrlDiscoverer(url);
			URL[] urlList = urlDiscoverer.findResources();

			/**
			 * this asserts checks whether findResource is returning null or array
			 * of urls.
			 */
			assertNotNull("URL list is null..", urlList);

			String testUrlName = url[0].toString();
			String resourceUrlName = urlList[0].toString();

			/**
			 * This assert checks whether urls returning from findResource() method
			 * and urls from test case are equal.
			 */
			assertEquals("URLs are not equal", testUrlName, resourceUrlName);

			for (URL ur : urlList) {
				/**
				 * this asserts checks that whether urls returning from findResource
				 * method are ends with .class or .jars.
				 */
				assertTrue(ur.toString().endsWith(".class") || ur.toString().endsWith(".jar"));
				logger.log(Level.INFO, ur.toString());
			}

			/**
			 * If URL is folder then it tests whether it is giving .class and .jar
			 * files from that folder.
			 */

			folder[0] = new File(loader.getResource("com/assets/Demo.jar").getFile());
			folderURL[0] = folder[0].toURI().toURL();

			UrlDiscoverer disc = new UrlDiscoverer(folderURL);
			URL[] urlFolderList = disc.findResources();

			/**
			 * this asserts checks whether findResource is returning null or array
			 * of urls.
			 */
			assertNotNull("Folder URL is null..", urlFolderList);

			String testFolderUrlName = urlFolderList[0].toString();
			String resourceFolderUrlName = folderURL[0].toString();

			/**
			 * This assert checks whether urls returning from findResource() method
			 * and urls from test case are equal.
			 */
			assertEquals("URLs are not equal", testFolderUrlName, resourceFolderUrlName);
		} catch (MalformedURLException e) {
			logger.log(Level.SEVERE," " ,e);
		}
	}
}