package com.hibernate.base.service.impl;

import com.hibernate.base.service.ProxyDAO;
import com.hibernate.one.to.many.dao.CategoryDAO;
 

public class SerivceProxyDAOImpl implements ProxyDAO {
	
	 private CategoryDAO categoryDAO;

	public CategoryDAO getCategoryDAO() {
		return categoryDAO;
	}

	public void setCategoryDAO(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}
	 
}
