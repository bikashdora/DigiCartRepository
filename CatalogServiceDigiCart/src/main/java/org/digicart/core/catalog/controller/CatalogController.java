package org.digicart.core.catalog.controller;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;
import org.digicart.core.catalog.domain.Sku;
import org.digicart.core.catalog.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.democommerce.common.vendor.service.exception.PaymentException;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * BooksController class
 */
@Controller
public class CatalogController {
	
	
	private static final Logger logger = Logger.getLogger(CatalogController.class);
	@Autowired
	CatalogService catalogService;

	
	 @RequestMapping(value = "/login", method = RequestMethod.GET)
	    public String login(ModelMap model) {
	        return "login";
	    }
	 
	    @RequestMapping(value = "/home", method = RequestMethod.GET)
	    public String getHome(ModelMap model) {
	        return "home";
	    }
	    
	    @RequestMapping(value = "/", method = RequestMethod.GET)
	    public String getHomePage(ModelMap model) {
	        return "home";
	    }
	 
	    @RequestMapping(value = "/logout", method = RequestMethod.GET)
	    public String logout(ModelMap model) {
	        return "logout";
	    }
	    
	    @RequestMapping(value="getDefaultSkusProductsForCategory/{categoryName}",method = RequestMethod.GET)
	    public @ResponseBody List<Sku> updateBook(@PathVariable("categoryName") String categoryName)
	    {			
			// preconditions
			checkNotNull(categoryName);
			logger.debug("categoryName"+categoryName);
			return catalogService.findDefaultSkusProductsForCategory(categoryName);
			}
	 
	
	    
	    
	    /*resam added*/
	    
	  /*  @RequestMapping(value = "/CreditCard", method = RequestMethod.GET)
	    public String creditCardpage(Model model) throws PaymentException {
	        return "CreditCardHome";
	    }
	    
	    @RequestMapping(value = "/confirmation", method = RequestMethod.GET)
	    public String creditCardPaymentSuccessful(Model model) throws PaymentException {
	        return "success";
	    }*/
	    
	
}
