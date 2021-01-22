package com.prgr.main.service;

import java.util.List;

import com.prgr.main.entity.Person;
import com.prgr.main.exception.UserNotFoundException;

public interface PersonService {
	
	/**
	 * This method takes credentials from controller and then 
	 * checks it with repository.
	 * @param username
	 * @param password
	 * @return boolean(T/F)
	 */
	 boolean loginAdmin(String username,String password);
	/**
	 * This method takes credentials from controller and then 
	 * checks it with repository.
	 * @param username
	 * @param password
	 * @return boolean(T/F)
	 * @throws UserNotFoundException 
	 */
	 List<Person> loginPerson(String email,String password);
	/**
	 * This method takes person details from controller 
	 * and add it to the repository.
	 * @param 
	 * @return Person Object
	 */
	 Person addPerson(Person person);
	/**
	 * This method takes person details from controller 
	 * and update it to the repository.
	 * @param 
	 * @return Product Object
	 */
	 Person updatePerson(Person person);
	/**
	 * This method get calls from controller 
	 * and fetch all the person from repository.
	 * @param 
	 * @return  List<Person>
	 */
	 List<Person> getAllPerson();
	/**
	 * This method get a person based on their 
	 * from repository
	 * @param personId
	 * @return boolean
	 */
	 boolean getPerson(int personId);
}
