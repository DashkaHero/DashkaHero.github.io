<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Listaurant - Your online restaurant reservation system</title>
		
		<link rel="stylesheet" type="text/css" href="../jquery_lib/jquery-ui.css"/>
		<link rel="stylesheet" type="text/css" href="../jquery_lib/jquery-ui-timepicker.css"/>		
		<link rel="stylesheet" type="text/css" href="../css/listaurant.css"/>
		
		<script src="../jquery_lib/jquery.js" type="text/javascript"></script>
		<script src="../jquery_lib/jquery-ui.js" type="text/javascript"></script>
		<script src="../jquery_lib/jquery-ui-timepicker.js" type="text/javascript"></script>
		<script src="../js/listaurant.js" type="text/javascript"></script>
		<script src="../js/utility.js" type="text/javascript"></script>
	</head>
	<c:url var="home" value="/list/home" />
	<c:url var="makeReserve" value="/list/reserve/search" />
	<c:url var="myReserve" value="/list/reserve/viewall" />
	<c:url var="restReview" value="/list/review/search" />
	<c:url var="orderTogo" value="/list/togo/search" />
	<body>
		<div id="header">
			<table summary="header" width="100%">
				<tr>
					<td width="30%"><img id=logo alt="Site logo" src="../images/listaurant2.jpg" /></td>
					<td class="title">listaurant</td>
					<td width="20%">
					<button id="signin">
						Sign in
					</button></td>
				</tr>
			</table>
		</div>
		<div id="loginbox" class="ui-widget-content ui-corner-all"></div>
		<div id="registerDialog" title="Member Registration"></div>
		<div id="newReviewDialog" title="Write a Review"></div>
		<div class="listaurantInfo" title=""></div>
		<div id="content">
			<div id="menu">
				<ul>
					<li>
						<a href="${home}">Home</a>
					</li>
					<li>
						<a href="${makeReserve}">Make a Reservation</a>
					</li>
					<li>
						<a href="${orderTogo}">Order a Takeout</a>
					</li>					
					<li>
						<a href="${myReserve}">My Reservations</a>
					</li>
					<li>
						<a href="${restReview}">Restaurant Reviews</a>
					</li>					
				</ul>
			</div>
		</div>
		<div id="footer">
			<p i>
				
			</p>
		</div>
	</body>
</html>
