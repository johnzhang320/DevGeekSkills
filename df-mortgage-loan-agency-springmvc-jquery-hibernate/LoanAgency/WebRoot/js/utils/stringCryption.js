//=============================================================================
// stringCryption.js
// - created 2014.05.26 
// Author: John Zhang
// Company: devprobe.com
// License: LGPL
// Using jQuery.jCryption-1.1.js to encrypt HTML input text/password string 
// ASSUMES 
// load following library in your caller code
// jquery.jcryption-1.1.js
// jquery-1.8.0.js
// stringCrpytion.js
//
// USAGE
//   for example:
//    var publicKey = stringCryption. getPublicKey(URL); 
//    var encryptedString = stringCryption.Encrypt(element_id);
//    URL could be GET request from servlet or map in spring , which call the java code to generate public key 
//
//
//----------------------------------------------------------------------------- 
(function() {
  // private variables
  URL=	"getKeyPair"; // default servlet URL	
  publicKey = null;
  encryptedString = null;
  
  // Internal Class
  var stringCryption_ = {
	  isBlank: function (str) {
		 return (undefined==str || null==str|| !str || 0 === str.length || /^\s*$/.test(str) );
	  },  
	  getPublicKey: function(url) {  	
		  // hold until server response
		  $.jCryption.getPublicKey(url,function(receivedKeys) {  
		      	publicKey = receivedKeys;  		            
		   });  
		   return publicKey;
	  },
	  
	  getPublicKey: function() {  		
		// hold until server response
		  $.jCryption.getPublicKey(URL,function(receivedKeys) {  
		      	publicKey = receivedKeys;  		            
		   });  
		   return publicKey;
	  },
	  
	  encrypt: function(elementId) {
		  if (isBlank(publicKey)) {
			  alert("Please call stringCryption.initialize() first or check if publicKey URL is correct");
			  return null;
		  } 
		  var value = document.getElementById(elementId).value;
		  if (isBlank(value)) {
			  return null;
		  }
		  $.jCryption.encrypt(value, publicKey, function(encryptedValue) {                
			  document.getElementById(elementId).value=encryptedValue;       
			  encryptedString = encryptedValue;
          });  
		  return encryptedString;
	  }, 
	  initialize: function (encryptField) {
		  
		  stringCryption_.getPublicKey(); 
		  /**
		   *  Leave the encrypted box, encrypt it immediately
		   */
		  $("#"+encryptField).blur(function(){  
	          var cVal = $(this).val(); 
	            
	          if(!isBlank(cVal) ){  
	         	  stringCryption_.encrypt(encryptField); 
	            
	           }  
	       }); 
		  /**
		   *  Enter the encrypted box, remove encrypted string immediately
		   */
		  $("#"+encryptField).focus(function(){  
		        $(this).val("");  
		  });   
	  }
  }; 
   
  // external setting for stringCryption
  stringCryption = stringCryption_;
  
}) ();
