package com.email.list.reader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;

public class ReadXLSFile implements EmailListService {
	public static Logger LOG  =Logger.getLogger(ReadXLSFile.class); 

	/**
	 *  Implement abstract function
	 */
	public List<EmailDynamicVO> getDynamicEmailInfo(InputStream fis) {
		List<EmailDynamicVO> dyList = new ArrayList<EmailDynamicVO>();
		int rowCount=0;
		Workbook w;
		StringBuffer content = new StringBuffer(); 
		try {    
			w = Workbook.getWorkbook(fis);
			// Get the first sheet
			Sheet sheet = w.getSheet(0);
			// Loop over first 10 column and lines
			for (int i = 0; i < sheet.getRows(); i++) {			        
				int colPtr=START_COL_NUMBER;  
           
				EmailDynamicVO dvo = new EmailDynamicVO();     
            
				for (int j = 0; j < sheet.getColumns(); j++) {		       
					jxl.Cell cell = sheet.getCell(j, i);				        	  
					CellType type = cell.getType();
		              
					if (type == CellType.LABEL) {
						String colVal =cell.getContents().trim();
		            	 dvo.addCol(colVal);		            
		            	 colPtr++;
					}
				}  // Second Loop
				dvo.setTotalCols(colPtr);
				dyList.add(dvo);
			}
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
