package com.loan.agent.mvc.utils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

 
import com.loan.agent.calculators.vo.CompareLoanVo;
import com.loan.agent.dao.Agents;
import com.loan.agent.dao.SessionManager;
import com.loan.agent.dao.SessionManagerDAO;
import com.loan.agent.mvc.controller.AgentController;
import com.loan.agent.services.AgentAdService;
import com.loan.agent.services.AgentReviewService;
import com.loan.agent.services.Constant;
import com.loan.agent.services.QuoteDBService;
import com.loan.agent.services.impl.AgentAdServiceImpl;
import com.loan.agent.services.impl.LookupDataInitialServiceImpl;
import com.loan.agent.services.impl.QuoteDBServiceImpl;
import com.lowagie.text.pdf.codec.Base64.OutputStream;

import javax.servlet.http.HttpServletRequest;


public class Utility {
	
	
	private QuoteDBService quoteDBService; 


	static Logger LOG=Logger.getLogger(Utility.class);	
	 
	//private QuoteDBService quoteDBService;
	 

	private static Integer currentAgentId=null;
	
	 
	private static HttpServletRequest request=null;
	private static HttpServletResponse response=null;
	static Utility handler=null;
	
	public static Utility getInstance() {
		if (handler==null) {
			handler = new Utility();
		}
		return handler;
	}
	private Utility () {} 
	 
	
	public static Double getDoubleParameter(String str) throws Exception {
		if (request==null) {
			LOG.info("please initilize request by 	Utility.setRequest(request,response)");
			return 0.0;
		}
		String data = ServletRequestUtils.getStringParameter(request, str);
		
		if (data!=null && data.trim().length()>0) {
			return getDouble(data);
		}
		return 0.0;
	}
	
	public static Integer getAgentIdParameter(String str) throws Exception {
		if (request==null) {
			LOG.info("please initilize request by 	Utility.setRequest(request,response)");
			return 0;
		}
		/**
		 *  Get encoded parameter string
		 */
		String data = ServletRequestUtils.getStringParameter(request, str);
		LOG.info("Current data string="+data);
		if (data!=null && data.trim().length()>0) {
			/**
			 *  get decoded parameter string
			 */
			String decoded = ui.getDecodedString(data);
			
			return getInteger(decoded);
		}
		return 0;
	}
	
	public static Integer getIntegerParameter(String str) throws Exception {
		if (request==null) {
			LOG.info("please initilize request by 	Utility.setRequest(request,response)");
			return 0;
		}
		String data = ServletRequestUtils.getStringParameter(request, str);
		LOG.info("Current data string="+data);
		if (data!=null && data.trim().length()>0) {
			return getInteger(data);
		}
		return 0;
	}
	public static String getStringParameter(String str) throws Exception {
		if (request==null) {
			LOG.info("please initilize request by 	Utility.setRequest(request,response)");
			return null;
		}
		String data = ServletRequestUtils.getStringParameter(request, str);
		
		if (data!=null && data.trim().length()>0) {
			return data;
		}
		return null;
	}

    public static Blob getBlobFromObject(Object obj) {
    	  ByteArrayOutputStream bos =null;
          ObjectOutputStream out = null;
          Blob blob =null;
    	try {
    		 bos = new ByteArrayOutputStream();
    	     out = new ObjectOutputStream(bos);
    	     out.writeObject(obj);
    	     byte[] data = bos.toByteArray();
    		  
    		 blob = Hibernate.createBlob(data);
    		 LOG.info("Wrote to ByteArrayOutputStream already!");
    	} catch (Exception e) {
    		e.printStackTrace();
    		
    	} finally {
    		if (out!=null) {
    			try {
    				out.flush();
    				out.close();
    			} catch (Exception e) {}
    		}
    	}
    	return blob;
    }
  
