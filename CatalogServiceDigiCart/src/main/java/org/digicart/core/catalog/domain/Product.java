package org.digicart.core.catalog.domain;






import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Implementations of this interface are used to hold data for a Product.  A product is a general description
 * of an item that can be sold (for example: a hat).  Products are not sold or added to a cart.  {@link Sku}s
 * which are specific items (for example: a XL Blue Hat) are sold or added to a cart.
 * <br>
 * <br>
 * You should implement this class if you want to make significant changes to how the
 * Product is persisted.  If you just want to add additional fields then you should extend {@link ProductImpl}.
 *
 * @author btaylor
 * @see {@link ProductImpl},{@link Sku}, {@link Category}
 */
public interface Product extends Serializable {

    /**
     * The id of the Product.
     *
     * @return the id of the Product
     */
    public Long getId();

    /**
     * Sets the id of the Product.
     *
     * @param id - the id of the product
     */
    public void setId(Long id);

    /**
     * Returns the name of the product that is used for display purposes.
     * <br />
     * <br />
     * <b>Note:</b> this is a convenience method that merely serves as
     * a pass-through to the same method via {@link getDefaultSku()}
     * 
     * @return the name of the product
     */
    public String getName();

    /**
     * Sets the name of the product that is used for display purposes.
     * <br />
     * <br />
     * <b>Note:</b> this is a convenience method that merely serves as
     * a pass-through to the same method via {@link getDefaultSku()}
     * 
     * @param name - the name of the Product
     */
    public void setName(String name);

    /**
     * Returns a brief description of the product that is used for display.
     * <br />
     * <br />
     * <b>Note:</b> this is a convenience method that merely serves as
     * a pass-through to the same method via {@link getDefaultSku()}
     * 
     * @return a brief description of the product
     */
    public String getDescription();

    /**
     * Sets a brief description of the product that is used for display.
     * <br />
     * <br />
     * <b>Note:</b> this is a convenience method that merely serves as
     * a pass-through to the same method via {@link getDefaultSku()}
     * 
     * @param description - a brief description of the product
     */
    public void setDescription(String description);

    /**
     * Returns a long description of the product that is used for display.
     * <br />
     * <br />
     * <b>Note:</b> this is a convenience method that merely serves as
     * a pass-through to the same method via {@link getDefaultSku()}
     * 
     * @return a long description of the product
     */
    public String getLongDescription();

    /**
     * Sets a long description of the product that is used for display.
     * <br />
     * <br />
     * <b>Note:</b> this is a convenience method that merely serves as
     * a pass-through to the same method via {@link getDefaultSku()}
     * 
     * @param longDescription the long description
     */
    public void setLongDescription(String longDescription);

    /**
     * Returns the first date a product will be available that is used to determine whether
     * to display the product.
     * <br />
     * <br />
     * <b>Note:</b> this is a convenience method that merely serves as
     * a pass-through to the same method via {@link getDefaultSku()}
     * 
     * @return the first date the product will be available
     */
    public Date getActiveStartDate();

    /**
     * Sets the first date a product will be available that is used to determine whether
     * to display the product.
     * <br />
     * <br />
     * <b>Note:</b> this is a convenience method that merely serves as
     * a pass-through to the same method via {@link getDefaultSku()}
     * 
     * @param activeStartDate - the first day the product is available
     */
    public void setActiveStartDate(Date activeStartDate);

    /**
     * Returns the last date a product will be available that is used to determine whether
     * to display the product.
     * <br />
     * <br />
     * <b>Note:</b> this is a convenience method that merely serves as
     * a pass-through to the same method via {@link getDefaultSku()}
     * 
     * @return the last day the product is available
     */
    public Date getActiveEndDate();

    /**
     * Sets the last date a product will be available that is used to determine whether
     * to display the product.
     * <br />
     * <br />
     * <b>Note:</b> this is a convenience method that merely serves as
     * a pass-through to the same method via {@link getDefaultSku()}
     * 
     * @param activeEndDate - the last day the product is available
     */
    public void setActiveEndDate(Date activeEndDate);

