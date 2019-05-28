package com.winj.service;

import java.util.Map;
import java.util.function.Consumer;

import org.pac4j.core.profile.CommonProfile;
import org.pac4j.core.profile.ProfileManager;
import org.pac4j.javalin.Pac4jContext;
import org.pac4j.jwt.config.signature.SecretSignatureConfiguration;
import org.pac4j.jwt.profile.JwtGenerator;

import io.javalin.Context;


/**
 * @author Edwin Jay Javier
 *
 */
public class AuthService {
	
	public static void authenticate(Context ctx, String salt, Consumer<Map<String, String>> handler) {
		
		var context = new Pac4jContext(ctx);
        var manager = new ProfileManager<CommonProfile>(context);
        var profile = manager.get(true);
        var token = "";
        if (profile.isPresent()) {
            var generator = new JwtGenerator<CommonProfile>(new SecretSignatureConfiguration(salt));
            token = generator.generate(profile.get());
        }
		
		handler.accept(Map.of("user", profile.get().getUsername() ,"token", token));
	}
	
}