    public static CompareLoanVo getCompareLoanVOFromBlob(Blob blob) {
    	  if (blob==null) return null; 
    	  ObjectInputStream ins = null;
   
    	  CompareLoanVo obj =null;
    	try {
    		 ins = new ObjectInputStream(blob.getBinaryStream()); 
    		  obj =(CompareLoanVo) ins.readObject();
    	  
    		 LOG.info("Read Object from Blob!");
    	} catch (Exception e) {
    		e.printStackTrace();
    		
    	} finally {
    		if (ins!=null) {
    			try {
    			 
    				ins.close();
    			} catch (Exception e) {}
    		}
    	}
    	return obj;
    }
    
    public static Object getObjectFromBlob(Blob blob) {
  	  if (blob==null) return null; 
  	  ObjectInputStream ins = null;
 
      Object obj =null;
  	try {
  		 ins = new ObjectInputStream(blob.getBinaryStream()); 
  		  obj = ins.readObject();
  	  
  		 LOG.info("Read Object from Blob!");
  	} catch (Exception e) {
  		e.printStackTrace();
  		
  	} finally {
  		if (ins!=null) {
  			try {
  			 
  				ins.close();
  			} catch (Exception e) {}
  		}
  	}
  	return obj;
  }
	public static String TermToDescription(String field)  {
		String retval=null;
		 if ("360".equals(field)) retval="30 Years Fixed";
		 if ("240".equals(field)) retval="20 Years Fixed";
		 if ("180".equals(field)) retval="15 Years Fixed";
		 if ("120".equals(field)) retval="10/1 ARM";
		 if ("84".equals(field)) retval="7/1 ARM";
		 if ("60".equals(field)) retval="5/1 ARM";
		 if ("36".equals(field)) retval="3/1 ARM";
		 return retval;
	}
	public static String DescriptionToTerm(String field)  {
		String retval=null;
		 
			 if ("30 Years Fixed".equalsIgnoreCase(field)) retval="360";
			 if ("20 Years Fixed".equalsIgnoreCase(field)) retval="240";
			 if ("15 Years Fixed".equalsIgnoreCase(field)) retval="180";
			 if ("10/1 ARM".equalsIgnoreCase(field)) retval="120";
			 if ("7/1 ARM".equalsIgnoreCase(field)) retval="84";
			 if ("5/1 ARM".equalsIgnoreCase(field)) retval="60";
			 if ("3/1 ARM".equalsIgnoreCase(field)) retval="36";
			 return retval;
	}
	
	public static Double DescriptionToDoubleTerm(String field)  {
		String retval=null;
		 
			 if ("30 Years Fixed".equalsIgnoreCase(field)) retval="360";
			 if ("20 Years Fixed".equalsIgnoreCase(field)) retval="240";
			 if ("15 Years Fixed".equalsIgnoreCase(field)) retval="180";
			 if ("10/1 ARM".equalsIgnoreCase(field)) retval="120";
			 if ("7/1 ARM".equalsIgnoreCase(field)) retval="84";
			 if ("5/1 ARM".equalsIgnoreCase(field)) retval="60";
			 if ("3/1 ARM".equalsIgnoreCase(field)) retval="36";
			 return Utility.getDouble(retval);
	}
	
	public static Integer DescriptionToIntegerTerm(String field)  {
		String retval=null;
		 
			 if ("30 Years Fixed".equalsIgnoreCase(field)) retval="360";
			 if ("20 Years Fixed".equalsIgnoreCase(field)) retval="240";
			 if ("15 Years Fixed".equalsIgnoreCase(field)) retval="180";
			 if ("10/1 ARM".equalsIgnoreCase(field)) retval="120";
			 if ("7/1 ARM".equalsIgnoreCase(field)) retval="84";
			 if ("5/1 ARM".equalsIgnoreCase(field)) retval="60";
			 if ("3/1 ARM".equalsIgnoreCase(field)) retval="36";
			 return Utility.getInteger(retval);
	}
	
	public static HttpServletRequest getRequest() {
		return request;
	}