    /**
     * Returns a boolean that indicates if the product is currently active.
     * <br />
     * <br />
     * <b>Note:</b> this is a convenience method that merely serves as
     * a pass-through to the same method via {@link getDefaultSku()}
     * 
     * @return a boolean indicates if the product is active.
     */
    public boolean isActive();
    
    /**
     * Gets the default {@link Sku} associated with this Product. A Product is
     * required to have a default Sku which holds specific information about the Product
     * like weight, dimensions, price, etc.  Many of the Product attributes that
     * have getters and setters on Product are actually pass-through to the default Sku.
     * <br />
     * <br />
     * Products can also have multiple Skus associated with it that are represented by
     * {@link ProductOption}s. For instance, a large, blue shirt. For more information on
     * that relationship see {@link #getAdditionalSkus()}.
     * 
     * @return the default Sku for this Product
     */
    public Sku getDefaultSku();

    /**
     * Sets the default Sku for this Product
     * <br />
     * <br />
     * Note: this operation is cascaded with CascadeType.ALL which saves from having to persist the Product
     * in 2 operations: first persist the Sku and then take the merged Sku, set it as this Product's default
     * Sku, and then persist this Product.
     * 
     * @param defaultSku - the Sku that should be the default for this Product
     */
    public void setDefaultSku(Sku defaultSku);
    
    /**
     * Returns all the {@link Sku}s that are associated with this Product (including {@link #getDefaultSku()})
     * regardless of whether or not the {@link Sku}s are active or not
     * <br />
     * <br />
     * Note: in the event that the default Sku was added to the list of {@link #getAdditionalSkus()}, it is filtered out
     * so that only a single instance of {@link #getDefaultSku()} is contained in the resulting list
     * 
     * @return all the Skus associated to this Product
     */
    public List<Sku> getAllSkus();
    
   
    /**
     * @return whether or not the default sku can be used for a multi-sku product in the case that no 
     * product options are set. Defaults to false if not specified. Note that this only affects multi-sku
     * products.
     */
    public Boolean getCanSellWithoutOptions();

    /**
     * Sets whether or not the default sku can be sold in the case that no product options are specified. Note
     * that this only affects multi-sku products.
     * 
     * @param canSellWithoutOptions
     */
    public void setCanSellWithoutOptions(Boolean canSellWithoutOptions);   
     
    /**
     * Returns the default {@link Category} this product is associated with.
     *
     */
    public Category getDefaultCategory();

    /**
     * Sets the default {@link Category} to associate this product with.
     *
     * @param defaultCategory - the default {@link Category} to associate this product with
     */
    public void setDefaultCategory(Category defaultCategory);

    /**
     * Returns the model number of the product
     * @return the model number
     */
    public String getModel();

    /**
     * Sets the model number of the product
     * @param model
     */
    public void setModel(String model);

    /**
     * Returns the manufacture name for this product
     * @return the manufacture name
     */
    public String getManufacturer();

    /**
     * Sets the manufacture for this product
     * @param manufacturer
     */
    public void setManufacturer(String manufacturer);
    
    /**
     * Returns the {@link Dimension} for this product
     * <br />
     * <br />
     * <b>Note:</b> this is a convenience method that merely serves as
     * a pass-through to the same method via {@link getDefaultSku()}
     * 
     * @return a ProductDimensions object
     * 
     */
    //public Dimension getDimension();

    /**
     * Sets the {@link Dimension} for this product
     * <br />
     * <br />
     * <b>Note:</b> this is a convenience method that merely serves as
     * a pass-through to the same method via {@link getDefaultSku()}
     * 
     * @param dimension
     * 
     */
   // public void setDimension(Dimension dimension);

    /**
     * Returns the dimension width
     * <br />
     * <br />
     * <b>Note:</b> this is a convenience method that merely serves as
     * a pass-through to the same method via {@link getDefaultSku()}
     * 
     * @return width dimension of the product
     * 
     */
    public BigDecimal getWidth();

    /**
     * Sets the dimension width
     * <br />
     * <br />
     * <b>Note:</b> this is a convenience method that merely serves as
     * a pass-through to the same method via {@link getDefaultSku()}
     * 
     * @param width
     * 
     */
    public void setWidth(BigDecimal width);

