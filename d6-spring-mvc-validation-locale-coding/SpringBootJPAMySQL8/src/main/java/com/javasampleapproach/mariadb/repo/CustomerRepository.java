package com.javasampleapproach.mariadb.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.javasampleapproach.mariadb.entity.Customer;


public interface CustomerRepository extends CrudRepository<Customer, Long>{
	List<Customer> findByLastName(String lastName);
}
