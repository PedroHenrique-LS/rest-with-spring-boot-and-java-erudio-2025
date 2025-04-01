package com.github.PedroHenrique_LS.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.PedroHenrique_LS.dto.PersonDTO;
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
		logger.info("Creating one People!");
		var entity = ObjectMapper.parseObject(person, Person.class);
		return ObjectMapper.parseObject(repository.save(entity), PersonDTO.class) ;
	}
	
	public PersonDTO update(PersonDTO person) {
		logger.info("Update one People!");
		Person entity =  repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		return ObjectMapper.parseObject(repository.save(entity), PersonDTO.class) ;
		
	}
	
	public List<PersonDTO> findAll() {
		logger.info("Find all People!");

		return ObjectMapper.parseListObjects(repository.findAll(), PersonDTO.class);

	}
	
	public PersonDTO findById(Long id) {
		logger.info("finding one Person!");
		
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		return ObjectMapper.parseObject(entity, PersonDTO.class);
	}
	
	public void delete(Long id) {
		
		logger.info("Delete one person!");
		Person entity =  repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		repository.delete(entity);
	}
	


}
