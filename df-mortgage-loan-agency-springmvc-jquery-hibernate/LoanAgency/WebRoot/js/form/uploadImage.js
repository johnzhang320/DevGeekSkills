	function uploadImage() { 		 
 		 errorFlag=false; 		 
 		 document.getElementById("cProgressSamll").style.display="block";
 		 if (!errorFlag) {
 		     document.getElementById("actionType").value="uploadimage";
 			 document.forms['emailServerForm'].action='uploadImage.html';
 			 document.forms['emailServerForm'].submit();
 		 }
 	}

 	function deleteAttachment(attachment) { 		 
		  errorFlag=false; 		 
		 document.getElementById("cProgressSamll").style.display="block";
		 if (!errorFlag) {
		     document.getElementById("actionType").value="deleteImage";
			 document.forms['emailServerForm'].action='uploadImage.html';
			 document.forms['emailServerForm'].submit();
		 }
	}