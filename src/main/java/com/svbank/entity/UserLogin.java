package com.svbank.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity (name = "UserLogin")
@Table(name="USER_LOGIN")
public class UserLogin {
     
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="USERNAME")
    private BigInteger username;
     
    @Column(name="PASSWORD")
    private String password;
    
    @Column(name="ENABLED")
    private boolean enabled;
    
    @Column(name="DEFAULT_PWD")
    private boolean defaultPwd;

	public BigInteger getUsername() {
		return username;
	}

	public void setUsername(BigInteger username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isDefaultPwd() {
		return defaultPwd;
	}

	public void setDefaultPwd(boolean defaultPwd) {
		this.defaultPwd = defaultPwd;
	}
	
}