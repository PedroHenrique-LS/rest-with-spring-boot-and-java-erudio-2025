package com.github.PedroHenrique_LS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.PedroHenrique_LS.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
