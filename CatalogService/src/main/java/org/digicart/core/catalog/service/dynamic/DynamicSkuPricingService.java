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
package org.digicart.core.catalog.service.dynamic;

import java.util.HashMap;

import javax.validation.constraints.NotNull;

//import org.digicart.core.catalog.domain.ProductOptionValueImpl;
import org.digicart.common.money.Money;
import org.digicart.core.catalog.domain.Sku;

/**
 * <p>Interface for calculating dynamic pricing for a {@link Sku}. This should be hooked up via a custom subclass of 
 * {@link org.broadleafcommerce.core.web.catalog.DefaultDynamicSkuPricingFilter} where an implementation of this class
 * should be injected and returned in the getPricing() method.</p>
 * 
 * <p>Rather than implementing this interface directly, consider subclassing the {@link DefaultDynamicSkuPricingServiceImpl}
 * and providing overrides to methods there.</p>
 * 
 * @author jfischer
 * @see {@link DefaultDynamicSkuPricingServiceImpl}
 * @see {@link org.broadleafcommerce.core.web.catalog.DefaultDynamicSkuPricingFilter}
 * @see {@link SkuPricingConsiderationContext}
 */
public interface DynamicSkuPricingService {

    /**
     * While this method should return a {@link DynamicSkuPrices} (and not just null) the members of the result can all
     * be null; they do not have to be set
     * 
     * @param sku
     * @param skuPricingConsiderations
     * @return
     */
    @NotNull
    @SuppressWarnings("rawtypes")
    public DynamicSkuPrices getSkuPrices(Sku sku, HashMap skuPricingConsiderations);

    /**
     * Used for t
     * 
     * @param sku
     * @param skuPricingConsiderations
     * @return
     */
   /* @SuppressWarnings("rawtypes")
    public DynamicSkuPrices getSkuBundleItemPrice(SkuBundleItem sku, HashMap skuPricingConsiderations);
*/
    /**
     * Execute dynamic pricing on the price of a product option value. 
     * @param productOptionValueImpl
     * @param priceAdjustment
     * @param skuPricingConsiderationContext
     * @return
     */
  /*  @SuppressWarnings("rawtypes")
    public DynamicSkuPrices getPriceAdjustment(ProductOptionValueImpl productOptionValueImpl, Money priceAdjustment,
            HashMap skuPricingConsiderationContext);*/

}
