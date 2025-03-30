package com.github.PedroHenrique_LS.controllers;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.PedroHenrique_LS.model.Person;
import com.github.PedroHenrique_LS.services.PersonService;


@RestController
@RequestMapping("/person")
public class PersonController {

	//@Autowired
	private PersonService service;
	
	public PersonController(PersonService service) {
		this.service = service;
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE )
	public Person findById(@PathVariable("id") Long id) {
		
		return service.findById(id);
		
	}
	
	@PostMapping(
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
			)
	public Person Create(@RequestBody Person person) {
		
		return service.create(person);
		
	}
	
	@PutMapping(value = "/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
			)
	public Person updatePerson(@RequestBody Person person, @PathVariable("id") String id) {
	
		return service.update(person);
		
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE )
	public List<Person> findAll() {
		return service.findAll();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		service.delete(id);
		return ResponseEntity.noContent().build();
		
	}
	
	
}
