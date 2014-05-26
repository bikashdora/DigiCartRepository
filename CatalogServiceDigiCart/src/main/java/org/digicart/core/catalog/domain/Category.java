package org.digicart.core.catalog.domain;

/*
 * Category Interface.
 * Category is a Group of Products.
 * Implementation Class is {@CategoryImpl} 
 * 
 */


import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;



public interface Category extends Serializable {

    /**
     * Gets the primary key.
     * 
     * @return the primary key
     */
    
    public Long getId();

    /**
     * Sets the primary key.
     * 
     * @param id the new primary key
     */
    public void setId( Long id);

    /**
     * Gets the name.
     * 
     * @return the name
     */
   
    public String getName();

    /**
     * Sets the name.
     * 
     * @param name the new name
     */
    public void setName( String name);

    /**
     * Gets the default parent category.
     * 
     * @return the default parent category
     */
   
    public Category getDefaultParentCategory();

    /**
     * Sets the default parent category.
     * 
     * @param defaultParentCategory the new default parent category
     */
    public void setDefaultParentCategory( Category defaultParentCategory);

    /**
     * Gets the url. The url represents the presentation layer destination for
     * this category. For example, if using Spring MVC, you could send the user
     * to this destination by returning {@code "redirect:"+currentCategory.getUrl();}
     * from a controller.
     * 
     * @return the url for the presentation layer component for this category
     */
   
    public String getUrl();
    
    /**
     * Creates the SEO url starting from this category and recursing up the
     * hierarchy of default parent categories until the topmost category is
     * reached. The url key for each category is used for each segment
     * of the SEO url.
     * 
     * @return the generated SEO url for this category
     */
    public String getGeneratedUrl();

    /**
     * Sets the url. The url represents the presentation layer destination for
     * this category. For example, if using Spring MVC, you could send the user
     * to this destination by returning {@code "redirect:"+currentCategory.getUrl();}
     * from a controller.
     * 
     * @param url the new url for the presentation layer component for this category
     */
    public void setUrl( String url);
    
    /**
     * Gets the url key. The url key is used as part of SEO url generation for this
     * category. Each segment of the url leading to a category is comprised of the url
     * keys of the various associated categories in a hierarchy leading to this one. If
     * the url key is null, the the name for the category formatted with dashes for spaces.
     * 
     * @return the url key for this category to appear in the SEO url
     */    
    public String getUrlKey();
    
    /**
     * Sets the url key. The url key is used as part of SEO url generation for this
     * category. Each segment of the url leading to a category is comprised of the url
     * keys of the various associated categories in a hierarchy leading to this one.
     * 
     * @param urlKey the new url key for this category to appear in the SEO url
     */
    public void setUrlKey(String urlKey);

    /**
     * Gets the description.
     * 
     * @return the description
     */
   
    public String getDescription();

    /**
     * Sets the description.
     * 
     * @param description the new description
     */
    public void setDescription( String description);

    /**
     * Gets the active start date. If the current date is before activeStartDate,
     * then this category will not be visible on the site.
     * 
     * @return the active start date
     */
   
    public Date getActiveStartDate();

    /**
     * Sets the active start date. If the current date is before activeStartDate,
     * then this category will not be visible on the site.
     * 
     * @param activeStartDate the new active start date
     */
    public void setActiveStartDate( Date activeStartDate);

    /**
     * Gets the active end date. If the current date is after activeEndDate,
     * the this category will not be visible on the site.
     * 
     * @return the active end date
     */
   
    public Date getActiveEndDate();

    /**
     * Sets the active end date. If the current date is after activeEndDate,
     * the this category will not be visible on the site.
     * 
     * @param activeEndDate the new active end date
     */
    public void setActiveEndDate( Date activeEndDate);

    /**
     * Checks if is active. Returns true if the startDate is null or if the current
     * date is after the start date, or if the endDate is null or if the current date
     * is before the endDate.
     * 
     * @return true, if is active
     */
    public boolean isActive();

    /**
     * Gets the display template. The display template can be used to help create a unique key
     * that drives the presentation layer destination for this category. For example, if
     * using Spring MVC, you might derive the view destination in this way:
     *
     * {@code view = categoryTemplatePrefix + currentCategory.getDisplayTemplate();}
     * 
     * @return the display template
     */
   
    public String getDisplayTemplate();

    /**
     * Sets the display template. The display template can be used to help create a unique key
     * that drives the presentation layer destination for this category. For example, if
     * using Spring MVC, you might derive the view destination in this way:
     *
     * {@code view = categoryTemplatePrefix + currentCategory.getDisplayTemplate();}
     * 
     * @param displayTemplate the new display template
     */
    public void setDisplayTemplate( String displayTemplate);

    /**
     * Gets the child category url map. This map is keyed off of the {@link #getGeneratedUrl()} values
     * for this category and all of its child categories. By calling get on this map using the
     * generated url for a given category, you will receive the list of immediate child categories.
     * This is inefficient, so its use is highly discouraged.
     *
     * @return the child category url map
     * @deprecated This approach is inherently inefficient and should no longer be used
     */
    
   

   

