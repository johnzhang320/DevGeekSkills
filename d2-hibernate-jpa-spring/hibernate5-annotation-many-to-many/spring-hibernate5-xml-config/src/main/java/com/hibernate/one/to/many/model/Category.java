package com.hibernate.one.to.many.model;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
 
import javax.persistence.UniqueConstraint;
 
 
@Entity
@Table(name = "CATEGORY")
 
 
public class Category {
 
    private long id;
    
   
    private String name;
 
    public Category() {
	 
	}
    
   
    public Category(String name) {
		this.name = name;
	}

	private Set<Product> products;
    @Id
    @Column(name="CATEGORY_ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)  // IDENTITY is much better than AUTO because AUTO need hibernate_sequece if empty table, but IDENTITY create initial value(1) if empty table
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	@Column(name="NAME", unique=true, nullable=false, length=100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@OneToMany(mappedBy="category",cascade=CascadeType.ALL,fetch=FetchType.LAZY)   // LAZY cause HQL can not display any data (parent and child)
	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", products=" + products + "]";
	}
    
    
}