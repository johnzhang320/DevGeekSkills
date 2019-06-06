package com.spring.data.cassandra.model;

import com.datastax.driver.core.DataType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.data.cassandra.core.cql.Ordering.DESCENDING;
import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.PARTITIONED;
/**
 * 
 * CREATE TABLE people_by_social_security(
	  social_security TEXT,
	  first_name TEXT,
	  date_of_birth TIMESTAMP,
	  person_id UUID,
	  last_name TEXT,
	  salary DOUBLE,
	  PRIMARY KEY ((social_security), date_of_birth, person_id)
	) WITH CLUSTERING ORDER BY (date_of_birth ASC, person_id DESC);
	
    primary key consists of partition and clustering columns, in this example the only partition column is  social_security and 
    the clustering columns are date_of_birth and person_id. 
	Primary key is defined by class : PersonKey.
 *
 */
/**
 * An external key class needs to implement Serializable and have it's equals and hashcode methods defined as foloowing class 
 * Once that is done, all we need to do is define how the primary key is formed by using @PrimaryKeyColumn on the properties that 
 * make up the key. PrimaryKeyColumn comes with a set of properties to give you all the control you need over the key.

	name ---- it represents the name of the column in the table. This is not necessary if property matches the field name.
	type ---- Takes in either PrimaryKeyType.PARTITIONED or PrimaryKeyType.CLUSTERED. It will be CLUSTERED by default so you only really
	          need to mark the partition columns with PARTITIONED.
	ordinal - Determines the order that the ordering is applied in. The lowest value is applied first, therefore in the above example
	          dateOfBirth's order is applied before id.
	ordering - Determines the direction that ordering is applied to a clustering column. Therefore if the field is also marked with 
	           PARTITIONED the value ordering provides is ignored. The value can be Ordering.ASCENDING or Ordering.DESCENDING with ASCENDING 
	           being the default value. Look back at the table definition and see how the annotations in the PersonKey match up to the primary key.
 */
@PrimaryKeyClass
public class PersonKey implements Serializable {

  @PrimaryKeyColumn(name = "social_security", type = PARTITIONED)
  private String socialSecurity;

  @PrimaryKeyColumn(name = "date_of_birth", ordinal = 0)  //apply before persion_id because ordinal =9 and person_id is 1
  private LocalDateTime dateOfBirth;

  @PrimaryKeyColumn(name = "person_id", ordinal = 1, ordering = DESCENDING)
  private UUID id;

  
 

  	public PersonKey(String socialSecurity, LocalDateTime dateOfBirth, UUID id) {
		super();
		this.socialSecurity = socialSecurity;
		this.dateOfBirth = dateOfBirth;
		this.id = id;
	}
	
	public String getSocialSecurity() {
		return socialSecurity;
	}
	
	public void setSocialSecurity(String socialSecurity) {
		this.socialSecurity = socialSecurity;
	}
	
	public LocalDateTime getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(LocalDateTime dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public UUID getId() {
		return id;
	}
	
	public void setId(UUID id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "PersonKey [socialSecurity=" + socialSecurity + ", dateOfBirth=" + dateOfBirth + ", id=" + id + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((socialSecurity == null) ? 0 : socialSecurity.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonKey other = (PersonKey) obj;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (socialSecurity == null) {
			if (other.socialSecurity != null)
				return false;
		} else if (!socialSecurity.equals(other.socialSecurity))
			return false;
		return true;
	}

 
}
