package pyramid.infra.dispatcher;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.common.base.Preconditions;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import pyramid.infra.core.PyramidInfraException;
import pyramid.infra.core.annotations.Service;

public class InvocableStore {

	public BiMap<String, Invocable> invocableMap = HashBiMap.create();
	
	private static Logger logger = Logger.getLogger(InvocableStore.class.getName()) ;
	
	/**
	 * @throws IOException
	 */
	public void buildServiceGrammar() throws IOException {
		
		InvocableScanner scanner = new InvocableScanner();
		
		Set<Class<?>> interfaces = scanner.getServiceInterfaceSet();
		
		processServiceInterfaceSet(interfaces);
	}
	
	/**
	 * Prepare the map that will contain the invocables 
	 * Takes special care of parents that may not be processed yet
	 * @param interfaces
	 */
	public void processServiceInterfaceSet(Set<Class<?>> interfaces) {
		
		Set<Class<?>> pendingSet = new HashSet<Class<?>>();
		
		logger.log(Level.INFO, "Building invocables map - running processServiceInterfaceSet" );
		
		for (Class<?> inter : interfaces) {

			Service service = inter.getAnnotation(Service.class);
			
			String serviceName = ( service.name().equals(Service.DEFAULTNAME) ? inter.getSimpleName() : service.name() );

			Class<?> parent = service.parent();
			
			// No parent specified 
			if (parent == Service.NULLPARENT.class) { 
				Invocable invocable = new Invocable(serviceName , inter) ;
				invocableMap.put(serviceName , invocable) ;	
				logger.log(Level.INFO, "adding invocable >>> " + serviceName + " --->>>>--- " + inter.getCanonicalName());
				pendingSet.remove(inter) ;
			}else{ // there is a parent 
				String parentPath = getParentPath(inter , parent, pendingSet) ;
				if (parentPath != null){
					serviceName = parentPath + "/" + serviceName ;
					logger.log(Level.INFO,"adding invocable >>> " + serviceName + " --->>>>--- " + inter.getCanonicalName());
					Invocable invocable = new Invocable(serviceName , inter) ;
					invocableMap.put(serviceName , invocable) ;	
					pendingSet.remove(inter);
				}
			}
		}
		
		if ( !pendingSet.isEmpty()){
			processServiceInterfaceSet(pendingSet);
		}
	}

	public String getParentPath(Class<?> service, Class<?> parent, Set<Class<?>> pending) {

		Annotation[] parentAns = parent.getAnnotations();

		for (Annotation annotation : parentAns) {

			if (annotation.annotationType() == Service.class) {

				String name = this.getServicePath(parent);
				
				// If parent service path is not found add the current service to pending set
				if (name != null && !name.isEmpty())
					return name;
				
				pending.add(service);

			}
		}
		
		return null ;
	}

	/**
	 * @param pr
	 * @return service name for specified class ... 
	 * TODO else throw exception
	 */
	private String getServicePath(Class<?> pr) {
		Preconditions.checkNotNull(pr, "Null parameter not allowed here") ;
		
		for (Invocable inv : invocableMap.inverse().keySet()){
			if (inv.getInvocable()!= null && inv.getInvocable().equals(pr))
				return inv.getServiceName();
		}
	
		return null ;
	}

	public Class<?> getInvocableService(String name) {
		Preconditions.checkNotNull(name, "Null parameter not allowed here") ;
	
		Invocable inv = invocableMap.get(name);
		
		if (inv == null)
			throw new PyramidInfraException("Path is not supported for invocation - " + name) ;
		
		return inv.getInvocable();
	}
	
	public Invocable getInvocable(String name){
		return invocableMap.get(name);
	}

}