    /**
     * Returns the dimension height
     * <br />
     * <br />
     * <b>Note:</b> this is a convenience method that merely serves as
     * a pass-through to the same method via {@link getDefaultSku()}
     * 
     * @return height dimension of the product
     * 
     */
    public BigDecimal getHeight();

    /**
     * Sets the dimension height
     * <br />
     * <br />
     * <b>Note:</b> this is a convenience method that merely serves as
     * a pass-through to the same method via {@link getDefaultSku()}
     * 
     * @param height
     * 
     */
    public void setHeight(BigDecimal height);

    /**
     * Returns the dimension depth
     * <br />
     * <br />
     * <b>Note:</b> this is a convenience method that merely serves as
     * a pass-through to the same method via {@link getDefaultSku()}
     * 
     * @return width depth of the product
     * 
     */
    public BigDecimal getDepth();

    /**
     * Sets the dimension depth
     * <br />
     * <br />
     * <b>Note:</b> this is a convenience method that merely serves as
     * a pass-through to the same method via {@link getDefaultSku()}
     * 
     * @param depth
     */
    public void setDepth(BigDecimal depth);
    
    /**
     * Gets the dimension girth
     * <br />
     * <br />
     * <b>Note:</b> this is a convenience method that merely serves as
     * a pass-through to the same method via {@link getDefaultSku()}
     * 
     * @return the dimension girth
     */
    public BigDecimal getGirth();
    
    /**
     * Sets the dimension girth
     * <br />
     * <br />
     * <b>Note:</b> this is a convenience method that merely serves as
     * a pass-through to the same method via {@link getDefaultSku()}
     * 
     * @param girth
     */
    public void setGirth(BigDecimal girth);

    /**
     * Returns the dimension container size
     * 
     * <br />
     * <br />
     * <b>Note:</b> this is a convenience method that merely serves as
     * a pass-through to the same method via {@link getDefaultSku()}
     * 
     * @return dimension container size
     */
   // public ContainerSizeType getSize();

    /**
     * Sets the dimension container size
     * <br />
     * <br />
     * <b>Note:</b> this is a convenience method that merely serves as
     * a pass-through to the same method via {@link getDefaultSku()}
     * 
     * @param size
     */
  //  public void setSize(ContainerSizeType size);

    /**
     * Gets the dimension container shape
     * <br />
     * <br />
     * <b>Note:</b> this is a convenience method that merely serves as
     * a pass-through to the same method via {@link getDefaultSku()}
     * 
     * @return dimension container shape
     */
   // public ContainerShapeType getContainer();

    /**
     * Sets the dimension container shape
     * <br />
     * <br />
     * <b>Note:</b> this is a convenience method that merely serves as
     * a pass-through to the same method via {@link getDefaultSku()}
     * 
     * @param container
     */
   // public void setContainer(ContainerShapeType container);

    /**
     * Returns a String representation of the dimension
     * <br />
     * <br />
     * <b>Note:</b> this is a convenience method that merely serves as
     * a pass-through to the same method via {@link getDefaultSku()}
     * 
     * @return a dimension String
     */
    public String getDimensionString();

    /**
     * Returns the weight of the product
     * <br />
     * <br />
     * <b>Note:</b> this is a convenience method that merely serves as
     * a pass-through to the same method via {@link getDefaultSku()}
     * 
     * @return weight of product
     */
   // public Weight getWeight();

    /**
     * Sets the product weight
     * <br />
     * <br />
     * <b>Note:</b> this is a convenience method that merely serves as
     * a pass-through to the same method via {@link getDefaultSku()}
     * 
     * @param weight
     */
  //  public void setWeight(Weight weight);

    /**
     * Returns a List of this product's related Cross Sales
     * @return
     */
   // public List<RelatedProduct> getCrossSaleProducts();

    /**
     * Sets the related Cross Sales
     * @param crossSaleProducts
     */
  //  public void setCrossSaleProducts(List<RelatedProduct> crossSaleProducts);

    /**
     * Returns a List of this product's related Up Sales
     * @return
     */
   // public List<RelatedProduct> getUpSaleProducts();

