package org.appops.scanner;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.hamcrest.Matcher;
import org.junit.Test;

/**
 * 
 * 
 * This Class checks if the url exists in the java.class.path
 */

public class ClassPathDiscovererTest {

	@Test
	public void findResourcesTest() throws MalformedURLException {

		URL[] urls;

		List<URL> list = new ArrayList<URL>();
		URL[] ur = new URL[2];

		// gets a relative path of a particluar file
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("result-jar/a.jar").getFile());
		File file1 = new File(classLoader.getResource("result-class/").getFile());

		ur[0] = file.toURI().toURL();
		ur[1] = file1.toURI().toURL();

		ClasspathDiscoverer path = new ClasspathDiscoverer();
		urls = path.findResources();

		assertTrue(urls != null);
		for (URL url : urls) {

			// checks if the given url ends with .jar
			if (url.toString().endsWith(".jar")) {
				assertTrue(url.toString().endsWith(".jar"));

				// checks if the Url is not equal
				assertNotEquals(url, ur[0]);

				// checks if the Url is equal
				assertEquals(url, ur[0]);
			}

			// checks if the given url is a class folder
			if (url.toString().endsWith("/")) {
				
				//checks if the url is not equal
				assertNotEquals(url,ur[1]);

				//checks if the url is equal
				assertEquals(url,ur[1]);
				
				
				assertTrue(url.toString().endsWith("/"));

			}

		}

	}

}
