package com.boot.security.auth.model;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
@Entity
@Table(name="ROLES_PERMISSIONS")
public class RolePermission {
	
	private Long id;
	
	private Role role;
	
	private Permission permission;

    private String rolePermissionPair;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ROLE_PERMISSION_ID")    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ROLE_ID")
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="PERMISSION_ID")
	@OrderBy("PERMISSION_ID")
	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}
	
	@Column(name="ROLE_PERMISSION_PAIR")
	public String getRolePermissionPair() {
		return rolePermissionPair;
	}

	public void setRolePermissionPair(String rolePermissionPair) {
		this.rolePermissionPair = rolePermissionPair;
	}
	
	public void createRolePermissionPair() {
		if (this.role!=null && this.permission!=null) {
			this.rolePermissionPair = role.getName()+"_"+permission.getName();
		} else {
			System.out.println("role or permission are not initialized yet!");
		}
	}
}
