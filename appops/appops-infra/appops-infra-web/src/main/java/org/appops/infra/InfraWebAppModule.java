package org.appops.infra;

import com.google.inject.servlet.ServletModule;

public class InfraWebAppModule extends ServletModule  {
	
	 @Override
     protected void configureServlets() {
       serve("/infraEntryPoint/*").with(WebAppEntryPoint.class);
     }

}
