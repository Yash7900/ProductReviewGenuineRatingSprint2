package com.prgr.main.service;

import java.util.List;

import com.prgr.main.entity.Person;

public interface PersonService {
	
	/**
	 * This method takes credentials from controller and then 
	 * checks it with repository.
	 * @param username
	 * @param password
	 * @return boolean(T/F)
	 */
	public boolean loginAdmin(String username,String password);
	/**
	 * This method takes credentials from controller and then 
	 * checks it with repository.
	 * @param username
	 * @param password
	 * @return boolean(T/F)
	 */
	public boolean loginPerson(String email,String password);
	/**
	 * This method takes person details from controller 
	 * and add it to the repository.
	 * @param 
	 * @return Person Object
	 */
	public Person addPerson(Person person);
	/**
	 * This method takes person details from controller 
	 * and update it to the repository.
	 * @param 
	 * @return Product Object
	 */
	public Person updatePerson(Person person);
	/**
	 * This method get calls from controller 
	 * and fetch all the person from repository.
	 * @param 
	 * @return  List<Person>
	 */
	public List<Person> getAllPerson();
	
	public boolean getPerson(int personId);
}
