package org.javaturk.rest.greet.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.*;

@Configuration
@ComponentScan({ "org.javaturk.rest.greet.resource", "org.javaturk.rest.greet.repo" })
public class AppConfig {

	@Bean
	public MappingJackson2HttpMessageConverter jsonMessageConverter() {
		return new MappingJackson2HttpMessageConverter();
	}
	
	@Bean
	public MappingJackson2XmlHttpMessageConverter xmlMessageConverter() {
		return new MappingJackson2XmlHttpMessageConverter();
	}
	
	

	/**
	 * Not to have: org.springframework.http.converter.HttpMessageNotWritableException: No converter found for return value
	 * of type: class [Lorg.javaturk.springMvc.temperatureConverter.domain.TemperatureType]
	 * 
	 * In case of web.xml:
	 * 
	 * <!-- Configure to plugin JSON as request and response in method handler -->
	 * <beans:bean class= "org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter">
	 * <beans:property name="messageConverters"> <beans:list> <beans:ref bean="jsonMessageConverter"/> </beans:list>
	 * </beans:property> </beans:bean>
	 * 
	 * <!-- Configure bean to convert JSON to POJO and vice versa --> <beans:bean id="jsonMessageConverter" class=
	 * "org.springframework.http.converter.json.MappingJackson2HttpMessageConverter"> </beans:bean>
	 * 
	 * @return
	 */
	@Bean
	public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
		RequestMappingHandlerAdapter adapter = new RequestMappingHandlerAdapter();
		List list = new ArrayList();
		list.add(jsonMessageConverter());
		list.add(xmlMessageConverter());
		adapter.setMessageConverters(list);
		return adapter;
	}

//	@Bean
//	public MappingJackson2HttpMessageConverter customJson() {
//		return new MappingJackson2HttpMessageConverter(new Jackson2ObjectMapperBuilder().indentOutput(true)
//				.serializationInclusion(JsonInclude.Include.NON_NULL).propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE).build());
//	}

}
