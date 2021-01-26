package com.prgr.main.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prgr.main.entity.Person;
import com.prgr.main.entity.Staff;
import com.prgr.main.exception.UserNotFoundException;
import com.prgr.main.service.PersonService;
import com.prgr.main.toc.Message;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/prgr")
public class LoginController {
	private static final Logger LOGGER=LoggerFactory.getLogger(AdminController.class); //initializing Logger.
	
	@Autowired
	private PersonService personService;
	
	@PostMapping("/admin")
	/**
	 *  This method accepts login credentials from admin
	 * and pass that credentials to service layer loginAdmin() method.
	 * @param 
	 * @return  ResponseEntity<String>
	 */
	
	public ResponseEntity<Message> adminLogin(@RequestBody Staff staff) {
		LOGGER.info("admin Login");
		List<Staff> login = personService.loginAdmin(staff.getEmailId(),staff.getPassword());
		Message msg=new Message();
		if (login.isEmpty()) {
			LOGGER.info("admin Login Failed");
			msg.setResMessage("Login Failed ");
			msg.setStatus(404);
			return new ResponseEntity<Message>(msg,HttpStatus.NOT_FOUND);
			
		} else {
			LOGGER.info("admin Login Successful");
			msg.setResMessage("Login Successful ");
			msg.setStatus(200);
			msg.setStaffList(login);
			return new ResponseEntity<Message>(msg,HttpStatus.OK);
		}
	}
	
	/**
	 *  This method accepts login credentials from user
	 * and pass that credentials to service layer loginPerson() method.
	 * @param 
	 * @return  ResponseEntity<String>
	 * @throws UserNotFoundException 
	 */
	@PostMapping("/user")
	public ResponseEntity<Message> userLogin(@RequestBody Person person) throws UserNotFoundException{
		LOGGER.info("user Login");
		List<Person> login=personService.loginPerson(person.getEmailId(),person.getPassword());
		Message msg=new Message();
			if(login.isEmpty()) {
				LOGGER.info("user Login Failed");
				msg.setResMessage(" Login Failed ");
				msg.setStatus(404);
				return new ResponseEntity<Message>(msg,HttpStatus.NOT_FOUND);
				
			}
			else {
				LOGGER.info("user Login Successful");
				msg.setResMessage("Login Successful ");
				msg.setPersonList(login);
				msg.setStatus(200);
				return new ResponseEntity<Message>(msg,HttpStatus.OK);
			}	
		}
		
	
	}

