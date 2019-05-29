package com.winj.javalin;

import org.avaje.agentloader.AgentLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.ebean.Ebean;


/**
 * @author Edwin Jay Javier
 *
 */
public class EbeanBootstrap {
	
	private static final Logger log = LoggerFactory.getLogger(EbeanBootstrap.class);
	
	public EbeanBootstrap() {
		
		log.info("Starting Ebean...");
		
		AgentLoader.loadAgentByMainClass("io.ebean.enhance.Transformer", "debug=0");
		
		final var db = Ebean.getDefaultServer();
		db.createSqlQuery("/* EbeanHealthCheck */ SELECT 1 ").findOne();

	}

}
