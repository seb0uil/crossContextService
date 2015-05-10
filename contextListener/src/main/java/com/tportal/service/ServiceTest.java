package com.tportal.service;

/**
 * Exemple de service
 * @author seb0ui
 *
 */
public class ServiceTest extends Service<Test> {
	
	/**
	 * Nom du service impl�ment�
	 */
	public static final String TEST = "test";

	/**
	 * 
	 */
	static Service serviceTest;
	
	/**
	 * R�cup�ration du singleton
	 * @return L'interface Test
	 */
	synchronized public static Test getInstance() {
		if (serviceTest == null) {
			serviceTest = new ServiceTest();
			serviceTest.setClass(Test.class, TEST);
		}
		return (Test) serviceTest.getService();
	}
	
	/**
	 * R�initialise le singleton, n�cessaire afin de changer l'impl�mentation
	 * de celui-ci
	 */
	public static void init() {
		serviceTest = null;
	}
}
