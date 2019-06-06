<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row text-center">
	<strong> User Details</strong>
</div>
<div class="row" style="border: 1px solid green; padding: 10px">
	<div class="col-md-4 text-center">
		<strong>Name</strong>
	</div>
	<div class="col-md-4 text-center">
		<strong>Email</strong>
	</div>
	<div class="col-md-4 text-center">
		<strong>Address</strong>
	</div>
</div>
<c:forEach var="user" items="${users}">
	<div class="row" style="border: 1px solid green; padding: 10px">
		<div class="col-md-4 text-center">${user.name}</div>
		<div class="col-md-4 text-center">${user.email}</div>
		<div class="col-md-4 text-center">${user.address}</div>
	</div>
</c:forEach>
