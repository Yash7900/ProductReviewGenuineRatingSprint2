package com.prgr.main.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Reviewer")
/**
 * Person Entity Class
 * 
 * @author Aswathy
 *
 */
public class Person implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int personId;

	@Column(name = "FIRST_NAME")
	@NotNull
	@Size(min = 2, max = 15, message = "Enter Valid First_Name")
	private String firstName;

	@Column(name = "LAST_NAME")
	@NotNull
	@Size(min = 2, max = 15, message = "Enter Valid Last_Name")
	private String lastName;

	@Column(name = "ADDRESS")
	@NotNull
	@Size(min = 2, max = 250, message = "Enter Valid Address")
	private String address;

	@Column(name = "PHONE")
	// @Pattern(regexp="^[0-9]{10}",message="Enter Valid Phone number.")
	@NotNull
	@Digits(integer=10,fraction=0,message="Enter Valid PhoneNumber.")
	private Long phoneNumber;

	@Column(name = "EMAILID")
	@Email
	private String emailId;

	@Column(name = "Password")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Password must be combination of letters and numbers")
	private String password;

	

	public Person() {

	}

	public Person(final int personId,final String firstName,final String lastName,final String address,final Long phoneNumber,final String emailId,
			final String password) {
		super();
		this.personId = personId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.emailId = emailId;
		this.password = password;
		
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(final int personId) {
		this.personId = personId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(final String emailId) {
		this.emailId = emailId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(final long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	

	@Override
	public String toString() {
		return "Reviewer [personId=" + personId + ", firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber="
				+ phoneNumber + ", password=" + password + "]";
	}

}
