package com.prgr.main.service;

import java.util.List;

import com.prgr.main.entity.Person;

public interface PersonService {
	
	public boolean loginAdmin(String username,String password);
	public boolean loginPerson(String email,String password);
	public Person addPerson(Person person);
	public Person updatePerson(Person person);
	public List<Person> getAllPerson();
}
