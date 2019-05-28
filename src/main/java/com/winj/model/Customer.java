package com.winj.model;

import com.winj.model.finder.CustomerFinder;
import javax.persistence.Entity;
import javax.persistence.Id;

import io.ebean.Model;

@Entity
public class Customer extends Model {

	public static final CustomerFinder find = new CustomerFinder();

	@Id
	long id;

	String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
