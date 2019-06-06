package com.email.list.reader;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.loan.agent.mvc.utils.Files;
import com.loan.agent.services.Constant;

import au.com.bytecode.opencsv.CSVReader;

//import jxl.Cell;
import jxl.CellType;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;

public class ReadExcelEmailList {
	
	public static Logger LOG  =Logger.getLogger(ReadExcelEmailList.class); 
	public static final int START_COL_NUMBER=0;
	public static final int PRELOAD_ROW_NUMBER=15;
	
	public static List<SimpleEmailLineVO> readEmailListFromHTML(String fileContent,HttpServletRequest request,int firstNameColPtr,int emailColPtr,boolean preLoad) {
		List<SimpleEmailLineVO> list =  null;
	 	
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
  	     
		MultipartFile multipartFile = multipartRequest.getFile(fileContent);
		
		request.getSession().setAttribute(Constant.MULTI_PART_FILE,multipartFile);
		
		
		
		if (multipartFile==null) {
			LOG.info("fileContent may be wrong!") ;
			return null;
		}
	 	String pathFile = multipartFile.getOriginalFilename();
 	 	try {
 			InputStream fstream = multipartFile.getInputStream();
 			request.getSession().setAttribute(Constant.FILE_STREAM,fstream);
 			
			list = readEmailListFromStream(pathFile,fstream,  firstNameColPtr, emailColPtr,preLoad);
 		} catch (Exception e) {
			LOG.info(" readEmailListFromHTML failed !"+e.getMessage()) ;
			return null;
		}
		  
		return list;
	}
	
