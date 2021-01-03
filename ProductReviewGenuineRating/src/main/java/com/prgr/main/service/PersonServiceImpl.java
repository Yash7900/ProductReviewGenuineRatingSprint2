package com.prgr.main.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prgr.main.entity.Person;
import com.prgr.main.exception.UserNotFoundException;
import com.prgr.main.repository.PersonRepository;

@Service
@Transactional
/**
 * PersonServiceImpl class
 * @author YASH
 *
 */
public class PersonServiceImpl implements PersonService{
	private static final Logger logger=LoggerFactory.getLogger(PersonServiceImpl.class);
	@Autowired
	private PersonRepository personRepo;

	/**
	 * This method takes person details from controller 
	 * and add it to the repository.
	 * @param 
	 * @return Person Object
	 */
	@Override
	public Person addPerson(Person person) {
		logger.info("adding Person");
		return personRepo.save(person);
		
	}

	/**
	 * This method takes person details from controller 
	 * and update it to the repository.
	 * @param 
	 * @return Product Object
	 */
	@Override
	public Person updatePerson(Person person) {
		logger.info("Updating Person");
		Person presentPerson=personRepo.getOne(person.getPersonId());
		if(presentPerson==null) {
			return null;
		}
		else {
			
			return personRepo.save(person);
		}
		
	}

	/**
	 * This method get calls from controller 
	 * and fetch all the person from repository.
	 * @param 
	 * @return  List<Person>
	 */
	@Override
	public List<Person> getAllPerson() {
		logger.info("getAllPerson()");
		// TODO Auto-generated method stub
		return personRepo.findAll();
	}

	/**
	 * This method takes credentials from controller and then 
	 * checks it with repository.
	 * @param username
	 * @param password
	 * @return boolean(T/F)
	 * @throws UserNotFoundException 
	 */
	@Override
	public boolean loginPerson(String email, String password) {
		logger.info("Person login");
		// TODO Auto-generated method stub
		Person person=personRepo.findByEmailId(email);
		if(person.getEmailId().equals(email) && person.getPassword().equals(password)) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * This method takes credentials from controller and then 
	 * checks it with repository.
	 * @param username
	 * @param password
	 * @return boolean(T/F)
	 */
	@Override
	public boolean loginAdmin(String username, String password) {
		// TODO Auto-generated method stub
		logger.info("admin login");
		if(username.equals("admin") && password.equals("admin1234")) {
			return true;
		}
		else {
		return false;
		}
	}
	/**
	 * This method get a person based on their 
	 * from repository
	 * @param personId
	 * @return boolean
	 */
	@Override
	public boolean getPerson(int personId) {
		// TODO Auto-generated method stub
		logger.info("getPerson()");
			if(personRepo.findById(personId).isPresent()) {
				return true;
			}
		return false;
	}

}
