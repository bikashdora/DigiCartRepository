package org.digicart.core.catalog.service;



import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.digicart.core.catalog.dao.CategoryDao;
import org.digicart.core.catalog.dao.ProductDao;
import org.digicart.core.catalog.domain.Category;
import org.digicart.core.catalog.domain.Product;
import org.digicart.core.catalog.service.type.ProductType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import org.digicart.core.catalog.domain.ProductBundle;
//import org.digicart.core.catalog.domain.ProductBundleComparator;
//import org.digicart.core.catalog.domain.SkuFee;

@Service("blCatalogService")
public class CatalogServiceImpl implements CatalogService {

    @Resource(name="blCategoryDao")
    protected CategoryDao categoryDao;

    @Resource(name="blProductDao")
    protected ProductDao productDao;

   /* @Resource(name="blSkuDao")
    protected SkuDao skuDao;
    
    @Resource(name="blProductOptionDao")
    protected ProductOptionDao productOptionDao;

    @Resource(name = "blCatalogServiceExtensionManager")
    protected CatalogServiceExtensionManager extensionManager;*/

    @Override
    public Product findProductById(Long productId) {
        return productDao.readProductById(productId);
    }

    @Override
    public List<Product> findProductsByName(String searchName) {
        return productDao.readProductsByName(searchName);
    }

    @Override
    public List<Product> findProductsByName(String searchName, int limit, int offset) {
        return productDao.readProductsByName(searchName, limit, offset);
    }

    @Override
    public List<Product> findActiveProductsByCategory(Category category) {
        return productDao.readActiveProductsByCategory(category.getId());
    }

    @Override
    public List<Product> findActiveProductsByCategory(String categoryName) {
        return productDao.readActiveProductsByCategory(categoryName);
    }
    
   /* @Override
    public List<Product> findFilteredActiveProductsByCategory(Category category, ProductSearchCriteria searchCriteria) {
        return productDao.readFilteredActiveProductsByCategory(category.getId(), searchCriteria);
    }

    @Override
    public List<Product> findFilteredActiveProductsByQuery(String query, ProductSearchCriteria searchCriteria) {
        return productDao.readFilteredActiveProductsByQuery(query, searchCriteria);
    }*/

    @Override
    public List<Product> findActiveProductsByCategory(Category category, int limit, int offset) {
        return productDao.readActiveProductsByCategory(category.getId(), limit, offset);
    }

    @Override
    @Deprecated
    public List<Product> findActiveProductsByCategory(Category category, Date currentDate) {
        return productDao.readActiveProductsByCategory(category.getId(), currentDate);
    }
   /* 
    @Override
    @Deprecated
    public List<Product> findFilteredActiveProductsByCategory(Category category, Date currentDate, ProductSearchCriteria searchCriteria) {
        return productDao.readFilteredActiveProductsByCategory(category.getId(), currentDate, searchCriteria);
    }
    
    @Override
    @Deprecated
    public List<Product> findFilteredActiveProductsByQuery(String query, Date currentDate, ProductSearchCriteria searchCriteria) {
        return productDao.readFilteredActiveProductsByQuery(query, currentDate, searchCriteria);
    }*/

    @Override
    @Deprecated
    public List<Product> findActiveProductsByCategory(Category category, Date currentDate, int limit, int offset) {
        return productDao.readActiveProductsByCategory(category.getId(), currentDate, limit, offset);
    }

  /*  @Override
    public List<ProductBundle> findAutomaticProductBundles() {
        List<ProductBundle> bundles =  productDao.readAutomaticProductBundles();
        Collections.sort(bundles, new ProductBundleComparator());
        return bundles;
    }*/

    @Override
    @Transactional("blTransactionManager")
    public Product saveProduct(Product product) {
        return productDao.save(product);
    }

    @Override
    @Transactional("blTransactionManager")
    public Category findCategoryById(Long categoryId) {
        return categoryDao.readCategoryById(categoryId);
    }

