package org.digicart.core.catalog.dao;

/*
 * #%L
 * BroadleafCommerce Framework
 * %%
 * Copyright (C) 2009 - 2013 Broadleaf Commerce
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.digicart.common.persistence.Status;
import org.digicart.common.time.SystemTime;
import org.digicart.core.catalog.domain.Category;
import org.digicart.core.catalog.domain.CategoryImpl;
import org.digicart.core.catalog.domain.Product;
import org.hibernate.ejb.QueryHints;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Jeff Fischer
 */
@Repository("blCategoryDao")
public class CategoryDaoImpl implements CategoryDao {

    protected Long currentDateResolution = 10000L;
    protected Date cachedDate = SystemTime.asDate();

    protected Date getCurrentDateAfterFactoringInDateResolution() {
        Date returnDate = SystemTime.getCurrentDateWithinTimeResolution(cachedDate, currentDateResolution);
        if (returnDate != cachedDate) {
            if (SystemTime.shouldCacheDate()) {
                cachedDate = returnDate;
            }
        }
        return returnDate;
    }

    @PersistenceContext(unitName="blPU")
    protected EntityManager em;

    /*@Resource(name="blEntityConfiguration")
    protected EntityConfiguration entityConfiguration;
*/
   

    @Override
    public Category save(Category category) {
        return em.merge(category);
    }

    @Override
    public Category readCategoryById(Long categoryId) {
        return em.find(CategoryImpl.class, categoryId);
    }

    @Override   
    public Category readCategoryByName(String categoryName) {
        Query query = em.createNamedQuery("DC_READ_CATEGORY_BY_NAME");
        query.setParameter("categoryName", categoryName);
        query.setHint(QueryHints.HINT_CACHEABLE, true);
        query.setHint(QueryHints.HINT_CACHE_REGION, "query.Catalog");
        try {
        	return (Category) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        
    }
    
    @Override
    public List<Category> readAllParentCategories() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Category> criteria = builder.createQuery(Category.class);
        Root<CategoryImpl> category = criteria.from(CategoryImpl.class);

        criteria.select(category);
        criteria.where(builder.isNull(category.get("defaultParentCategory")));
        TypedQuery<Category> query = em.createQuery(criteria);
        query.setHint(QueryHints.HINT_CACHEABLE, true);
        query.setHint(QueryHints.HINT_CACHE_REGION, "query.Catalog");

        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Category> readCategoriesByName(String categoryName) {
        TypedQuery<Category> query = em.createNamedQuery("DC_READ_CATEGORY_BY_NAME", Category.class);
        query.setParameter("categoryName", categoryName);
        query.setHint(QueryHints.HINT_CACHEABLE, true);
        query.setHint(QueryHints.HINT_CACHE_REGION, "query.Catalog");
        return query.getResultList();
    }

    @Override
    public List<Category> readCategoriesByName(String categoryName, int limit, int offset) {
        TypedQuery<Category> query = em.createNamedQuery("DC_READ_CATEGORY_BY_NAME", Category.class);
        query.setParameter("categoryName", categoryName);
        query.setHint(QueryHints.HINT_CACHEABLE, true);
        query.setHint(QueryHints.HINT_CACHE_REGION, "query.Catalog");
        query.setFirstResult(offset);
        query.setMaxResults(limit);

        return query.getResultList();
    }

    @Override
    public List<Category> readAllCategories() {
        TypedQuery<Category> query = em.createNamedQuery("DC_READ_ALL_CATEGORIES", Category.class);
        query.setHint(QueryHints.HINT_CACHEABLE, true);
        query.setHint(QueryHints.HINT_CACHE_REGION, "query.Catalog");
        return query.getResultList();
    }

    @Override
    public List<Category> readAllCategories(int limit, int offset) {
        TypedQuery<Category> query = em.createNamedQuery("DC_READ_ALL_CATEGORIES", Category.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setHint(QueryHints.HINT_CACHEABLE, true);
        query.setHint(QueryHints.HINT_CACHE_REGION, "query.Catalog");

        return query.getResultList();
    }

    @Override
    public List<Product> readAllProducts() {
        TypedQuery<Product> query = em.createNamedQuery("DC_READ_ALL_PRODUCTS", Product.class);
        //don't cache - could take up too much memory
        return query.getResultList();
    }

    @Override
    public List<Product> readAllProducts(int limit, int offset) {
        TypedQuery<Product> query = em.createNamedQuery("DC_READ_ALL_PRODUCTS", Product.class);
        //don't cache - could take up too much memory
        query.setFirstResult(offset);
        query.setMaxResults(limit);

        return query.getResultList();
    }

    @Override
    public Long getCurrentDateResolution() {
        return currentDateResolution;
    }

    @Override
    public void setCurrentDateResolution(Long currentDateResolution) {
        this.currentDateResolution = currentDateResolution;
    }

    @Override
    public void delete(Category category) {
        ((Status) category).setArchived('Y');
        em.merge(category);
    }
    
    @Override
    public void delete(String categoryName) {
    	Category category = readCategoryByName(categoryName);    	
        ((Status) category).setArchived('Y');
        em.merge(category);
    }

  /*  @Override
    public Category create() {
        return (Category) entityConfiguration.createEntityInstance(Category.class.getName());
    }*/
    
    @Override
    public void createCategory(String categoryName,Date activeStartDate,Date activeEndDate,String longDescription,String defaultParentCategory) {
    	Category category = new CategoryImpl();
    	category.setActiveEndDate(activeEndDate);
    	category.setName(categoryName);
    	category.setActiveStartDate(activeStartDate);
    	category.setLongDescription(longDescription);
    	category.setDefaultParentCategory(readCategoryByName(defaultParentCategory));
    	category.setTaxCode("123");
        em.persist(category);
    }

	@Override
	public List<Category> readAllSubCategories(String categoryName) {
		// TODO Auto-generated method stub
		
		Category category = readCategoryByName(categoryName);
		if(category==null)
			return null;
		 TypedQuery<Category> query = em.createNamedQuery("DC_READ_ALL_SUBCATEGORIES", Category.class);
	        query.setParameter("defaultParentCategoryId",category.getId());
	        query.setHint(QueryHints.HINT_CACHEABLE, true);
	        query.setHint(QueryHints.HINT_CACHE_REGION, "query.Catalog");
	        return query.getResultList();
		
	}

	@Override
	public List<Category> readAllSubCategories(Category category, int limit,
			int offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> readActiveSubCategoriesByCategory(Category category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> readActiveSubCategoriesByCategory(Category category,
			int limit, int offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category findCategoryByURI(String uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category create() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> readAllSubCategories(Category category) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Category> readActiveSubCategoriesByCategory(String categoryName) {
		
		Category category = readCategoryByName(categoryName);
		if(category==null)
			return null;
		 TypedQuery<Category> query = em.createNamedQuery("DC_READ_ACTIVE_SUBCATEGORIES_BY_CATEGORY", Category.class);
	     query.setParameter("defaultParentCategoryId",category.getId());
	     query.setParameter("currentDate", getCurrentDateAfterFactoringInDateResolution());
	     query.setHint(QueryHints.HINT_CACHEABLE, true);
	     query.setHint(QueryHints.HINT_CACHE_REGION, "query.Catalog");
	     return query.getResultList();
	}
	
	 

  
}
