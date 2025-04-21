package com.github.PedroHenrique_LS.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.PedroHenrique_LS.controllers.PersonController;
import com.github.PedroHenrique_LS.dto.v1.PersonDTO;
import com.github.PedroHenrique_LS.exception.RequiredObjectIsNullException;
import com.github.PedroHenrique_LS.exception.ResourceNotFoundException;
import com.github.PedroHenrique_LS.mapper.ObjectMapper;
import com.github.PedroHenrique_LS.model.Person;
import com.github.PedroHenrique_LS.repository.PersonRepository;

@Service
public class PersonService {

	private Logger logger = LoggerFactory.getLogger(PersonService.class.getName());
	
	@Autowired 
	PersonRepository repository;

	public PersonDTO create(PersonDTO person) {
		if(person == null) throw new RequiredObjectIsNullException();
		logger.info("Creating one People!");
		var entity = ObjectMapper.parseObject(person, Person.class);
		var dto = ObjectMapper.parseObject(repository.save(entity), PersonDTO.class) ;
		addHateoasLinks(dto);
		return dto;
	}
	
	
	public PersonDTO update(PersonDTO person) {
		
		if(person == null) throw new RequiredObjectIsNullException();
		
		logger.info("Update one People!");
		Person entity =  repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		var dto = ObjectMapper.parseObject(repository.save(entity), PersonDTO.class) ;
		addHateoasLinks(dto);
		return dto;
	}
	
	public List<PersonDTO> findAll() {
		logger.info("Find all People!");
		List<PersonDTO> people = ObjectMapper.parseListObjects(repository.findAll(), PersonDTO.class);
		for (PersonDTO personCurrent : people) {
			addHateoasLinks(personCurrent);
		}
		
		return people;
	}
	
	public PersonDTO findById(Long id) {
		logger.info("finding one Person!");
		
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		var dto = ObjectMapper.parseObject(entity, PersonDTO.class);
		addHateoasLinks(dto);
		return dto;
	}

	
	public void delete(Long id) {
		
		logger.info("Delete one person!");
		Person entity =  repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		repository.delete(entity);
	}
	
	private void addHateoasLinks(PersonDTO dto) {
		dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel().withType("GET"));
		
		dto.add(linkTo(methodOn(PersonController.class).findAll()).withRel("findAll").withType("GET"));
		
		dto.add(linkTo(methodOn(PersonController.class).create(dto)).withRel("create").withType("POST"));
		
		dto.add(linkTo(methodOn(PersonController.class).updatePerson(dto, dto.getId())).withRel("update").withType("UPDATE"));
		
		dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
		
	}


}
