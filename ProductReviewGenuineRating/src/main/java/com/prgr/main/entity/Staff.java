package com.prgr.main.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Staff {

	@Id
	private Integer staffId;
	
	@Column
	private String userName;
	
	@Column
	private String password;

	public Staff() {
		super();
	}

	

	public Staff(Integer staffId, String userName, String password) {
		super();
		this.staffId = staffId;
		this.userName = userName;
		this.password = password;
	}



	public Integer getStaffId() {
		return staffId;
	}



	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}



	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	@Override
	public String toString() {
		return "Staff [staffId=" + staffId + ", userName=" + userName + ", password=" + password + "]";
	}
	
	
}
