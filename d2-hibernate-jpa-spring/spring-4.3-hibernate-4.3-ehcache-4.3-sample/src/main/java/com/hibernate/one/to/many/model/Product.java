package com.hibernate.one.to.many.model;

import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
/**
 * READ_ONLY: Used only for entities that never change (exception is thrown if an attempt to update such an entity is made). 
* 				It is very simple and performant. Very suitable for some static reference data that don’t change
	NONSTRICT_READ_WRITE: Cache is updated after a transaction that changed the affected data has been committed. Thus, strong 
				consistency is not guaranteed and there is a small time window in which stale data may be obtained from cache. This kind of
	 			strategy is suitable for use cases that can tolerate eventual consistency
	READ_WRITE: This strategy guarantees strong consistency which it achieves by using ‘soft’ locks: When a cached entity 
				is updated, a soft lock is stored in the cache for that entity as well, which is released after the transaction is
	 			committed. All concurrent transactions that access soft-locked entries will fetch the corresponding data directly 
	 			from database
	TRANSACTIONAL: Cache changes are done in distributed XA transactions. A change in a cached entity is either committed or
	 			rolled back in both database and cache in the same XA transaction
 * 
 */
@Entity
@Cacheable
@Cache(usage =  CacheConcurrencyStrategy.READ_WRITE, region="category")
@Table(name = "PRODUCT",
	uniqueConstraints = {
		@UniqueConstraint(
				columnNames= {"name"},
				name="uk_product_name"
		)
	}
)
public class Product {
    private long id;
    private String name;
    private String description;
    private float price;
 
    private Category category;
    
    private Set<Order> order;
    
    public Product() {
    }

	public Product(String name, String description, float price, Category category) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.category = category;
	}
	@Id
	@Column(name="PRODUCT_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CATEGORY_ID")
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	 
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="product")
	public Set<Order> getOrder() {
		return order;
	}

	public void setOrder(Set<Order> order) {
		this.order = order;
	}

	 
    
}