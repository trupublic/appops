package org.appops.infra.core.annotations;

public @interface Operation {
	
	/**
	 * @return defaults to the name of operation itself. Do provide an alternate name incase the method is overloaded
	 */
	public String name() default "" ; 
	
}
