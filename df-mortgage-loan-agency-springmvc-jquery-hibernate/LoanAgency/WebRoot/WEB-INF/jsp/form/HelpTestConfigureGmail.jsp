 <strong>Test Gmail </strong>
  <br><br> When Agents signup, Website securely stores Agent Gmail Account to save a lot of operations when sending email. We only save SHA256 digested password which never be reversed, no one can use digested password to login even system administrator.
    
  <br><br><strong>Setting procedures and RoadMap</strong> 
    
  <br><br>(1) For Agent Email Marketing, For Agent Menu-->Agent Signup --> Signup Page-->Press button --> Connection--> Type your email and password .
  <br>If first time sign up, connection may be failed (may not) even successful message shown but you still could not send email, you'd better go your Gmail to check if No_Reply was coming. If Yes, below steps help you out:      
  <br><br>  <img src="<%=request.getContextPath()%>/images/agents/Step1PopoutTestGmail.png">
  <br><br>(2) First time test the gmail, Google will send you an no-reply email<br> 
  <img src="<%=request.getContextPath()%>/images/agents/Step2FindNoReplyEmail.png"><br>
  <br><br> (3) Google notice you 'Someone' want to access your Gmail. The 'Someone' is my webhost , which IP Address is 162.253.126.0(jdz2.dailyrazor.com). You must allow my webhost access your gmail address for email sending. find the steps in screen shots. 
         
  <br><br><img src="<%=request.getContextPath()%>/images/agents/Step3ToStep5AllowWebhostToAccessGmail.png">