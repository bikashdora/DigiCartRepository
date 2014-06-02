package org.digicart.core.catalog.domain;



import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;

/**
 * The Class CategoryProductXrefImpl is the default implmentation of {@link Category}.
 * This entity is only used for executing a named query.
 *
 * If you want to add fields specific to your implementation of BroadLeafCommerce you should extend
 * this class and add your fields.  If you need to make significant changes to the class then you
 * should implement your own version of {@link Category}.
 * <br>
 * <br>
 * This implementation uses a Hibernate implementation of JPA configured through annotations.
 * The Entity references the following tables:
 * DC_CATEGORY_PRODUCT_XREF,
 *
 * @see {@link Category}, {@link ProductImpl}
 * @author btaylor
 */
@Entity
@Polymorphism(type = PolymorphismType.EXPLICIT)
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "DC_CATEGORY_PRODUCT_XREF")

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="blCategories")

public class CategoryProductXrefImpl implements CategoryProductXref {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_PRODUCT_ID")
    protected Long id;

    @ManyToOne(targetEntity = CategoryImpl.class, optional=false)
    @JoinColumn(name = "CATEGORY_ID")
    protected Category category = new CategoryImpl();

    /** The product. */
    @ManyToOne(targetEntity = ProductImpl.class, optional=false)
    @JoinColumn(name = "PRODUCT_ID")
    protected Product product = new ProductImpl();

    /** The display order. */
    @Column(name = "DISPLAY_ORDER", precision = 10, scale = 6)
    protected BigDecimal displayOrder;

    public BigDecimal getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(BigDecimal displayOrder) {
        this.displayOrder = displayOrder;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryProductXrefImpl)) return false;

        CategoryProductXrefImpl that = (CategoryProductXrefImpl) o;

        if (category != null ? !category.equals(that.category) : that.category != null) return false;
        if (product != null ? !product.equals(that.product) : that.product != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = category != null ? category.hashCode() : 0;
        result = 31 * result + (product != null ? product.hashCode() : 0);
        return result;
    }
}
