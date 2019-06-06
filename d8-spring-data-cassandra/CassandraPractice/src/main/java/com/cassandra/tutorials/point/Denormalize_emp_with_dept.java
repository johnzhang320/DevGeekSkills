package com.cassandra.tutorials.point;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class Denormalize_emp_with_dept {
	/**
	 * emp is joint to dept, then employee has the department information
	 * 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 
		 String query = "CREATE TABLE IF NOT EXISTS emp_with_dept("
		 				+ "emp_id int,"
			            + "emp_name text, "
			            + "dept_name text, "
			            + "emp_city text, "
			            + "emp_sal varint, "
			            + "emp_phone varint, "			           
				        + "dept_location text, "
				        + "dept_phone varint,"
				        + "primary key (emp_id, dept_name)"
			            + " );";
		 Cluster cluster = Utils.getCluster(null);
		 Session session = Utils.getSession(cluster, Utils.KEYSPACE);
		 session.execute(query);
		 String insertTitle = "Insert into emp_with_dept ("
				    +"emp_id,"
				    + "emp_name, "
		            + "dept_name, "
		            + "emp_city, "
		            + "emp_sal, "
		            + "emp_phone, "			           
			        + "dept_location, "
			        + "dept_phone) values (";
		 
		 String insertData[] = {
				  "(101, 'R&D Center','5455 Great America Parkway,Santa Clara,CA',4089328976);",
		          "(102, 'Human Recource','5456 Great America Parkway,Santa Clara,CA',4089328976);",
		          "(103, 'Logistic Center','1472 Kinmberly, San Josea,CA',4082338976);",
		          "(104, 'Marketing','2123 Zenker Ave, San Jose,CA',5109328976);"
			  };
	}

}
