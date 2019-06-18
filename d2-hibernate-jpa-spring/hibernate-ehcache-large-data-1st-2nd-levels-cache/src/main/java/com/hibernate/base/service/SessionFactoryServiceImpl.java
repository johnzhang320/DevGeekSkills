package com.hibernate.base.service;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hibernate.one.to.many.dao.DmarcDetailReportsDAO;
import com.hibernate.utils.HibernateUtil;

public class SessionFactoryServiceImpl implements SessionFactoryService {
	private static final Logger Log = Logger
			.getLogger(SessionFactoryServiceImpl.class);
	private static SessionFactory sessionFactory =null;
	private  Session session;
	private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
	public SessionFactory getSessionFactory() {
		sessionFactory = HibernateUtil.getSessionFactory();
		return sessionFactory;
	}
	public Session getSession() {
		  
    	session = 	threadLocal.get(); 
    	try {
	    	if (session == null || !session.isOpen()) {
				if (sessionFactory == null) {
					getSessionFactory();
				}
				session = (sessionFactory != null) ? sessionFactory.openSession()
						: null;
				threadLocal.set(session);
				 
			}
    	} catch (Exception e) {
    		Log.info("Failed to get Session:"+e.getMessage());
    		
    	}
        return session;
    }
		
	public void closeSession()  {
        Session session = (Session) threadLocal.get();
        threadLocal.set(null);

        if (session != null) {
            session.close();
        }
    }

}
