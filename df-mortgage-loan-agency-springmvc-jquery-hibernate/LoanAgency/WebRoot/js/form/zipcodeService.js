/**
	 *  @parameter
	 *  @stateSymbol is abbrevation stateSymbol likes CA means California
	 *  @countyField is '#'+county variable name in JSP such as #propertyCounty
	 *  findCountyByState(stateSymbol,countyField)
	 *  in formUtils.js
	 */
 
	$("#propertyState").change(function(event){
	     var str = $(this).val();
	 	 if (str==="") return;
	 	 findCountyByState(str,"#propertyCounty");
	 	 
	});
	/**
	 *  findStateCountyByZip(Zip,stateField,countyField)
	 *  in formUtils.js
	 */
	$("#propertyZipCode").keyup(function(event){
	     var str = $(this).val();
	     //alert(str.length);
	 	 if (str==="") return;
	 	 if (!isNumeric(str)) {
	 	    errorWarn(zipCodeFieldName,"Wrong !","Zip Code should be 5 numeric digitals !<br>");
 		     
	 	 }
	 	 if (str.length==5) {
	    	 if (isValidZipCode(str)) {
	     
	 		   findStateCountyByZip(str,"#propertyZipCode","#propertyState","#propertyCounty"); 	 
	    	 }
	 	 } else if (str.length>5) {
	  	    errorWarn(zipCodeFieldName,"Wrong !","Zip Code should not be greater than 5 digitals !<br>");

	 	 }
	});
	
	/* Find county by State by DWR and Java */
	var countyFieldName;
	var stateFieldName;
	var zipCodeFieldName;
	var zipCodeValue;
	 function findCountyByState(stateSymbol,countyField) {
		 // findByStateSymbol is CountyDAO method (hibernate)
		countyFieldName = countyField;
		document.getElementById("cProgressSamll").style.display="block";
		StateCountyManager.findCountyByStateSymbol(stateSymbol,countyCallBack);
		}

	var countyCallBack = function (countyArray) { 	      
	    var options='<option value="select county"> </option>';
	    for (var i = 0; i < countyArray.length; i++) {
	           options += '<option value="' + countyArray[i]  + '">' + countyArray[i] + '</option>';
	         }
	         $(countyFieldName).html(options);	    
	         setTimeout(circleScreenShow,600);     
	};

	 /* Find State an County by Zip*/
	 

	function findStateCountyByZip(zipcode,zipCodeField,stateField,countyField) {
		 // findByStateSymbol is CountyDAO method (hibernate)
		// start circle progress screen
		stateFieldName = stateField;
		countyFieldName = countyField;
		zipCodeFieldName = zipCodeField;
		zipCodeValue = zipcode;
	
		StateCountyManager.findStateCountyByZip(zipcode,stateCountyCallBack);
	 }

	var originalStateOption;
	$(document).ready(function(){	
		originalStateOption=$("#propertyState").html();
	});

	var stateCountyCallBack = function (scObject) {	      
	   var countyOptions;
	   var stateOptions;
	   i=0;
	   scList = scObject[0];
	   
	   if(scList === undefined) {
		   errorWarn(zipCodeFieldName,"Wrong Zip!","There is NO such zip code: "+zipCodeValue+" in US Postal Database !<br>");
		  
	      return;
	   }
		       document.getElementById("cProgressSamll").style.display="block";
		
	   			stateOptions='<option value="'+scList[0]+'" selected="selected">'+scList[1]+'</option>'+originalStateOption;
	   		  		
	   			countyOptions='<option value="'+scList[2]+'">'+scList[2]+'</option>';
	   			
	   	 		$(stateFieldName).html(stateOptions);
	   	 		
	   	 		$(countyFieldName).html(countyOptions);
	   	 	    cleanWarn(zipCodeFieldName);
	   	 	    setTimeout(circleScreenShow,600);
		 };
	 
	function circleScreenShow() {
		document.getElementById("cProgressSamll").style.display="none";
	}	 
	var stateSymbol;
	var stateNameValue;
	function findStateAll(state,stateName) {
		stateSymbol = state;
		stateNameValue=stateName;
		StateCountyManager.findStateAll(stateAllCallBack);
		
	}
  
	var stateAllCallBack = function (scObject) {	    
		var stateOptions='<option value="'+stateSymbol+'">'+stateNameValue+'</option>';
		alert(scObject.length);
		for (i=0;i<scObject.length;i++) {
			scList = scObject;
			 
			stateOptions+='<option value="'+scList[0]+'">'+scList[1]+'</option>';
		}
		$(stateFieldName).html(stateOptions);
		
	};