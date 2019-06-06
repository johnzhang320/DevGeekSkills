package com.hibernate.one.to.one.primary.model;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
/*
 * create database productsdb;
use productsdb;
 
CREATE TABLE `product` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `description` varchar(512) NOT NULL,
  `price` float NOT NULL,
  PRIMARY KEY (`product_id`)
);
 
CREATE TABLE `product_detail` (
  `product_id` int(11) NOT NULL,
  `part_number` varchar(45) NOT NULL,
  `dimension` varchar(45) NOT NULL,
  `weight` float NOT NULL,
  `manufacturer` varchar(45) NOT NULL,
  `origin` varchar(45) NOT NULL,
  PRIMARY KEY (`product_id`)
);
 */

@Entity
@Table(name = "PRODUCT121")
public class Product121 {
	private long productId;
    private String name;
    private String description;
    private float price;
    private ProductDetail productDetail;
    
    
    
	public Product121() {
	}
	
	public Product121( String name, String description, float price, ProductDetail productDetail) {
		
		this.name = name;
		this.description = description;
		this.price = price;
		this.productDetail = productDetail;
	}
	/*
	 * @Id and @GeneratedValue: are used in conjunction to map a field as the primary key of the table. Typically, 
	 * the primary key’s values are auto-generated.
	 * On the Product side, we use the @OneToOne and @PrimaryKeyJoinColumn annotations to tell Hibernate creates a 
	 * one-to-one association with the ProductDetail and the join column is the primary key column.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PRODUCT_ID")
	public long getProductId() {
		return productId;
	}
	
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn
	public ProductDetail getProductDetail() {
		return productDetail;
	}
	
	public void setProductId(long productId) {
		this.productId = productId;
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

	public void setProductDetail(ProductDetail productDetail) {
		this.productDetail = productDetail;
	}
    
    
}
