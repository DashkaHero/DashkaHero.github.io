<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<c:url var="searchURL" value="/list/togo/result" />
<form:form modelAttribute="restaurantSearchTo" action="${searchURL}" method="post" id="srchTogoForm" name="srchTogoForm" >
	<p id="srchTogoError" class="ui-state-error ui-corner-all">
		<span class="ui-icon ui-icon-alert"></span>
		<span>Search for restaurants in your neighbourhood to order a takeout</span>
	</p>
	<table border="0" summary="search" width="100%" cellspacing="10px">
		<tr>
			<td width="35%">Which Restaurant?</td>
			<td width="15%">What Cuisine?</td>
			<td width="30%">Near (Address, City, State or Zip)</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>
				<form:input path="restName" id="restName" size="35" maxlength="35"/>
				&nbsp;&nbsp;&nbsp;&nbsp;<span class="subheading">&lt;OR&gt; </span>
			</td>
			<td>
				<form:select path="cuisine" id="cuisine">
					<form:option value="0" label="Select"></form:option>
					<form:options items="${restaurantSearchTo.cuisineList}" itemValue="cuisineId" itemLabel="cuisineName" />
				</form:select>
			</td>
			<td><form:input path="near" id="near" size="35" maxlength="35"/></td>
			<td>
				<button id="srchButton" type="submit" name="srchButton">
					<span class="ui-icon ui-icon-search"></span>Find
				</button>
			</td>
		</tr>
	</table>

	<c:if test="${!empty restaurantList}">
		<table border="0" summary="result" width="100%" cellspacing="10px">
		<c:forEach items="${restaurantList}" var="rs">
			<tr><td colspan="5"><hr /></td></tr>
			<tr>
				<td width="15%"><img src="../images/${rs.restaurantImage}" alt="" /></td>
				<td width="25%">${rs.restaurantName}<br /><span class="subheading">Cuisine: </span>${rs.cuisine}</td>
				<td width="20%">${rs.streetAddress}<br />${rs.city}, ${rs.state}&nbsp;${rs.zip}
					<br /><span class="subheading">Ph: </span>${rs.phone}
				</td>
				<td width="20%">
					<c:if test="${empty rs.avgRating}">Not yet rated</c:if>
					<c:if test="${!empty rs.avgRating}">
						<c:forEach begin="1" end="${rs.avgRating}">
							<img src='../images/star.png' alt='star' />&nbsp;
						</c:forEach>
					</c:if>
				</td>
				<td width="20%">
					<button class="togoButton" name="togoButton${rs.restaurantId}" id="togoButton${rs.restaurantId}">
						Order Takeout
					</button>
				</td>
			</tr>
		</c:forEach>
		</table>
	</c:if>
</form:form>
