package com.spring.data.cassandra.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.data.cassandra.model.Person;
import com.spring.data.cassandra.model.PersonKey;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PersonRepository extends CassandraRepository<Person, PersonKey> {

  List<Person> findByKeySocialSecurity(final String socialSecurity);

  List<Person> findByKeySocialSecurityAndKeyDateOfBirthGreaterThan(
      final String socialSecurity, final LocalDateTime dateOfBirth);

  @Query(allowFiltering = true)
  List<Person> findByLastName(final String lastName);
  
  @Query(allowFiltering = true)
  List<Person> findByFirstName(final String firstName);
}
