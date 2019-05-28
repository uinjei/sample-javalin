package com.winj.service;

import java.util.List;
import java.util.function.Consumer;

import com.winj.model.Customer;
import com.winj.model.finder.CustomerFinder;


/**
 * @author Edwin Jay Javier
 *
 */
public class CustomerService {
	
	private final static CustomerFinder find = Customer.find;
	
	public static void findAll(Consumer<List<Customer>> handler) {
		handler.accept(find.all());
	}
	
	public static void findOne(Long id, Consumer<Customer> handler) {
		handler.accept(find.byId(id));
	}
}
