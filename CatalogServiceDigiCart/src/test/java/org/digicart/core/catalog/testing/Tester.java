package org.digicart.core.catalog.testing;

import java.net.URI;
import java.util.Date;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.representation.Form;



public class Tester {
	  public static void main(String[] args) {
	    ClientConfig config = new DefaultClientConfig();
	    Client client = Client.create(config);
	    WebResource service = client.resource(getBaseURI());
	       
	    // create a Todo
	    Form form = new Form();
	  
	    
	   // String jsonRes= service.accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, form);
	 
	 		form.clear();
			form.add("categoryName", "Default");
			form.add("activeStartDate", "2/5/2014");
			form.add("activeEndDate", "2/6/2014");
			form.add("longDescription", "Categories of Default Items");
			form.add("defaultParentCategory", "Mobile");
			
		    ClientResponse response = service.path("createCategory")
		        .type(MediaType.APPLICATION_FORM_URLENCODED)
		        .post(ClientResponse.class, form);		     
		    System.out.println("Form response " + response.getEntity(String.class));
	    
		  
			
		    
	  }
	  
	  

	  private static URI getBaseURI() {
	    return UriBuilder.fromUri("http://localhost:8080/CatalogService/service/catalogService").build();
	  }
	} 