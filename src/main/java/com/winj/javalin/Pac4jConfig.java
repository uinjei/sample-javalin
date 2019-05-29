package com.winj.javalin;

import org.pac4j.core.client.Clients;
import org.pac4j.core.config.Config;
import org.pac4j.core.config.ConfigFactory;
import org.pac4j.core.context.HttpConstants;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.credentials.UsernamePasswordCredentials;
import org.pac4j.core.credentials.authenticator.Authenticator;
import org.pac4j.core.exception.CredentialsException;
import org.pac4j.core.profile.CommonProfile;
import org.pac4j.http.client.direct.DirectBasicAuthClient;
import org.pac4j.http.client.direct.HeaderClient;
import org.pac4j.javalin.DefaultHttpActionAdapter;
import org.pac4j.javalin.Pac4jContext;
import org.pac4j.jwt.config.signature.SecretSignatureConfiguration;
import org.pac4j.jwt.credentials.authenticator.JwtAuthenticator;

import io.javalin.ForbiddenResponse;
import io.javalin.UnauthorizedResponse;


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
	
	private class BasicAuthenticator implements Authenticator<UsernamePasswordCredentials> {

	    @Override
	    public void validate(UsernamePasswordCredentials credentials, WebContext context) {
	        if (credentials.getUsername().equals(credentials.getPassword())) {
	            var profile = new CommonProfile();
	            profile.setId(credentials.getUsername());
	            profile.addAttribute("username", credentials.getUsername());
	            credentials.setUserProfile(profile);
	        } else {
	            throw new CredentialsException("Invalid credentials");
	        }
	    }

	}
	
	private class HttpActionAdapter extends DefaultHttpActionAdapter {

	    @Override
	    public Void adapt(int code, Pac4jContext context) {
	        if (code == HttpConstants.UNAUTHORIZED) {
	            throw new UnauthorizedResponse();
	        } else if (code == HttpConstants.FORBIDDEN) {
	            throw new ForbiddenResponse();
	        } else {
	            return super.adapt(code, context);
	        }
	    }
	}
}
