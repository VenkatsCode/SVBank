package com.svbank.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity (name = "AccountRequests")
@Table(name="ACCOUNT_REQUESTS")
public class AccountRequests {
     
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="REQ_ID")
    private String reqId;
     
    @Column(name="F_NAME")
    private String firstName;
    
    @Column(name="l_NAME")
    private String lastName;
    
    @Column(name="GENDER")
    private String gender;
    
    @Column(name="DOB")
    private Date dob;
    
    @Column(name="ADDRESS")
    private String address;
    
    @Column(name="PH_NO")
    private String phoneNumber;
    
    @Column(name="EMAIL")
    private String email;
    
    @Column(name="STATUS")
    private int status;
    
    @Column(name="REJECT_REASON")
    private String rejectReason;
    
    

	public String getReqId() {
		return reqId;
	}

	public void setReqId(String reqId) {
		this.reqId = reqId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
}