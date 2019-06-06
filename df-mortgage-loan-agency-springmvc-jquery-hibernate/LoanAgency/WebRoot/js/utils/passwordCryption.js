    var keys;  
    //var URL = "http://localhost:8080/FrontEndEncryption/getPublicKey";
    /**
     *  Create KeyPair Servlet URL
     */
    var URL = "getKeyPair";
    function getKeys() {  
     
        $.jCryption.getPublicKey(URL,function(receivedKeys) {  
            keys = receivedKeys;  
            
        });  
        return  keys;
    } 
    function isBlank(str) {
        return (null==str|| !str || 0 === str.length || /^\s*$/.test(str));
    }
    
    $(function(){  
        var hasPass = $('#password');  
       /**
        *  Initialize keypair while load this page        
        */  
        stringCryption.getPublicKey(); 
    	 
 	   
       // getKeys();  
        
        $(hasPass).focus(function(){  
              $(hasPass).val("");  
              $("#password").val("");
        });  
        /**
         *  lost focus to encrypt password
         */
     $(hasPass).blur(function(){  
             var cVal = $(this).val(); 
               
             if(!isBlank(cVal) ){  
            	  stringCryption.encrypt("password"); 
               
              }  
          }); 
      });  
 
    function viewEncPass(){
    	var encPass=$("#password").val();
    	if (isBlank(encPass) || ""==encPass ) {
    		alert("Please enter password first!");
    	} else {
    		alert("The password encrypted by RSA Public Key:\n"+encPass);
    	}
    }
    
    function submitPsswordForm(){       
        var flag = true;  
        var value = document.getElementById("password").value;                
        if(isBlank(value)){  
           flag = false;   
        }  
        if(flag == true){       	
        	 
            document.forms[0].submit();  
        }else {  
            if(isBlank(value)){  
                alert("Please key in your password.");  
                $("#password").focus();  
            } 
        }  
    }   
 