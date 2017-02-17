package org.appops.infra.core;

import java.util.Set;

import com.google.inject.Module;

/**
 * Implement this interface to define your application entry point
 * @author Debasish.Padhy
 *
 */
public interface PyramidApplication {

	
	/**
	 * Defines your application entry point method 
	 * 
	 * @return error code
	 */
	public void runApplication(String[] args)throws PyramidInfraException;

	public void stopApplication(String[] args);

}
