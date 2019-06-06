package net.codejava.hibernate;

 
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.db.to.code.hibernate.model.Users;
 
 

public class UserDao {
	static Logger Log = Logger.getLogger( UserDao.class);
    protected static EntityManager entityManager;
    protected static EntityManagerFactory factory;
    private static Integer primaryKey = 7; 
    protected static void setup() {
        factory = Persistence.createEntityManagerFactory("UsersDB");
        entityManager = factory.createEntityManager();
    }
    public static void persistData() {
    	Users newUsers = new Users();
    	newUsers.setEmail("billjoy@gmail.com");
    	newUsers.setFullname("bill Joy");
    	newUsers.setPassword("billi");
    	entityManager.getTransaction().begin(); 
    	entityManager.persist(newUsers);
    	entityManager.getTransaction().commit();
    	 
    	
    }
    public static void updateData() {
    	Users existingUsers = new Users();
    	existingUsers.setUserId(1);
    	existingUsers.setEmail("John.zhang320@gmail.com");
    	existingUsers.setFullname("John Zhang");
    	existingUsers.setPassword("billionaire");
    	entityManager.getTransaction().begin();  
    	entityManager.merge(existingUsers);
    	entityManager.getTransaction().commit();
    	 
    }
    public static void findData() {
    	
    	Users user = entityManager.find(Users.class, primaryKey);
    	 
    	Log.info(user.getEmail());
    	Log.info(user.getFullname());
    	Log.info(user.getPassword());
    }
    public static void RunQuery() {
    	String sql = "SELECT u from Users u where u.email = 'John.zhang320@gmail.com'";
    	Query query = entityManager.createQuery(sql);
    	Users user = (Users) query.getSingleResult();
    	 
    	Log.info(user.getEmail());
    	Log.info(user.getFullname());
    	Log.info(user.getPassword());
    }
    public static void delete() {
     	entityManager.getTransaction().begin();   
    	Users reference = entityManager.getReference(Users.class, primaryKey);
    	entityManager.remove(reference);  // remove table totally
    	entityManager.getTransaction().commit(); 
    }
    public static void main(String[] args) {
    	setup();
    	Log.info("------------Delete----------------");
    /*	delete();
    	primaryKey = 2;
    	Log.info("------------Insert billjoy@gmail.com----------------");
    	persistData();
    	findData();
    	Log.info("-------------Change To John.zhang320@gmail.com----------------");
    	updateData();
     	findData(); */
    	Log.info("-------------Run SQL Query----------------");
    	//updateData();
    	RunQuery(); 
    	entityManager.close();
    	factory.close();
    	
    }
}
