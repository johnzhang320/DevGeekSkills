package com.db.to.code.hibernate.model;
// Generated May 21, 2019 3:01:58 PM by Hibernate Tools 5.2.10.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Product generated by hbm2java
 */
@Entity
@Table(name = "product", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Product implements java.io.Serializable {

	private int productId;
	private Category category;
	private String name;
	private String description;
	private Float price;

	public Product() {
	}

	public Product(int productId) {
		this.productId = productId;
	}

	public Product(int productId, Category category, String name, String description, Float price) {
		this.productId = productId;
		this.category = category;
		this.name = name;
		this.description = description;
		this.price = price;
	}

	@Id

	@Column(name = "product_id", unique = true, nullable = false)
	public int getProductId() {
		return this.productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Column(name = "name", unique = true, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", length = 512)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "price", precision = 12, scale = 0)
	public Float getPrice() {
		return this.price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", category=" + category + ", name=" + name + ", description="
				+ description + ", price=" + price + "]";
	}

}
