package com.email.list.reader;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import au.com.bytecode.opencsv.CSVReader;

import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;

public class ReadCSVFile implements EmailListService {
	public static Logger LOG  =Logger.getLogger(ReadCSVFile.class); 

	/**
	 *  Implement abstract function
	 */
	public List<EmailDynamicVO> getDynamicEmailInfo(InputStream fis) {
		List<EmailDynamicVO> dyList = new ArrayList<EmailDynamicVO>();
		 
		CSVReader csvReader =null;
		BufferedReader frd =null;
		try {    
				/**
				 *  Read CSV file line by line
				 */
				 DataInputStream in = new DataInputStream(fis);
				 frd = new BufferedReader(new InputStreamReader(in));				
				 /**
				  *  CSVReader can default delimit column by {,} , {","}, we also add {'\''}  {\|} {;} {#} 
				  */
				 csvReader = new CSVReader(frd);
				 String [] row =null;
				 
				 int rowCount=0;
				 while ((row=csvReader.readNext())!=null) {
				 
					 EmailDynamicVO dvo = new EmailDynamicVO();
		             
		             String colVal = null;
		             int colPtr=START_COL_NUMBER;
					 for (int i=0;i<row.length;i++) {
						 colVal = row[i];
						 dvo.addCol(colVal);
		                 colPtr++;
					 } // second loop
					 dvo.setTotalCols(colPtr);
					 dyList.add(dvo);
					 rowCount++; 		
				 
		         } // first loop
		} catch (Exception e)
		{
			LOG.error("Read Excel file failure, message is "+e.getMessage());
			return null;
		} finally {
			try {        		
		 
				fis.close();
		
			} catch (Exception e) {
				LOG.error("Read Excel File, normall Close failure, message is "+e.getMessage());
				return null;
			}
		}
		return dyList;
	}		 
}
