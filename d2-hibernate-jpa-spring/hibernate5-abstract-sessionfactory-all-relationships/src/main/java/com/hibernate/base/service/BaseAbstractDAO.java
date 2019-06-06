package com.hibernate.base.service;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

import com.google.common.base.Preconditions;

public class BaseAbstractDAO<T> extends BaseHibernateDAO {
	
	public BaseAbstractDAO(SessionFactoryService service) {
		super(service);
	}
	protected Class<T> clazz;
	
    protected final void setClazz(final Class<T> clazzToSet) {
        clazz = Preconditions.checkNotNull(clazzToSet);
    }
 
    
    public T findOne(final long id) {
        return (T) getSession().get(clazz, id);
    }

   
    public List<T> findAll() {
        return getSession().createQuery("from " + clazz.getName()).list();
    }

   
    public void create(final T entity) {
        Preconditions.checkNotNull(entity);
        getSession().saveOrUpdate(entity);
      }

   
    public T update(final T entity) {
        Preconditions.checkNotNull(entity);
        return (T) getSession().merge(entity);
    }

  
    public void delete(final T entity) {
        Preconditions.checkNotNull(entity);
        getSession().delete(entity);
    }


    public void deleteById(final long entityId) {
        final T entity = findOne(entityId);
        Preconditions.checkState(entity != null);
        delete(entity);
    }

	
}