	public static void setRequest(HttpServletRequest request,HttpServletResponse response) {
		Utility.request = request;		
		Utility.response = response;	
		//Utility.getInstance().AgentIdProcessor(request,response);
		
	}
	
	public static void setRequest(HttpServletRequest request) {
		Utility.request = request;
		//Utility.getInstance().AgentIdProcessor(request);
	 
	}
	 
	 
	public static Integer getCurrentAgentId() {
		return currentAgentId;
	}
	public static void setCurrentAgentId(Integer currentAgentId) {
		Utility.currentAgentId = currentAgentId;
	}
	 
   	
 	public void AgentIdProcessor(HttpServletRequest request) {
		try {
		   // LOG.info("AgentIdProcessor begin");	    
		 
		/*	String paramAgentIdStr = request.getParameter(Constant.PARAM_AGENT_ID); 
			if (paramAgentIdStr!=null) {
			     paramAgentIdStr = ui.getDecodedString(paramAgentIdStr);
				 request.getSession().setAttribute(Constant.PARAM_AGENT_ID, new Integer(paramAgentIdStr));
			} */
		    
		    /**
		     *  Set session timeout is 2 hours
		     */
		 //  
		    /**
		     *  Once user click on email link with parameter agent id and get into loans-agent.com
		     *  this id will be available until they close session, so save it to param save agent id 
		     *  After user close window, next time they open they still can see this agent profile
		     *  to do this by cookie
		     */
		    
			Integer paramAgentId=(Integer) request.getSession().getAttribute(Constant.PARAM_AGENT_ID);			 
			
			Integer loginAgentId =(Integer) request.getSession().getAttribute(Constant.LOGIN_AGENT_ID); 
			
			 
			
			String localMachineName = java.net.InetAddress.getLocalHost().getHostName();
		
			HttpSession session = request.getSession(true);
			
			String currentSessionId = session.getId();
			
			//LOG.info("Utility.AgentIdProcessor() localMachineName="+localMachineName+",sessionId="+session.getId()); 
				
		 	
		//	LOG.info("Parameter Agentid="+paramAgentId+",LoginAgentId="+loginAgentId);

			   /**
		     *  session_manager table:   
  				client_machine_name 
  				login_agent_session_id        varchar(255),
  				login_agent_id 
  				param_agent_id   
  				login_agent_name 
  				param_agent_name 
  				(1) fetch sessionManager by localMachineName, one local machine keep only unique record in DB
  				    if no record found, use current localmachine, session_id, login_agent_id and param_agent_id create one
  				(2) If login_agent_id is not null, , loginAgent code save login agentid and session id to db two agent id and session_id
  				(3) if login_agent_id is null,  compare if current session id is login_agent_session_id, if same, 
  				    means login_id was removed from session, fetch db agent_login id to save it to session
  				    if session_ids are different or db session id is null, means login session expired, fetch db param_agent_id 
  				    to session 
  				(4) If param_agent_id is not null , means current user clicks on the links in their email , then 
  				    it should overwrite all db and session id.
		     */
			//quoteDBService =(QuoteDBService) SpringFramework.getBean("QuoteDBService");

		  //  SessionManagerDAO sessionHandler =quoteDBService.getSessionManagerDAO();  
		    
		  /*  List<SessionManager> smList = sessionHandler.findByClientMachineName(localMachineName);
		    
		    SessionManager SM = null;
		    Integer managerId = null;  // just for modify merge
		    SessionManager existSM =null;
		    
		    if (null!=smList && smList.size()>0) {
    			existSM= smList.get(0);
    			managerId =existSM.getManagerId(); 		
    			
    		} 
		  */
			QuoteDBService quoteDBService = new QuoteDBServiceImpl();
			
			SessionManager existSM =  quoteDBService.findSessionManagerByClientMachineName(localMachineName);  // just for modify merge
			
		    SessionManager SM = null;
		    Integer managerId = null;
		    
		    if (null!=existSM) {
		    	managerId  = existSM.getManagerId();
		    }
		    

			 
			if (loginAgentId!=null) {				 
				// rewrite login agent id , then start a new period
				 
				 request.getSession().setAttribute(Constant.LOGIN_AGENT_ID,loginAgentId); 
				
				// LOG.info("AgentIdProcessor setAgentProfile() run with loginAgentId="+loginAgentId);
				 
				 AgentAdService adHandler =new AgentAdServiceImpl();
				 
				 adHandler.setAgentProfile(request,loginAgentId); 
			
				// LOG.info("AgentIdProcessor rewrite loginAgentId="+loginAgentId);
				 
			} else {
				/**
				 *  (1) if login_agent_id is null,  compare if current session id is login_agent_session_id, if same, 
  				    means login_id was removed from session, fetch db agent_login id to save it to session
  				    if session_ids are different or db session id is null, means login session expired, fetch db param_agent_id 
  				    to session 
					(2) all JSPs use Constant.LOGIN_AGENT_NAME as current agent name
				 */
				if (null!=existSM) {
					// login agent id ==null
					if (currentSessionId.equalsIgnoreCase(existSM.getLoginAgentSessionId())) { // session_ids are same
						if (null==paramAgentId) {
							// recover the login agent id 							 
							request.getSession().setAttribute(Constant.LOGIN_AGENT_ID,existSM.getLoginAgentId());  
							request.getSession().setAttribute(Constant.LOGIN_AGENT_NAME,existSM.getLoginAgentName());  
						}
					} else { // session_ids are different
						if (null==paramAgentId) {							 
							request.getSession().setAttribute(Constant.PARAM_AGENT_ID,existSM.getParamAgentId());
							request.getSession().setAttribute(Constant.LOGIN_AGENT_NAME,existSM.getParamAgentName());
						}
					}
				}
				/** 
				 *  login_agent_id is null, looking for paramAgentIdStr
				 */
				if (null!=paramAgentId) {
					// rewrite paramAgentId to datbase
					 
					request.getSession().setAttribute(Constant.PARAM_AGENT_ID,paramAgentId); 
					quoteDBService = (QuoteDBService) SpringFramework.getBean("QuoteDBService");
					Agents agents = quoteDBService.getAgentsDAO().findById(paramAgentId);						
					String agentName = agents.getFirstName()+" "+agents.getLastName();
					SM = new SessionManager();					
					SM.setClientMachineName(localMachineName);
					SM.setLoginAgentSessionId(session.getId());
					SM.setLoginAgentId(null);   
					SM.setParamAgentId(paramAgentId);			
			    	SM.setLoginAgentName(null);
			    	SM.setParamAgentName(agentName);
			    	
			    	quoteDBService.updateSaveSessionManager(SM, managerId);
			    	
			    	
			    /*	if (null==managerId) {
			    		sessionHandler.save(SM);
			    	} else {
			    		SM.setManagerId(managerId);
			    		sessionHandler.merge(SM);
			    	}*/
					 
					LOG.info("AgentIdProcessor setAgentProfile() run with paramAgentId");
					
					AgentAdService adHandler =new AgentAdServiceImpl();
					
					adHandler.setAgentProfile(request,paramAgentId);
			
				} else { // login_agent_id is null and paramAgentIdstr  is null , recover paramAgentId at least
					if (null!=existSM) {  
						 
						// reset PARAM_AGENT_ID immediately
						request.getSession().setAttribute(Constant.PARAM_AGENT_ID,existSM.getParamAgentId()); 
						request.getSession().setAttribute(Constant.LOGIN_AGENT_NAME,existSM.getParamAgentName());
						AgentAdService adHandler =new AgentAdServiceImpl();
						adHandler.setAgentProfile(request,existSM.getParamAgentId());
					}  else {
							LOG.info("Utility.AgentIdProcessor() PARAM_AGENT_ID cookie NOT Found!");
						 
					}
				}
			} 
		} catch (Exception e) {
			LOG.info("Utility.AgentIdProcessor failed! "); 
			e.printStackTrace();
		}
		Integer agentID =(Integer) request.getSession().getAttribute(Constant.AGENT_ID);	
		if (agentID==null) {
			request.getSession().setAttribute(Constant.DUTY_AGENT_ONLY,"yes");
		}
		
		//LOG.info("AgentIdProcessor end");
	} 
  	
  	
   	/**
   	 * If cookie not exists , create new cookie, if exist , only modify by new cookieValue
   	 * 
   	 * @param response
   	 * @param cookieName
   	 * @param cookieValue
   	 * @param days
   	 * @return
   	 */
	public boolean setCookie(HttpServletResponse response,String cookieName,String cookieValue,int days) {
		//LOG.info("setCookie  begin");
		
		Cookie cookie= getCookie(request,cookieName);
		/**
		 *  Cookie exist
		 */
		if (cookie!=null) {
			// LOG.info("setCookie  cookie name "+cookieName+", exists!");
			 cookie.setValue(cookieValue);			 
			 response.addCookie(cookie);
		//	 LOG.info("setCookie Modify cookie  end!");
			 return true;
		}
		
		  /**
		   * Cookie not exists, create one
		   *  if days ==-1 , 'never expires'
	       */
		int maxAge;
	//    if (days==-1) {
	    	maxAge=-1;
	   // } else {
	   // 	maxAge=60*60*24*days;
	   // }
		
		    cookie = new Cookie(cookieName,cookieValue);
		    cookie.setPath(request.getContextPath());
			cookie.setMaxAge(maxAge); //days
			response.addCookie(cookie);
			LOG.info("setCookie  end");
		return true;
	 }
	
