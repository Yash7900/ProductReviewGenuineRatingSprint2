package com.prgr.main.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Staff {

	@Id
	private Integer staffId;
	
	@Column
	private String emailId;
	
	@Column
	private String password;

	public Staff() {
		super();
	}

	

	public Staff(Integer staffId, String emailId, String password) {
		super();
		this.staffId = staffId;
		this.emailId = emailId;
		this.password = password;
	}



	public Integer getStaffId() {
		return staffId;
	}



	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}



	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	@Override
	public String toString() {
		return "Staff [staffId=" + staffId + ", emailId=" + emailId + ", password=" + password + "]";
	}
	
	
}
