
package org.appops.scanner;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class ClasspathDiscoverer extends Discoverer {

	/** The filter. */
	private Filter filter;

	/**
	 * Instantiates a new classpath reader.
	 */
	public ClasspathDiscoverer() {
		filter = new FilterImpl();
	}

	/**
	 * Uses java.class.path system-property to fetch URLs
	 * 
	 * @return the URL[]
	 */
	@SuppressWarnings("deprecation")
	@Override
	public final URL[] findResources() {
		
		List<URL> list = new ArrayList<URL>();
		
		String classpath = System.getProperty("java.classpath");
		
		if (classpath == null || classpath.isEmpty())
			classpath = System.getProperty("java.class.path") ;
		
		System.out.println("Used classpath ----- " + classpath);
		
		StringTokenizer tokenizer = new StringTokenizer(classpath,
				File.pathSeparator);

		while (tokenizer.hasMoreTokens()) {
			String path = tokenizer.nextToken();
			try {
				path = java.net.URLDecoder.decode(path, "UTF-8");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			File fp = new File(path);
			if (!fp.exists())
				throw new RuntimeException(
						"File in java.class.path does not exist: " + fp);
			try {
				list.add(fp.toURL());
			} catch (MalformedURLException e) {
				throw new RuntimeException(e);
			}
		}
		return list.toArray(new URL[list.size()]);
	}

	/* @see org.appops.scanner.Discoverer#getFilter() */
	public final Filter getFilter() {
		return filter;
	}

	/**
	 * @param filter
	 */
	public final void setFilter(Filter filter) {
		this.filter = filter;
	}
}
