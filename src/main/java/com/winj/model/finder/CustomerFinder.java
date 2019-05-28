package com.winj.model.finder;

import com.winj.model.Customer;
import com.winj.model.query.QCustomer;
import io.ebean.Finder;

public class CustomerFinder extends Finder<Long,Customer> {

  /**
   * Construct using the default Database.
   */
  public CustomerFinder() {
    super(Customer.class);
  }


  /**
   * Start a new typed query.
   */
  protected QCustomer where() {
     return new QCustomer(db());
  }

  /**
   * Start a new document store query.
   */
  protected QCustomer text() {
     return new QCustomer(db()).text();
  }
}
