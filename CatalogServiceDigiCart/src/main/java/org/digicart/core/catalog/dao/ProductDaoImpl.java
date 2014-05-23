/*
 * #%L
 * digicart Framework
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
package org.digicart.core.catalog.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.digicart.common.time.SystemTime;
import org.digicart.core.catalog.domain.Product;
import org.digicart.core.catalog.domain.ProductImpl;
import org.digicart.core.catalog.service.type.ProductType;
import org.springframework.stereotype.Repository;
//import org.digicart.catalog.domain.ProductBundle;

/**
 * @author Jeff Fischer
 * @author Andre Azzolini (apazzolini)
 */
@Repository("blProductDao")
public class ProductDaoImpl implements ProductDao {

   // private static final SupportLogger logger = SupportLogManager.getLogger("Enterprise", ProductDaoImpl.class);

    @PersistenceContext(unitName="blPU")
    protected EntityManager em;

    /*@Resource(name="blEntityConfiguration")
    protected EntityConfiguration entityConfiguration;*/

   /* @Resource(name="blSandBoxHelper")
    protected SandBoxHelper sandBoxHelper;*/

   /* @Resource(name = "blProductDaoExtensionManager")
    protected ProductDaoExtensionManager extensionManager;*/

    /*@Resource(name = "blDialectHelper")
    protected DialectHelper dialectHelper;*/

    protected Long currentDateResolution = 10000L;
    protected Date cachedDate = SystemTime.asDate();

    @Override
    public Product save(Product product) {
        return em.merge(product);
    }

    @Override
    public Product readProductById(Long productId) {
        return em.find(ProductImpl.class, productId);
    }
    
    @Override
	public Product createProduct(Product product) {
		return null;
		
	}
    
   /* @Override
    public Product create(ProductType productType) {
        return (Product) entityConfiguration.createEntityInstance(productType.getType());
    }*/
    
	@Override
	public List<Product> readProductsByIds(List<Long> productIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> readProductsByName(String searchName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> readProductsByName(String searchName, int limit,
			int offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> readActiveProductsByCategory(Long categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> readActiveProductsByCategory(Long categoryId,
			int limit, int offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> readActiveProductsByCategory(Long categoryId,
			Date currentDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> readActiveProductsByCategory(Long categoryId,
			Date currentDate, int limit, int offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> readProductsByCategory(Long categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> readProductsByCategory(Long categoryId, int limit,
			int offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Product product) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Product> findProductByURI(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> readAllActiveProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> readAllActiveProducts(Date currentDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> readAllActiveProducts(int page, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> readAllActiveProducts(int page, int pageSize,
			Date currentDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long readCountAllActiveProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long readCountAllActiveProducts(Date currentDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getCurrentDateResolution() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCurrentDateResolution(Long currentDateResolution) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Product create(ProductType productType) {
		// TODO Auto-generated method stub
		return null;
	}

   }
