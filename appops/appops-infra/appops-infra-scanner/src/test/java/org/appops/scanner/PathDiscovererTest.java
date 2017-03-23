package org.appops.scanner;

import static org.junit.Assert.*;

import java.io.File;
import java.net.URL;

import org.junit.Test;

public class PathDiscovererTest {

	/*
	 * This method scans the folder containing .class files
	 */
	@Test
	public void classFilesPathTest() {
		URL[] classUrls;

		ClassLoader classLoader = getClass().getClassLoader();

		// gets the relative folder path of the folders containing .class files
		File file1 = new File(classLoader.getResource("result-class/").getPath());

		// gets all the files
		File[] classFiles = file1.listFiles();
		assertTrue(classFiles != null);

		// checks if all files ends with .class
		for (File f1 : classFiles) {
			assertTrue(f1.getName().endsWith(".class"));
		}

		String[] classNames = new String[classFiles.length];
		for (int i = 0; i < classFiles.length; i++) {
			classNames[i] = classFiles[i].getPath();
		}

		PathDiscoverer path1 = new PathDiscoverer(classNames, true);

		classUrls = path1.findResources();

		for (URL url : classUrls) {

			assertTrue(url.toString().endsWith(".class"));

		}

	}

	/*
	 * This method scans the folder containing .jar files
	 */
	@Test
	public void jarFilesPathTest() {
		URL[] jarUrls;

		ClassLoader classLoader = getClass().getClassLoader();
		// gets the relative folder path of the folder containing .jar files
		File file2 = new File(classLoader.getResource("result-jar/").getPath());

		// gets all the files
		File[] jarFiles = file2.listFiles();
		assertTrue(jarFiles != null);

		// checks if all files ends with .jar
		for (File f1 : jarFiles) {
			assertTrue(f1.getName().endsWith(".jar"));
		}

		String[] jarNames = new String[jarFiles.length];
		for (int i = 0; i < jarFiles.length; i++) {
			jarNames[i] = jarFiles[i].getPath();
		}

		PathDiscoverer path2 = new PathDiscoverer(jarNames, true);

		jarUrls = path2.findResources();
		for (URL url : jarUrls) {
			assertTrue(url.toString().endsWith(".jar"));
		}

	}

	/**
	 * This method scans the folder containing .class or .jar files 
	 */
	@Test
	public void classJarFilesPathTest() {

		URL[] jarClassUrls;

		ClassLoader classLoader = getClass().getClassLoader();
		
		
		// gets the relative folder path of the folder containing .class and .jar files
		File file3 = new File(classLoader.getResource("result-class-jar/").getPath());

		// gets all files
		File[] classJarFiles = file3.listFiles();
		assertTrue(classJarFiles != null);

		// checks if all files ends with .class or .jar
		for (File f1 : classJarFiles) {
			assertTrue(f1.getName().endsWith(".class") || f1.getName().endsWith(".jar"));
		}

		String[] classJarNames = new String[classJarFiles.length];
		for (int i = 0; i < classJarFiles.length; i++) {
			classJarNames[i] = classJarFiles[i].getPath();

		}

		PathDiscoverer path3 = new PathDiscoverer(classJarNames, true);

		jarClassUrls = path3.findResources();

		for (URL url : jarClassUrls) {
			assertTrue(url.toString().endsWith(".class") || url.toString().endsWith(".jar"));
		}

	}

}
