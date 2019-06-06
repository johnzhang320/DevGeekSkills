package com.interview.salesforce.oo.design;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;

// Read morse code into Map<Character, String> stop at "*"
public class ReadInputMorseCode {
	
	private Map<Character, String> encodeMap =new  ConcurrentHashMap<Character, String>();
	private Map<String,Character> decodeMap =new  ConcurrentHashMap<String,Character>();
	private List<String> encodingWords =new  ArrayList<String>();
	private List<String> decodingWords =new  ArrayList<String>();
	private Map<String, String> dictionary = new ConcurrentHashMap<String,String>();
	public static final String DEFUALT_FILE = "c:/problemc.txt";
	/**
	 * Read Input file into three data Structure
	 * @param fileName
	 */
	public void readInputMorseCode() {
		readInputMorseCode(DEFUALT_FILE);
	}
	public void readInputMorseCode(String fileName) {
		 File file = new File(fileName);
	        FileInputStream fis = null;
	        DataInputStream dis = null;
	        BufferedReader br = null;
	        
	        try {

	            fis = new FileInputStream(fileName);
	            dis = new DataInputStream(fis);
	            br = new BufferedReader(new InputStreamReader(dis));
	            
	            String line = null;
	            while((line = br.readLine()) != null){
	            	if (!line.equalsIgnoreCase("*")) {
	            		String buf[] = line.split(" ");
	            		encodeMap.put(buf[0].charAt(0),buf[1]);
	            		decodeMap.put(buf[1], buf[0].charAt(0));
	            		System.out.println(buf[0].charAt(0)+" "+buf[1]);
	            	} else {
	            		break;
	            	}
	            }
	            while((line = br.readLine()) != null){
	            	if (!line.equalsIgnoreCase("*")) {
	            		encodingWords.add(line); 
	            		System.out.println(line);
	            	} else {
	            		break;
	            	}
	            }
	            while((line = br.readLine()) != null){
	            	if (!line.equalsIgnoreCase("*")) {
	            		String buf[] = line.split(" ");
	            		System.out.println(buf[0]);
	            		 
	            		decodingWords.add(buf[0]); 
	            		if (buf.length>1) {
	            			decodingWords.add(buf[1]); 
	            			System.out.println(buf[1]);
		            		
	            		}	
	            		
	            	} else {
	            		break;
	            	}
	            }
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally{
	            try{if(br != null) br.close();}catch(Exception ex){}
	        }
	        return ;
	     
	}
	/**
	 * If I have more time , I can save to file
	 * @return
	 */
	public String encodeContext() {
		StringBuffer line = new StringBuffer();
		 
		for (String s: encodingWords) {
		 
			StringBuffer charStr =new StringBuffer();
			for (int i=0; i<s.length();i++) {
				char key = s.charAt(i);
				if (encodeMap.containsKey(key)) {
					charStr.append(encodeMap.get(key));
 				}
			}
			line.append(s+" => "+charStr+"\n");
		}
		return line.toString();
	}
	/**
	 * Decode , kind of trick because no space with each morse code, we can create a dictionary using given word context line
	 * create Map<encodeString, words>, compare decoding List with encodeString of given dictionary content
	 * @param args
	 */
	public String decodeContext() {
		// Get dictionary first
		for (String s: encodingWords) {
			 
			StringBuffer charStr =new StringBuffer();
			for (int i=0; i<s.length();i++) {
				char key = s.charAt(i);
				if (encodeMap.containsKey(key)) {
					charStr.append(encodeMap.get(key));
 				}
			}
			dictionary.put(charStr.toString(),s);
		}
		StringBuffer line = new StringBuffer();
		for (String key:decodingWords) {
			// key is decode string
			if (dictionary.containsKey(key)) { // if decode string exists in dictionary, return the word by dictionary.get(key)
				line.append(key +" => "+dictionary.get(key)+"\n");
			}
		}
		return line.toString();
	}
	
	public void reset() {
		 encodeMap.clear();
		 decodeMap.clear();
		 encodingWords.clear();
		 dictionary.clear();
	}
	


	public List<String> getEncodingWords() {
		return encodingWords;
	}

	public List<String> getDecodingWords() {
		return decodingWords;
	}
	public Map<Character, String> getEncodeMap() {
		return encodeMap;
	}
	public Map<String, Character> getDecodeMap() {
		return decodeMap;
	}
	public static String getDefualtFile() {
		return DEFUALT_FILE;
	}
	public Map<String, String> getDictionary() {
		return dictionary;
	}

	public static void main(String[] args) {
		 ReadInputMorseCode ref = new  ReadInputMorseCode();
		 ref.readInputMorseCode(DEFUALT_FILE);
		 System.out.println("\nEncoded Dictionary Context as following!");
		 System.out.println(ref.encodeContext());
		 System.out.println("Decode Morse Code Context to Word which occured in Dictionary as following!");
		 System.out.println(ref.decodeContext()); 
	}
	 
}
