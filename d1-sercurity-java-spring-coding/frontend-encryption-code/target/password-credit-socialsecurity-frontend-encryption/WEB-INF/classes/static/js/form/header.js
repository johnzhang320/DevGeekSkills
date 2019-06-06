function languageSwitch(event) {
	/**
	 * Call LocaleChangeConntroller
	 */
 	var currentLocale = document.getElementById("localeChange").value; 
	//alert("currentLocale="+currentLocale);  
	var baseUrl = "localeChange.html?language="+currentLocale;			     
	    $.ajax({
			type: 'POST',			 
			dataType: "json",
			async:false,   /*Wait until the server response*/
			url: baseUrl,			
			success: function(data, textStatus, jqXHR){
			    /**
			     * option:
			     * false: Reload Current Page from the cache. default
			     * true : Reload current page from server
			     */
			  
			     var option = false;
				location.reload(option);
				 
			},
			   error: function(jqXHR, textStatus, errorThrown){
				    
			}
		});
		 event.preventDefault();
	}