package com.hibernate.many.to.many.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="USERS")
public class User {
	private long id;
	
	@Column(unique=true)
	private String username;
	private String password;
	private String email;
	
	private Set<Group> groups = new HashSet<Group>();
	
	
	public User() {
	 
	}
	public User(String username, String password, String email) {
		 
		
		this.username = username;
		this.password = password;
		this.email = email;

	}
	public void addGroup(Group group) {
		groups.add(group);
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="USER_ID")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	/*
	 * This side is much simpler than the owner side, as we only need to specify the mappedBy attribute of 
	 * the @ManyToMany annotation. That means this groups collection is mapped by the users collection on
	 *  the owner side.
	 */
	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="users")
 	public Set<Group> getGroups() {
		return groups;
	}
	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}
	
}
