package org.appops.infra.core;

/**
 * Implement this interface to define your application entry point
 * @author Debasish.Padhy
 *
 */
public interface AppOpsApplication {

	
	/**
	 * Defines your application entry point method 
	 * 
	 * @return error code
	 */
	public void runApplication(String[] args)throws AppOpsInfraException;

	public void stopApplication(String[] args);

}
