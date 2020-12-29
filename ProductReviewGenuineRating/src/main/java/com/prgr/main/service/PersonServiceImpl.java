package com.prgr.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prgr.main.entity.Person;
import com.prgr.main.repository.PersonRepository;

@Service
@Transactional
public class PersonServiceImpl implements PersonService{
	
	@Autowired
	private PersonRepository personRepo;

	/**
	 * Registration of Users
	 */
	
	@Override
	public Person addPerson(Person person) {
		return personRepo.save(person);
		
	}

	/**
	 * Updating User details
	 */
	@Override
	public Person updatePerson(Person person) {
		Person presentPerson=personRepo.getOne(person.getPersonId());
		if(presentPerson==null) {
			return null;
		}
		else {
			
			return personRepo.save(person);
		}
		
	}

	/**
	 * fetch all Users from Repository
	 */
	@Override
	public List<Person> getAllPerson() {
		// TODO Auto-generated method stub
		return personRepo.findAll();
	}

	/**
	 * User login Checks with Repository
	 */
	@Override
	public boolean loginPerson(String email, String password) {
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
	 * Admin login Checks with Repository
	 */
	@Override
	public boolean loginAdmin(String username, String password) {
		// TODO Auto-generated method stub
		if(username.equals("admin") && password.equals("admin1234")) {
			return true;
		}
		else {
		return false;
		}
	}

}
