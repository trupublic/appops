package org.appops.scanner;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * This Class is Only used to Scan the Arrays of path
 * 
 */

public class PathDiscoverer extends Discoverer {

	private String arrayPath[];
	private Filter filter;

	/* Instantiates a new PathDiscoverer */
	public PathDiscoverer(String[] paths, boolean b) {
		filter = new FilterImpl();
		arrayPath = paths;
	}

	@Override
	public final Filter getFilter() {
		return filter;
	}

	/* Return a Set of URLs */
	@Override
	public URL[] findResources() {
		List<URL> list = new ArrayList<URL>();
		list = getUrlListFromArraypath(arrayPath);
		return list.toArray(new URL[list.size()]);

	}

	private List<URL> getUrlListFromArraypath(String[] arrayPath2) {

		List<URL> list = new ArrayList<URL>();

		for (String path : arrayPath2) {
			list.add(getUrlFromPath(path));
		}
		return list;

	}

	private URL getUrlFromPath(String path) {
		try {
			path = java.net.URLDecoder.decode(path, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		File fp = new File(path);
		if (!fp.exists())
			throw new RuntimeException("File in java.class.path does not exist: " + fp);
		try {
			return fp.toURI().toURL();
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @param filter
	 */
	public final void setFilter(Filter filter) {
		this.filter = filter;
	}

}
