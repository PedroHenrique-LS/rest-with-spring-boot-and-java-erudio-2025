package com.github.PedroHenrique_LS.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.PedroHenrique_LS.dto.v1.PersonDTO;
import com.github.PedroHenrique_LS.exception.RequiredObjectIsNullException;
import com.github.PedroHenrique_LS.model.Person;
import com.github.PedroHenrique_LS.repository.PersonRepository;
import com.github.PedroHenrique_LS.unitetests.mapper.mocks.MockPerson;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {
	
	MockPerson input;
	
	@Mock
	PersonRepository repository;
	
	@InjectMocks
	private PersonService service;

	@BeforeEach
	void setUp() throws Exception {
		input = new MockPerson();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testCreate() {
		Person person = input.mockEntity(1);
		Person persisted = person;
		person.setId(1L);
		
		PersonDTO dto = input.mockDTO(1);
		
		
		when(repository.save(person)).thenReturn(persisted);
		var result = service.create(dto);
		
		assertNotNull(result);
		assertNotNull(person.getId());
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Address Test1", result.getAddress());
		assertEquals("Female", result.getGender());
		
		assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self") 
				&& link.getHref().endsWith("/person/1")
				&&  link.getType().equals("GET")));
		
		assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll") 
				&& link.getHref().endsWith("/person")
				&&  link.getType().equals("GET")));
		
		assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create") 
				&& link.getHref().endsWith("/person")
				&&  link.getType().equals("POST")));
		
		assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update") 
				&& link.getHref().endsWith("/person/1")
				&&  link.getType().equals("PUT")));
		
		assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete") 
				&& link.getHref().endsWith("/person/1")
				&&  link.getType().equals("DELETE")));
		
	}
	
	@Test
	void testCreateWithNullPerson() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, 
				() -> {
					service.create(null);
				}
		);
		
		String expectedMessag = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.equals(expectedMessag));
		
	}

	@Test
	void testUpdate() {
		Person person = input.mockEntity(1);
		Person persisted = person;
		person.setId(1L);
		
		PersonDTO dto = input.mockDTO(1);
		
		when(repository.findById(1L)).thenReturn(Optional.of(person));
		when(repository.save(person)).thenReturn(persisted);
		var result = service.update(dto);
		
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getLinks());
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Address Test1", result.getAddress());
		assertEquals("Female", result.getGender());
		
		assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self") 
				&& link.getHref().endsWith("/person/1")
				&&  link.getType().equals("GET")));
		
		assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll") 
				&& link.getHref().endsWith("/person")
				&&  link.getType().equals("GET")));
		
		assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create") 
				&& link.getHref().endsWith("/person")
				&&  link.getType().equals("POST")));
		
		assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update") 
				&& link.getHref().endsWith("/person/1")
				&&  link.getType().equals("PUT")));
		
		assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete") 
				&& link.getHref().endsWith("/person/1")
				&&  link.getType().equals("DELETE")));
		
	}
	
	@Test
	void testUpdateWithNullPerson() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, 
				() -> {
					service.update(null);
				}
		);
		
		String expectedMessag = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.equals(expectedMessag));
		
	}

	@Test
	void testFindById() {
		Person person = input.mockEntity(1);
		person.setId(1L);
		when(repository.findById(1L)).thenReturn(Optional.of(person));
		var result = service.findById(1L);
		
		assertNotNull(result);
		assertNotNull(person.getId());
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Address Test1", result.getAddress());
		assertEquals("Female", result.getGender());
		
		assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self") 
				&& link.getHref().endsWith("/person/1")
				&&  link.getType().equals("GET")));
		
		assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll") 
				&& link.getHref().endsWith("/person")
				&&  link.getType().equals("GET")));
		
		assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create") 
				&& link.getHref().endsWith("/person")
				&&  link.getType().equals("POST")));
		
		assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update") 
				&& link.getHref().endsWith("/person/1")
				&&  link.getType().equals("PUT")));
		
		assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete") 
				&& link.getHref().endsWith("/person/1")
				&&  link.getType().equals("DELETE")));
		
	}

	@Test
	void testDelete() {
		Person person = input.mockEntity(1);
        person.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(person));

        service.delete(1L);
        verify(repository, times(1)).findById(anyLong());
        verify(repository, times(1)).delete(any(Person.class));
        verifyNoMoreInteractions(repository);
	}
	
	@Test
	void testFindAll() {
		
		List<Person> list = input.mockEntityList();
		when(repository.findAll()).thenReturn(list);
		List<PersonDTO> people = service.findAll();
		
		assertNotNull(people);
		assertEquals(14, people.size());
		
		var personOne = people.get(1);
		
		assertNotNull(personOne);
		assertNotNull(personOne.getId());
		assertNotNull(personOne.getLinks());
		assertEquals("First Name Test1", personOne.getFirstName());
		assertEquals("Last Name Test1", personOne.getLastName());
		assertEquals("Address Test1", personOne.getAddress());
		assertEquals("Female", personOne.getGender());
		
		assertNotNull(personOne.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self") 
				&& link.getHref().endsWith("/person/1")
				&&  link.getType().equals("GET")));
		
		assertNotNull(personOne.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll") 
				&& link.getHref().endsWith("/person")
				&&  link.getType().equals("GET")));
		
		assertNotNull(personOne.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create") 
				&& link.getHref().endsWith("/person")
				&&  link.getType().equals("POST")));
		
		assertNotNull(personOne.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update") 
				&& link.getHref().endsWith("/person/1")
				&&  link.getType().equals("PUT")));
		
		assertNotNull(personOne.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete") 
				&& link.getHref().endsWith("/person/1")
				&&  link.getType().equals("DELETE")));
		
		var personFour = people.get(4);
		
		assertNotNull(personFour);
		assertNotNull(personFour.getId());
		assertNotNull(personFour.getLinks());
		assertEquals("First Name Test4", personFour.getFirstName());
		assertEquals("Last Name Test4", personFour.getLastName());
		assertEquals("Address Test4", personFour.getAddress());
		assertEquals("Male", personFour.getGender());
		
		assertNotNull(personFour.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self") 
				&& link.getHref().endsWith("/person/4")
				&&  link.getType().equals("GET")));
		
		assertNotNull(personFour.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll") 
				&& link.getHref().endsWith("/person")
				&&  link.getType().equals("GET")));
		
		assertNotNull(personFour.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create") 
				&& link.getHref().endsWith("/person")
				&&  link.getType().equals("POST")));
		
		assertNotNull(personFour.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update") 
				&& link.getHref().endsWith("/person/4")
				&&  link.getType().equals("PUT")));
		
		assertNotNull(personFour.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete") 
				&& link.getHref().endsWith("/person/4")
				&&  link.getType().equals("DELETE")));
		
var personSeven = people.get(7);
		
		assertNotNull(personSeven);
		assertNotNull(personSeven.getId());
		assertNotNull(personSeven.getLinks());
		assertEquals("First Name Test7", personSeven.getFirstName());
		assertEquals("Last Name Test7", personSeven.getLastName());
		assertEquals("Address Test7", personSeven.getAddress());
		assertEquals("Female", personSeven.getGender());
		
		assertNotNull(personSeven.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self") 
				&& link.getHref().endsWith("/person/7")
				&&  link.getType().equals("GET")));
		
		assertNotNull(personSeven.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll") 
				&& link.getHref().endsWith("/person")
				&&  link.getType().equals("GET")));
		
		assertNotNull(personSeven.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create") 
				&& link.getHref().endsWith("/person")
				&&  link.getType().equals("POST")));
		
		assertNotNull(personSeven.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update") 
				&& link.getHref().endsWith("/person/7")
				&&  link.getType().equals("PUT")));
		
		assertNotNull(personSeven.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete") 
				&& link.getHref().endsWith("/person/7")
				&&  link.getType().equals("DELETE")));
		
		
	}

}
