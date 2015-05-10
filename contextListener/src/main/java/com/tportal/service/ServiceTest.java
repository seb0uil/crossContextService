package com.tportal.service;

/**
 * Exemple de service
 * @author seb0ui
 *
 */
public class ServiceTest extends Service<Test> {
	
	/**
	 * Nom du service implémenté
	 */
	public static final String TEST = "test";

	/**
	 * 
	 */
	static Service serviceTest;
	
	/**
	 * Récupération du singleton
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
	 * Réinitialise le singleton, nécessaire afin de changer l'implémentation
	 * de celui-ci
	 */
	public static void init() {
		serviceTest = null;
	}
}
