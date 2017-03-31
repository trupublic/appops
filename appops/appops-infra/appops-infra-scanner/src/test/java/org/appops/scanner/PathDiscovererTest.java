package org.appops.scanner;

import static org.junit.Assert.*;

import java.io.File;
import java.net.URL;
import java.util.StringTokenizer;

import org.junit.Test;

public class PathDiscovererTest {

	/*
	 * Scans the folder path containing .class files
	 */

	@Test
	public void classFilesPathTest() {

		URL[] classUrls = new URL[2];

		// Used to load a classes at runtime
		ClassLoader classLoader = getClass().getClassLoader();

		// gets the relative path of the folder containing class files
		File file = new File(classLoader.getResource("result-class/").getPath());
		File file1 = new File(classLoader.getResource("result-class1/").getPath());

		String[] str = new String[2];
		str[0] = file.toString();
		str[1] = file1.toString();

		PathDiscoverer pathname = new PathDiscoverer(str, true);

		classUrls = pathname.findResources();

		assertTrue(classUrls != null);

	}

	/*
	 * This method scans the folder containing .jar files
	 */
	@Test
	public void jarFilesPathTest() {
		URL[] jarUrls = new URL[2];

		ClassLoader classLoader = getClass().getClassLoader();

		// gets the relative folder path of the folder containing .jar files
		File file1 = new File(classLoader.getResource("result-jar/").getPath());
		File file2 = new File(classLoader.getResource("result-jar1/").getPath());

		String[] str = new String[2];
		str[0] = file1.toString();
		str[1] = file2.toString();

		PathDiscoverer pathname = new PathDiscoverer(str, true);

		jarUrls = pathname.findResources();

		assertTrue(jarUrls != null);

	}

	/**
	 * This method scans the folder containing .class and .jar files
	 */
	@Test
	public void classJarFilesPathTest() {

		URL[] jarClassUrls = new URL[2];
		ClassLoader classLoader = getClass().getClassLoader();

		// gets the relative path of the folder
		File file1 = new File(classLoader.getResource("result-class-jar/").getPath());
		File file2 = new File(classLoader.getResource("result-class-jar1/").getPath());

		String[] str = new String[2];
		str[0] = file1.toString();
		str[1] = file2.toString();

		PathDiscoverer discover = new PathDiscoverer(str, true);

		jarClassUrls = discover.findResources();

		assertTrue(jarClassUrls != null);

	}

}
