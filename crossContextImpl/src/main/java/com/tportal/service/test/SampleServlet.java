package com.tportal.service.test;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.tportal.service.ServiceTest;
import com.tportal.service.test.impl.TestImpl;

/**
 * Servlet implementation class SampleServlet
 */
public class SampleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public void init(ServletConfig config) throws ServletException {
    	ServiceTest.addService(ServiceTest.TEST, TestImpl.class.getName(), config.getServletContext());
    	config.getServletContext().log("Init addService");
    }
    
}
