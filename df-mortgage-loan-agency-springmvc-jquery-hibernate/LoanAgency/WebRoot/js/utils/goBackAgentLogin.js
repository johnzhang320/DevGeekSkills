  window.onload=cancelActivityRefresh();
   var timer;
   var IDE_TIME=1*10;    // 2 hours to consider send emails 
   

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