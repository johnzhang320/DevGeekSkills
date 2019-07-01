package com.boot.security.auth.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
@Entity
@Table(name="PERMISSIONS")
public class Permission {
	
	private Long id;

	private String name;

	private String category;       //c1 -- file_management, c2 -- connectivity , c3 --Acoount management, c4-- user management , c5--billing
	
	private List<RolePermission> rolePermissions = new ArrayList<RolePermission>();
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PERMISSION_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	@Column(name="name", unique=true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@OneToMany(mappedBy="permission", fetch=FetchType.EAGER)
	public List<RolePermission> getRolePermissions() {
		return rolePermissions;
	}

	public void setRolePermissions(List<RolePermission> rolePermissions) {
		this.rolePermissions = rolePermissions;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	 
	
	 
}