    /**
     * Gets the long description.
     * 
     * @return the long description
     */
   
    public String getLongDescription();

    /**
     * Sets the long description.
     * 
     * @param longDescription the new long description
     */
    public void setLongDescription( String longDescription);    
    /**
     * Build category hierarchy by walking the default category tree up to the root category.
     * If the passed in tree is null then create the initial list.
     * 
     * @param currentHierarchy
     * @return
     */
    
    public List<Category> buildCategoryHierarchy(List<Category> currentHierarchy);
    
    /**
     * Build the full category hierarchy by walking up the default category tree and the all parent
     * category tree.
     * 
     * @param currentHierarchy
     * @return the full hierarchy
     */
    public List<Category> buildFullCategoryHierarchy(List<Category> currentHierarchy);


    /**
     * Gets the child category url map. This map is keyed off of the {@link #getGeneratedUrl()} values
     * for this category and all of its child categories. By calling get on this map using the
     * generated url for a given category, you will receive the list of immediate child categories.
     * This is inefficient, so its use is highly discouraged.
     *
     * @return the child category url map
     * @deprecated This approach is inherently inefficient and should no longer be used
     */
    @Deprecated
    public Map<String,List<Long>> getChildCategoryURLMap();   

    /**
     * Gets the child categories. This list includes all categories, regardless
     * of whether or not they are active.
     *
     * @deprecated use getAllChildCategoryXrefs() instead.
     * @return the list of active and inactive child categories.
     */
   
    public List<Category> getAllChildCategories();

    /**
     * Checks for child categories.
     *
     * @return true, if this category has any children (active or not)
     */
    public boolean hasAllChildCategories();

    /**
     * Sets the list of child categories (active and inactive)
     *
     * @deprecated Use setAllChildCategoryXrefs() instead.
     * @param childCategories the list of child categories
     */
    public void setAllChildCategories( List<Category> childCategories);

    /**
     * Gets the child categories. If child categories has not been previously
     * set, then the list of active only categories will be returned.
     *
     * @deprecated Use getChildCategoryXrefs() instead.
     * @return the list of active child categories
     */

   
    public List<Category> getChildCategories();

    /**
     * Gets the child category ids. If child categories has not been previously
     * set, then the list of active only categories will be returned. This method
     * is optimized with Hydrated cache, which means that the algorithm required
     * to harvest active child categories will not need to be rebuilt as long
     * as the parent category (this category) is not evicted from second level cache.
     *
     * @return the list of active child category ids
     */
   
    public List<Long> getChildCategoryIds();

    /**
     * Sets the all child category ids. This should be a list
     * of active only child categories.
     *
     * @param childCategoryIds the list of active child category ids.
     */
    public void setChildCategoryIds( List<Long> childCategoryIds);

    /**
     * Checks for child categories.
     *
     * @return true, if this category contains any active child categories.
     */
    public boolean hasChildCategories();

    /**
     * Sets the all child categories. This should be a list
     * of active only child categories.
     *
     * @deprecated Use setChildCategoryXrefs() instead.
     * @param childCategories the list of active child categories.
     */
    
    public void setChildCategories( List<Category> childCategories);

   

   
    /**
     * Retrieve all parent categories
     *
     * @deprecated Use getAllParentCategoryXrefs() instead.
     * @return the list of parent categories
     */
    @Deprecated
   
    public List<Category> getAllParentCategories();

    /**
     * Sets the list of parent categories
     *
     * @deprecated Use setAllParentCategoryXrefs() instead.
     * @param allParentCategories the list of parent categories
     */
    @Deprecated
    public void setAllParentCategories( List<Category> allParentCategories);


    /**
     * Convenience method to retrieve all of this {@link Category}'s {@link Product}s filtered by
     * active. If you want all of the {@link Product}s (whether inactive or not) consider using
     * {@link #getAllProducts()}.
     *
     * @deprecated Use getActiveProductXrefs() instead.
     * @return the list of active {@link Product}s for this {@link Category}
     * @see {@link Product#isActive()}
     */
    @Deprecated
    public List<Product> getActiveProducts();

    /**
     * Retrieve all the {@code Product} instances associated with this
     * category.
     * <br />
     * <b>Note:</b> this method does not take into account whether or not the {@link Product}s are active or not. If
     * you need this functionality, use {@link #getActiveProducts()}
     * @deprecated Use getAllProductXrefs() instead.
     * @return the list of products associated with this category.
     */
    @Deprecated
   
    public List<Product> getAllProducts();

    /**
     * Set all the {@code Product} instances associated with this
     * category.
     *
     * @deprecated Use setAllProductXrefs() instead.
     * @param allProducts the list of products to associate with this category
     */
    @Deprecated
    public void setAllProducts( List<Product> allProducts);

    /**
     * Returns the tax code of this category.
     * @return taxCode
     */
    public String getTaxCode();

    /**
     * Sets the tax code of this category.
     * @param taxCode
     */
    public void setTaxCode(String taxCode);
}
