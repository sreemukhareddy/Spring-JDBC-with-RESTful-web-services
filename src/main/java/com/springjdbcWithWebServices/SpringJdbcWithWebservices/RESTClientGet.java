package com.springjdbcWithWebServices.SpringJdbcWithWebservices;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RESTClientGet {
	
		
	public static void main(String[] args) {
		
	
	Client client=ClientBuilder.newClient();
	
	
	Response response=client.target("http://localhost:8080/SpringJdbcWithWebservices/webapi/customers/readallcustomers").request("application/json").get();
	System.out.println(response);
	
//	RestServicePathParam rsp=response.readEntity(RestServicePathParam.class);
	System.out.println(response.readEntity(String.class));
}
}
