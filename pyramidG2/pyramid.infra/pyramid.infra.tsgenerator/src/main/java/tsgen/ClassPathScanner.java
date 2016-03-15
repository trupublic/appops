package tsgen;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.pa.tsgen.scanner.ClasspathDiscoverer;
import com.pa.tsgen.scanner.Discoverer;
import com.pa.tsgen.scanner.listener.ClassAnnotationDiscoveryListener;

public class ClassPathScanner {
	
	public ClassPathScanner(){
	
	}
	
	/**
	 * returns the set of classes annotated with specified annotation 
	 * @param t class object representing the annotation
	 * @return
	 * @throws IOException
	 */

	public Set<Class<?>> getInterfacesAnnotatedWith(Class<?> t) throws IOException{
		Discoverer discoverer = new ClasspathDiscoverer();
		MyAnnotationDiscoveryListener listener = new MyAnnotationDiscoveryListener(new String[] {t.getCanonicalName()});
		listener.findOnlyInterfaces = true ;
		discoverer.addAnnotationListener(listener);
		discoverer.discover();
		return listener.getDiscovered() ;
	}

	
	public Set<Class<?>> getClassesAnnotatedWith(Class<?> t) throws IOException{
		Discoverer discoverer = new ClasspathDiscoverer();
		MyAnnotationDiscoveryListener listener = new MyAnnotationDiscoveryListener(new String[] {t.getCanonicalName()});
		discoverer.addAnnotationListener(listener);
		discoverer.discover();
		return listener.getDiscovered() ;
	}

	static class MyAnnotationDiscoveryListener implements ClassAnnotationDiscoveryListener {

		boolean findOnlyInterfaces = false ;
		
		String[] supportedAnnotations ; 
		
		HashSet<Class<?>> discoveredClasses = new HashSet<Class<?>> () ;
		
		public MyAnnotationDiscoveryListener(String[] annotations){
			supportedAnnotations = annotations ;
		}
		
		public Set<Class<?>> getDiscovered(){
			return discoveredClasses ;
		}
		
		@Override
		public String[] supportedAnnotations() {
			return supportedAnnotations ;
		}

		@Override
		public void discovered(String clazz, String annotation) {
			
			Class<?> clazzInstance = null ;
			
			try {
				clazzInstance = Class.forName(clazz) ;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			// In this case it finds all interfaces only
			if (findOnlyInterfaces && clazzInstance.isInterface())
				discoveredClasses.add(clazzInstance) ;
			else
				// Finds all classes and interfaces annotated
				discoveredClasses.add(clazzInstance) ;
			
		}
	}
}
