package com.github.PedroHenrique_LS;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.github.PedroHenrique_LS.model.Person;

@Service
public class PersonService {

	private final AtomicLong counter = new AtomicLong();
	private Logger logger = Logger.getLogger(PersonService.class.getName());

	public Person create(Person person) {
		logger.info("Creating one People!");
		return person;
	}
	
	public Person update(Person person) {
		logger.info("Update one People!");
		return person;
	}
	
	public List<Person> findAll() {
		logger.info("Find all People!");
		List<Person> persons = new ArrayList<>();

		for (int i = 0; i < 8; i++) {
			Person person = mockPerson(i);
			persons.add(person);

		}

		return persons;

	}
	
	public Person findById(String id) {
		logger.info("finding one Person!");
		
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Pedro");
		person.setLastName("Laurindo");
		person.setAddress("Minha casa");
		person.setGender("Male");
		
		return person;
		
	}
	
	public void delete(String id) {
		
		logger.info("Delete one person!");
		
	}
	

	private Person mockPerson(int i) {
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("FirstName 0" + i);
		person.setLastName("LastName 0" + i);
		person.setAddress("Casa person 0" + i);
		person.setGender("Gender person 0" + i);
		return person;
	}


}
