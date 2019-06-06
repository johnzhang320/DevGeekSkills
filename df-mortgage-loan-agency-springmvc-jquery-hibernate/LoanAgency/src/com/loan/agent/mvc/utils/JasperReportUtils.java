package com.loan.agent.mvc.utils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jfree.util.Log;

import com.loan.agent.calculators.CleanAmortList;
import com.loan.agent.calculators.vo.AmortizationLineVo;
import com.loan.agent.mvc.controller.CalculatorController;

 

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;

public class JasperReportUtils {
	
	static Logger LOG = Logger.getLogger(JasperReportUtils.class);
	
	public static JasperReport loadJasperReport(String location)
	{
		JasperReport curReport = null;
		
		try
		{
			curReport = (JasperReport)JRLoader.loadObjectFromLocation(location);
		}
		catch(Exception e)
		{
			Log.info("IO Error when open file: "+location);
			e.printStackTrace();
		}
		
		return curReport;
		
	}
	
	
	
public static String writeToPDF(String baseResultPath, InputStream JasperBuilderFile,
		Map parameters, List dataList,HttpServletRequest request,HttpServletResponse response) {
		
		LOG.info("writeToPDF: JaspBuilderFile="+JasperBuilderFile);
		FileOutputStream out=null;
	    //parameters=null;
		 
		try {
			JRBeanCollectionDataSource JRDataList = new JRBeanCollectionDataSource(dataList);
			//LOG.info("writeToPDF: step1 JRDataList="+JRDataList);
		//	 CleanAmortList.cleanAmortVoList(dataList);
			JasperReport jasperReport = (JasperReport)JRLoader.loadObject(JasperBuilderFile);
			//LOG.info("writeToPDF: step2 jasperReport="+jasperReport+",parameters.size()="+parameters.size());
			
        	JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters, JRDataList);  
        	
        	//LOG.info("writeToPDF: step3 jasperPrint="+jasperPrint);
    		
    		String resultPath = resultFileGenerator(baseResultPath,"pdf");
    		
			File file = new File(resultPath);
		
			File resultParent = file.getParentFile ();
		    
			LOG.info("writeToPDF: create dir resultParent="+resultParent);
		
			resultParent.mkdirs ();
			//LOG.info("writeToPDF: step4");
			out = new FileOutputStream(file);      
			
			JasperExportManager.exportReportToPdfStream(jasperPrint,out);
			//LOG.info("writeToPDF: step5");
			LOG.info("writeToPDF: write PDF to="+resultPath);
			out.close();
			
			return resultPath;
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e.getMessage());
		} finally {
			if (out!=null) {
				try {
					out.flush(); 
					out.close();
					 
					 
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}




public static String resultFileGenerator(String baseName,String fileType) {
	return baseName+getArchiveTimeStamp(System.currentTimeMillis())+"."+fileType;
}

public static String getArchiveTimeStamp(Long timeStamp) {
	SimpleDateFormat dayformatter = new SimpleDateFormat(("yyyy-MM-dd-hh-mm-ss"));
	String created_on= dayformatter.format (new Date (timeStamp.longValue ()));
	return "-on-"+created_on;		 
	 
}

public static String getPrintTime(long milliSeconds) {
	Long hours=0L;
	Long minutes=0L;
	Long remindSeconds = 0L;
	hours = milliSeconds/(60*60*1000);
	remindSeconds= milliSeconds%(60*60*1000);
	minutes = remindSeconds/(60*1000);
	
	String temp="";
	if (hours>0) temp += hours.toString()+"h ";
	if (minutes>0) temp+=minutes.toString()+"m";
	if (temp=="") temp="0";
	return temp;	
}
}

