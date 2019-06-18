package com.hibernate.one.to.one.test;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.stat.Statistics;

import com.hibernate.one.to.one.model.Address;
import com.hibernate.one.to.one.model.Employee;
import com.hibernate.utils.HibernateUtil;

public class HibernateEHCacheMain {
	static Logger Log = Logger.getLogger( HibernateEHCacheMain.class);
	public static void main(String[] args) {
		
		Log.info("Temp Dir:"+System.getProperty("java.io.tmpdir"));
		
		//Initialize Sessions
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Statistics stats = sessionFactory.getStatistics();
		Log.info("Stats enabled="+stats.isStatisticsEnabled());
		stats.setStatisticsEnabled(true);
		Log.info("Stats enabled="+stats.isStatisticsEnabled());
		
		Session session = sessionFactory.openSession();
		Session otherSession = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Transaction otherTransaction = otherSession.beginTransaction();
		
		printStats(stats, 0);
		
		Employee emp = (Employee) session.get(Employee.class, 1L);   // get return null and load exception if no record
		if (emp==null) {
			Address adr = new Address();
			adr.setAddressLine1("1445 Broadway");
			
			adr.setCity("New York");
			adr.setZipcode("91002");
			emp = new Employee();
			 
			emp.setAddress(adr);
			emp.setName("Jennifer Lopez");
			emp.setSalary(1323423312L);
			 
			session.save(emp);
			transaction.commit();
			emp = (Employee) session.get(Employee.class, 1L);
		}
		printData(emp, stats, 1);
		
		emp = (Employee) session.load(Employee.class, 1L);
		printData(emp, stats, 2);
		
		//clear first level cache, so that second level cache is used
		session.evict(emp);
		emp = (Employee) session.load(Employee.class, 1L);
		printData(emp, stats, 3);
		
		//emp = (Employee) session.load(Employee.class, 3L);
		//printData(emp, stats, 4);
		
		emp = (Employee) otherSession.load(Employee.class, 1L);
		printData(emp, stats, 5);
		
		//Release resources
		transaction.commit();
		otherTransaction.commit();
		sessionFactory.close();
	}

	public static void printStats(Statistics stats, int i) {
		System.out.println("\n***** Test Case " + i + " *****");
		System.out.println("Fetch Count="
				+ stats.getEntityFetchCount());
		System.out.println("Second Level Hit Count="
				+ stats.getSecondLevelCacheHitCount());
		System.out.println("Second Level Miss Count="
						+ stats
								.getSecondLevelCacheMissCount());
		System.out.println("Second Level Put Count="
				+ stats.getSecondLevelCachePutCount()+"\n");
	}

	public static void printData(Employee emp, Statistics stats, int count) {
		System.out.println(count+":: Name="+emp.getName()+", Zipcode="+emp.getAddress().getZipcode());
		printStats(stats, count);
	}

}
