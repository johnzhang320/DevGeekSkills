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
 
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "PRODUCT_DETAIL")
public class ProductDetail {
	
	private long productId;
    private String partNumber;
    private String dimension;
    private float weight;
    private String manufacturer;
    private String origin;
    private Product121 product;
	
	
	public ProductDetail() {
 	}

	public ProductDetail(String partNumber, String dimension, float weight, String manufacturer, String origin,
			Product121 product) {
		 
		this.partNumber = partNumber;
		this.dimension = dimension;
		this.weight = weight;
		this.manufacturer = manufacturer;
		this.origin = origin;
		this.product = product;
	}
	/*
	 * On the ProductDetail side, we need to use the @GenericGenerator to specify a foreign key strategy in order to generate 
	 * values for the product_id column as a foreign key. And the @OneToOne annotation tells Hibernate that the product field 
	 * is a one-to-one association which is mapped by this productDetail.
	 *
	 */
	@Id
	@GeneratedValue(generator="foreigngen",strategy=GenerationType.IDENTITY)
	@GenericGenerator(name="foreigngen",strategy="foreign",parameters=@Parameter(name="property",value="product"))
	@Column(name="PRODUCT_ID")
	public long getProductId() {
		return productId;
	}
	
 	public void setProductId(long productId) {
		this.productId = productId;
	}
 	/*
 	 * PRIMARY key is specified by Product121
 	 */
 	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL,mappedBy="productDetail")	
	public Product121 getProduct() {
		return product;
	}
	@Column(name = "PART_NUMBER")
	public String getPartNumber() {
		return partNumber;
	}
	
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	public String getDimension() {
		return dimension;
	}
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public void setProduct(Product121 product) {
		this.product = product;
	}
     
}
