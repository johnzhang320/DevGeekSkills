package com.hibernate.many.to.many.join.table.model;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
 
@Entity
@Table(name = "USERS_GROUPS")
public class UserGroup {
    private long id;
    private User user;
    private Group group;
     
    // additional fields
    private boolean activated;
    private Date registeredDate;
 
    @Id
    @GeneratedValue
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
    @JoinColumn(name = "GROUP_ID")
    public Group getGroup() {
        return group;
    }
 
    public void setGroup(Group group) {
        this.group = group;
    }
 
    public boolean isActivated() {
        return activated;
    }
 
    public void setActivated(boolean activated) {
        this.activated = activated;
    }
 
    @Column(name = "REGISTERED_DATE")
    @Temporal(TemporalType.DATE)
    public Date getRegisteredDate() {
        return registeredDate;
    }
 
    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }
}
