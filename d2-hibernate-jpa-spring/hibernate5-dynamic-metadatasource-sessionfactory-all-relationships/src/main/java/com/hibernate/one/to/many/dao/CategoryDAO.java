package com.hibernate.one.to.many.dao;

import com.hibernate.base.service.BaseAbstractDAO;
import com.hibernate.base.service.SessionFactoryService;
import com.hibernate.one.to.many.model.Category;

public class CategoryDAO extends BaseAbstractDAO<Category>{
	 public CategoryDAO(SessionFactoryService service) {
		 super(service);
	 }
}
