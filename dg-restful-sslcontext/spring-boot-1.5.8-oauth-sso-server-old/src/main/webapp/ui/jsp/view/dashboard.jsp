<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<h1>Congratulations, you successfully sign on!</h1>
<b>Okta 'Single Sign On (SSO) redirect your login to your User Detail Dashboard page!</b><br>
<br/> 
 <fieldset>
 
        <legend><span>User Details </span></legend>
        <table style="width:940px;">
			<tr class="row" style="border: 1px solid green; padding: 10px" >
				<td class="col-md-4 text-center" style="border: 1px solid green; padding: 10px">Name</td>
				<td class="col-md-4 text-center" style="border: 1px solid green; padding: 10px">Email</td>
				<td class="col-md-4 text-center" style="border: 1px solid green; padding: 10px">Address</td>
			</tr> 
			<c:forEach var="user" items="${users}">
			   <tr class="row" style="border: 1px solid green; padding: 10px">
				<td class="col-md-4 text-center" style="border: 1px solid green; padding: 10px">${user.name}</td>
				<td class="col-md-4 text-center" style="border: 1px solid green; padding: 10px">${user.email}</td>
				<td class="col-md-4 text-center" style="border: 1px solid green; padding: 10px">${user.address}</td>
				</tr>
			
			</c:forEach>
	 
   		</table>
 </fieldset>
 <br>
 <a href="http://localhost:8080/SpringbootAdvancedDemo/oktaSSO.html/"> Back to Springboot Advanced Demo</a> 
  

 </b>