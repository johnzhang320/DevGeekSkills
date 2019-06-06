package com.hibernate.blob.test;

import org.apache.log4j.Logger;
import org.hibernate.Session;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.hibernate.blob.model.Person;

import com.hibernate.utils.BaseHibernateDAO;
import com.hibernate.utils.BaseTestDAO;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
 
import org.junit.jupiter.api.TestInfo;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
/*
 *  before run this test , change below property to 'validate'
 *    <property name="hibernate.hbm2ddl.auto">validate</property>  
 *    in hibernate.cfg.xml file
 */
@RunWith(JUnitPlatform.class)
public class BlobDataQueryTest extends BaseTestDAO  {
	static Logger Log = Logger.getLogger( BlobDataQueryTest.class);
	 
	private static Session session; 
	private static String photoFilePathToRead = "SpringbootSecurityEnhancement.jpg";
	private static String photoFilePathToSave = "SpringbootSecurityEnhancement.jpg";
	 
	public void AccessPhotoTest() throws IOException {
		String photoFilePathToRead = "SpringbootSecurityEnhancement.jpg";
		savePersonWithPhoto(photoFilePathToRead);
         
        int personId = 1;
        String photoFilePathToSave = "SpringbootSecurityEnhancement.jpg";
        readPhotoOfPerson(personId, photoFilePathToSave);
	}
	//@Test 
	public void savePersonWithPhoto(String photoFilePath) throws IOException {
	        Person person = new Person("Tom Cruise");
	        byte[] photoBytes = readBytesFromFile(photoFilePath);
	        person.setPhoto(photoBytes);
	        session.save(person);
	    }
	@Test     
	public void readPhotoOfPerson(int personId, String photoFilePath) throws IOException {
	        Person person = (Person) session.get(Person.class, personId);
	        byte[] photoBytes = person.getPhoto();
	        saveBytesToFile(photoFilePath, photoBytes);
    }
     
    private static byte[] readBytesFromFile(String filePath) throws IOException {
        File inputFile = new File(filePath);
        FileInputStream inputStream = new FileInputStream(inputFile);
         
        byte[] fileBytes = new byte[(int) inputFile.length()];
        inputStream.read(fileBytes);
        inputStream.close();
         
        return fileBytes;
    }
     
    private static void saveBytesToFile(String filePath, byte[] fileBytes) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(filePath);
        outputStream.write(fileBytes);
        outputStream.close();
    }
	 
}
