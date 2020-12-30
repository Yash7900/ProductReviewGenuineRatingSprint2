package com.prgr.main.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Ignore;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.prgr.main.entity.Person;
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
class PersonRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private PersonRepository personRepo;
	
	public Person getPerson() {
		Person person=new Person();
		person.setFirstName("yash");
		person.setLastName("varadkar");
		person.setAddress("amboli");
		person.setPhoneNumber(983356549);
		person.setEmailId("yash@email.com");
		person.setPassword("yash08");
		return person;
	}

	@Test
	@Rollback(false)
	@Order(1)
	public void testAddPerson() {
		Person person=getPerson();
		Person savePerson=personRepo.save(person);
		Person getPerson=personRepo.getOne(savePerson.getPersonId());
		
		assertThat(getPerson).isEqualTo(savePerson);	
	}
	
	@Test
	@Order(2)
	public void getAllPerson(){
		List<Person> personList=personRepo.findAll();
		assertThat(personList).size().isGreaterThan(0);
	}
	
	@Test
	//@Ignore
	@Order(3)
	public void updatePerson() {
		Person person=getPerson();
		Person savedPerson=personRepo.getOne(1);
		savedPerson.setFirstName("ram");
		Person updatePerson=personRepo.save(savedPerson);
		assertThat(updatePerson).isNotEqualTo(savedPerson);	
	}

}
