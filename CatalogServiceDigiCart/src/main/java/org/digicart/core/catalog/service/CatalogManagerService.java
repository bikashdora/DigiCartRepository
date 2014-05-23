package org.digicart.core.catalog.service;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.digicart.core.catalog.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
		
		catalogService.createCategory(categoryName,activeStartDate,activeEndDate,longDescription,defaultParentCategory);
	}
	/*Get All Categories whose parent category is null*/
	@Path("getAllParentCategories")
	@GET	
	@Produces(MediaType.APPLICATION_JSON)
	public List<Category>  readAllParentCategories()  {
		// preconditions			
		return catalogService.findAllParentCategories();
	}
	
	
	@Path("getCategoryByName")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Category> getCategory(@FormParam("categoryName") String categoryName) {
		// preconditions
		checkNotNull(categoryName);		
		return catalogService.findCategoriesByName(categoryName);
	}
	
	@Path("getAllCategories")
	@GET
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Category> getAllCategories() {
			
		return catalogService.findAllCategories();
	}	
	
	@Path("getAllSubCategories")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Category> getAllSubCategories(@FormParam("categoryName") String categoryName) {
		// preconditions
		checkNotNull(categoryName);		
		return catalogService.findAllSubCategories(categoryName);
	}
	
	@Path("getActiveSubCategoriesByCategory")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Category> getActiveSubCategoriesByCategory(@FormParam("categoryName") String categoryName) {
		// preconditions
		checkNotNull(categoryName);		
		return catalogService.findAllSubCategories(categoryName);
	}
	
	
}