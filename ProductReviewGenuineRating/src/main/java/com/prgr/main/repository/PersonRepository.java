package com.prgr.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prgr.main.entity.Person;

public interface PersonRepository extends JpaRepository<Person,Integer> {
	/**
	 * This method checks if this email exists.
	 * @param email
	 * @return
	 */
	List<Person> findByEmailIdAndPassword(String email,String password);
	
	

	

}
