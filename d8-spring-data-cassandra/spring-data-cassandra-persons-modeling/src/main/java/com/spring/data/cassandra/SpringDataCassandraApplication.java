package com.spring.data.cassandra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.cassandra.repository.Query;

import com.spring.data.cassandra.model.Person;
import com.spring.data.cassandra.model.PersonKey;
import com.spring.data.cassandra.repository.PersonRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class SpringDataCassandraApplication implements CommandLineRunner {

  @Autowired private PersonRepository personRepository;

  public static void main(final String args[]) {
    SpringApplication.run(SpringDataCassandraApplication.class);
  }

  @Override
  public void run(String... args) throws Exception {
	String sso[]= {"609-12-2123","609-23-2453","609-34-3443","608-48-3414","612-75-6312","629-55-3342","612-15-5312"};
	String firstName[]= {"john", "Eric","Leo"  , "Suresh","Scott","Bhvan","Ernie"};
	String lastName[] = {"Doe","Shafa","Huang","Ai","Hurbet","Yafa","Smith"};
	personRepository.deleteAll();  // if table exists , run it
	for (int i=0;i<firstName.length;i++) {
	    final PersonKey key = new PersonKey(sso[i], LocalDateTime.now(), UUID.randomUUID());
	    final Person p = new Person(key, firstName[i], lastName[i], 1200*(i+1));
	    personRepository.insert(p);
	}

	 System.out.println("find All");
	    personRepository.findAll().forEach(System.out::println);
	    
    System.out.println("find by SocialSecurity");
    personRepository.findByKeySocialSecurity("609-12-2123").forEach(System.out::println);

    System.out.println("find by SocialSecurity and date of birth greater than date");
    personRepository
        .findByKeySocialSecurityAndKeyDateOfBirthGreaterThan("629-55-3342", LocalDateTime.now().minusDays(1))
        .forEach(System.out::println);

    System.out.println("find by last name");
    personRepository.findByLastName("Doe").forEach(System.out::println);
    
    System.out.println("find by first name");
    personRepository.findByLastName("Smith").forEach(System.out::println);
  }
}
/**
 * find by SocialSecurity
 * 
	find All
	Person [key=PersonKey [socialSecurity=609-34-3443, dateOfBirth=2019-05-31T17:29:28.690, id=f7ac637c-7d8c-4c50-9145-c2ba07ac9814], firstName=Leo, lastName=Huang, salary=3600.0]
	Person [key=PersonKey [socialSecurity=609-12-2123, dateOfBirth=2019-05-31T17:29:28.657, id=3dffa8f1-aa84-4aaa-ac92-2793952abf44], firstName=john, lastName=Doe, salary=1200.0]
	Person [key=PersonKey [socialSecurity=609-23-2453, dateOfBirth=2019-05-31T17:29:28.686, id=196556ae-56cb-464e-af39-f86c7154b589], firstName=Eric, lastName=Shafa, salary=2400.0]
	Person [key=PersonKey [socialSecurity=612-15-5312, dateOfBirth=2019-05-31T17:29:28.699, id=b7aef444-1dec-4e02-8be2-0ea995d19453], firstName=Ernie, lastName=Smith, salary=8400.0]
	Person [key=PersonKey [socialSecurity=629-55-3342, dateOfBirth=2019-05-31T17:29:28.696, id=a0a4c813-dd53-43ce-bba9-b2415787c6f8], firstName=Bhvan, lastName=Yafa, salary=7200.0]
	Person [key=PersonKey [socialSecurity=608-48-3414, dateOfBirth=2019-05-31T17:29:28.692, id=85ed36ad-3a49-4b7c-8c6b-bc5c8b936b52], firstName=Suresh, lastName=Ai, salary=4800.0]
	Person [key=PersonKey [socialSecurity=612-75-6312, dateOfBirth=2019-05-31T17:29:28.694, id=14c5a22d-ce5f-4b26-b6e0-2479a17b8e62], firstName=Scott, lastName=Hurbet, salary=6000.0]
	find by SocialSecurity
	Person [key=PersonKey [socialSecurity=609-12-2123, dateOfBirth=2019-05-31T17:29:28.657, id=3dffa8f1-aa84-4aaa-ac92-2793952abf44], firstName=john, lastName=Doe, salary=1200.0]
	find by SocialSecurity and date of birth greater than date
	Person [key=PersonKey [socialSecurity=629-55-3342, dateOfBirth=2019-05-31T17:29:28.696, id=a0a4c813-dd53-43ce-bba9-b2415787c6f8], firstName=Bhvan, lastName=Yafa, salary=7200.0]
	find by last name
	Person [key=PersonKey [socialSecurity=609-12-2123, dateOfBirth=2019-05-31T17:29:28.657, id=3dffa8f1-aa84-4aaa-ac92-2793952abf44], firstName=john, lastName=Doe, salary=1200.0]
	find by first name
	Person [key=PersonKey [socialSecurity=612-15-5312, dateOfBirth=2019-05-31T17:29:28.699, id=b7aef444-1dec-4e02-8be2-0ea995d19453], firstName=Ernie, lastName=Smith, salary=8400.0]
	    
	    
	cqlsh:mykeyspace> select * from people_by_social_security;
	
	 social_security | date_of_birth                   | person_id                            | firstname | lastname | salary
	-----------------+---------------------------------+--------------------------------------+-----------+----------+--------
	     609-34-3443 | 2019-05-31 23:53:03.215000+0000 | 9b31f80a-bf04-4498-ad28-fc92ab30c930 |       Leo |    Huang |   3600
	     609-12-2123 | 2019-05-31 23:53:03.185000+0000 | cbf8b870-59b8-4240-a87f-9d157bf8cd0f |      john |      Doe |   1200
	     609-23-2453 | 2019-05-31 23:53:03.213000+0000 | 9e850056-7f6d-4551-8802-877a4f1ad097 |      Eric |    Shafa |   2400
	     612-15-5312 | 2019-05-31 23:53:03.223000+0000 | a4361067-508e-4810-a4fd-0b9e256ef404 |     Ernie |    Smith |   8400
	     629-55-3342 | 2019-05-31 23:53:03.221000+0000 | 7f08061f-a3fc-4d75-b925-ac8f9a11c154 |     Bhvan |     Yafa |   7200
	     608-48-3414 | 2019-05-31 23:53:03.217000+0000 | 5bb561db-e054-49a8-b34f-f42b78aae87c |    Suresh |       Ai |   4800
	     612-75-6312 | 2019-05-31 23:53:03.219000+0000 | 6d03d535-73cb-46af-bf5f-801241e3271b |     Scott |   Hurbet |   6000


 */
		
		
