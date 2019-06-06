package com.loan.agent.services;

import java.util.List;

 
/**
 * Constants for communication between form and controller
 * @author johnzhang
 *
 */
		
public interface Constant {
	
	public static final String ADJUSTEDMONTHLYPAYMENT="adjustedmonthlypayment";
	public static final String BEGIN_BALANCE="begin_balance";
	public static final String EXTRA_PMT="extra_PMT";
	public static final String EXTRA_YPMT="extra_YPMT";
	public static final String EXTRA_YPMT_ONCE="extra_YPMT_once";
	public static final String FIRST_DATE="first_date";
	public static final String FIRST_MM="first_mm";
	public static final String FIRST_YYYY="first_yyyy";
	public static final String INT_RATE="int_rate";
	public static final String LOAN_AMT="loan_amt";
	public static final String MONTH="month";
	public static final String MONTHLYPAYMENT="monthlypayment";
	public static final String ONCE_DATE="once_date";
	public static final String PMT_DATE="PMT_date";
	public static final String PMT_MM="PMT_mm";
	public static final String PMT_YYYY="PMT_yyyy";
	public static final String REALPAYMONTHS="realpaymonths";
	public static final String STRSUMMARYINTPAYMENT="strsummaryintpayment";
	public static final String STRSUMMARYMONTHPAYMENT="strsummarymonthpayment";
	public static final String STRSUMMARYPRINPAYMENT="strsummaryprinpayment";
	public static final String TERM="term";
	public static final String YEAR_INT_RATE="year_int_rate";
	public static final String YPMT_DATE="YPMT_date";
	public static final String YPMT_MM="YPMT_mm";
	public static final String YPMT_ONCE_DATE="once_date";
	public static final String YPMT_ONCE_MM="YPMT_once_mm";
	public static final String YPMT_ONCE_YYYY="YPMT_once_yyyy";
	public static final String YPMT_YYYY="YPMT_yyyy";
	
	public static final String PDF_BASE_DIR="/tempdir";
	
	public static final String UPLOAD_FILE_TEMP="/uploadFile/temp";
	
	public static final String UPLOAD_WEB_FILE="uploadWebFile";
	
	public final static int EIGHT_CHARS=8;
	public final static int SIX_CHARS=6;
	public final static int FOUR_CHARS=4;
	
	public static final String PURCHASE_PRICE ="purch_price";	
	public static final String PROPERTY_TAX="prop_tax";	
	public static final String PROPERTY_INSURANCE="prop_inc";
	
	public static final String NON_RENTAL_INCOME ="nonRentalIncome";	
	public static final String RENTAL_INCOME="rentalIncome";	
	public static final String DEBT="debt";	
	public static final String LTV ="LTV";	
	
	public static final String DOWN_PAYMENT ="downPayment";

	public static final String AGENT_ID ="agentId";
	/**
	 *  Level 1
	 */
	public static final String USER_ASSIGN_AGENT_ID ="userAssignAgentId";
	/**
	 *  Level 1
	 */
	public static final String LOGIN_AGENT_ID ="loginAgentId";
	public static final String LOGIN_AGENT_NAME="loginAgentName";
	public static final String LOGIN_AGENT_EMAIL ="loginAgentEmail";
	public static final String LOGIN_AGENT_PASSWORD="loginAgentPassword";
	
	/**
	 *  level 2
	 */
	
	public static final String PARAM_AGENT_ID ="paramAgentId";
	
	/**
	 * Level 3
	 */
 
	public static final String TIMER_AGENT_ID ="timerAgentId";
	
	public static final String PARAM_MY_EMAIL ="paramMyEmail";
	
	public static final int COOKIE_AGE_DAYS=30;
	
	
	public static final String PREV_LOGIN_AGENT_ID ="PrevLoginAgentId";
	
	public static final String PARAM_SAVED_AGENT_ID ="ParamSavedAgentId";
	
	public static final String FORWARD_AGENT_LOGIN="AgentLogin";
	
	public static final String AGENT_LOGIN_FOR="agentLoginFor";
	
	public static final String USER_LOGIN_DIRECT="userLoginDirect";
	
	public static final String AGENT_LOGIN_DIRECT="agentLoginDirect";	
	
	public static final String AGENT_LOGIN_REDIRECT="AgentLoginRedirect";
	
	public static final String AGENT_PROFILE="agentProfile";
	
	public static final String HOST_NAME="hostName";
	
