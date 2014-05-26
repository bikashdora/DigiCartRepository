package org.digicart.core.catalog.domain;

/*
 * CategoryImpl.java
 */


import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.digicart.common.persistence.ArchiveStatus;
import org.digicart.common.persistence.Status;
import org.digicart.common.util.DateUtil;
import org.digicart.common.util.UrlUtil;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Type;

/**
 * @author bTaylor
 * @author Jeff Fischer
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="DC_CATEGORY")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="dcCategories")
@SQLDelete(sql="UPDATE DC_CATEGORY SET ARCHIVED = 'Y' WHERE CATEGORY_ID = ?")

public class CategoryImpl implements Category,Status {

    

	private static final long serialVersionUID = 1L;
    private static final Log LOG = LogFactory.getLog(CategoryImpl.class);
    
    private static String buildLink(Category category, boolean ignoreTopLevel) {
        Category myCategory = category;
        StringBuilder linkBuffer = new StringBuilder(50);
        while (myCategory != null) {
            if (!ignoreTopLevel || myCategory.getDefaultParentCategory() != null) {
                if (linkBuffer.length() == 0) {
                    linkBuffer.append(myCategory.getUrlKey());
                } else if(myCategory.getUrlKey() != null && !"/".equals(myCategory.getUrlKey())){
                    linkBuffer.insert(0, myCategory.getUrlKey() + '/');
                }
            }
            myCategory = myCategory.getDefaultParentCategory();
        }

        return linkBuffer.toString();
    }
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID" ,nullable = true)  
    protected Long id;

    @Column(name = "NAME", nullable = true)
    @Index(name="CATEGORY_NAME_INDEX", columnNames={"NAME"})   
    protected String name;

    @Column(name = "URL",nullable = true)  
    @Index(name="CATEGORY_URL_INDEX", columnNames={"URL"})
    protected String url;

    @Column(name = "URL_KEY",nullable = true)
    @Index(name="CATEGORY_URLKEY_INDEX", columnNames={"URL_KEY"})  
    protected String urlKey;

    @Column(name = "DESCRIPTION", nullable = true)
    protected String description;

    @Column(name = "TAX_CODE")
    protected String taxCode;

    @Column(name = "ACTIVE_START_DATE", nullable = true)
    protected Date activeStartDate;

    @Column(name = "ACTIVE_END_DATE", nullable = true)
    protected Date activeEndDate;

    @Column(name = "DISPLAY_TEMPLATE", nullable = true)
    protected String displayTemplate;

    @Lob
    @Type(type = "org.hibernate.type.StringClobType")
    @Column(name = "LONG_DESCRIPTION", length = Integer.MAX_VALUE - 1,nullable = true)
    protected String longDescription;

    @ManyToOne(targetEntity = CategoryImpl.class)
    @JoinColumn(name = "DEFAULT_PARENT_CATEGORY_ID", nullable = true)
    @Index(name="CATEGORY_PARENT_INDEX", columnNames={"DEFAULT_PARENT_CATEGORY_ID"})
    protected Category defaultParentCategory;

    @Embedded
    protected ArchiveStatus archiveStatus = new ArchiveStatus();   

 
    
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
    

    @Override
    public String getDescription() {
        return  description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

   
    @Override
    public void setActiveStartDate(Date activeStartDate) {
        this.activeStartDate = (activeStartDate == null) ? null : new Date(activeStartDate.getTime());
    }

    @Override
    public Date getActiveEndDate() {
        return activeEndDate;
    }

    @Override
    public void setActiveEndDate(Date activeEndDate) {
        this.activeEndDate = (activeEndDate == null) ? null : new Date(activeEndDate.getTime());
    }

    @Override
    public boolean isActive() {
        if (LOG.isDebugEnabled()) {
            if (!DateUtil.isActive(activeStartDate, activeEndDate, true)) {
                LOG.debug("category, " + id + ", inactive due to date");
            }
            if ('Y'==getArchived()) {
                LOG.debug("category, " + id + ", inactive due to archived status");
            }
        }
        return DateUtil.isActive(activeStartDate, activeEndDate, true) && 'Y'!=getArchived();
    }

    

   

	@Override
    public String getLongDescription() {
        return longDescription;
    }

    @Override
    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    @Override
    public Category getDefaultParentCategory() {
        return defaultParentCategory;
    }

    @Override
    public void setDefaultParentCategory(Category defaultParentCategory) {
        this.defaultParentCategory = defaultParentCategory;
    }

    @Override
    public Character getArchived() {
        if (archiveStatus == null) {
            archiveStatus = new ArchiveStatus();
        }
        return archiveStatus.getArchived();
    }

    @Override
    public void setArchived(Character archived) {
        if (archiveStatus == null) {
            archiveStatus = new ArchiveStatus();
        }
        archiveStatus.setArchived(archived);
    }
    
    @Override
    public String getUrl() {
        // TODO: if null return
        // if blank return
        // if startswith "/" return
        // if contains a ":" and no "?" or (contains a ":" before a "?") return
        // else "add a /" at the beginning
        if(url == null || url.equals("") || url.startsWith("/")) {
            return url;       
        } else if ((url.contains(":") && !url.contains("?")) || url.indexOf('?', url.indexOf(':')) != -1) {
            return url;
        } else {
            return "/" + url;
        }
    }
    
    @Override
    public String getGeneratedUrl() {
        return buildLink(this, false);
    }
    
    @Override
    public void setUrlKey(String urlKey) {
        this.urlKey = urlKey;
    }
    
    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getUrlKey() {
        if ((urlKey == null || "".equals(urlKey.trim())) && name != null) {
            return UrlUtil.generateUrlKey(name);
        }
        return urlKey;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (url == null ? 0 : url.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CategoryImpl other = (CategoryImpl) obj;

        if (id != null && other.id != null) {
            return id.equals(other.id);
        }

        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (url == null) {
            if (other.url != null) {
                return false;
            }
        } else if (!url.equals(other.url)) {
            return false;
        }
        return true;
    }

    //public static class Presentation {}

  /*  @Override
    public String getMainEntityName() {
        return getName();
    }
*/
    @Override
    public String getTaxCode() {
        return this.taxCode;
    }

    @Override
    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

	@Override
	public Date getActiveStartDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDisplayTemplate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDisplayTemplate(String displayTemplate) {
		// TODO Auto-generated method stub
		
	}
