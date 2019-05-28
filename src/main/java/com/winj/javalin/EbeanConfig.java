package com.winj.javalin;

import org.avaje.agentloader.AgentLoader;

import io.ebean.typequery.generator.Generator;
import io.ebean.typequery.generator.GeneratorConfig;


/**
 * @author Edwin Jay Javier
 *
 */
public class EbeanConfig {
	public static void configure() {
		AgentLoader.loadAgentByMainClass("io.ebean.enhance.Transformer", "debug=0");
		
		var config = new GeneratorConfig();

	    config.setEntityBeanPackage("com.winj.model");
	    config.setDestPackage("com.winj.model.query");

	    config.setOverwriteExistingFinders(true);
	    var generator = new Generator(config);
	    try {
	    	generator.generateQueryBeans();
	    	generator.generateFinders();
		    generator.modifyEntityBeansAddFinderField();
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	}
}
