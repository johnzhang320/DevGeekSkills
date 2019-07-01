<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
    <br>
    <p>
	    <a href="/SpringbootOAuth2OktaSSO/dashboard" title="title.oauth2.sso" style="height:15px;line-height:15px;">
	    <span><b><spring:message code="title.oauth2.sso"></spring:message></b></span></a>
    </p>
   
    <br>*  Single Sign On (SSO) is nothing but sign on one time and access more without signing again.
    <br>*  Actually when end user hit the resource page, <b>trusted Client uses Okta redirect a login 
    <br>*  page to this user</b>
	<br>*  Once this user login successfully, Okta redirects hitting page to him/her and save sign on access token into cookie,
	<br>*  next time accessing application in same context path, the user do not need sign on any more. 
    <br>*  Now <b>SpringbootAdvanceDemo end user who try to hit Okta protected resource: /SpringbootOAuth2OktaSSO/dashboard.
    <br>*  Okta trusted Client located in different Context <b>/SpringbootOAuth2OktaSSO</b> has Client Id and Client secret which
    <br>*  authenticated by Okta and it also has resource web page "dashboard". 
	
	
	<br>*  Above link is /SpringbootOAuth2OktaSSO/dashboard", once you, end user, click it, Okta redirect login page
	<br>
	<br>*  https://{yourOktaDomain}/oauth2/default/v1/authorize?client_id=xxxxxdirect_uri=
    <br>*  http://localhost:8080/SpringbootOAuth2OktaSSO/login&response_type=code&scope=profile%20email%20openid&state=Hb7j3L
	<br>  
	<br>*  Once you login Okta sign-on page successfully , target page /SpringbootOAuth2OktaSSO/dashboard will be shown up
			
	<br>*  Actually we implement Authorization Code Flow of Okta, The Authorization Code flow is best used by server-side apps 
	<br>*  where the source code is not publicly exposed. The apps should be server-side because the request that exchanges the 
	<br>*  authorization code for a token requires a client secret, which will have to be stored in your client
	<br>
	<br>*  In order to implement such SSO, Client need to create a Web Application in Okta and configure 
	<br>*  redirect login URI
	<br>*  redirect Page URI, 
	<br>*  Client Id
	<br>*  Client Secret
	<br>*  Find
	<br>*  default authorizer issue URI
	<br>
	<br>*  In Okta Client SpringbootOAuth2OktaSSO
	<br>*  Springboot is using <b>@EnableOAuth2Sso</b> annotation to enable the SSO and auto-configure okta 
	<br>*  clientId/ClientSecret the 
	<br>*  application.properties 
	<br>
	<br>*  okta.oauth2.issuer=https://{yourOktaDomain}/oauth2/default
	<br>*  okta.oauth2.clientId=xxxxx
	<br>*  okta.oauth2.clientSecret=xxxxx
    </div>
	 
     