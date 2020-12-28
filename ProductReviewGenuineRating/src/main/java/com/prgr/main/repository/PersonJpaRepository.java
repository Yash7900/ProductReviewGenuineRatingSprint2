package com.prgr.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prgr.main.entity.Person;

public interface PersonJpaRepository extends JpaRepository<Person,Integer> {

}
