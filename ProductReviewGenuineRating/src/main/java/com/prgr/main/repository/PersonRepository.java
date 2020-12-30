package com.prgr.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prgr.main.entity.Person;

public interface PersonRepository extends JpaRepository<Person,Integer> {
	
	public Person findByEmailId(String email);
	
	

	

}
