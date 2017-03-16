package org.appops.scanner;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.junit.Test;

public class PathDiscoveryTest {

	/**
	 * @responsibility-Checks if Urls is null and checks if urls ends with
	 *                        .class or .jar extension
	 * @throws IOException
	 */
	@Test
	public void findResourcesTest() throws IOException {

		String[] relativePath = new String[3];
		URL[] urls;

		// gets the relative Path
		ClassLoader classLoader = getClass().getClassLoader();
		File[] f = new File[3];
		f[0] = new File(classLoader.getResource("res/A.class").getFile());
		f[1] = new File(classLoader.getResource("res/B.class").getFile());
		f[2] = new File(classLoader.getResource("res/abc.jar").getFile());

		relativePath[0] = f[0].getAbsolutePath();
		relativePath[1] = f[1].getAbsolutePath();
		relativePath[2] = f[2].getAbsolutePath();

		PathDiscoverer path = new PathDiscoverer(relativePath, true);

		urls = path.findResources();

		assertTrue(urls != null);

		assertTrue(urlEndsWith(urls));
	}

	/**
	 * 
	 * @param urls-Accepts Urls
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
