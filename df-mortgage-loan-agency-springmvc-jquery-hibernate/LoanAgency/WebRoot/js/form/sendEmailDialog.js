function sendEmailToMyself() { 		 
		  errorFlag=false; 		 
		  document.getElementById("cProgressSamll").style.display="block";
		 if (!errorFlag) {
		     document.getElementById("actionType").value="sendEmailToMyself";
			 document.forms['emailServerForm'].action='sendEmailDialog.html';
			 document.forms['emailServerForm'].submit();
		 }
	}
function sendByEmailList() { 		 
	  errorFlag=false; 		 
	  document.getElementById("cProgressSamll").style.display="block";
	 if (!errorFlag) {
	     document.getElementById("actionType").value="sendByEmailList";
		 document.forms['emailServerForm'].action='sendEmailDialog.html';
		 document.forms['emailServerForm'].submit();
	 }
}
