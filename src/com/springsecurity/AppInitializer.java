package com.springsecurity;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// pointing our securityconfig class from here
		return new Class[] {SecurityConfig.class}; //new is for creating the class array and then passing
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// pointing our servletconfig class from here
		return new Class[] {ServletConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		String[] string = new String[]{"/"};	//anonymously adding "/" to the str obj 
		return string;
	}

}
