package com.prgr.main.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.prgr.main.entity.Person;
import com.prgr.main.repository.PersonRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
/**
 * PersonServiceTest
 * @author Siddhi
 *
 */
class PersonServiceTest {

	@InjectMocks
	private PersonServiceImpl personService;
	
	@MockBean
	private PersonRepository personRepo;
	
	@Test
	public void testAddPerson() {
		Person person=new Person();
		person.setFirstName("yash");
		person.setLastName("varadkar");
		person.setAddress("amboli");
		person.setPhoneNumber(983356549);
		person.setEmailId("yash@email.com");
		person.setPassword("yash12");
		
		Mockito.when(personRepo.save(person)).thenReturn(person);
		assertThat(personService.addPerson(person)).isEqualTo(person);
	}
	
	@Test
	public void testUpdatePerson(){
		Person person=new Person();
		person.setPersonId(1);
		person.setFirstName("Siddhi");
		person.setLastName("Sawant");
		person.setAddress("Mumbai");
		person.setPhoneNumber(787878787);
		person.setEmailId("siddhi@email.com");
		person.setPassword("siddhi08");
		
		Mockito.when(personRepo.getOne(1)).thenReturn(person);
		person.setEmailId("siddhi123@email.com");
		Mockito.when(personRepo.save(person)).thenReturn(person);
		
		assertEquals("siddhi123@email.com", person.getEmailId());
	}
	
	@Test
	public void testGetAllPerson()
	{
		Person person1=new Person();
		person1.setFirstName("Jyoti");
		person1.setLastName("Patil");
		person1.setAddress("Pune");
		person1.setPhoneNumber(123456789);
		person1.setEmailId("jyoti@email.com");
		person1.setPassword("jyoti33");
		
		Person person2=new Person();
		person2.setFirstName("Siddhi");
		person2.setLastName("Sawant");
		person2.setAddress("Mumbai");
		person2.setPhoneNumber(787878787);
		person2.setEmailId("siddhi@email.com");
		person2.setPassword("siddhi08");
		
		List<Person> personList=new ArrayList<>();
		personList.add(person1);
		personList.add(person2);
		
		Mockito.when(personRepo.findAll()).thenReturn(personList);
		assertThat(personService.getAllPerson()).isEqualTo(personList);
	}
	
	@Test
	public void testLoginAdmin()
	{
		String username="admin";
		String password="admin1234";
		
		assertTrue(personService.loginAdmin(username, password));
		
		String username1="admin";
		String password1="Admin12345";
		
		assertFalse(personService.loginAdmin(username1, password1));
	}
	
	@Test
	public void testLoginPerson() 
	{
		Person person=new Person();
		person.setFirstName("Siddhi");
		person.setLastName("Sawant");
		person.setAddress("Mumbai");
		person.setPhoneNumber(787878787);
		person.setEmailId("siddhi@email.com");
		person.setPassword("siddhi08");
		
		assertEquals(person.getEmailId(),"siddhi@email.com");
		assertEquals(person.getPassword(),"siddhi08");
		Mockito.when(personRepo.findByEmailId("siddhi@email.com")).thenReturn(person);
		assertTrue(personService.loginPerson(person.getEmailId(),person.getPassword()));
	}
	
}
