package com.winj.javalin;

import org.pac4j.core.client.Clients;
import org.pac4j.core.config.Config;
import org.pac4j.core.config.ConfigFactory;
import org.pac4j.http.client.direct.DirectBasicAuthClient;
import org.pac4j.http.client.direct.HeaderClient;
import org.pac4j.jwt.config.signature.SecretSignatureConfiguration;
import org.pac4j.jwt.credentials.authenticator.JwtAuthenticator;


/**
 * @author Edwin Jay Javier
 *
 */
public class Pac4jConfig implements ConfigFactory {
	
	 private String salt;
	
	 public Pac4jConfig(String  salt) {
		 this.salt = salt;
	 }
	 
	@Override
	public Config build(Object... parameters) {
		
		var headerClient = new HeaderClient("Token", new JwtAuthenticator(new SecretSignatureConfiguration(salt)));
		var directBasicAuthClient = new DirectBasicAuthClient(new BasicAuthenticator());
		var clients = new Clients(headerClient, directBasicAuthClient);
		var config = new Config(clients);
		
		//config.addAuthorizer("admin", new RequireAnyRoleAuthorizer("ROLE_ADMIN"));
		config.setHttpActionAdapter(new HttpActionAdapter());
		return config;
	}
}
