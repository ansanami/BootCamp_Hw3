package org.javaturk.rest.greet.config;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class GreetingRestWebApplicationInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) {
//		System.out.println("Initializing ConverterWebApplicationInitializer.");

		// Load Spring web application configuration
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(AppConfig.class);
		//context.register(MappingJackson2HttpMessageConverter.class);

		// Manage the lifecycle of the root application context
		servletContext.addListener(new ContextLoaderListener(context));

		// Create and register the DispatcherServlet
		DispatcherServlet servlet = new DispatcherServlet(context);
		ServletRegistration.Dynamic registration = servletContext.addServlet("app", servlet);
		registration.setLoadOnStartup(1);
		registration.addMapping("/spring/*");
	}
}
