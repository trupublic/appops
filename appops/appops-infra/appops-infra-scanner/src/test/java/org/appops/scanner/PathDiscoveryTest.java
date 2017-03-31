package org.appops.scanner;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Test;

public class PathDiscoveryTest {

	/**
	 * @responsibility-Checks if Urls is null and checks if urls ends with
	 *                        .class or .jar extension
	 * @throws IOException
	 */
	@Test
	public void findResourcesTest() throws IOException {

		// String[] relativePath = new String[3];
		URL[] urls;

/*		Files f = new File("./res/A.java");
		System.out.println(f.getAbsolutePath());
		System.out.println(f.isFile());*/
		

		URL path=getClass().getResource(".");
		File file=new File(path.toString());
		
		System.out.println(file);

	/*	boolean isDirectory=Files.isDirectory(file);
		System.out.println(":"+isDirectory);*/
		/*if(isDirectory==true){
			System.out.println("true");
		}
		else
		{
			System.out.println("false");
		}
		*/
		//System.out.println(f.getCanonicalPath());

		// File[] files=f.listFiles();

		/*
		 * URL classesRootDir =
		 * getClass().getProtectionDomain().getCodeSource().getLocation();
		 * System.out.println(classesRootDir);
		 */
		/*
		 * final File f = new
		 * File(A.class.getProtectionDomain().getCodeSource().getLocation().
		 * getPath());
		 * 
		 * System.out.println(f);
		 */
		// ClassLoader classLoader =
		// Thread.currentThread().getContextClassLoader();
		// File f = new File(classLoader.getResource("target/").getPath());
		// System.out.println(f);

		/*
		 * gets the folder path
		 */

		// ClassLoader classLoader = getClass().getClassLoader();
		// File f = new File(classLoader.getResource("res").getFile());
		// System.out.println(f);

		// URL url = classLoader.getResource("res/");
		// System.out.println(url);

		// String relativePath=f.getParent();

		// System.out.println(relativePath);

		// gets the relative Path
		// ClassLoader classLoader = getClass().getClassLoader();
		// File[] f = new File[3];
		// f[0] = new File(classLoader.getResource(".").getFile());
		// System.out.println(f[0]);

		// f[1] = new File(classLoader.getResource("res/").getFile());
		// f[2] = new File(classLoader.getResource("res/abc.jar").getFile());

		// relativePath[0] = f[0].getAbsolutePath();
		// relativePath[1] = f[1].getAbsolutePath();
		// relativePath[2] = f[2].getAbsolutePath();

		// PathDiscoverer path = new PathDiscoverer(relativePath, true);

		// urls = path.findResources();

		// assertTrue(urls != null);

		// assertTrue(urlEndsWith(urls));
	}

	/**
	 * 
	 * @param urls-Accepts
	 *            Urls
	 * @return-returns true if the url ends with .class or .jar extension
	 */
	public boolean urlEndsWith(URL[] urls) {
		for (URL url : urls) {
			if (url.toString().endsWith(".class") || url.toString().endsWith(".jar") == true) {
				return true;
			}
		}
		return false;
	}

}
