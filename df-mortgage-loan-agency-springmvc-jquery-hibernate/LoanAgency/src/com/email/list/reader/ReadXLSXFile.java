package com.email.list.reader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadXLSXFile implements EmailListService {
	public static Logger LOG  =Logger.getLogger(ReadXLSXFile.class); 

	/**
	 *  Implement abstract function
	 */
	public List<EmailDynamicVO> getDynamicEmailInfo(InputStream fis) {
		List<EmailDynamicVO> dyList = new ArrayList<EmailDynamicVO>();
		XSSFWorkbook xssfWorkbook = null;
		HSSFWorkbook hssfWorkbook = null;
		XSSFSheet xssSheet = null;
		Iterator<Row> rowIterator = null;
		try {
			//Create Workbook instance holding reference to .xlsx file			 
			xssfWorkbook = new XSSFWorkbook(fis); 				 
			//Get first/desired sheet from the workbook
			xssSheet = xssfWorkbook.getSheetAt(0);				 
			//Iterate through each rows one by one
			rowIterator = xssSheet.iterator();
			int rowCount=0;
			while (rowIterator.hasNext())
			{
        		Row row = rowIterator.next();
				//For each row, iterate through all the columns
        		Iterator<Cell> cellIterator = row.cellIterator();
        		int colPtr=START_COL_NUMBER;  
        		EmailDynamicVO dvo = new EmailDynamicVO();   
        		while (cellIterator.hasNext())
        		{
        			Cell cell = cellIterator.next();
        			//Check the cell type and format accordingly
        			String colVal = null;
        			switch (cell.getCellType())
        			{
                		case Cell.CELL_TYPE_BOOLEAN:
                			//System.out.print(cell.getBooleanCellValue() + "\t\t");   
                			if (cell.getBooleanCellValue() )  {
                				colVal="true";
                			} else {
                				colVal="false";
                			}
                			break;
                		case Cell.CELL_TYPE_NUMERIC:                    	 
                			colVal = new Double(cell.getNumericCellValue()).toString();
                			break;
                		case Cell.CELL_TYPE_STRING:   
                			colVal = cell.getStringCellValue();  
                			break;
        			}
                
        			dvo.addCol(colVal);				 
        			colPtr++;
        		} // second loop
        		dvo.setTotalCols(colPtr);
        		dyList.add(dvo);
        		rowCount++;
			}  // First Loop
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
