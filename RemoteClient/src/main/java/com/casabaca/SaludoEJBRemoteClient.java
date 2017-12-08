package com.casabaca;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class SaludoEJBRemoteClient {
	public static void main (String... vars) {
		try {
			printSaludo ();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void printSaludo () throws NamingException {
		SaludoEJBRemote ejbRemote = lookupRemoteEJB();
		System.out.println(ejbRemote.saludar("Galo"));
	}
	
	 

	private static SaludoEJBRemote lookupRemoteEJB() throws NamingException {
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

		final Context context = new InitialContext(jndiProperties);

		final String appName = "";
		final String moduleName = "ejb-remote-test-0.0.1-SNAPSHOT";
		final String distinctName = "";
		final String beanName = SaludoEJB.class.getSimpleName();
		final String viewClassName = SaludoEJBRemote.class.getName();
		System.out.println("Looking EJB via JNDI ");
		System.out.println(
				"ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);

		return (SaludoEJBRemote) context.lookup(
				"ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);

	}
}
