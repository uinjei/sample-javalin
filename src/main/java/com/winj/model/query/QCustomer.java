package com.winj.model.query;

import com.winj.model.Customer;
import io.ebean.Database;
import io.ebean.typequery.PLong;
import io.ebean.typequery.PString;
import io.ebean.typequery.TQRootBean;
import io.ebean.typequery.TypeQueryBean;

/**
 * Query bean for Customer.
 * 
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
@TypeQueryBean
public class QCustomer extends TQRootBean<Customer,QCustomer> {

  private static final QCustomer _alias = new QCustomer(true);

  /**
   * Return the shared 'Alias' instance used to provide properties to 
   * <code>select()</code> and <code>fetch()</code> 
   */
  public static QCustomer alias() {
    return _alias;
  }

  public PLong<QCustomer> id;
  public PString<QCustomer> name;


  /**
   * Construct with a given Database.
   */
  public QCustomer(Database database) {
    super(Customer.class, database);
  }

  /**
   * Construct using the default Database.
   */
  public QCustomer() {
    super(Customer.class);
  }

  /**
   * Construct for Alias.
   */
  private QCustomer(boolean dummy) {
    super(dummy);
  }
}
