package com.security.app.crypto.service;

import java.io.File;

public interface Constants {
	/**
	 *  Symmetric Key Authentication 
	 */
	
	public static final double RANDOM_NUMBER_LENGTH = 1000000000.0;
	public static final int RANDOM_EXPIRED_DURATION_MSEC =3610000;
	public static final int RANDOM_BUFFER_SCAN_DURATION_MSEC = 9000;
	public static final String RANDOM_ADDED ="added";
	public static final String RANDOM_TOO_OLD ="tooOld";
	public static final String RANDOM_REPEATED ="repeated";
	 
	
	 
	public static final String ERR_OUTSIDE_CLUSTER_CC_ACCESS ="outside cluster CC access!";
	public static final String ERR_RANDOM_NUMBER_TOO_OLD ="random number is too old !";
	public static final String ERR_RANDOM_NUMBER_REPEATED ="random number is repeated!";
	public static final String ERR_CCHOSTNAME_NOT_AVAIBLE ="CC hostname is not available!";
	public static final String ERR_RANDOM_NUMER_NOT_AVAIBLE ="random number is not available!";
	public static final String ERR_CC_TIMESTAMP_NOT_AVAIBLE ="CC sent timestamp is not available!";
	
	public static final String ERR_CCHOSTNAME_FAILED_DECRYPT ="CC hostname could not be descrypted!";
	public static final String ERR_RANDOM_NUMER_FAILED_DECRYPT ="random number could not be descrypted!";
	public static final String ERR_CC_TIMESTAMP_FAILED_DECRYPT ="CC sent timestamp could not be descrypted!";
	
	
	/**
	 *  Deployment Selection
	 */
	
	public static final boolean ALLOW_MLFWORR_API_WORK=false;     //false just for code testing without calling PMTA queue API work. Deploy it must be true
	
	public static final boolean REJECT_OUTSIDE_OF_CLUSTER_CC=true;     //false just for code testing . Deploy it must be true
	
	public static final String  CC_PORT_NUMBER="8080";           // 8080 for code testing in eclipse, As deploying, it must be null
	
	public static final Integer MAX_LINE_DETAIL=1000;          // limitation of DETAIL page lines
	
	
	public static final String CACHE_FILE_TOTAL_MESSAGE_IN_QUEUE="VMTAMsgQueue.txt";   
	
	public static final String CACHE_FILE_PER_HOSTE="VMTAPerHost.txt";   
	
	public static final String CACHE_FILE_INBOUND_PER_PATH="VMTAInboundPerPath.txt";   
	
	public static final String CACHE_FILE_OUTBOUND_PER_PATH="VMTAOutboundPerPath.txt";   
	
	
	public static final String CONNECTION_REFUSED="ConnectionRefused";
	public final static String VMTA_WEB_SERVICE_HANDLER = "/services/jsonServices/fetch_ra_vmta_to_json";
	public final static String MTA_STATUS_WEB_SERVICE_HANDLER = "/services/jsonServices/fetch_ra_mta_status_json";
	public final static String DELIVER_MESSAGES_WEB_SERVICE_HANDLER = "/services/jsonServices/deliver_all_queued_message";
	/**
	 *  Constants for mta_queuedetailsbyvmta.xml
	 */
	 public static final String ROOT_TAG ="contents";
	 public static final String VMTA_FILE ="mta_queuedetailsbyvmta.xml";
	 public final static String VMTA_TAG = "vmta";	 
	 public final static String VMTA_NAME_ATTR = "name";
	 public final static String EMAIL_TAG= "email";
	 
	 public final static String ENV_FROM_ATTR = "envfrom";
	 public final static String ENV_RCPT_TO_ATTR="envrcptto";
	 public final static String SUBJECT_ATTR="subject";	 
	 public final static String TIMEQUEUED_ATTR="timequeued";	
	 public final static String SPOOL_FILE_ATTR="spoolfile";
	 public final static String LASTERR_ATTR="lasterr";
	 public final static String HOST_NAME="hostname";
	 public final static String PATH_NAME="pathname";
	 public final static String REPORT_DIR="reports"+File.separator;
	 public final static String REPORT_FOLDER="reports";
	 
	 
	 public final static String DETAIL_LINES = "detailLines";    
	 public final static String DETAIL_NUMMSGS = "numMsgsInQueue";    
	 public final static String DETAIL_NUMMRCPTS = "numRcptsInQueue";    
	 
