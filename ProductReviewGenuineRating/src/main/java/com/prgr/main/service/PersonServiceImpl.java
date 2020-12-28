package com.prgr.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prgr.main.entity.Person;
import com.prgr.main.repository.PersonJpaRepository;

@Service
@Transactional
public class PersonServiceImpl implements PersonService{
	
	@Autowired
	private PersonJpaRepository personRepo;

	@Override
	public Person addPerson(Person person) {
		return personRepo.save(person);
		
	}

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

	@Override
	public List<Person> getAllPerson() {
		// TODO Auto-generated method stub
		return personRepo.findAll();
	}

	@Override
	public boolean loginPerson(String email, String Password) {
		// TODO Auto-generated method stub
		return false;
	}

}
