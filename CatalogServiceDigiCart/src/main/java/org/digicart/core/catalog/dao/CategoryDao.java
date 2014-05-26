package org.digicart.core.catalog.dao;




import org.digicart.core.catalog.domain.Category;
import org.digicart.core.catalog.domain.Product;

import java.util.Date;
import java.util.List;








import javax.validation.constraints.NotNull;

/**
 * {@code CategoryDao} provides persistence access to {@code Category} instances.
 *
 * @see Category
 * @see Product
 * @author Jeff Fischer
 */
public interface CategoryDao {

    /**
     * Retrieve a {@code Category} instance by its primary key
     *
     * @param categoryId the primary key of the {@code Category}
     * @return the {@code Category}  at the specified primary key
     */
    @NotNull
    public Category readCategoryById(@NotNull Long categoryId);

    /**
     * Retrieve a {@code Category} instance by its name.
     *
     * Broadleaf allows more than one category to have the same name. Calling
     * this method could produce an exception in such situations. Use
     * {@link #readCategoriesByName(String)} instead.
     *
     * @param categoryName the name of the category
     * @return the Category having the specified name
     */
    @NotNull   
    public Category readCategoryByName(@NotNull String categoryName);
    
    /**
     * @return a list of all categories that do not have a defaultParentCategory set
     */
    public List<Category> readAllParentCategories();

    /**
     * Retrieve a list of {@code Category} instances by name.
     *
     * @param categoryName the name to search by
     * @return the Category instances having the specified name
     */
    @NotNull
    public List<Category> readCategoriesByName(@NotNull String categoryName);

    @NotNull
    public List<Category> readCategoriesByName(@NotNull String categoryName, int limit, int offset);

    /**
     * Persist a {@code Category} instance to the datastore
     *
     * @param category the {@code Category} instance
     * @return the updated state of the passed in {@code Category} after being persisted
     */
    @NotNull
    public Category save(@NotNull Category category);

    /**
     * Retrieve all categories in the datastore
     *
     * @return a list of all the {@code Category} instances in the datastore
     */
    @NotNull
    public List<Category> readAllCategories();

    /**
     * Retrieve a subset of all categories
     *
     * @param limit the maximum number of results, defaults to 20
     * @param offset the starting point in the record set, defaults to 0
     * @return
     */
    @NotNull
    public List<Category> readAllCategories(@NotNull int limit, @NotNull int offset);

    /**
     * Retrieve all products in the datastore
     *
     * @return a list of all {@code Category} instances in the datastore, regardless of their category association
     */
    @NotNull
    public List<Product> readAllProducts();

    @NotNull
    public List<Product> readAllProducts(@NotNull int limit, @NotNull int offset);

    /**
     * Retrieve a list of all child categories of the passed in {@code Category} instance
     *
     * @param category the parent category
     * @return a list of all child categories
     */
    @NotNull
    public List<Category> readAllSubCategories(@NotNull Category category);

    /**
     * Retrieve a list of all child categories of the passed in {@code Category} instance
     *
     * @param category the parent category
     * @param limit the maximum number of results to return
     * @param offset the starting point in the record set
     * @return a list of all child categories
     */
    @NotNull
    public List<Category> readAllSubCategories(@NotNull Category category, @NotNull int limit, @NotNull int offset);

    /**
     * Removed the passed in {@code Category} instance from the datastore
     *
     * @param category the {@code Category} instance to remove
     */
    public void delete(@NotNull Category category);

    /**
     * Create a new {@code Category} instance. The system will use the configuration in
     * {@code /BroadleafCommerce/core/BroadleafCommerceFramework/src/main/resources/bl-framework-applicationContext-entity.xml}
     * to determine which polymorphic version of {@code Category} to instantiate. To make Broadleaf instantiate your
     * extension of {@code Category} by default, include an entity configuration bean in your application context xml similar to:
     * <p>
     * {@code
     *     <bean id="blEntityConfiguration" class="org.broadleafcommerce.common.persistence.EntityConfiguration">
     *          <property name="entityContexts">
     *              <list>
     *                  <value>classpath:myCompany-applicationContext-entity.xml</value>
     *              </list>
     *          </property>
     *      </bean>
     * }
     * </p>
     * Declare the same key for your desired entity in your entity xml that is used in the Broadleaf entity xml, but change the value to the fully
     * qualified classname of your entity extension.
     *
     * @return a {@code Category} instance based on the Broadleaf entity configuration.
     */
    @NotNull
    public Category create();

    /**
     * Retrieve a list of all active child categories of the passed in {@code Category} instance.
     * This method bases its search on a current time value. To make the retrieval of values more
     * efficient, the current time is cached for a configurable amount of time. See
     * {@link #getCurrentDateResolution()}
     *
     * @param category the parent category
     * @return a list of all active child categories
     */
    @NotNull
    public List<Category> readActiveSubCategoriesByCategory(Category category);

    /**
     * Retrieve a list of all active child categories of the passed in {@code Category} instance.
     * This method bases its search on a current time value. To make the retrieval of values more
     * efficient, the current time is cached for a configurable amount of time. See
     * {@link #getCurrentDateResolution()}
     *
     * @param category the parent category
     * @param limit the maximum number of results to return
     * @param offset the starting point in the record set
     * @return a list of all active child categories
     */
    @NotNull
    public List<Category> readActiveSubCategoriesByCategory(@NotNull Category category, @NotNull int limit, @NotNull int offset);

    public Category findCategoryByURI(String uri);

    /**
     * Returns the number of milliseconds that the current date/time will be cached for queries before refreshing.
     * This aids in query caching, otherwise every query that utilized current date would be different and caching
     * would be ineffective.
     *
     * @return the milliseconds to cache the current date/time
     */
    public Long getCurrentDateResolution();

    /**
     * Sets the number of milliseconds that the current date/time will be cached for queries before refreshing.
     * This aids in query caching, otherwise every query that utilized current date would be different and caching
     * would be ineffective.
     *
     * @param currentDateResolution the milliseconds to cache the current date/time
     */
    public void setCurrentDateResolution(Long currentDateResolution);

	

	void createCategory(String categoryName, Date activeStartDate,
			Date activeEndDate, String longDescription,
			String defaultParentCategory);

	public List<Category> readAllSubCategories(String categoryName);

	public List<Category> readActiveSubCategoriesByCategory(String categoryName);

	public void delete(String categoryName);

}