	public static final String AGENT_EMAIL_PICTURE_URL="agentEmailPictureURL";

	
	public static final String AGENT_EMAIL_LINKS="agentEmailLinks";
	
	public static final String AGENT_ADVERTISE_PAGE="AgentAdPage";
	
	public static final String GENERATE_AGENT_EMAIL="GenerateAgentEmail";
	
	public static final String PURCHASE_QUOTE_EMAIL="PurchaseQuoteEmail";
	
	public static final String REFINANCE_QUOTE_EMAIL="RefinanceQuoteEmail";

	
	public static final String INTEREST_RATE_LIST="InterestRateList";
	
	public static final String AGENT_LOGIN_SUCCESS="AgentSignupSuccess";
	public static final String AGENT_REVIEW_QUOTE="AgentReviewQuote";
	public static final String AGENT_REVIEW_QUOTE_REDIRECT="AgentReviewQuoteRedirect";
	public static final String AGENT_ENTER_RATE_SHEET="RateSheetForm";
	public static final String AGENT_EMAIL_MARKETING="EmailMarketing";
	public static final String AGENT_RELY_QUOTE_LOGIN="AgentReplyQuoteLogin";
	public static final String AGENT_NICHE_PROGRAM="NicheProgram";
	public static final String AGENT_EMAIL_MARKETING_REDIRECT="AgentEmailMarketingRedirect";
	public static final String AGENT_NICHE_PROGRAM_REDIRECT="AgentNicheProgramRedirect";
	public static final String AGENT_RELY_QUOTE_LOGIN_REDIRECT="AgentReplyQuoteLoginRedirect";
	
	
	
	public static final String AGENT_ENTER_RATE_SHEET_REDIRECT="RateSheetFormRedirect"; 
	
	
	public static final String RATE_SHEET_SAVE_SUCCESS="RateSaveSuccess";
	public static final String REDIRECT_TO_RATE_SHEET_FORM="rateSheetForm.html";
	public static final String RATE_SHEET_COMMAND_NAME="rateSheetForm";
	
	public static final String CONFORMING_RATE_LIST="conformingList";
	public static final String SUPER_CONFORMING_RATE_LIST="superConformingList";
	public static final String JUMBO_RATE_LIST="jumboList";
	
	
	
	public static final String USER_ID ="userId";
	public static final String QUOTE_ID ="quoteId";
	public static final String REPLY_ID ="replyId";
	public static final String CHOSEN_INDEX ="chooseIndex";
	public static final String ADVICE_NOTE="advice_note";
	public static final String EMAIL_ADDRESS="emailAddress";
	public static final String PASSWORD="password";
	public static final String REDIRECT_TO="redirectTo";
	
	public static final Integer CURRENT_LOAN=0;
	public static final Integer REPLY_LOAN1=1;
	public static final Integer REPLY_LOAN2=2;
	public static final Integer REPLY_LOAN3=3;
	public static final Integer REPLY_LOAN4=4;
	
	public static final Integer COMP_LOAN_NUM=4;
	
	public static final String TERM_30_YEARS="30 years fixed";
	public static final String TERM_15_YEARS="15 years fixed";
	public static final String TERM_10_YEARS="10/1 ARM";
	public static final String TERM_7_YEARS="7/1 ARM";
	public static final String TERM_5_YEARS="5/1 ARM";
	public static final String TERM_3_YEARS="3/1 ARM";
	public static final String WINDOWS_DATA_PATH="C:/Program Files (x86)/LoansAgentDir/data/";
	public static final String LINUX_DATA_PATH="/home/johnz148/public_html/loansagentdir/data/";
	public static final String AGENT_PICTURE_PATH="agentdir/pictures/";
	public static final String AGNET_EMAIL_LIST_PATH="agentdir/emailListFiles/";
	public static final String AGNET_EMAIL_ATTACHMENT_PATH="agentdir/attachedFiles/";
	public static final String LOAN_APPLICTION_FORM_PATH="agentdir/loanApplicationForms/";
	public static final String COMMON_LOG_PATH="commonlog/";
	public static final String TEMP_DIR="tempdir/";
	public static final String PNG_TYPE="image/png";
	public static final String JPEG_TYPE="image/jpeg";
	public static final String OCTET_STREAM="application/octet-stream";
	
	
	
	public static final String PREFIX_APP_FORM_FILE="appFormFile_";
	
	public static final String PREFIX_AGENT_PICTURE_FILE="agent_";
	
	
	public static final String HTML_CONTENT_APP_FORM="appFormFile";
	
