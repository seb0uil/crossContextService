package com.tportal.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * La classe serviceHandler sert de proxy au service.
 * Il gère le mécanisme de changement de classLoader et d'invocation
 * de la méthode attendu sur l'implémentation du service.
 * @author seb0uil
 *
 */
public class ServiceHandler implements InvocationHandler {
	ClassLoader targetServiceClassLoader;
	String serviceName;
	
	protected ServiceHandler(ClassLoader targetServiceClassLoader, String serviceName) {
		this.targetServiceClassLoader = targetServiceClassLoader;
		this.serviceName = serviceName;
	}
	
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		if (targetServiceClassLoader == null) {
			throw new Exception();
		}

		ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();

		try {
			Thread.currentThread().setContextClassLoader(targetServiceClassLoader);
			Class<?> webappServlet = targetServiceClassLoader.loadClass(serviceName);			
			Method getBeanMethod = webappServlet.getDeclaredMethod( method.getName(), method.getParameterTypes() );
			getBeanMethod.setAccessible(true);
			return  getBeanMethod.invoke(webappServlet.newInstance(), args);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Thread.currentThread().setContextClassLoader(currentClassLoader);
		}
		return null;
	}
}
