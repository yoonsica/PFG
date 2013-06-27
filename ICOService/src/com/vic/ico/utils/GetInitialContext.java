package com.vic.ico.utils;

import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class GetInitialContext {
	public static InitialContext getContext(){
		Properties props = new Properties();
        props.setProperty("java.naming.factory.initial",
                      "org.jnp.interfaces.NamingContextFactory");
        props.setProperty("java.naming.provider.url", "localhost:1099");
        props.setProperty("java.naming.factory.url.pkgs", "org.jboss.naming");
        InitialContext ctx=null;
		try {
			ctx = new InitialContext(props);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return ctx;
	}
}