	  public Cookie getCookie(HttpServletRequest request, String name) {
			//LOG.info("getCookie  begin");
	        if (null!=request.getCookies()) {
	        	//LOG.info("request.getCookies().length = "+request.getCookies().length);
	            for (Cookie cookie : request.getCookies()) {
	            	//LOG.info("Cookie Name= "+cookie.getName());
	                if (cookie.getName().equalsIgnoreCase(name)) {
	                    return cookie;
	                }
	            }
	        } else {
	        	LOG.info("request.getCookies() == null");
	        }
	       // LOG.info("getCookie  end");
	        return null;
	   }
	
	public static void setParamAgentId(HttpServletRequest request) {
		setRequest(request);
	}
	public static PrintWriter SetPostResponseContent(HttpServletResponse response) {
		
		PrintWriter out = null;
		try {
			out=response.getWriter();
			response.setContentType("text/html");
			response.setHeader("Cache-control", "no-cache, no-store");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Expires", "-1");
 
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Access-Control-Allow-Methods", "POST");
			response.setHeader("Access-Control-Allow-Headers", "Content-Type");
			response.setHeader("Access-Control-Max-Age", "86400");
		} catch (Exception e) {
			e.printStackTrace();
			LOG.info(e.getMessage());
		}
		setParamAgentId(request);
		return out;
	}

