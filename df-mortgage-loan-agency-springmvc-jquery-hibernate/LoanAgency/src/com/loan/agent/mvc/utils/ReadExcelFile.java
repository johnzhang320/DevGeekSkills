package com.loan.agent.mvc.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
 
public class ReadExcelFile {

  public static String  read(InputStream inputWorkbook)  {
	  Workbook w;
	  StringBuffer content = new StringBuffer(); 
	    try {
	      w = Workbook.getWorkbook(inputWorkbook);
	      // Get the first sheet
	      Sheet sheet = w.getSheet(0);
	      // Loop over first 10 column and lines
	      for (int i = 0; i < sheet.getRows(); i++) {
	    	  String line="";
	          for (int j = 0; j < sheet.getColumns(); j++) {
	       
	              Cell cell = sheet.getCell(j, i);
	              CellType type = cell.getType();
	             if (type == CellType.LABEL) {
	            
	                 line  += ","+cell.getContents().trim();
	            
	             
	             }

	          if (type == CellType.NUMBER) {
	            System.out.println("I got a number "
	                + cell.getContents());
	          }

	        }
	        line=line.substring(1);
	         //System.out.println(line);
	         content.append(line+"\n");
	             
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    if (content.length()<2) {
	    	return null;
	    }
	    return content.toString();
  }
		  

  public static String  read(String inputFile)  {
	 
	
	  File inputWorkbook = new File(inputFile);
	  if (!inputWorkbook.exists()) {
    	return null;
	  }
	  InputStream in =null;
	  try {
		  in = new FileInputStream(inputWorkbook);
		 return  read(in);
	  } catch (Exception e) {
		  e.printStackTrace();
	  }
      return null;
  }

  public static void main(String[] args) throws IOException {   
   
    System.out.println(ReadExcelFile.read("C:/JzhangDir/emailList/email_list_excel_2007.xls"));
  }
}
