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
 *  If you want to create create the table , change below property to 'create'
 *    <property name="hibernate.hbm2ddl.auto">create</property>  
 *    in hibernate.cfg.xml
 *  Keep test order, junit 5 run on the order in which methods present
 */
@RunWith(JUnitPlatform.class)
public class BlobDataInitializeTest extends BaseTestDAO  {
	static Logger Log = Logger.getLogger( BlobDataInitializeTest.class);
	 
	private static Session session; 
	private static String photoFilePathToRead = "/Users/jianzhang/photo/SpringbootSecurityEnhancement.jpg";
	private static String photoFilePathToSave = "/Users/jianzhang/photo/SpringbootSecurityEnhancement.jpg";
	 
	 
	@Test 
	public void savePersonWithPhoto() throws IOException {
	        Person person = new Person("Tom Cruise");
	        byte[] photoBytes = readBytesFromFile(photoFilePathToSave);
	        person.setPhoto(photoBytes);
	        Log.info("Saving Person");
	        session.save(person);
	        Log.info("Saved Person");
	    }
	//@Test     
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
