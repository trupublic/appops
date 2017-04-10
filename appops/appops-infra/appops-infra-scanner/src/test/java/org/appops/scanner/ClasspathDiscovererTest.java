package org.appops.scanner;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;

public class ClasspathDiscovererTest {
	Logger logger = Logger.getLogger(this.getClass().getCanonicalName());

	URL[] urlList;

	@Test
	public void testFindResources() throws MalformedURLException {

		ClasspathDiscoverer classpathDiscoverer = new ClasspathDiscoverer();
		urlList = classpathDiscoverer.findResources();

		Assert.assertNotNull(urlList);

		for (URL ur : urlList) {
			logger.log(Level.INFO, "" + ur);
		}

	}
}