package com.email.list.reader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.loan.agent.services.Constant;

public interface EmailListService {
	
	public static final int START_COL_NUMBER=0;
	
	public List<EmailDynamicVO> getDynamicEmailInfo(InputStream fis);
	
 	

}
