package com.winj.javalin.controller;

import com.winj.service.CustomerService;

import io.javalin.Context;


/**
 * @author Edwin Jay Javier
 *
 */
public class CustomerController {
	public static void findAll(Context ctx) {
		CustomerService.findAll(list -> ctx.json(list));
	}
}