	public static List<SimpleEmailLineVO> readExcelEmailList(String pathFile,int firstNameColPtr,int emailColPtr,boolean preLoad) {
		List<SimpleEmailLineVO> list =null;	 
		 
		InputStream fis = null;
		 
		try
        {
			File file = new File(pathFile);
			if (!file.exists()) {
				LOG.error("File can not be openned on path:"+pathFile);
				return null;
			}
			
			fis = new FileInputStream(file);
            
			list = readEmailListFromStream(pathFile,fis,  firstNameColPtr,emailColPtr,preLoad);
		 
             
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
		return list;
	}
	
	public static List<SimpleEmailLineVO> readEmailListFromStream(String pathFile,InputStream fis,  int firstNameColPtr,int emailColPtr,boolean preLoad) {
		List<SimpleEmailLineVO> list = new ArrayList<SimpleEmailLineVO>();
		List< EmailDynamicVO> dyList = new ArrayList< EmailDynamicVO>();
		int totalCol=START_COL_NUMBER; 
		 
		//FileInputStream fis = null;
		XSSFWorkbook xssfWorkbook = null;
		HSSFWorkbook hssfWorkbook = null;
		XSSFSheet xssSheet = null;
		HSSFSheet hssSheet = null; 
		Iterator<Row> rowIterator = null;
		String excelFileType="2007";
		CSVReader csvReader =null;
		BufferedReader frd =null;
		try
        {
			if (pathFile.endsWith(".txt")) { // read plain text file 	
				list = ParserEmailListOnPlainText.getPlainTextEmailListFromStream(fis);
				return list;
			}
			if (pathFile.endsWith(".csv")) { // read csv file 	
				excelFileType="csv";
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
				 totalCol=START_COL_NUMBER; 
				 int rowCount=0;
				 while ((row=csvReader.readNext())!=null) {
					 SimpleEmailLineVO svo = new SimpleEmailLineVO();	
					 EmailDynamicVO dvo = new EmailDynamicVO();
		             svo.setFIRST_NAME_COL(firstNameColPtr);
		             svo.setEMAIL_COL(emailColPtr);	
		             String colVal = null;
		             int colPtr=START_COL_NUMBER;
					 for (int i=0;i<row.length;i++) {
						 colVal = row[i];
						 if (preLoad) {
							  dvo.addCol(colVal);
						 } else {
							 svo.setEmailLineVO(colPtr, colVal, colVal); 
						 }
						 
		                 colPtr++;
					 }
					 if (totalCol>START_COL_NUMBER) {  // skip first line of header
						 if (preLoad) {
							dyList.add(dvo);
							
						 } else {
							 if (null!=svo.getEmailAddress() && null!=svo.getFirstName()) {
								 svo.setValid(true);
								 svo.setLineId(new Integer(rowCount+1).toString());
								 list.add(svo);
							 }  
						 }
		             }
					 
		             totalCol=colPtr; 
		       
		          	if (preLoad) {
		          		if (rowCount>=PRELOAD_ROW_NUMBER) {
		          			svo.setDynaList(dyList);
		          			list.add(svo);
		          		 
		          			preLoad=false;
		          			break;
		          		}
		          	}
		          	rowCount++;
		          	
				 }
				 if (preLoad) {
					 SimpleEmailLineVO svo = new SimpleEmailLineVO();		
		          	 svo.setDynaList(dyList);
		          	 
		          	 list.add(svo);
		          	 
		         }
				 return list;
			}
			if (pathFile.endsWith(".xlsx")) { // excel 2007 or later 	
				excelFileType="2010";
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
	                
	                SimpleEmailLineVO svo = new SimpleEmailLineVO();
	           	    EmailDynamicVO dvo = new EmailDynamicVO();
	                svo.setFIRST_NAME_COL(firstNameColPtr);
	                svo.setEMAIL_COL(emailColPtr);
	                
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
	                        	//System.out.print(cell.getNumericCellValue() + "\t\t");
	                        	colVal = new Double(cell.getNumericCellValue()).toString();
	                        	break;
	                        case Cell.CELL_TYPE_STRING:   
	                        	colVal = cell.getStringCellValue();  
	                        	//System.out.print(colPtr+","+svo.rltrim(colVal) +",totalCol="+totalCol+ "\t\t");
	                        	                  	
	                            break;
	                    }
	                    if (preLoad) {
							  dvo.addCol(colVal);
						 } else {
							 svo.setEmailLineVO(colPtr, colVal, colVal); 
						 }
	                    colPtr++;
	                 }
	               
	              
	                if (totalCol>START_COL_NUMBER) {  // skip first line of header
						 if (preLoad) {
							dyList.add(dvo);
							
						 } else {
							 if (null!=svo.getEmailAddress() && null!=svo.getFirstName()) {
								 svo.setValid(true);
								 svo.setLineId(new Integer(rowCount+1).toString());
								 list.add(svo);
							 }  
						 }
		             }
	                totalCol=colPtr; 
	                if (preLoad) {
		          		if (rowCount>=PRELOAD_ROW_NUMBER) {
		          			svo.setDynaList(dyList);
		          			list.add(svo);	          		 
		          			preLoad=false;
		          			break;
		          		}
		          	} // Second Loop
	                rowCount++;
	             }  // First Loop
	            if (preLoad) {
					 SimpleEmailLineVO svo = new SimpleEmailLineVO();		
		          	 svo.setDynaList(dyList);		            
		          	 list.add(svo);
		          	 
		         } 
			}		
		   if (pathFile.endsWith(".xls")) { // excel 2007 or later 					 
				excelFileType="97";
			
				int rowCount=0;
				Workbook w;
				StringBuffer content = new StringBuffer(); 
				    
				w = Workbook.getWorkbook(fis);
				// Get the first sheet
				Sheet sheet = w.getSheet(0);
				// Loop over first 10 column and lines
				for (int i = 0; i < sheet.getRows(); i++) {			        
			        int colPtr=START_COL_NUMBER;  
	                SimpleEmailLineVO svo = new SimpleEmailLineVO();
	           	    EmailDynamicVO dvo = new EmailDynamicVO();
	                svo.setFIRST_NAME_COL(firstNameColPtr);
	                svo.setEMAIL_COL(emailColPtr);
	                
				    for (int j = 0; j < sheet.getColumns(); j++) {
				       
				        jxl.Cell cell = sheet.getCell(j, i);				        	  
				        CellType type = cell.getType();
				              
				        if (type == CellType.LABEL) {
				             String colVal =cell.getContents().trim();
				        
				             if (preLoad) {
				            	 dvo.addCol(colVal);
				             } else {
				            	 svo.setEmailLineVO(colPtr, colVal, colVal); 
				             }
				             colPtr++;
				        }
				     }  // Second Loop
				    if (totalCol>START_COL_NUMBER) {  // skip first line of header
						 if (preLoad) {
							dyList.add(dvo);
							
						 } else {
							 if (null!=svo.getEmailAddress() && null!=svo.getFirstName()) {
								 svo.setValid(true);
								 svo.setLineId(new Integer(rowCount+1).toString());
								 list.add(svo);
							 }  
						 }
		             }
	                totalCol=colPtr; 
	                if (preLoad) {
		          		if (rowCount>=PRELOAD_ROW_NUMBER) {
		          			svo.setDynaList(dyList);
		          			list.add(svo);	          		 
		          			preLoad=false;
		          			break;
		          		}
		          	}
	                rowCount++;
				}  // First Loop
	            if (preLoad) {
					 SimpleEmailLineVO svo = new SimpleEmailLineVO();		
		          	 svo.setDynaList(dyList);		            
		          	 list.add(svo);
		          	 
		         } 
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
		return list;
	}
	
