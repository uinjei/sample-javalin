package com.winj.javalin;

import static com.winj.service.AuthService.authenticate;
import static io.javalin.apibuilder.ApiBuilder.before;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.post;

import java.util.Map;

import org.pac4j.javalin.SecurityHandler;

import com.winj.javalin.controller.CustomerController;

import io.javalin.Javalin;
import io.javalin.NotFoundResponse;

/**
 * @author Edwin Jay Javier
 *
 */
public class App {
	
	public static void main(String[] args) {
		
		final var salt = "12345678901234567890123456789012";
		var config = new Pac4jConfig(salt).build();
		
		/** bootstrap */
		new EbeanBootstrap();
		
        var app = Javalin.create()
        	.port(7000)
        	.routes(() -> {
        		
        		before("/login", new SecurityHandler(config, "DirectBasicAuthClient"));
        		post("/login", ctx -> authenticate(ctx, salt, profile -> ctx.json(profile)));
        		
        		before("/api.*", new SecurityHandler(config, "HeaderClient"));
	        	get("/api/customers", CustomerController::findAll);
	        	
        	}).exception(NotFoundResponse.class, (e, ctx) -> 
        		ctx.json(Map.of("status", e.getStatus(), "error", e.getMessage())))
        	.exception(NotFoundResponse.class, (e, ctx) -> 
        		ctx.json(Map.of("status", e.getStatus(), "error", e.getMessage())))
        	.start();
        
        Runtime.getRuntime().addShutdownHook(new Thread(() -> app.stop()));

    }
}
