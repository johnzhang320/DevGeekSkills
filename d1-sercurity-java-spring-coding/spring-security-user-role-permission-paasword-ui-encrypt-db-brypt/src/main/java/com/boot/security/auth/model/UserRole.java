package com.boot.security.auth.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="USERS_ROLES")
public class UserRole {
	private long id;
	private User user;
	private Role role;
	private String userRolePair;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "USER_GROUP_ID")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_ID")  
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ROLE_ID")
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	@Column(unique=true, name="USER_ROLE_PAIR")  // username + rolename
	public String getUserRolePair() {
		return userRolePair;
	}
	public void setUserRolePair(String userRolePair) {
		this.userRolePair = userRolePair;
	}
	public void createUserRolePair() {
		if (this.user!=null && this.role!=null) {
			this.userRolePair = user.getUsername()+"_"+role.getName();
		} else {
			System.out.println("user or role are not initialized yet!");
		}
	}
}
