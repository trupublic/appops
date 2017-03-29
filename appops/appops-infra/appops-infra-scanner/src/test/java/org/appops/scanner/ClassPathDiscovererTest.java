package org.appops.scanner;

import static org.junit.Assert.*;

import java.io.File;
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
 *This Class checks if the url exists in the java.class.path
 */

public class ClassPathDiscovererTest {

	@Test
	public void findResourcesTest() throws MalformedURLException {

		URL[] urls;

		List<URL> list = new ArrayList<URL>();

		URL url1 = new URL("file:/home/iternia/git/appops/appops/appops-infra/appops-infra-scanner/target/classes/");

		URL url2 = new URL("file:/home/iternia/Downloads/maven3/conf/logging/");

		ClasspathDiscoverer path = new ClasspathDiscoverer();

		String classpath = System.getProperty("java.classpath");
		String classpath1 = System.getProperty("java.class.path");

		assertTrue(classpath == null);
		assertTrue(classpath1 != null);

		StringTokenizer token = new StringTokenizer(classpath1, File.pathSeparator);

		urls = path.findResources();

		assertTrue(urls != null);

		// Valid url name in java.class.path
		assertTrue(findEqualsUrl(urls, url1));

		// Invalid url name in java.class.path
		assertFalse(findEqualsUrl(urls, url2));

	}

	@Test
	public void getUrlListTokensTest() throws MalformedURLException {

		List<URL> urlList = new ArrayList<URL>();

		ClasspathDiscoverer path = new ClasspathDiscoverer();

		URL url1 = new URL(
				"file:/home/iternia/.m2/repository/org/javassist/javassist/3.19.0-GA/javassist-3.19.0-GA.jar");

		URL url2 = new URL("file:/home/iternia/Downloads/maven3/conf/logging/");

		String classpath = System.getProperty("java.class.path");

		StringTokenizer tokenizer = new StringTokenizer(classpath, File.pathSeparator);

		urlList = path.getUrlListFromTokenizer(tokenizer);

		// Valid url name in java.class.path
		assertTrue(findEqualsList(urlList, url1));

		// InValid url name in java.class.path
		assertFalse(findEqualsList(urlList, url2));
	}

	/**
	 * 
	 * @param urls-list
	 *            of urls
	 * @param url-actual
	 *            url to be compared
	 * @return-returns true or false
	 */

	public boolean findEqualsUrl(URL[] urls, URL url) {

		for (URL ur : urls) {
			if (ur.equals(url)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param urls-list
	 *            of urls
	 * @param url-actual
	 *            url to be compared
	 * @return-returns true or false
	 */

	public boolean findEqualsList(List<URL> urls, URL url) {

		for (URL ur : urls) {
			if (ur.equals(url)) {
				return true;
			}
		}
		return false;
	}

}
