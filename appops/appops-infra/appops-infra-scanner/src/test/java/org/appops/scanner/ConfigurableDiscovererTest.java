package org.appops.scanner;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

public class ConfigurableDiscovererTest {

	@Test
	public void test() {
		Logger logger = Logger.getLogger(this.getClass().getCanonicalName());

		try{
			String paths[] = new String[1];
			URL[] urlList = new URL[1];
			File file[] = new File[1];

			paths[0] = "/home/iternia/git/appops/appops/appops-infra/appops-infra-scanner/target/test-classes/";

			ConfigurableDiscoverer configurableDiscoverer = new ConfigurableDiscoverer(paths, true);
			assertNotNull(configurableDiscoverer.findResources());

			configurableDiscoverer = new ConfigurableDiscoverer(paths, false);
			assertNotNull(configurableDiscoverer.findResources());

			URLClassLoader loader = new URLClassLoader(urlList, ClassLoader.getSystemClassLoader());
			file[0] = new File(loader.getResource("com/assets/A.class").getFile());
			urlList[0] = file[0].toURI().toURL();
			configurableDiscoverer = new ConfigurableDiscoverer(urlList);
			assertNotNull(configurableDiscoverer.findResources());
		}catch(Exception e){
			logger.log(Level.SEVERE," " ,e);
		}
	}
}
