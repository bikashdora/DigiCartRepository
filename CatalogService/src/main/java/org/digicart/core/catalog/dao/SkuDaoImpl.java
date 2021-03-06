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
import javax.persistence.TypedQuery;

import org.digicart.common.currency.domain.DigiCartCurrency;
import org.digicart.core.catalog.domain.Sku;
//import org.digicart.core.catalog.domain.SkuFee;
import org.digicart.core.catalog.domain.SkuImpl;
import org.springframework.stereotype.Repository;

/**
 * {@inheritDoc}
 *
 * @author Jeff Fischer
 */
@Repository("blSkuDao")
public class SkuDaoImpl implements SkuDao {

    @PersistenceContext(unitName="blPU")
    protected EntityManager em;

   /* @Resource(name="blEntityConfiguration")
    protected EntityConfiguration entityConfiguration;*/

    @Override
    public Sku save(Sku sku) {
        return em.merge(sku);
    }
    
  /*  @Override
    public SkuFee saveSkuFee(SkuFee fee) {
        return em.merge(fee);
    }*/

    @Override
    public Sku readSkuById(Long skuId) {
        return (Sku) em.find(SkuImpl.class, skuId);
    }

    @Override
    public Sku readFirstSku() {
        TypedQuery<Sku> query = em.createNamedQuery("BC_READ_FIRST_SKU", Sku.class);
        return query.getSingleResult();
    }

    @Override
    public List<Sku> readAllSkus() {
        TypedQuery<Sku> query = em.createNamedQuery("BC_READ_ALL_SKUS", Sku.class);
        return query.getResultList();
    }

    @Override
    public List<Sku> readSkusById(List<Long> ids) {
        TypedQuery<Sku> query = em.createNamedQuery("BC_READ_SKUS_BY_ID", Sku.class);
        query.setParameter("skuIds", ids);
        return query.getResultList();
    }

    @Override
    public void delete(Sku sku){
        if (!em.contains(sku)) {
            sku = readSkuById(sku.getId());
        }
        em.remove(sku);     
    }
    
    @Override
    public Sku create(Date activeStartDate,Date activeEndDate,Boolean available,DigiCartCurrency currency,String description,String name,Boolean taxable ) {
    		Sku sku = new SkuImpl();
    		sku.setActiveStartDate(activeStartDate);
    		sku.setActiveEndDate(activeEndDate);
    		sku.setAvailable(available);
    		sku.setCurrency(currency);
    		sku.setDescription(description);
    		sku.setName(name);
    		sku.setTaxable(taxable);
    		em.persist(sku);
    		
    		
    	return sku;
    }

    @Override
    public Sku create() {
       // return (Sku) entityConfiguration.createEntityInstance(Sku.class.getName());
    	return null;
    }
}
