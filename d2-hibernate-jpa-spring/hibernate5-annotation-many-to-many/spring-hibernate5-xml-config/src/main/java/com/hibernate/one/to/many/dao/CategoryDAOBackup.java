package com.hibernate.one.to.many.dao;

import com.hibernate.base.service.BaseAbstractDAO;
import com.hibernate.base.service.SessionFactoryService;
import com.hibernate.one.to.many.model.Category;

public class CategoryDAOBackup extends BaseAbstractDAO<Category>{
	 public CategoryDAOBackup(SessionFactoryService service) {
		 super(service);
	 }
}
