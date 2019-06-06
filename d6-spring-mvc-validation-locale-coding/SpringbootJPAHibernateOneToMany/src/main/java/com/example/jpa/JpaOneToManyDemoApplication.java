package com.example.jpa;
//https://github.com/callicoder/jpa-hibernate-tutorials
//https://www.callicoder.com/hibernate-spring-boot-jpa-one-to-many-mapping-example/
//http://www.java2s.com/Tutorials/Java/JPA/4010__JPA_Query_Select_Two_Entities.htm
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JpaOneToManyDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(JpaOneToManyDemoApplication.class, args);
	}
}
