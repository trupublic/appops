package org.appops.scanner;

import java.net.URL;

public class UrlDiscoverer extends Discoverer {

	public URL[]	url;

	public Filter	filter;

	public UrlDiscoverer() {

	}

	public UrlDiscoverer(URL[] url, Filter filter) {
		super();
		this.url = url;
		this.filter = filter;
	}

	public UrlDiscoverer(URL[] url) {
		super();
		this.url = url;
	}

	@Override
	public Filter getFilter() {
		return filter;

	}

	public URL[] getUrl() {
		return url;
	}

	public void setUrl(URL[] url) {
		this.url = url;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}

	/*
	 * public boolean checkUrl(File urlName) { String temp = urlName.toString(); if (temp.endsWith(".class") ||
	 * temp.endsWith(".jar")) return true; else return false; }
	 */

	/**
	 * This method returns the array of URLs.This method checks whether the URL is pointing to the folder or file and
	 * accordingly returns URLs.
	 */

	@Override
	public URL[] findResources() {

		if (url != null)

			return url;

		else
			return null;

	}

}
