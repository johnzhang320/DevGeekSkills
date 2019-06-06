package com.hibernate.one.to.one.primary.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hibernate.one.to.one.primary.model.ProductDetail;;

 

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long>{

}