	public static final String HTML_CONTENT_PICTURE="pictureContent";
	
	
	public static final String AGENT_FULL_NAME = "agentFullName";
	
    public static final String AGENT_FORM="agentTable";
	
	public static final String EMAIL_SERVER_FORM="emailServerForm";	
	
	public static final String EMAIL_FORM="emailForm";
	
	public static final String SUCCESS_VIEW_MESSAGE="successViewMessage";
	
	public static final String REDIRECT_MESSAGE="successViewMessage";
	
	public static final String DUTY_AGENT_ONLY="dutyAgentOnly";
	
	public static final String EMAIL_SERVER_CONNECTION_STATUS="esConnectionStatus";
	
	public static final String EMAIL_LIST_STATUS="emailListStatus";
	
	public static final String SERVER_EMAIL_ADDRESS="serverEmailAddress";
	
	public static final String SERVER_EMAIL_PASSWORD="serverEmailPassword";
	
	public static final String EMAIL_LIST_FIELD="emailListField";
	
	public static final String SAVE_EMAIL_LIST="saveEmailList";
	
	public static final String UPLOAD_EMAIL_LIST_FILE="uploadEmailListFile";
	
	public static final String UPLOAD_FILE_CONTENT="fileContent";
	
	public static final String ACTION_UPLOAD_DOC_FILE="uploadDocFile";
	
	public static final String ACTION_SEND_EMAIL_TO_MYSELF="sendEmailToMyself";
	
	public static final String ACTION_SEND_BY_EMAIL_LIST="sendByEmailList";
	
	public static final String AGENT_OBJECT = "agentObject";
	
	public static final String ATTACHMENT1="attachment1";
	public static final String ATTACHMENT2="attachment2";
	public static final String ATTACHMENT3="attachment3";
	public static final String ATTACHMENT4="attachment4";
	public static final String ATTACHMENT5="attachment5";
	
	public static final String IMAGE_FILE_NAME="imageFilename";
	
	public static final String IMAGE_FILE_SIZE="imageFilesize";
	
	public static final String IMAGE_FILE_TYPE="contentType";
	
	public static final String ACTION_SAVE_CHANGED_EMAILLIST="saveChangedEmailList";
	
	public static final String EMAILLIST_ERROR_MESSAGE="emailListErrorMessage";
	
	public static final String RECIPIENT_EMAIL_LIST="recipientEMAIlList";
	
	public static final String EMAIL_SEND_STATUS="emailSendStatus";
	
	public static final String PREVIOUS_EMAIL_SEND_STATUS="previousEmailSendStatus";
	
	public static final String FEEDBACK="feedback";
	
	public static final String FEEDBACK_NODATA="feedbackNodata";
	
	public static final String FEEDBACK_TERMINATED="feedbackTerminated";
	
	public static final String FEEDBACK_COMPLETED="feedbackCompleted";
	
	public static final String TEXT_HTML_CONTENT="text/html";
	
	public static final String TEXT_PLAIN_CONTENT="text/plain";
	
	 
	
	public static final String TEXT_APPL_CONTENT="text/application";
	
	public static final String XSL_APPL_CONTENT="application/vnd.ms-excel";
	
	public static final String MSWORD_APPL_CONTENT="application/msword";
	
	public static final String APPL_APPL_CONTENT="application/pdf";
	
	public static final String PNG_APPL_CONTENT="image/png";
	
	
	
	public static final String TEXT_CONTENT="text";
	
	public static final String APPL_CONTENT="application";
	
	public static final String IMAGW_CONTENT="image";
	
	public static final String UPLOAD_IMAGE="uploadimage";
	
	public static final String DELETE_IMAGE="deleteimage";
	
	public static final String SHOW_ATTACHMENTS="showAttachments";
	
	public static final String FROM_MAIL_ADDRESS="fromEmailAddress";
	
	public static final String EMAIL_SUBJECT="emailSubject";
	
	public static final String EMAIL_CONTENT="emailContent";
	
	public static final String ACTION_UPLOAD_EMAIL_CONTENT="uploadEmailContent";
	
	public static final String ACTION_CLEAN_EMAIL="cleanEmail";
	
	public static final String ACTION_DOWNLOAD_EMAIL_CONTENT="saveEmailContent";
	
	public static final String ACTION_DOWNLOAD_EMAIL_LIST="downLoadEmailList";
	
	public static final String ACTION_SEND_EMAIL="sendEmailContent";
	
