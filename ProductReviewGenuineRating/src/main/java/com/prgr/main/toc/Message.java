package com.prgr.main.toc;

import java.util.List;

import com.prgr.main.entity.Feedback;
import com.prgr.main.entity.Person;
import com.prgr.main.entity.Product;
import com.prgr.main.entity.Staff;

public class Message {
	
	private String resMessage;
	
	private List<Person> personList;
	
	private List<Product> productList;
	
	private List<Feedback> feedbackList;
	
	private int status;
	
	private List<Staff> staffList; 

	public List<Staff> getStaffList() {
		return staffList;
	}

	public void setStaffList(List<Staff> staffList) {
		this.staffList = staffList;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getResMessage() {
		return resMessage;
	}

	public void setResMessage(String resMessage) {
		this.resMessage = resMessage;
	}

	public List<Person> getPersonList() {
		return personList;
	}

	public void setPersonList(List<Person> personList) {
		this.personList = personList;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public List<Feedback> getFeedbackList() {
		return feedbackList;
	}

	public void setFeedbackList(List<Feedback> feedbackList) {
		this.feedbackList = feedbackList;
	}
	
	

}
