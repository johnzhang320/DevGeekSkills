 
   	
  // 	function refreshAgentOnDuty() {
   	//	setInterval(function() { getAgentOnDutyInfo("getAgentInfo.html");},10000);
   		
   //	}
              	 
     	    function getAgentOnDutyInfo(baseUrl) { 
     	 
     	    $.ajax({
			type: 'POST',			 
			dataType: "json",
			url: baseUrl,			
			success: function(data, textStatus, jqXHR){
			    var agentName = data.agent.firstName+" "+data.agent.lastName;					
	 
				var render = "<span>Agent Name:<b>"+ agentName+"</b></span><br>"+
				  		     "<span>License DRE#:<b>"+ data.agent.dreNo+"</b></span><br>"+
				  		     "<span>License NMLS#:<b>"+ data.agent.nmlsNo+"</b></span><br>"+
		                     "<span>License State:<b>"+ data.agent.licenseEligibleState+"</b></span><br>"+
				             "<span>Work Phone:<b>"+ data.agent.workPhone+"</b></span><br>"+
				             "<span>Cell Phone:<b>"+ data.agent.cellPhone+"</b></span><br>"+
				             "<span>Email:<b>"+ data.agent.emailAddress+"</b></span><br>"+
				             "<span>Company:<b>"+ data.agent.companyName+"</b></span><br>"+				           
				             "<span>Expertise:<b>"+ data.agent.description+"</b></span><br>";
				             
				$("#agentInfo").html("");
				$("#agentInfo").html(render);
				 
			},
			error: function(jqXHR, textStatus, errorThrown){
				 
			}
		});
		}
		 
		  function getfetchDynamicRate() { 
		  var myUrl = "fetchDynamicRate.html";
     	      $.ajax({
			type: 'POST',			 
			dataType: "json",
			url: myUrl,			
			success: function(data, textStatus, jqXHR){
			    var agentName = data.rate1.intRate+" "+data.rate1.termName;
			 
			 
				 
				 
			},
			error: function(jqXHR, textStatus, errorThrown){
			 
			}
		});
		} 
		 
	  function getCurrentRates() { 
		   var rateUrl = "http://www.corralesluxuryandreo.com/webservice/mortgageRates.asmx/getMortgageRates";
		   
		   
     	    $.ajax({
				type: "POST",
				url: "http://www.corralesluxuryandreo.com/webservice/mortgageRates.asmx/getMortgageRates",
				dataType: "text/xml; charset=utf-8",
				success: function(xml){				  
				    
					//xmlDoc = $.parseXML( xml );
   					// $xml = $( xmlDoc );
				
				     $(xml).find('mortgageRate').children().each(function(){
				        // current document tag
 	                    var Document = $(this);
 	                    // array of properties 	                    
 	                    var latestRate5 = Document.find('symbol').filter(function(){
 	                           return $(this).text() === '5/1 ARM';
 	                        }).siblings('latestRate').text();     
 	                    var latestRate15 = Document.find('symbol').filter(function(){
 	                           return $(this).text() === '15 Year Fixed';
 	                        }).siblings('latestRate').text(); 
				        var latestRate30 = Document.find('symbol').filter(function(){
 	                           return $(this).text() === '30 Year Fixed';
 	                        }).siblings('latestRate').text(); 
				        var postedDate= Document.find('symbol').filter(function(){
 	                           return $(this).text() === '30 Year Fixed';
 	                        }).siblings('postedDate').text(); 
 	                           $("#r5years").html("<b>"+latestRate5+"</b>"); 
 	                    $("#r15years").html("<b>"+latestRate15+"</b>"); 
 	                    $("#r30years").html("<b>"+latestRate30+"</b>"); 
				   });
		        },
			error: function(jqXHR, textStatus, errorThrown){
				 
			} 
		   }); 
	   }