	public static final String ACTION_SEND_TO_MYSELF="myself";
	
	public static final String ACTION_SEND_TO_EMAIL_LIST="emailList";
	
	public static final String ACTION_GENERATE_AGENT_EMAIL="generateAgentEmailContent";
	
	public static final String ATTACHMENT_LIST="attachmentList";
	
	
	public static final String AGENT_EMAIL_HTML="generateAgentEmail.html";
	
	public static final String TO_EMAIL_ADDRESS="toEmailAddress";
	
 
	
	public static final String PURCHASE_QUOTE_EMAIL_HTML="purchaseQuoteEmail.html";
	
	public static final String REFINANCE_QUOTE_EMAIL_HTML="refinanceQuoteEmail.html";
	
	
	public static final String AUTH_EMAIL_ADDRESS="authEmailAddress";
	public static final String AUTH_PASSWORD="authPassword";

	public static final String STATE_SELECTED_LIST="stateSelectedList";
	
	public static final String SUCCESS_MESSAGE="successMessage";
	
	public static final String NICHE_FORM = "nicheForm";
	
	public static final String NICHES_LIST_VIEW = "NicheListView";
	
	public static final String NICHES_INTRO = "NichesIntro";
	
	public static final String FORWARD_NICHES_LIST_VIEW = "NicheListView";
	
	public static final String NICHES_LIST = "NichesList";
	
	public static final String FORWARD_NICHES_FORM = "NicheForm";
	
	public static final String ACTION_NICHE_ADD = "nicheAdd";
	
	public static final String ACTION_NICHE_DELETE = "nicheDelete";
	
	public static final String ACTION_NICHE_EDIT = "nicheEdit";
	
	public static final String PARAM_NICHE_ID = "paramNicheId";
	
	public static final String ACTION_SAVE_NICHE="saveNicheAction";
	
	public static final String ACTION_TYPE="actionType";
	
	public static final String TURN_OFF_NICHE="turnOffNiche";
	
	public static final String MENU_CALL="menuCall";
	
	public static final String ACTION_CHECK_LIST_EDIT="appCheckListEdit";
	
	public static final String ACTION_CHECK_LIST_DELETE="appCheckListDelete";
	
	public static final String ACTION_CHECK_LIST_ADD="appCheckListAdd";
	
	public static final String PARAM_CHECK_ID="paramCheckId";
	
	public static final String APP_CHECK_LIST_LIST="appCheckListsList";
	
	public static final String FORWARD_CHECK_LIST_VIEW = "AppCheckListView";
	
	public static final String FORWARD_CHECK_LIST_FORM = "AppCheckListForm";
	
	public static final String CHECK_LIST_FORM ="appCheckListForm";
	
	public static final String AGENT_DATA_LIST ="AgentDataList";
	
	public static final String CURRENT_CHECK_STATUS_ID ="currentCheckStatusId";
	
	public static final String CURRENT_CHECK_STATUS ="currentCheckStatus";
	
	public static final String NICHE_PROGRAM_ID="NicheProgramId";
	
	public static final String APPLICATION_FORM_ID ="ApplicationFormId";
	
	public static final String INTEREST_RATE_ID ="InteratRateId";
	
	public static final String LOANS_AGENT_LINKS_ID ="LoansAgentLinksId";
	
	public static final String AGENT_PICTURE_ID ="AgentPictureId";
	
	public static final String AGENT_DATA_FORM ="AgentDataForm";
	
	public static final String ACTION_CURRENT_CHECK="actionCurrentCheck";
	
	public static final String ACTION_SELECT_ALL_CHECK="actionSelectAllCheck";
	
	public static final String ACTION_PULL_SELECTED_DATA="actionPullSelectedData";
	
	public static final String SELECT_ALL_STATUS="selectAllStatus";
	
	public static final String CHECK_BOX_CHECKED="checked";
	
	public static final String CHECK_BOX_UNCHECKED="unchecked";
	
	public static final String PULL_ALL_AGENT_DATA="pullALLAgentData";
	
	public static final String QUOTE_LIST="QuoteList";
	
	public static final String FORWARD_QUOTE_PULL_FORM="QuotePullForm";
	
	public static final String QUOTE_FORM="quoteForm"; 
	
	public static final String FORWARD_QUOTE_PULL_CHILD_FORM="QuotePullChildForm";
	
	public static final String ACTION_QUOTE_PULL="actionQuotePull"; 
	
	public static final String ACTION_QUOTE_DELETE="actionQuoteDelete"; 
	