    /**
     * Sets the related Up Sales
     * @param upSaleProducts
     */
   // public void setUpSaleProducts(List<RelatedProduct> upSaleProducts);

    /**
     * Returns whether or not the product is featured
     * @return isFeaturedProduct as Boolean
     */
    public boolean isFeaturedProduct();

    /**
     * Sets whether or not the product is featured
     * @param isFeaturedProduct
     */
    public void setFeaturedProduct(boolean isFeaturedProduct);

    /**
     * Generic key-value pair of attributes to associate to this Product for maximum
     * extensibility.
     *
     * @return the attributes for this Product
     */
   // public Map<String, ProductAttribute> getProductAttributes();

    /**
     * Sets a generic list of key-value pairs for Product
     * @param productAttributes
     */
   // public void setProductAttributes(Map<String, ProductAttribute> productAttributes);

    /**
     * Gets the promotional message for this Product. For instance, this could be a limited-time
     * Product
     * 
     * @return the Product's promotional message
     */
    public String getPromoMessage();

    /**
     * Sets the promotional message for this Product
     * 
     * @param promoMessage
     */
    public void setPromoMessage(String promoMessage);

   
    /**
     * A product can have a designated URL.   When set, the ProductHandlerMapping will check for this
     * URL and forward this user to the {@link #getDisplayTemplate()}. 
     * 
     * Alternatively, most sites will rely on the {@link Product#getGeneratedUrl()} to define the
     * url for a product page. 
     * 
     * @see org.broadleafcommerce.core.web.catalog.ProductHandlerMapping
     * @return
     */
    public String getUrl();

    /**
     * Sets the URL that a customer could type in to reach this product.
     * 
     * @param url
     */
    public void setUrl(String url);
    
    /**
     * Sets a url-fragment.  By default, the system will attempt to create a unique url-fragment for 
     * this product by taking the {@link Product.getName()} and removing special characters and replacing
     * dashes with spaces.
     */ 
    public String getUrlKey();

    /**
     * Sets a url-fragment to be used with this product.  By default, the system will attempt to create a 
     * unique url-fragment for this product by taking the {@link Product.getName()} and removing special characters and replacing
     * dashes with spaces.
     */
    public void setUrlKey(String url);

    /**
     * Returns the name of a display template that is used to render this product.   Most implementations have a default
     * template for all products.    This allows for the user to define a specific template to be used by this product.
     * 
     * @return
     */
    public String getDisplayTemplate();

    /**
     * Sets the name of a display template that is used to render this product.   Most implementations have a default
     * template for all products.    This allows for the user to define a specific template to be used by this product.
     * @param displayTemplate
     */
    public void setDisplayTemplate(String displayTemplate);
    
    /**
     * Generates a URL that can be used to access the product.  
     * Builds the url by combining the url of the default category with the getUrlKey() of this product.
     */
    public String getGeneratedUrl();
    
    /** 
     * Returns a list of the cross sale products for this product as well
     * all cross sale products in all parent categories of this product.
     * 
     * @return the cumulative cross sale products
     */
    //public List<RelatedProduct> getCumulativeCrossSaleProducts();
    
    /** 
     * Returns a list of the upsale products for this product as well as
     * all upsale products in all parent categories of this product.
     * 
     * @return the cumulative upsale products
     */
   // public List<RelatedProduct> getCumulativeUpSaleProducts();

    /**
     * Removes any currently stored dynamic pricing
     */
    public void clearDynamicPrices();

  
    @Deprecated
    public List<Category> getAllParentCategories();

    /**
     * Sets all parent {@link Category}s this product is associated with.
     *
     * @deprecated Use setAllParentCategoryXrefs() instead.
     * @param allParentCategories - a List of all parent {@link Category}(s) to associate this product with
     */
    @Deprecated
    public void setAllParentCategories(List<Category> allParentCategories);

    /**
     * Returns the tax code of the product. If the tax code is null, then returns the tax code of this products category.
     * @return taxCode
     */
    public String getTaxCode();

    /**
     * Sets the tax code for this product.
     * @param taxCode
     */
    public void setTaxCode(String taxCode);

}
