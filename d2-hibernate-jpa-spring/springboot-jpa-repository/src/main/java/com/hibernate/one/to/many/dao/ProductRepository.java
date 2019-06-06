package com.hibernate.one.to.many.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hibernate.one.to.many.model.Product;

 

public interface ProductRepository extends JpaRepository<Product, Long>{

}