	public static final String QUOTE_LOAN_TYPE="quoteLoanType"; 
	
	public static final String QUOTE_PULL_TO_EMAIL="quotePullToEmail"; 
	
	public static final String EMAIL_DYNAMIC_LIST="EmailDynamicList"; 	
	
	public static final String CURRENT_UPLOADING_FILE="currentUploadingFile"; 	
 	
	public static final String SIMPLE_EMAIL_LINE_LIST="SimpleEmailLineList"; 
	
	public static final String ACTION_CHOSEN_FIRST_NAME_PTR="chosenFirstNamePtr"; 
	
	public static final String ACTION_CONFIRM_CHOSEN="confirmChosen"; 
	
	public static final String ACTION_CHOSEN_EMAIL_PTR="chosenEmailPtr"; 
	
	public static final String FIRST_NAME_PTR="firstNamePtr"; 
	
	public static final String EMAIL_PTR="emailPtr"; 
	
	public static final String FIRSTNAME_PTR_LIST="firstNamePtrList"; 
	
	public static final String EMAIL_PTR_LIST="emailPtrList"; 
	
	public static final String FORWARD_EMAIL_LIST_PROCESS_FORM="EmailListProcessForm"; 
	
	public static final String MULTI_PART_FILE="multipartFile";
	
	public static final String FILE_STREAM="fstream";
	
	public static final String DYNAMIC_EMAIL_LIST="dyList";
	
	public static final String APPLICATION_FORM_PATH="appFormPath";
	
	public static final String FORWARD_DOWNLOAD_APPLICATION_FORM="DownloadApplicationForm";
	
	public static final String ACTION_DOWNLOAD_APP_FORM="downloadAppForm";
	
	public static final String REDIRECT_DOWNLOAD_APPLICATION_FORM="DownloadApplicationFormRedirect";
	
	public static final String PARAM_AGENT_REPLY_QUOTE="agentReplyQuote";
	
	public static final String FORWARD_APPCHECKLIST_PULL_FORM="AppCheckListPullForm";
	
	public static final String PARAM_APP_CHECKLIST_IDS="ParamAppCheckIds";
	
	public static final String SELECTED_APP_CHECKLIST="selectedAppCheckList";
	
	public static final String PARAM_PARENT_CALL="parentCall";
	
	public static final String FROM_EMAIL_ADDRESS="fromEmailAddress";
	
	public static final String SUBJECT="subject";
	
	public static final String HELP_TAB_POINTER="helpTab"; 
	
	public static final String HELP_CURRENT_JSP_CODE="currentJSPCode"; 
	
	public static final String FORWARD_WEBSITE_HELP="WebsiteHelp";
	
	public static final String FORWARD_WEBSITE_HELP_POPOUT="WebsiteHelpPopout";
	
	public static final String PREV_PAGE_EMAILADDRESS="prevPageEmailAddress";
	
	public static final String FORWARD_FORGET_PASSWARD_SUCCESS="ForgetPasswordSuccess";
	
	public static final String FORWARD_RESET_PASSWORD_SUCCESS="ResetPasswordSuccess";
	
	public static final String DB_DEVICE_DRIVER="com.mysql.jdbc.Driver";	
	
	public static final String FORWARD_BULK_EMAIL_SENDER="BulkEmailSender";
	
	public static final String FORWARD_CONFIGURE_EMAIL_SERVER="ConfigureEmailServer";
	
	
	public static final String CERT_PIC_CODE_STRING="certPicCodeString";
	
	public static final String CERT_PIC_CODE_MESSAGE="certPicCodeMessage";
	
	public static final String ENTERED_CERT_PIC_CODE="enteredCertPicCode";
	
	public static final String CERT_PIC_CODE_NOT_MATCH="Text Code not match. Please try again.";	
	
	public static final String FORWARD_AGENT_SIGNUP="AgentSignup"; 
	
	public static final String DELIVER_FROM_EMAIL="support.staff@loans-agent.com";
	public static final String DELIVER_SMTP_SERVER="smtp.gmail.com";
	public static final String DELIVER_AUTH_USERNAME="loans.agent.service@gmail.com";   //"support.staff@loans-agent.com";
	public static final String DELIVER_AUTH_PASSWORD="nus6457299";                 //"nus6547299";
	
	public static final String PASSWORD_RESET_STATUS="passwordResetStatus";
	
	public static final String CERT_PIC_CODE_DIGITS="certPicCodeDigits";
	
	
}
