package com.springjdbcWithWebServices.SpringJdbcWithWebservices;
import java.util.List;//http://localhost:8080/SpringJdbcWithWebservices/webapi/customers

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Path("customers")
public class MainApp {
  
      ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

      CustomerJDBCTemplate customerJDBCTemplate =(CustomerJDBCTemplate)context.getBean("customerJDBCTemplate");
      
     // ------Records Creation--------
      @POST
      @Path("newcustomer")
      @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
      @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
      public Customer createCustomer(Customer customer) {
      customerJDBCTemplate.create(customer.getName(),customer.getAge() );
      return customer;
      }    
      
      
      
      
      
      /*------Listing Multiple Records--------*/
      @GET
      @Path("readallcustomers")
      @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
      public List<Customer> readCustomers(){
      List<Customer> customers = customerJDBCTemplate.listCustomers();
      return customers;
      }
      
      
      /*-------Listing a single customer-----*/
      @GET
      @Path("readacustomer/{id}")
      @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
      public Customer readCustomer( @PathParam("id") int id){
      Customer customer = customerJDBCTemplate.getCustomer(id);
      return customer;
      }
      
      

      /*----Updating Record with ID = ? -----*/
      @PUT
      @Path("updatecustomer")
      @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
      @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
      public Customer updateCustomer(Customer customer) {
      customerJDBCTemplate.update(customer.getId(), customer.getAge());
      Customer updatedcustomer = customerJDBCTemplate.getCustomer(customer.getId());
      return updatedcustomer;
      }
      
      @DELETE
      @Path("deletecustomer")
      @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
      @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
      public Customer deleteCustomer(Customer delcustomer) {
    	  customerJDBCTemplate.delete(delcustomer.getId());
    	  return delcustomer;
      }
      
      
   }
