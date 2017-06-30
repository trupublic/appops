package org.appops.scanner;

import java.net.URL;


public class ConfigurableDiscoverer extends Discoverer {

	boolean classPath = false, path = false, url = false;
	
	private Filter filter;
	
	private String[] arrayPath;
	private URL[] urls;

	public ConfigurableDiscoverer(String paths[],boolean b){
		/*classpath*/
		filter=new FilterImpl();
		if(b == true){
			arrayPath = paths;
			classPath = true;
		}
		/*path*/
		else{
			arrayPath = paths;
			path = true;
		}
	}
	
	public ConfigurableDiscoverer(URL[] urls){
		filter=new FilterImpl();
		this.urls = urls;
		url = true;
	}
	
	@Override
	public Filter getFilter() {
		return filter;
	}

	@Override
	public URL[] findResources() {
		if(classPath == true){
			ClasspathDiscoverer classpathDiscoverer = new ClasspathDiscoverer(arrayPath, true);
			return classpathDiscoverer.findResources();
		}
		
		else if(path == true){
			PathDiscoverer pathDiscoverer = new PathDiscoverer(arrayPath, false);
			return pathDiscoverer.findResources();
		}
		
		else if(url ==true){
			UrlDiscoverer urlDiscoverer = new UrlDiscoverer(urls);
			return urlDiscoverer.findResources();
		}
		return null;
	}

}
