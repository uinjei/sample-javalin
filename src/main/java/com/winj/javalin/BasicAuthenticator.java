package com.winj.javalin;

import org.pac4j.core.context.WebContext;
import org.pac4j.core.credentials.UsernamePasswordCredentials;
import org.pac4j.core.credentials.authenticator.Authenticator;
import org.pac4j.core.exception.CredentialsException;
import org.pac4j.core.profile.CommonProfile;


/**
 * @author Edwin Jay Javier
 *
 */
public class BasicAuthenticator implements Authenticator<UsernamePasswordCredentials> {

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