/*
	@Override
	public List<Category> buildCategoryHierarchy(List<Category> currentHierarchy) {
		// TODO Auto-generated method stub
		return null;
	}*/

/*	@Override
	public List<Category> buildFullCategoryHierarchy(
			List<Category> currentHierarchy) {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	public List<Category> getAllChildCategories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasAllChildCategories() {
		// TODO Auto-generated method stub
		return false;
	}

	/*@Override
	public void setAllChildCategories(List<Category> childCategories) {
		// TODO Auto-generated method stub
		
	}*/

	@Override
	public List<Category> getChildCategories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Long> getChildCategoryIds() {
		// TODO Auto-generated method stub
		return null;
	}

	/*@Override
	public void setChildCategoryIds(List<Long> childCategoryIds) {
		// TODO Auto-generated method stub
		
	}*/

	@Override
	public boolean hasChildCategories() {
		// TODO Auto-generated method stub
		return false;
	}

	/*@Override
	public void setChildCategories(List<Category> childCategories) {
		// TODO Auto-generated method stub
		
	}*/

	@Override
	public List<Category> getAllParentCategories() {
		// TODO Auto-generated method stub
		return null;
	}

/*	@Override
	public void setAllParentCategories(List<Category> allParentCategories) {
		// TODO Auto-generated method stub
		
	}*/

	@Override
	public List<Product> getActiveProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAllProducts(List<Product> allProducts) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Category> buildCategoryHierarchy(List<Category> currentHierarchy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> buildFullCategoryHierarchy(
			List<Category> currentHierarchy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAllChildCategories(List<Category> childCategories) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setChildCategoryIds(List<Long> childCategoryIds) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setChildCategories(List<Category> childCategories) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAllParentCategories(List<Category> allParentCategories) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, List<Long>> getChildCategoryURLMap() {
		// TODO Auto-generated method stub
		return null;
	}



	
		
	}

   