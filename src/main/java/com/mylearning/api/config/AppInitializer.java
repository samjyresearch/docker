package com.mylearning.api.config;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.web.context.ConfigurableWebApplicationContext;


public class AppInitializer implements ApplicationContextInitializer<ConfigurableWebApplicationContext> {


	   public void initialize(ConfigurableWebApplicationContext ctx) {
		try{
			MutablePropertySources sources = ctx.getEnvironment().getPropertySources();
			//PropertySource ps = new ResourcePropertySource(PROPERTIES_LOCATION,  getClass().getClassLoader());		
			//sources.addFirst(new ResourcePropertySource("mylearning.properties",  getClass().getClassLoader()));

		}catch(Exception e)
		{
		}
		
	}
}