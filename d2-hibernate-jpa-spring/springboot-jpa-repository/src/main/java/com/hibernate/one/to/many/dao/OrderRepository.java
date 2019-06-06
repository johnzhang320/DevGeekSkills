package com.hibernate.one.to.many.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hibernate.one.to.many.model.Order;

 

public interface OrderRepository extends JpaRepository<Order, Long>{
	   
}
