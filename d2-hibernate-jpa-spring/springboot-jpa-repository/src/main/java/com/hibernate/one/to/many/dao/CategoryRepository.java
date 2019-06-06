package com.hibernate.one.to.many.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hibernate.one.to.many.model.Category;
 
public interface CategoryRepository extends JpaRepository<Category, Long>{
	public Category findByCategoryId(int categoryId);
	public Category findByName(String name);
}
