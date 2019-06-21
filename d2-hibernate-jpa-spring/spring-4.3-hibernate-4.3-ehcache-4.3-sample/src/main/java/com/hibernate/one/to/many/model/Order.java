package com.hibernate.one.to.many.model;

import java.util.Date;

import javax.persistence.Cacheable;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@Table(name = "ORDERS")
public class Order {
	private int id;
	private String customerName;
	private Date purchaseDate;
	private float amount;
	private Product product;
 
	@Id
	@Column(name = "ORDER_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "CUSTOMER_NAME")
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Column(name = "PURCHASE_DATE")
	@Temporal(TemporalType.DATE)
	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ID")
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
