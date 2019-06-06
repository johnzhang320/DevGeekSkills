<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'heatBeat.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="-1">     
 
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<%
   String jQueryPath =request.getContextPath()+"/BuildJQuery/development-bundle/";
    String imagePath =request.getContextPath()+"/images/layoutImages/";
 %>
 
 <link rel="stylesheet" href="<%=jQueryPath%>themes/redmond/jquery-ui-1.8.23.custom.css"/> 
  <script type="text/javascript" src="<%=jQueryPath%>jquery-1.8.0.js"></script>

  </head>
  
  <body >
  <div id="emailSendStatus">
  </div>
   <script type="text/javascript">
 
   window.document.onload=process();
	 
   function process() {
   var i=0;
   	while (i < 10) {
 	  i++;
	  //var baseUrl = "/servlet/heartBeatServlet";		
	   var baseUrl = "viewEmailSendStatus.html";	     
	    $.ajax({
			type: 'GET',			 
			dataType: "json",
			cache: false,  
			async: false,
			url: baseUrl,			
			success: function(data, textStatus, jqXHR){
			  
				document.getElementById("emailSendStatus").innerHTML+=data.feedback + "<br>";
				 
			},
			   error: function(jqXHR, textStatus, errorThrown){
				    alert("Error");
			}
		});
		 
		 }
	 }
	</script>
  </body>
</html>