	 /**
	  *  Constants for mta_status.xml
	  */
	 public final static String MTA_STATUS_FILE ="mta_status.xml";
	 
	 public final static String MTA_STATUS_CCJSONVO = "MTAStatusCCJsonVO";
	 public final static String MTA_DETAILVO = "MTADetailVO";
	 
	 public final static String TOTAL_MESSAGES_IN_QUEUES= "totalMessagesInQueues";
	 public final static String TOTAL_SECTION= "totalSection";
	 public final static String INBOUND_SECTION= "inboundSection";
	 public final static String OUTBOUND_SECTION= "outboundSection";
	 public final static String INBOUND_PATHS="inboundpaths";
	 public final static String OUTBOUND_PATHS="outboundpaths";
	 
	
	 
	 public final static String STATSBYHOST_TAG= "statsbyhost";
	 public final static String CONTENTS_TAG= "contents"; 
	 public final static String META_STATUS_TAG= "metastatus"; 
	 public final static String MTA_SERVICE_RUNNING_ATTR= "mtaservicerunning"; 
	 public final static String HOST_TAG= "host"; 
	 public final static String INBOUND_PATH_TAG="inboundpath";
	 public final static String OUTBOUND_PATH_TAG="outboundpath";
	 public final static String MTA_CONFIG_ATTR="mtaconfig";
	
	 public final static String DETAIL_LOAD_DATA_ONLY="loadDataOnly";  
	 public final static String DETAIL_VMTA_NAME_ATTR="vmtaname"; 
	 public final static String DETAIL_PATH_IDE_ATTR="pathid"; 
	 public final static String DETAIL_OUADM_NAME_ATTR="ouname"; 
	 public final static String DETAIL_CONTEXT_JSONSTR="contextJsonStr"; 
	 
	 public final static String QUEUE_LENGTH_RCPTS_ATTR="queuelengthrcpts";
	 public final static String MSGS_IN_LAST_HOUSR_ATTR="msgsinlasthour";
	 public final static String QUEUE_LENGTH_MSGS_ATTR="queuelengthmsgs";
	 public final static String NQHOSTNAME_ATTR="nqhostname";
	 public final static String PATH_DESCRIPTION_ATTR="pathdescription";
	 
	 public final static String MESSAGE_FROM_RA="message";
 
	 
	 public final static String SERVICE_STATUS= "serviceStatus";
	 public final static String NO_MTA_STATUS_DATA= "noMtaStatusData";
	 
	 
	 /**
	  *  Error Message from RA to CC
	  */
	 public final static String ERR_NO_COMMAND_TYPE ="No command type";
	 public final static String ERR_READ_RA_NO_HOST="Read RA without Hostname";		 
	 public final static String SUCCEED_TO_RETRIEVE="Succeed to retrieve";
	 /**
	  *  CommandType will be sent to RA from CC
	  */
	 public final static String CMD_READ_RA_PATH_INFO="cmd read RA path INFO";
	 public final static String CMD_READ_RA_VMTA_INFO="cmd read RA VMTA INFO";
	 
	 public final static String CMD_DELIVER_ALL_QUEUED_MESSAGE="cmd deliever all queued message";
	 public final static String SUCCEED_DELIVER_ALL_QUEUED_MESSAGE="SucceedOndelivering";

	
	 public static final String FETCH_RA_MTA_STATUS_TO_JSON= "/fetch_ra_mta_status_json";		 
	 public static final String FETCH_RA_MTA_STATUS_TO_XML= "/fetch_ra_mta_status_xml";		
	 public static final String FETCH_RA_VMTA_TO_JSON= "/fetch_ra_vmta_to_json";
	 public static final String FETCH_RA_VMTA_TO_XML= "/fetch_ra_vmta_to_json";

}
