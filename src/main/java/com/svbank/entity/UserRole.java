package com.svbank.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity (name = "UserRole")
@Table(name="USER_ROLE")
public class UserRole {
     
    @Id
    @Column(name="USER_ID")
    private String userId;
     
    @Column(name="USER_ROLE")
    private String userRole;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
    
    
}