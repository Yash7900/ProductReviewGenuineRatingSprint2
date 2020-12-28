package com.prgr.main.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prgr.main.entity.Person;
import com.prgr.main.service.PersonService;

@RestController
@RequestMapping("/prgr/user")
public class UserController {
	
	@Autowired
	private PersonService personService;
	
	@PostMapping("/addperson")
	public ResponseEntity<Person> addPerson(@Valid @RequestBody Person person){
		Person savePerson=personService.addPerson(person);
		if (savePerson == null) {
			return new ResponseEntity("Registration Failed", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Person>(savePerson, HttpStatus.OK);
		
	}
	
	@PutMapping("/updateperson")
	public ResponseEntity<Person> updatePerson(@Valid @RequestBody Person person){
		Person updtPerson=personService.updatePerson(person);
		if (updtPerson == null) {
			return new ResponseEntity("Registration Failed", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Person>(updtPerson, HttpStatus.OK);
		
	}
	

}
