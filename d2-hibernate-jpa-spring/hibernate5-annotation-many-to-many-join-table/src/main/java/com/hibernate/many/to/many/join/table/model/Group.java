package com.hibernate.many.to.many.join.table.model;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "GROUPS")
public class Group {
    private long id;
    private String name;
 
    private Set<UserGroup> userGroups = new HashSet<UserGroup>();
     
    public Group() {
    }
 
    public Group(String name) {
        this.name = name;
    }
         
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "GROUP_ID")
    public long getId() {
        return id;
    }
 
    public void setId(long id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    @OneToMany(mappedBy = "group")
    public Set<UserGroup> getUserGroups() {
        return userGroups;
    }
 
    public void setUserGroups(Set<UserGroup> groups) {
        this.userGroups = groups;
    }
     
    public void addUserGroup(UserGroup userGroup) {
        this.userGroups.add(userGroup);
    }
 
}
