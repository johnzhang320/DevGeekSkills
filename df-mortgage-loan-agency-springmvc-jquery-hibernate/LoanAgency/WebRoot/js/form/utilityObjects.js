 /**
  * Utility is a singleton object, the most methods are public
  * and regular expressions are private 
  * IE doesn't support convertToSHA256
  * @param e
  * @returns
  */

	 
		function convertToSHA256(e){	 
	    //var output = $.sha256(e);
	    return e;
	}	
	 
	 
	function getStdUSDollar(nStr)
	{
	    nStr = nStr.replace(/,/g ,"");
	    nStr = nStr.replace('$','');
	  
	    var cstr = nStr.split('');
	    var len = cstr.length-1;
	    var count=0;
	    var a="";
		for (var i=len;i>=0;i--) {
			if (count==2) {
				count=0;
				if (i==0) a=cstr[i]+a; else	a=","+cstr[i]+a;
			} else {
				a=cstr[i]+a;
				count++;
			}
		}
	    return a;
 	}
 



/**
 *  String Date1 and Date2 
 *  Format MM-DD-YYYY
 *  if Date1>Date2 return 1
 *  if Date1=Date2 return 0
 *  if Date1<Date2 return -1
 * @param Date1
 * @param Date2
 */
		
function compareDates (Date1,Date2) {
	     if (Date1==="" || Date2==="") return null;
	     var mm1=Date1.substring(0,2);
	     var dd1=Date1.substring(3,5);
		 var yyyy1=Date1.substring(6,10);
		 var mm2=Date2.substring(0,2);
		 var dd2=Date2.substring(3,5);
		 var yyyy2=Date2.substring(6,10); 
		
		 var dValue1 = parseInt(yyyy1)*365+parseInt(mm1)*30+parseInt(dd1);
		 var dValue2 = parseInt(yyyy2)*365+parseInt(mm2)*30+parseInt(dd2); 
		 
		 if (dValue1<dValue2) return 1;
		 if (dValue1==dValue2) return 0;
		 if (dValue1>dValue2)  return -1;
		 
}

/**
 *  Date1 Format MM-DD-YYYY
 *  Date2 =new Date()
 *  if Date1>Date2 return 1
 *  if Date1=Date2 return 0
 *  if Date1<Date2 return -1
 * @param Date1
 * @param Date2
 */
		
function compareDateToNow (Date1,Date2) {
	     if (Date1==="" || Date2==="") return null;
	    
	     var mm1=Date1.substring(0,2);
	     if (mm1.substring(0,1)=='0') mm1 = mm1.substring(1,2);
	     var dd1=Date1.substring(3,5);
		 var yyyy1=Date1.substring(6,10);
		 var mm2=Date2.getMonth()+1;
		 var dd2=Date2.getDate();
		 var yyyy2=Date2.getYear()+1900; 
		 var year = parseInt(yyyy1);
		 var month = parseInt(mm1);
		 var day= parseInt(dd1);
		 
		 var dValue1 =year *365+month*30+parseInt(day);
		 var dValue2 = yyyy2*365+mm2*30+dd2; 
		 //console.log("yyyy1="+year+",yyyy2="+yyyy2+",mm1="+month+",mm2="+mm2+",dd1="+day+",dd2="+dd2+ ",Date1 Value="+dValue1+",Now Value="+dValue2);
		 if (dValue1<dValue2) return 1;
		 if (dValue1==dValue2) return 0;
		 if (dValue1>dValue2)  return -1;
		 
}

function errorWarn(fieldName,shortMessage,errorMessage) {
	
	  var str = $(fieldName).val();
      var label = fieldName+"Label";
      var msg = fieldName+"Msg";
	   $(label).css('color','#ff0000');
       $(fieldName).css('border','1px solid red');
       $(msg).html(shortMessage); 
       $("#errorBox").css('display','block');
  	   $("#errorBox").html(errorMessage); 
}

function cleanWarn(fieldName) {
	 
	 var str = $(fieldName).val();
    var label = fieldName+"Label";
    var msg = fieldName+"Msg";
	    $(fieldName).css('border','1px solid #888888');
		$(label).css('color','#1040A3');
		 $(msg).html("");
		 $("#errorBox").html("");  
     $("#errorBox").css('display','none');
	 
}
	 
function isNumeric(passedVal) {
	if (passedVal==undefined) return true;
	 var  passedVal =passedVal.replace(/,/g ,"");
	  // passedVal =passedVal.replace(/./g ,""); 
	for (i=0; i<passedVal.length; i++) {
		var bit = passedVal.substr(i,1);
		if (!isDigit(bit)) {
			return false;
		}
	}
	return true;
}



var reg_email=/^\w+([\.-]?\w+)@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
var reg_phoneNo = /^\(?(\d{3})\)?[\.\-\/]?(\d{3})[\.\-\/]?(\d{4})$/;
var reg_zip = /^\d\d\d\d\d$/;

function isValidEmail(email) {
	 
	
	if (reg_email.test(email)) {
		return true;
	}
	return false;
}
function isValidPhoneNo(phoneNo) {     
   
	if (reg_phoneNo.test(phoneNo)) {
		return true;
	}
	return false;
}

function getArrayPhoneNo(phoneNo) {
	 
	return reg_phoneNo.exec(phoneNo);
}
function isValidZipCode(zip) {
	 
	
	if (reg_zip.test(zip)) {
		return true;
	}
	return false;
}

function dollarValue(str) {
	str = str.replace(/,/g,"");
	return parseFloat(str);
}

function isDigit(val) {
	var str="0123456789.";
	var retVal=true;
	if (str.indexOf(val)==-1) {
		retVal=false;
	}
	return retVal;
}

function trim(stringToTrim) {
	return stringToTrim.replace(/^\s+|\s+$/g,"");
}
	
