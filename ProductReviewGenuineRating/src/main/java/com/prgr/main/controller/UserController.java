package com.prgr.main.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prgr.main.entity.Person;
import com.prgr.main.service.PersonService;

@RestController
@RequestMapping("/prgr/user")
public class UserController {
	
	@Autowired
	private PersonService personService;
	
	/**
	 * This method is used to register user.It accepts details of user
	 * and pass the control to the service layer addPerson() method.
	 * @param 
	 * @return ResponseEntity<Person>
	 */
	@PostMapping("/addperson")
	public ResponseEntity<Person> addPerson(@Valid @RequestBody Person person){
		Person savePerson=personService.addPerson(person);
		if (savePerson == null) {
			return new ResponseEntity("Registration Failed", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Person>(savePerson, HttpStatus.OK);
		
	}
	/**
	 *  This method accpets login credentials from user
	 * and pass that credentials to service layer loginPerson() method.
	 * @param 
	 * @return  ResponseEntity<String>
	 */
	@GetMapping("/login")
	public ResponseEntity<String> adminLogin(@RequestParam("email")String email,@RequestParam("password")String password) {
		boolean login=personService.loginPerson(email, password);
		if(login) {
			return new ResponseEntity<String>("Login Successful", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Login Failed", HttpStatus.NOT_FOUND);
		}
	}
	/**
	 * It accepts details from user and pass it to
	 * the service layer updatePerson() method.
	 * @param 
	 * @return ResponseEntity<Person> 
	 */
	@PutMapping("/updateperson")
	public ResponseEntity<Person> updatePerson(@Valid @RequestBody Person person){
		Person updtPerson=personService.updatePerson(person);
		if (updtPerson == null) {
			return new ResponseEntity("Registration Failed", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Person>(updtPerson, HttpStatus.OK);
		
	}
	

}