    @Override
    @Transactional("blTransactionManager")
    public Category findCategoryByName(String categoryName) {
        return categoryDao.readCategoryByName(categoryName);
    }

    @Override
    @Transactional("blTransactionManager")
    public List<Category> findCategoriesByName(String categoryName) {
        return categoryDao.readCategoriesByName(categoryName);
    }

    @Override
    @Transactional("blTransactionManager")
    public List<Category> findCategoriesByName(String categoryName, int limit, int offset) {
        return categoryDao.readCategoriesByName(categoryName, limit, offset);
    }

    @Override
    @Transactional("blTransactionManager")
    public Category saveCategory(Category category) {
        return categoryDao.save(category);
    }
    
    @Override
    @Transactional("blTransactionManager")
    public void removeCategory(Category category){
        categoryDao.delete(category);
    }

    @Override
    @Transactional("blTransactionManager")
    public void removeCategory(String categoryName){
        categoryDao.delete(categoryName);
    }
    
    @Override
    @Transactional("blTransactionManager")
    public void removeProduct(Product product) {
        productDao.delete(product);
    }
    
    @Override
    @Transactional("blTransactionManager")
    public void removeProduct(Long productId) {
     
    	   try {
			productDao.delete(productId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
     
       
       
    	
    }

    @Override
    @Transactional("blTransactionManager")
    public List<Category> findAllCategories() {
        return categoryDao.readAllCategories();
    }

    @Override
    @Transactional("blTransactionManager")
    public List<Category> findAllCategories(int limit, int offset) {
        return categoryDao.readAllCategories(limit, offset);
    }
    
    @Override
    @Transactional("blTransactionManager")
    public List<Category> findAllParentCategories() {
        return categoryDao.readAllParentCategories();
    }

    @Override
    @Transactional("blTransactionManager")
    public List<Category> findAllSubCategories(String categoryName) {
        return categoryDao.readAllSubCategories(categoryName);
    }

    @Override
    @Transactional("blTransactionManager")
    public List<Category> findAllSubCategories(Category category, int limit, int offset) {
        return categoryDao.readAllSubCategories(category, limit, offset);
    }

    @Override
    @Transactional("blTransactionManager")
    public List<Category> findActiveSubCategoriesByCategory(Category category) {
        return categoryDao.readActiveSubCategoriesByCategory(category);
    }

    @Override
    @Transactional("blTransactionManager")
    public List<Category> findActiveSubCategoriesByCategory(Category category, int limit, int offset) {
        return categoryDao.readActiveSubCategoriesByCategory(category, limit, offset);
    }

    @Override
    @Transactional("blTransactionManager")
    public List<Product> findAllProducts() {
        return categoryDao.readAllProducts();
    }

    @Override
    @Transactional("blTransactionManager")
    public List<Product> findAllProducts(int limit, int offset) {
        return categoryDao.readAllProducts(limit, offset);
    }

   /* @Override
    public List<Sku> findAllSkus() {
        return skuDao.readAllSkus();
    }

    @Override
    public Sku findSkuById(Long skuId) {
        return skuDao.readSkuById(skuId);
    }

    @Override
    @Transactional("blTransactionManager")
    public Sku saveSku(Sku sku) {
        return skuDao.save(sku);
    }*/
    
 /*   @Override
    @Transactional("blTransactionManager")
    public SkuFee saveSkuFee(SkuFee fee) {
        return skuDao.saveSkuFee(fee);
    }
    */
   /* @Override
    public List<Sku> findSkusByIds(List<Long> ids) {
        return skuDao.readSkusById(ids);
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    public void setSkuDao(SkuDao skuDao) {
        this.skuDao = skuDao;
    }*/

    @Override
    @Transactional("blTransactionManager")
    public List<Product> findProductsForCategory(Category category) {
        return productDao.readProductsByCategory(category.getId());
    }

    @Override
    @Transactional("blTransactionManager")
    public List<Product> findProductsForCategory(Category category, int limit, int offset) {
        return productDao.readProductsByCategory(category.getId(), limit, offset);
    }

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    @Transactional("blTransactionManager")
    public Map<String, List<Long>> getChildCategoryURLMapByCategoryId(Long categoryId) {
        Category category = findCategoryById(categoryId);
        if (category != null) {
            return category.getChildCategoryURLMap();
        }
        return null;
    }
    
    @Override
    @Transactional("blTransactionManager")
    public  void createCategory(String categoryName,Date activeStartDate,Date activeEndDate,String longDescription,String defaultParentCategory) {
    	this.categoryDao.createCategory(categoryName,activeStartDate,activeEndDate,longDescription,defaultParentCategory);
    	        
    }

	
	@Override
	@Transactional("blTransactionManager")
	public Category findCategoryByURI(String uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional("blTransactionManager")
	public Product findProductByURI(String uri) {
		// TODO Auto-generated method stub
		return null;
	}

	
    
   /* @Override
    public Sku createSku() {
        return skuDao.create();
    }*/
    
    @Override
    public Product createProduct(ProductType productType) {
        return productDao.create(productType);
    }
    
    @Override
    @Transactional("blTransactionManager")
    public void createProduct(String productName,String description,String longDescription,Date activeStartDate,Date activeEndDate,String manufacturer,Boolean isFeaturedProduct,String model,String defaultCategory) {
    	this.productDao.createProduct(productName,description,longDescription,activeStartDate,activeEndDate,manufacturer,isFeaturedProduct,model,defaultCategory);
    	
    }
    
   
    
    
/*
    @Override
    public List<ProductOption> readAllProductOptions() {
        return productOptionDao.readAllProductOptions();
    }
    
    @Override
    @Transactional("blTransactionManager")
    public ProductOption saveProductOption(ProductOption option) {
        return productOptionDao.saveProductOption(option);
    }
    
    @Override
    public ProductOption findProductOptionById(Long productOptionId) {
        return productOptionDao.readProductOptionById(productOptionId);
    }

    @Override
    public ProductOptionValue findProductOptionValueById(Long productOptionValueId) {
        return productOptionDao.readProductOptionValueById(productOptionValueId);
    }*/

	@Override
	public List<Category> findAllSubCategories(Category category) {
		return null;
	}

	@Override
	public List<Product> findProductsForCategory(String categoryName) {
		return productDao.readProductsByCategoryName(categoryName);
	}

   /* @SuppressWarnings("rawtypes")
	@Override
    public Category findCategoryByURI(String uri) {
        if (extensionManager != null) {
            ExtensionResultHolder holder = new ExtensionResultHolder();
            ExtensionResultStatusType result = extensionManager.getProxy().findCategoryByURI(uri, holder);
            if (ExtensionResultStatusType.HANDLED.equals(result)) {
                return (Category) holder.getResult();
            }
        }
        return categoryDao.findCategoryByURI(uri);
    }

    @SuppressWarnings("rawtypes")
	@Override
    public Product findProductByURI(String uri) {
        if (extensionManager != null) {
            ExtensionResultHolder holder = new ExtensionResultHolder();
            ExtensionResultStatusType result = extensionManager.getProxy().findProductByURI(uri, holder);
            if (ExtensionResultStatusType.HANDLED.equals(result)) {
                return (Product) holder.getResult();
            }
        }
        List<Product> products = productDao.findProductByURI(uri);
        if (products == null || products.size() == 0) {
            return null;
        } else if (products.size() == 1) {
            return products.get(0);
        } else {
            // First check for a direct hit on the url
            for(Product product : products) {
                if (uri.equals(product.getUrl())) {
                    return product;
                }
            }
            
            for(Product product : products) {
                // Next check for a direct hit on the generated URL.
                if (uri.equals(product.getGeneratedUrl())) {
                    return product;
                }
            }
            
            // Otherwise, return the first product
            return products.get(0);
        }
    }

	@Override
	public Map<String, List<Long>> getChildCategoryURLMapByCategoryId(
			Long categoryId) {
		// TODO Auto-generated method stub
		return null;
	}*/
    
}
