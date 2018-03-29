<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<c:url var="orderURL" value="/list/togo/order" />
<form:form modelAttribute="togoOrderTo" action="${orderURL}" method="post" id="viewmenuForm" name="viewmenuForm" >
	<table border="0" summary="header" width="100%" cellspacing="10px">
		<tr>
			<td width="75%">
			<p id="viewmenuError" class="ui-state-error ui-corner-all">
				<span class="ui-icon ui-icon-alert"></span>
				<span>Choose items and click on Order button to complete your takeout order"</span>
			</p>
			</td>
			<td width="10%">&nbsp;</td>
			<td width="15%">
			<button id="orderButton" type="submit" name="orderButton">Finish Order</button>
			</td>
		</tr>
	</table>
	<br /><br />
	
	<div>
		<c:forEach var="category" items="${menuMap}">
			<div class="ui-widget-content ui-corner-all">
				<h3 class="ui-widget-header ui-corner-all"><c:out value="${category.key}"></c:out></h3>
				<table border="0" summary="menu1" width="80%" cellspacing="10px">
				<c:forEach var="menuItem" items="${category.value}">
					<tr>
						<td width="50%">
							<span class="subheading">${menuItem.itemName}</span><br />
							${menuItem.description}
						</td>
						<fmt:formatNumber var="itemPrice" type="currency" value="${menuItem.price}" />
						<td width="25%">${itemPrice}</td>
						<td width="25%">
							<form:checkbox class="addItem" path="orderItems" id="item${menuItem.itemCode}" value="${menuItem}"/>
							<label for="item${menuItem.itemCode}">Add Item</label>
						</td>
					</tr>
				</c:forEach>	
				</table>
			</div>
		</c:forEach>
	</div>
	<form:hidden path="restaurantId" id="restaurantId" />
</form:form>
