package com.hibernate.one.to.many.model;

import java.util.Set;

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
 
@Entity
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