	// upload File
	public static FileDocument  uploadFile(MultipartFile multipartFile) {
		
		  
	    FileDocument file = new FileDocument();
	    try {
	    	LOG.info("pictureContent FileName="+multipartFile.getOriginalFilename()+",multipartFile.getBytes().size()="+multipartFile.getBytes().length);
	   
	    	file.setContentType(multipartFile.getContentType());
	    	file.setFileName(multipartFile.getOriginalFilename());
	    	file.setContent(Hibernate.createBlob(multipartFile.getBytes()));
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	LOG.info(e.getMessage());
	    }
	    
		return file;
	}
	public static void fetchFile(HttpServletResponse response,FileDocument doc) {
		try {
            response.setHeader("Content-Disposition", "inline;filename=\"" +doc.getFileName()+ "\"");
            ServletOutputStream out = response.getOutputStream();
            response.setContentType(doc.getContentType());
            IOUtils.copy(doc.getContent().getBinaryStream(), out);
            LOG.info("Blob document fetched!");
            
            out.flush();
            out.close();
         
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public static BigDecimal getDecimal(String s) {
		if (s==null || s=="") return null; 
		String str = s.replace(",", "");
		str = str.replace("$", "");
		return  new BigDecimal(s); 
	}
	
	public static Double getDouble(String s) {
		if (s==null || s=="") return null; 
		String str = s.replace(",", "");
		str = str.replace("$", ""); 
		return Double.parseDouble(str);
	}
	
	
	public static Integer getInteger(String s) {
		if (s==null || s=="") return null; 
		String str = s.replace(",", "");
		str = str.replace("$", ""); 
		LOG.info(str);
		return Integer.parseInt(str);
	}
	
	public static Integer getMonth(String s) {
		if (s==null || s=="") return null; 
		 
		return  Integer.parseInt(s.substring(0,2));
	}
	public static Integer getYear(String s) {
		if (s==null || s=="") return null; 
		 
		return  Integer.parseInt(s.substring(6,10));
	}
	public static Float getFloat(String s) {
		if (s==null || s=="") return null; 
		String str = s.replace(",", "");
		str = str.replace("$", "");  
		return Float.parseFloat(str);
	}
	
	public static Date getDate(String s) {
		Date date = null;
		if (s==null || s=="") return null; 
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		 try
		    {
		       date = formatter.parse(s);
		    }
		    catch (ParseException e)
		    {
		      
		      e.printStackTrace();
		    }
		 return date;
	}
	public static String getHalfYearBeforeToday( ) {
		Calendar date = Calendar.getInstance();
  		Calendar cldr;
  		SimpleDateFormat dateformatter = new SimpleDateFormat ("MM-dd-yyyy");

  		cldr = (Calendar) date.clone();
  		cldr.add(Calendar.DAY_OF_YEAR, - (365 / 2));
  		String startDate = dateformatter.format(cldr.getTime());
  		return startDate;
	}
	public static Timestamp getCurrentTimeStamp() {
		// create a java calendar instance
		Calendar calendar = Calendar.getInstance();

		// get a java.util.Date from the calendar instance.
		// this date will represent the current instant, or "now".
		java.util.Date now = calendar.getTime();

		// a java current time (now) instance
		return new java.sql.Timestamp(now.getTime());
	}
	
	public static String renderDollar(Double d) {
		if (d==null ) return null; 
	  DecimalFormat df = new DecimalFormat("$#,###.00");
	  return df.format(d);
	}
	public static String renderDouble(Double d) {
		if (d==null ) return null; 
	  DecimalFormat df = new DecimalFormat("####.00");
	  return df.format(d);
	}
	
	public static String renderInteger(Integer i) {
		if (i==null ) return null; 	 
	  return (new Integer(i)).toString();
	}
	public static String renderRate(Float d) {
		if (d==null ) return null; 
		  DecimalFormat df = new DecimalFormat("##.###%");
		  return df.format(d/100.0);
	}
	public static String renderRate(Double d) {
		if (d==null ) return null; 
		  DecimalFormat df = new DecimalFormat("##.###%");
		  return df.format(d/100.0);
	}
	public static String renderDate(Date d) {
		if (d==null ) return null; 
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		return formatter.format(d);
	}
	public static String renderTimestamp(Timestamp d) {
		if (d==null ) return null; 
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy hh.mm.ss");
		return formatter.format(d);
	}
	public static String getMain(String str) {
		return str.replace(" ", "_");
	}
	
	public static boolean isEmpty(String str) {
		boolean retVal=true;
		if (str==null) {
			return retVal;
		} else {
			if (str.trim().length()==0) {
				return retVal;
			} else {
				return false;
			}
		}
				
	}
	
	public byte[] getImageByteArrayByFile(String origfile) {
		LOG.info("getImageByteArray begin");
		 
		byte[] bytes=null;
		
		
		File file= null;
		 
		
		file= new File(origfile);
		if (!file.exists()) {
		     LOG.info("Can not find the picture file! ");
		     return null;
	    }		 
		 
		 FileInputStream fis=null;
		 ByteArrayOutputStream bos=null;
		 LOG.info("read image begin ");
		 try {
			   bos = new ByteArrayOutputStream();
		       byte[] buf = new byte[1024];
		       
		       fis= new FileInputStream(file);
		       
		       for (int readNum; (readNum = fis.read(buf)) != -1;) {
		          //Writes to this byte array output stream
		           bos.write(buf, 0, readNum); 
		       }
		       bytes = bos.toByteArray();       
		       LOG.info("read image OK!  bytes size="+ bytes.length);
		 } catch (Exception e) {
			 LOG.info("Error read image byte! "+e.getMessage());
		 } finally {
			 if (fis!=null)
			 try {
				 fis.close();
				 bos.close();
			 } catch (Exception e)
			 {}
		 
		 }
		// file.delete();
		 LOG.info("getImageByteArray begin");
		 return bytes;
	}
	
	/*
	String uploadPicture=Utility.getStringParameter("uploadPicture");
	
	if (uploadPicture!=null && "yes".equalsIgnoreCase(uploadPicture)) {
		Long currentTime = System.currentTimeMillis();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	     
		MultipartFile multipartFile = multipartRequest.getFile("pictureContent");
		
		String contentType = multipartFile.getContentType();
		 
		    Create temp directory /uploadfileDir/ and convert URL to absolute path
		  
		String realPath = getServletContext ().getRealPath("/");
		String uploadFiles = realPath.replace("\\", "/")+Constant.UPLOAD_FILE_TEMP;
		File file = new File(uploadFiles);	
	
	
		if (!file.exists()) {
			LOG.info("Creating dirctory ="+file.getAbsolutePath());
			
			file.mkdirs();
		}
		
		String destFile = file.getAbsolutePath()+"/img_"+currentTime.toString();
		
		LOG.info ("upload file destFile="+destFile);
		AgentController agentCtl = new AgentController();
		
		String resultFile=agentCtl.writePicture(request, destFile);
		
		if (resultFile==null) {
			return null;
		}
		LOG.info ("upload file temporary OS file in "+ resultFile);
		String ext[] = resultFile.split("\\.");
		byte[] blobAsBytes=agentCtl.getImageByteArrayByFile(resultFile);
		
		if (blobAsBytes==null) {
			LOG.info("blobAsBytes=null");
			return null;
		} else {
			LOG.info("blobAsBytes.length="+blobAsBytes.length);
		}
		request.getSession().setAttribute("uploadWebFile", resultFile); 
		request.getSession().setAttribute("contentType", contentType); 
		ModelAndView mode = new ModelAndView("AgentSignup"); 	
		return mode;
	}*/
	public static String getFileExtention(String fileName) {
		String retVal=null;
		if (isEmpty(fileName)) {
			return null;
		}
		int len = fileName.length();
		int i=0;
		for (i=len-1;i>=0; i--) {
			if ('.'==fileName.charAt(i)) {
			   break;	
			}
		}
		if (i>0) {
			return fileName.substring(i);
		}
		return null;
	}
	
	
	public static void main(String [] args) {
		System.out.println(getMain("external trusted server"));
		String str="   ";
		System.out.println("case 1 str is " +isEmpty(str));
		str="";	
		System.out.println("case 2 str is " +isEmpty(str));
		str=null;
		System.out.println("case 3 str is " +isEmpty(str));
		str="hehe";
		System.out.println("case 4 str is " +isEmpty(str));
		System.out.println( getFileExtention("GoogaiInc-Dennie-Cha-Loan-Application-Form.pdf"));
		System.out.println( getFileExtention("Flight booking status_12_23_12_27_2011.doc"));
		
	}
}
