package com.springjdbcWithWebServices.SpringJdbcWithWebservices;

import java.util.List;
import javax.sql.DataSource;

public interface CustomerDAO {
  
  // public void setDataSource(DataSource ds);
  
   public void create(String name, Integer age);
   
  
   public Customer getCustomer(int id);
   
 
   public List<Customer> listCustomers();
   
 
   public void delete(Integer id);
   
  
   public void update(Integer id, Integer age);
}
