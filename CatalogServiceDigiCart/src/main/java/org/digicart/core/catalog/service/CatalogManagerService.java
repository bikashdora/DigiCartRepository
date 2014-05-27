package org.digicart.core.catalog.service;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.digicart.common.currency.domain.DigiCartCurrency;
import org.digicart.common.currency.domain.DigiCartCurrencyImpl;
import org.digicart.core.catalog.domain.Category;
import org.digicart.core.catalog.domain.Product;
import org.digicart.core.catalog.domain.Sku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Path("/catalogService")
public class CatalogManagerService {

	@Autowired
	CatalogService catalogService;

	@Path("createCategory")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public void createCategory(@FormParam("categoryName") String categoryName,
			@FormParam("activeStartDate") Date activeStartDate,
			@FormParam("activeEndDate") Date activeEndDate,
			@FormParam("longDescription") String longDescription,
			@FormParam("defaultParentCategory") String defaultParentCategory) {
		// preconditions
		checkNotNull(categoryName);
		checkNotNull(activeStartDate);
		checkNotNull(activeEndDate);
		checkNotNull(longDescription);
		checkNotNull(defaultParentCategory);

		catalogService.createCategory(categoryName, activeStartDate,
				activeEndDate, longDescription, defaultParentCategory);
	}

	/* Get All Categories whose parent category is null */
	@Path("getAllParentCategories")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Category> readAllParentCategories() {
		// preconditions
		return catalogService.findAllParentCategories();
	}

	@Path("getCategoryByName")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Category> getCategory(
			@FormParam("categoryName") String categoryName) {
		// preconditions
		checkNotNull(categoryName);
		return catalogService.findCategoriesByName(categoryName);
	}

	@Path("getAllCategories")
	@GET	
	@Produces(MediaType.APPLICATION_JSON)
	public List<Category> getAllCategories() {

		return catalogService.findAllCategories();
	}

	@Path("getAllSubCategories")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Category> getAllSubCategories(
			@FormParam("categoryName") String categoryName) {
		// preconditions
		checkNotNull(categoryName);
		return catalogService.findAllSubCategories(categoryName);
	}

	@Path("getActiveSubCategoriesByCategory")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Category> getActiveSubCategoriesByCategory(
			@FormParam("categoryName") String categoryName) {
		// preconditions
		checkNotNull(categoryName);
		return catalogService.findAllSubCategories(categoryName);
	}
	
	@Path("removeCategory")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	
	public void removeCategory(
			@FormParam("categoryName") String categoryName) {
		// preconditions
		checkNotNull(categoryName);
		 catalogService.removeCategory(categoryName);
	}
	
	

	/*public Category saveCategory(Category category) {
		return categoryDao.save(category);
	}*/

	@Path("createProduct")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public void createProduct(@FormParam("productName") String productName,
			@FormParam("description") String description,
			@FormParam("longDescription") String longDescription,
			@FormParam("ActiveStartDate") Date activeStartDate,
			@FormParam("ActiveEndDate") Date activeEndDate,
			@FormParam("Manufacturer") String manufacturer,
			@FormParam("isFeaturedProduct") Boolean isFeaturedProduct,
			@FormParam("DefaultCategory") String defaultCategory,			
			@FormParam("Model") String model) {
		// preconditions
		checkNotNull(productName);
		checkNotNull(description);
		checkNotNull(longDescription);
		checkNotNull(activeStartDate);
		checkNotNull(activeEndDate);
		checkNotNull(manufacturer);
		checkNotNull(isFeaturedProduct);
		checkNotNull(defaultCategory);
		Sku sku = catalogService.createSku(activeStartDate, activeEndDate, true,
				 new DigiCartCurrencyImpl(),description,"NokiaXLMobileBlack",true);
		catalogService.createProduct(productName,description, longDescription, activeStartDate, activeEndDate, manufacturer, isFeaturedProduct, model, defaultCategory,sku);
		
		
	}
	
	@Path("getProductsByName")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public List <Product> getProductsByName(
			@FormParam("searchName") String searchName) {
		// preconditions
		checkNotNull(searchName);
		return catalogService.findProductsByName(searchName);
	}

	@Path("getActiveProductsByCategory")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public List <Product> getActiveProductsByCategory(
			@FormParam("categoryName") String categoryName) {
		// preconditions
		checkNotNull(categoryName);
		return catalogService.findActiveProductsByCategory(categoryName);
	}

	@Path("getAllProducts")
	@GET	
	@Produces(MediaType.APPLICATION_JSON)
	public List <Product> getAllProducts() {		
		return catalogService.findAllProducts();
	}

	@Path("getProductsForCategory")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_XML)
	public String  getProductsForCategory(
			@FormParam("categoryName") String categoryName) {
		// preconditions
		checkNotNull(categoryName);
		//Type productType = new TypeToken<List<Product>>() {}.getType();
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(catalogService.findProductsForCategory(categoryName));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		//return new Gson().toJson(catalogService.findProductsForCategory(categoryName),productType);
	}
	
	
	@Path("removeProduct")
	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public void removeProduct(
			@FormParam("productId") Long productId) {
		// preconditions
		checkNotNull(productId);
		try{
			 catalogService.removeProduct(productId);
		}
		
		catch(Exception e)
		{
			
		}
		
	}
	
	
	
	
	public void createSku(Date activeStartDate, Date activeEndDate, Boolean available,
			DigiCartCurrency currency, String description, String name,
			Boolean taxable) {
		// preconditions
		
	
				
		
	}
	
	
	
		
}