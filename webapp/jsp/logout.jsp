<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<c:url var="submitURL" value="/list/showlogin" />
<form:form modelAttribute="userDetail" action="${submitURL}" method="get" id="logout" name="logout">
	<br />
	<p>
		Hello <span style="font-size:11pt;font-weight:bold;color:#3fa12b">
		<c:out value="${userDetail.firstName} ${userDetail.middleName} ${userDetail.lastName}"></c:out>		
		</span>
		<br /> Welcome to Listaurant!
	</p>
	<br/>
	<hr/>
	<br/>
	<input type="submit" value="Sign Out" id="logoutButton" name="logoutButton" />
</form:form>
<script>
	var lbl = '<c:out value="${userDetail.firstName} ${userDetail.middleName} ${userDetail.lastName}"/>';  
	$("#signin").button("option", "label", lbl);
</script>

