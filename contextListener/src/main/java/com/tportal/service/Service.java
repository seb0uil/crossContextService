package com.tportal.service;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

/**
 * la classe Service sert de classe parente aux diff�rentes impl�mentations pour
 * les services (cf. ServiceTest.java).
 * Celle-ci, � partir de la map {@link #services}, r�cup�re les �l�ments n�cessaires � la constitution
 * du proxy pour l'appel de l'impl�mentation du service.
 * @author seb0uil
 *
 * @param <T>
 */
public class Service<T> {

	private T service;
	
	/**
	 * Map de correspondance entre les noms des service et les serviceLoader.
	 * Chaque service doit �tre enregistr� pour �tre accessible, et une entr�e est donc
	 * cr��e dans la map.
	 */
	private static Map<String, ServiceLoader> services = new HashMap<String, ServiceLoader>();
	
	/**
	 * D�finie l'interface du service et le nom de celui-ci
	 * Le nom permet de retrouver l'impl�mentation et le context de l'interface
	 * @param clazz
	 * @param serviceName
	 */
	public void setClass(Class<T> clazz, String serviceName) {
		if (service == null) {
				service = getProxy(clazz, serviceName);
		}
	}

	/**
	 * Retourne le service attendu
	 * @return
	 */
	public T getService() {
		return service;
	}

	
	/**
	 * G�n�re le proxy pour acc�der � l'impl�mentation attendu du service
	 * @param clazz interface du service
	 * @param serviceName
	 * @return Un proxy � destination de l'impl�mentation du service
	 */
	@SuppressWarnings("unchecked")
	public T getProxy(Class<T> clazz, String serviceName) {
		T service = (T) Proxy.newProxyInstance(
				Service.class.getClassLoader(),
				new Class[] { clazz },
				new ServiceHandler(services.get(serviceName).getTargetServiceClassLoader(),
						services.get(serviceName).getClassName())
				);
		return service;
	}

	/**
	 * Enregistre un service et son impl�mentation
	 * @param serviceName
	 * @param className
	 * @param servletContext
	 */
	public static void addService(String serviceName, String className, ServletContext servletContext) {
		ServiceLoader sl = new ServiceLoader(className, servletContext);
		services.put(serviceName, sl);
	}

	/**
	 * ServiceLoader utilis� pour stocker la classe impl�mentant le service ainsi que le context
	 * dans lequel cette impl�mentation est disponible.
	 * @author SBE10599
	 *
	 */
	private static class ServiceLoader {
		/**
		 * fqdn de la classe impl�mentant le service
		 */
		String className;
		/**
		 * classLoader dans lequel {@link #className} est accessible
		 */
		ClassLoader targetServiceClassLoader;
		
		ServiceLoader(String className, ServletContext servletContext ) {
			this.className = className;
			targetServiceClassLoader = servletContext.getContext(servletContext.getContextPath()).getClassLoader();
		}

		/* Getter */
		public String getClassName() {
			return className;
		}

		public ClassLoader getTargetServiceClassLoader() {
			return targetServiceClassLoader;
		}
	}
}
