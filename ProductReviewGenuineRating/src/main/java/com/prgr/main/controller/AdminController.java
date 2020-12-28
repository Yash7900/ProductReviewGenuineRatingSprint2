package com.prgr.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prgr.main.entity.Person;
import com.prgr.main.service.PersonService;

@RestController
@RequestMapping("/prgr/admin")
public class AdminController {
	
	@Autowired
	private PersonService personService;
	
	/**
	 * Admin Enter the username and password
	 * checks for proper credentials
	 * @param username
	 * @param password
	 * @return true
	 */
	@GetMapping("/login")
	public ResponseEntity<String> adminLogin(@RequestParam("username")String username,@RequestParam("password")String password) {
		boolean login=personService.loginAdmin(username, password);
		if(login) {
			return new ResponseEntity<String>("Login Successful", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Login Failed", HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * Admin fetch all users
	 * @return
	 */
	@GetMapping("/allusers")
	public ResponseEntity<List<Person>> getAllPerson(){
		List<Person> personList=personService.getAllPerson();
		if(personList.isEmpty()) {
			return new ResponseEntity("No Person Found.", HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<List<Person>>(personList, HttpStatus.OK);
		}
	}
}
