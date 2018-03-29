<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<c:url var="searchURL" value="/list/reserve/new/submit" />
<form:form modelAttribute="reservationTo" action="${searchURL}" method="post" id="reserveForm" name="reserveForm" >
	<table border="0" summary="restaurant" width="100%" cellspacing="10px">
		<c:set var="rs" value="${reservationTo.restaurant}" />
		<tr>
			<td width="20%"><img src="../images/${rs.restaurantImage}" alt="" /></td>
			<td width="30%">${rs.restaurantName}
			<br />
			<span class="subheading"> Cuisine: </span>${rs.cuisine}
			<br />
			<form:hidden path="reservation.restaurantId" id="restaurantId" />
			</td>
			<td width="30%">${rs.streetAddress}<br />${rs.city}, ${rs.state}&nbsp;${rs.zip}
			<br />
			<span class="subheading">Ph: </span>${rs.phone}
			<br />
			<br />
			<span class="subheading">Website: </span>${rs.website}</td>
			<td>
				<c:if test="${!empty rs.avgRating}">
					<c:forEach begin="1" end="${rs.avgRating}">
						<img src='../images/star.png' alt='star' />&nbsp;
					</c:forEach>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="4"><hr /></td>
		</tr>
		<?php
		}
		?>
	</table>
	<p class="subheading">
		&nbsp;&nbsp;Pick a date, time and party size to make your reservation:
	</p>
	<table border="0" summary="reservation" width="65%" cellspacing="10px">
		<tr>
			<td width="33%">Date</td>
			<td width="33%">Time</td>
			<td width="33%">Party Size</td>
		</tr>
		<tr>
			<td width="25%">
			<form:input path="reservation.reserveDate" id="reserveDate" size="15" maxlength="10"/>
			</td>
			<td width="25%">
			<form:input path="reservation.reserveTime" id="reserveTime" size="15" maxlength="10"/>
			</td>
			<td width="25%">
			<form:select path="reservation.tableType" id="partySize">
				<form:options items="${reservationTo.partySizeList}" itemValue="typeId" itemLabel="typeName" />
			</form:select>
			</td>
			<td width="25%">
			<input type="submit" value="Reserve" id="makeResBtn" name="makeResBtn" />
			</td>
		</tr>
	</table>
	<br/><br/>
	<p id="reserveError" class="ui-state-error ui-corner-all">
		<span class="ui-icon ui-icon-alert"></span>
		<span>Please note that reservations can be made up to 7 days in advance only</span>
	</p>	
</form:form>
