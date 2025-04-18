package com.github.PedroHenrique_LS.controllers;

import java.util.Date;
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

import com.github.PedroHenrique_LS.dto.v1.PersonDTO;
import com.github.PedroHenrique_LS.dto.v2.PersonDTOV2;
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
	public PersonDTO findById(@PathVariable("id") Long id) {
		
		var person =  service.findById(id);
		
		person.setBirthDay(new Date());
		//person.setPhoneNumber("+55 (87) 999301961");
		person.setPhoneNumber("");
		person.setLastName(null);
		person.setSensitiveData("123456");
		
		return person;
		
	}
	
	@PostMapping(
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
			)
	public PersonDTO Create(@RequestBody PersonDTO person) {
		
		return service.create(person);
		
	}
	
	@PostMapping(value = "/v2",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
			)
	public PersonDTOV2 CreateV2(@RequestBody PersonDTOV2 person) {
		
		return service.createV2(person);
		
	}
	
	@PutMapping(value = "/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
			)
	public PersonDTO updatePerson(@RequestBody PersonDTO person, @PathVariable("id") String id) {
	
		return service.update(person);
		
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE )
	public List<PersonDTO> findAll() {
		return service.findAll();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		service.delete(id);
		return ResponseEntity.noContent().build();
		
	}
	
	
}
