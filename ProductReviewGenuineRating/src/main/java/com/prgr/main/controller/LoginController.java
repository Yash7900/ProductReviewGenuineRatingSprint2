package com.prgr.main.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prgr.main.exception.UserNotFoundException;
import com.prgr.main.service.PersonService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/prgr")
public class LoginController {
	private static final Logger LOGGER=LoggerFactory.getLogger(AdminController.class); //initializing Logger.
	
	@Autowired
	private PersonService personService;
	
	@GetMapping("/adminlogin/{user}/{pass}")
	//
	public boolean adminLogin(@PathVariable("user")final String username,@PathVariable("pass")final String password) {
		LOGGER.info("admin Login");
		boolean login = personService.loginAdmin(username,password);
		if (login) {
			LOGGER.info("admin Login Successful");
			//return new ResponseEntity<String>("Login Successful", HttpStatus.OK);
			return true;
		} else {
			LOGGER.info("admin Login Failed");
			//return new ResponseEntity<String>("Login Failed", HttpStatus.NOT_FOUND);
			return false;
		}
	}
	
	/**
	 *  This method accepts login credentials from user
	 * and pass that credentials to service layer loginPerson() method.
	 * @param 
	 * @return  ResponseEntity<String>
	 * @throws UserNotFoundException 
	 */
	@GetMapping("/login/{user}/{pass}")
	public boolean userLogin(@PathVariable("user")final String email,@PathVariable("pass")final String password) throws UserNotFoundException{
		
			boolean login=personService.loginPerson(email, password);
			LOGGER.info("user Login");
			if(login) {
				LOGGER.info("user Login Successful");
				//return new ResponseEntity<String>("Login Successful", HttpStatus.OK);
				return true;
				
			}
			else {
				LOGGER.info("user Login Failed");
				//return new ResponseEntity<String>("Check your emailId and password", HttpStatus.NOT_FOUND);
				return false;
			}	
		}
		
	
	}

