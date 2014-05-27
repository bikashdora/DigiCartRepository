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

public class Tester1 {
	public static void main(String[] args) {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(getBaseURI());

		// create a Todo
		Form form = new Form();
		form.clear();
		form.add("productName", "NokiaXL");
		form.add("description", "New Nokia Windows Phone");
		form.add("longDescription",
				"New Nokia Windows Phone having portability for Androids Apps");
		form.add("ActiveStartDate", "2/6/2014");
		form.add("ActiveEndDate", "3/7/2014");
		form.add("Manufacturer", "Nokia");
		form.add("isFeaturedProduct", "Yes");
		form.add("DefaultCategory", "Default");
		form.add("Model", "NokiaXL123");

		ClientResponse response = service.path("createProduct")
				.type(MediaType.APPLICATION_FORM_URLENCODED)
				.post(ClientResponse.class, form);

		form.clear();
		form.add("categoryName", "Default");
		response = service.path("getProductsForCategory")
				.type(MediaType.APPLICATION_FORM_URLENCODED)
				.post(ClientResponse.class, form);
		System.out.println("Form response " + response.getEntity(String.class));

	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri(
				"http://localhost:8080/CatalogService/service/catalogService")
				.build();
	}
}