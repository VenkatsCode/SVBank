package com.svbank.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity (name = "UserSecurity")
@Table(name="USER_SECURITY")
public class UserSecurity {
     
    @Id
    @Column(name="USER_ID")
    private String userId;
     
    @Column(name="SQ1")
    private int securityQuestion1 ;
 
    @Column(name="SQ2")
    private int securityQuestion2;
 
    @Column(name="SA1")
    private String securityAnswer1;
     
    @Column(name="SA2")
    private String securityAnswer2;
    
    @Column(name="SEC_CODE")
    private String securityCode;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getSecurityQuestion1() {
		return securityQuestion1;
	}

	public void setSecurityQuestion1(int securityQuestion1) {
		this.securityQuestion1 = securityQuestion1;
	}

	public int getSecurityQuestion2() {
		return securityQuestion2;
	}

	public void setSecurityQuestion2(int securityQuestion2) {
		this.securityQuestion2 = securityQuestion2;
	}

	public String getSecurityAnswer1() {
		return securityAnswer1;
	}

	public void setSecurityAnswer1(String securityAnswer1) {
		this.securityAnswer1 = securityAnswer1;
	}

	public String getSecurityAnswer2() {
		return securityAnswer2;
	}

	public void setSecurityAnswer2(String securityAnswer2) {
		this.securityAnswer2 = securityAnswer2;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}
}