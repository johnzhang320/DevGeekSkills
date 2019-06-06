<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ page language="java" import ="com.loan.agent.mvc.formbean.NicheForm" %>
<%@ page language="java" import ="com.loan.agent.services.Constant" %>

<%@ page language="java" import ="java.util.List" %>

   <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common/tablescroll.css" />
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/utils/tablescroll.js"> </script> 
  <%
  
	   int line=0;
	   String nicheIntro =(String) request.getSession().getAttribute(Constant.NICHES_INTRO);
	 
   %>
   <script type="text/javascript">				 
   window.onload=cancelActivityRefresh();
   var timer;
   var IDE_TIME=30*60;    // 30 minutes 
   function startActivityRefresh() { 		 
 	     timer = setInterval(function() { 	    	
 	       $('#emailSendStatus').load('viewEmailSendStatus.html'); 	       
 	    }, 1000);
 	    
 	}

 	function cancelActivityRefresh() {
 	    clearInterval(timer);
 	}
 	 function goToLogin() {
   	     if (window.opener != null && !window.opener.closed) {
        	  window.opener.location.href = '/agentLogout.html';
         }  else {
              window.open('/agentLogout.html', 'login');
         }
   	      window.open('','_parent','');
          window.close();
       //window.self.close();
      }
  
     attachEvent(window,'load',function(){
	  var idleSeconds = IDE_TIME;   // if no activities for 5 minutes, then close window  
	  var idleTimer;
	  function resetTimer(){
	    clearTimeout(idleTimer);
	    idleTimer = setTimeout(goToLogin,idleSeconds*1000);
	  }
	 
	  attachEvent(document.body,'mousemove',resetTimer);
	  attachEvent(document.body,'keydown',resetTimer);
	  attachEvent(document.body,'click',resetTimer);

	  resetTimer(); // Start the timer when the page loads
	});
 
	function attachEvent(obj,evt,fnc,useCapture){
	  if (obj.addEventListener){
	    obj.addEventListener(evt,fnc,!!useCapture);
	    return true;
	  } else if (obj.attachEvent){
	    return obj.attachEvent("on"+evt,fnc);
	  }
	} 
   </script>  
 <script  type="text/javascript">
/*<![CDATA[*/

jQuery(document).ready(function($)
{
	$('#thetable4').tableScroll({height:700});
  
});

/*]]>*/

 
 
      
   function nicheEditDelete(actionType,nicheIdStr) {
     
   	    
       javascript:popUpFixedName('/nicheForm.html?actionType='+actionType+'&paramNicheId='+nicheIdStr,'600','520');
   }
       
   function formatTextArea(textArea) {
        textArea.value = textArea.value.replace(/(^|\r\n|\n)([^*]|$)/g, "\n");
    }

    window.onload = function() {
        var textArea = document.getElementById("nicheIntro");
        textArea.onkeyup = function(evt) {
            evt = evt || window.event;

            if (evt.keyCode == 13) {
              //  document.getElementById('nicheIntro').value = document.getElementById('nicheIntro').value + "\n<br>";
            }
        };
    };    
  </script>
 
  
 

   <div class="tableScrollbox">   
   
   	  <label class="captain">
   	      <bean:write name="loginAgentName" /> Offered Niche Loan Program   
   	  </label>   	
     
 <table style="border:0px;" >
  <tr><td>
  <input type="button" name="add" value="Add" class="buttonImage" onclick="javascript:popUpFixedName('/nicheForm.html?actionType=nicheAdd','600','520');">
   </td>
  
  </tr>
  </table> 
    <logic:present name="NichesList" scope="session" >  
           

       <table  id="thetable4" width="100%" border="0" cellpadding="0" cellspacing="0" >
     		<thead>
  			<tr>
    	
   		 	  		 
   		 	<td width="90%"><font class="tstitle">Niche Program</font></td>
   		 	<td><label class="title">
   		 	   <font class="tstitle">Edit</font>
   			</label>
   			</td>     	
   			<td><label class="title">
   		 	   <font class="tstitle">Delete</font>
   			</label>
   			</td>     
      	</tr>   
 	   </thead>
 	   	<tbody>
 	   	
 	    
 	 	<logic:iterate id="review" name="NichesList" type="NicheForm" scope="session" indexId="rowIndex"> 
   	 	 
  		      <%if (line%2==0) {%>
      			<tr class="rowwhite">    
        	 <%} else { %>
        	    <tr class="rowshade"> 
        	   <%}
        	    line++;        	    
        	    %>
    	 	  
    	    <td width="95%">
    	    <font style="font-size:13px;color:RGB(50,50,50);font-weight:bold;">  
    	        <bean:write name="review" property="nicheTitle"/> 
    	     
    	     </font> 
    	     <font style="font-size:12px;color:RGB(50,50,50);">  
    	   		 <bean:write name="review" property="nicheNote"/> 
    	   	 </font>
    	    </td>                   
     		
  		   <td>
  		  
  			<input type="button" name="edit" value="Edit" class="tinybutton" onclick="javascript:nicheEditDelete('nicheEdit','<bean:write name="review" property="nicheId"/>');">
 			</td><td> 
 			 <input type="button" name="delete" value="Delete" class="tinybutton" onclick="javascript:nicheEditDelete('nicheDelete','<bean:write name="review" property="nicheId"/>');">
 			 </td>  
  			
  			</tr>
    		 
          </logic:iterate>
         
        </tbody>
 </table>
    
 </logic:present>
 <br>
   <div id="saveNicheIntroSuccess" style="display:none;">
   	<div class="saveSuccess">   	
      	  <span id="nicheIntroSpan" style="font-weight:bold; color:GREEN; font-size:15px;"></span>
     </div> 
   </div>  
   <label class="captain">
   	      <bean:write name="loginAgentName" /> Niche Program Description  
   	  </label>  
   	  
	 <textarea id="nicheIntro" name="nicheIntro" class="noteWidthClass" >
	  
	 	<bean:write name="NichesIntro" scope="session"/>	 	
	  
	 </textarea>	 
	 <br>
	 <input type="button" value="Save" class="buttonImage" onClick="javascript:submitNicheIntro();">
  			    
 </div>
    
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/formUtils.js"> </script>  
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/nicheListView.js"> </script>   
        




 