	public static String generateHTMLTemplate(List<EmailDynamicVO> list) {
		String header="<style> \n"+
		".chooseDiv { \n"+
		"}	 \n"+
		".chooseDiv table,th,td		   \n"+
		"{	 \n"+
		"	background-color:rgb(250,254,254);		 \n"+
		"	border:1px solid black;	   \n"+
		"	border-collapse:collapse   \n"+
		"}	 \n"+
		".chooseDiv th,td		   \n"+
		"{	 \n"+
		"	padding:6px;		   \n"+
		"}	 \n"+
		"</style> \n"+
		"	 \n"+
		" <div class=\"chooseDiv\">	   \n"+
		"	 \n"+
		" <table   border=\"1\" callpadding=\"1\" callpadding=\"0\"> \n"+
		"   <caption><strong>Please Choose First Name and Email</strong> </caption>	   \n"+
		"    <thead> \n"+
		"       	<tr>		   \n"+
		"       	  <th style=\"border:0px;\">Fname</th>	 \n";
		int totalCol = 0;
		if (null!=list && list.size()>0) {
			EmailDynamicVO vo = list.get(0);
			totalCol = vo.getTotalCols(); 
		}
		StringBuffer hBuf = new StringBuffer();
		for (int i =0 ; i<totalCol; i++) {
			hBuf.append("<th><input type=\"radio\" name=\"firstName\" value=\"col"+new Integer(i).toString()+"\"></th>\n");
		}
		hBuf.append("   </tr>\n");
		for (int i =0 ; i<totalCol; i++) {
			hBuf.append("<th><input type=\"radio\" name=\"emailAddress\" value=\"col"+new Integer(i).toString()+"\"></th>\n");
		}
		hBuf.append("    </tr>\n");
		
		hBuf.append("</thead> \n");
		hBuf.append("<tbody>\n");
		for (EmailDynamicVO vo : list) {
			hBuf.append("\n<tr>\n<td style=\"border:0px;\">&nbsp;</td>\n");
			for (String s: vo.getColList()) {
				hBuf.append("<td>"+s+"</td>\n");
			}
			hBuf.append("</tr>");
		}		
		 
		hBuf.append("\n</tbody>\n");		 
		hBuf.append("</table>\n"); 
		hBuf.append("</div>"); 
		return header+hBuf.toString();
	}
	 
	
	public static void main(String args[]) {
		//String pathFile = "C:/JzhangDir/emailList/email_list_excel_csv_file.csv";
		//String pathFile = "C:/JzhangDir/emailList/MixedPlanTextEmailList.txt";
		String pathFile = "C:/JzhangDir/emailList/emailaddrTest.txt";
		//String pathFile = "C:/JzhangDir/emailList/MixedPlanTextEmailListExample2.txt";
		//String pathFile =   "C:/JzhangDir/emailList/email_list_excel_2007.xls";
		//List<SimpleEmailLineVO> list = readExcelEmailList(pathFile,0,2,false);
		File file = new File(pathFile);
		if (!file.exists()) {
			LOG.error("File can not be openned on path:"+pathFile);
			return;
		}
		try {
		InputStream fis = new FileInputStream(file);
		List<SimpleEmailLineVO> list = readEmailListFromStream(pathFile,fis,0,2,false) ;
		
		for (SimpleEmailLineVO vo : list) {
			LOG.info("FirstName="+vo.getFirstName()+",EmailName="+vo.getEmailAddress()); //+",totalCols="+vo.getDynaList().get(0).getTotalCols());
		}
		LOG.info("totalCols="+list.get(0).getDynaList().get(0).getTotalCols());
		/*LOG.info("list="+list);
		if (null!=list) {		 
			String html = generateHTMLTemplate(list.get(0).getDynaList());
			LOG.info("html="+html);
		}*/
		} catch (Exception e) {}
	}
}
