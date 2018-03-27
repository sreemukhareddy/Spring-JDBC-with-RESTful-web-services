package com.springjdbcWithWebServices.SpringJdbcWithWebservices;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class CustomerJDBCTemplate implements CustomerDAO {
   private DataSource dataSource;
   private JdbcTemplate jdbcTemplateObject;
   private NamedParameterJdbcTemplate namedparemeterjdbctemplate;
   
   public CustomerJDBCTemplate(DataSource datasource,NamedParameterJdbcTemplate namedparameterjdbctemplate) {
	   this.dataSource=datasource;
	   this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	   this.namedparemeterjdbctemplate=namedparameterjdbctemplate;
   }
   
  /* public void setDataSource(DataSource dataSource) {
      this.dataSource = dataSource;
      this.jdbcTemplateObject = new JdbcTemplate(dataSource);
   }*/
   public void create(String name, Integer age) {
	   
	   // the below code is for namedparameter jdbc template
	   String SQL = "insert into customer (name, age) values ( :name, :age)";
	   Map namedparameters=new HashMap();
	   namedparameters.put("name", name);
	   namedparameters.put("age",age);
	   namedparemeterjdbctemplate.update(SQL, namedparameters);
	   System.out.println("created");
	   return ;
	   
	   // the below code is for normal jdbc template
	   
	   /* String SQL = "insert into customer (name, age) values (?, ?)";
      jdbcTemplateObject.update( SQL, name, age);
      System.out.println("Created Record Name = " + name + " Age = " + age);
      return;*/
   }
   public Customer getCustomer(int id) {
	   
	  // the below code is for namedparameter jdbc template
	   
	   String SQL = "SELECT * FROM customer WHERE id= :id";
	   SqlParameterSource namedParameters = new MapSqlParameterSource("id", Integer.valueOf(id));
	Customer customer = (Customer) namedparemeterjdbctemplate.queryForObject(SQL, namedParameters, new CustomerMapper());
	 return customer;
	   
	   
	   
	  // the below code is for normal jdbc template
	   
     /* String SQL = "select * from customer where id = ?";
      Customer customer = jdbcTemplateObject.queryForObject(SQL,new Object[]{id}, new CustomerMapper());
      
      return customer;*/
   }
   public List<Customer> listCustomers() {
	   
	  // the below code is for namedparametet jdbc template
	   
	   String SQL = "SELECT * FROM customer";
	   List<Customer> customers =  namedparemeterjdbctemplate.query(SQL, new CustomerMapper());
	     return customers;
	   
	  // the below code is for normal jdbc template
	   
      /*String SQL = "select * from customer";
      List <Customer> customers = jdbcTemplateObject.query(SQL, new CustomerMapper());
      return customers;*/
   }
   public void delete(Integer id) {
	   
	   String SQL="delete from customer where id= :id";
	   SqlParameterSource parameters=new MapSqlParameterSource("id", Integer.valueOf(id));
	   namedparemeterjdbctemplate.update(SQL, parameters);
	   
	  // the below code is for normal jdbc template
	   
     /* String SQL = "delete from customer where id = ?";
      jdbcTemplateObject.update(SQL, id);
      System.out.println("Deleted Record with ID = " + id );
      return;*/
   }
   public void update(Integer id, Integer age){
	   
	   String SQL="update customer set age= :age where id= :id";
	   SqlParameterSource parameters = new MapSqlParameterSource();
	   ((MapSqlParameterSource) parameters).addValue("age", age);
	   ((MapSqlParameterSource) parameters).addValue("id", id);
	   namedparemeterjdbctemplate.update(SQL, parameters);
	   
	   
	   
	  // the below code is for normal jdbc template
	   
     /* String SQL = "update customer set age = ? where id = ?";
      jdbcTemplateObject.update(SQL, age, id);
      System.out.println("Updated Record with ID = " + id );
      return;*/
   }

}