package com.hibernate.many.to.many.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(  name="GROUPS",
	     uniqueConstraints= {
	    		@UniqueConstraint(
	    				columnNames= {"name"},
	    				name="uk-mtom-group-name"
	    	    ) 
	     }
	  )
public class Group {
	private long id;
	private String name;
	private Set<User> users = new HashSet<User>();
	
	public Group() {
 	}

	public Group(String name) {
	 
		this.name = name;
	}
	@Id
	@Column	(name="GROUP_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
    public void addUser(User user) {
        this.users.add(user);
    }
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(
			name="USERS_GROUPS",
			joinColumns = @JoinColumn(name="GROUP_ID"),
			inverseJoinColumns = @JoinColumn(name="USER_ID")
	)
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	
}
