package com.boot.security.auth;

public class InitializeData {
	public static String ROLE_NAMES[] = {
		"Primary Owner","Owner","Admin","Editor","Contributor","Viewer","Billing"		
	};
	public static String[] File_management = {			
			"View files/folders",
			"Download files/folders",				
			"Create folders",							
			"Upload files/folders",							
			"Move files/folders",							
			"Copy files/folders",							
			"Rename files/folders",							
			"Delete files/folders",							
			"Share folders",							
			"Empty trash",							
			"Lock/unlock folders",							
			"Change folder settings"};		
	
	public static String[] Connectivity={
			"FTP access",							
			"S3 access"};		
	
	public static String[] Account_management= {
			"View analytics",							
			"View usage statistics",							
			"Enable/Disable CDN",							
			"Configure Digimarc",							
			"Account email alerts",							
			"Purge CDN cache",							
			"Change account name",							
			"Edit account details",							
			"Transfer account ownership",							
			"Close account",							
			"User management",
			"Invite new user",							
			"Remove user",							
			"Appoint admins",							
			"Demote admins",							
			"Appoint owners",							
			"Demote owners"};		
	public static String[] User_management = {
			"Invite new user",
			"Remove user",
			"Appoint admins",
			"Demote admins",
			"Appoint owners",
			"Demote owners"
			
	};
	public static String[] Billing= {
			"View invoices",							
			"Change billing plan",							
			"Add/Edit payment method",							
			"Add/Edit billing details",							
			"Billing email alerts"};							

}
