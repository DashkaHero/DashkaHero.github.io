<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<c:url var="editURL" value="/list/reserve/edit/submit" />
<form:form modelAttribute="reservationTo" action="${editURL}" method="post" id="viewRsvForm" name="viewRsvForm" >
	<table border="0" summary="reservation" width="100%" cellspacing="10px">
		<c:set var="rs" value="${reservationTo.restaurant}" />
		<tr>
			<td width="33%"><img src='../images/reserved.jpg' alt='' /></td>
			<td width="33%">
				<span class="subheading"> Reservation Number </span> <br />
				${reservationTo.reservation.reservationNumber}
				<form:hidden path="reservation.reservationNumber" id="rsvNo" />			
			</td>
			<td width="33%">
				<span class="subheading"> Reserved At </span> <br />
				${rs.restaurantName}<br />
				${rs.streetAddress}<br />
				${rs.city}, ${rs.state}&nbsp;${rs.zip}<br />				
				Ph: ${rs.phone}
			<td>
		</tr>
		<tr>
			<td colspan="3">
			<hr />
			</td>
		</tr>
	</table>
	<p class="subheading">
		&nbsp;&nbsp;Reservation Details:
	</p>
	<table border="0" summary="reservation" width="65%" cellspacing="10px">
		<tr>
			<td width="33%">Date</td>
			<td width="33%">Time</td>
			<td width="33%">Party Size</td>
		</tr>
		<tr>
			<td width="33%">
				<form:input path="reservation.reserveDate" id="rsvDate" size="15" maxlength="10" disabled="true" />
			</td>
			<td width="33%">
				<form:input path="reservation.reserveTime" id="rsvTime" size="15" maxlength="10" disabled="true" />
			</td>
			<td width="33%">
				<form:select path="reservation.tableType" id="partiSize" disabled="true">
					<form:options items="${reservationTo.partySizeList}" itemValue="typeId" itemLabel="typeName" />
				</form:select>
			</td>
		</tr>
	</table>
	<br />
	<button id="editRsvBtn">Edit</button>
	&nbsp;&nbsp;&nbsp;<input type="submit" value="Cancel" id="cancelRsvBtn" name="cancelRsvBtn" />
	&nbsp;&nbsp;&nbsp;<button id="backBtn"><span class="ui-icon ui-icon-arrowreturnthick-1-w"></span>Back</button>
	<br /><br/>
	<p id="rsvEditError" class="ui-state-error ui-corner-all">
		<span class="ui-icon ui-icon-alert"></span>
		<span>Click on Edit to update your reservation, Click on cancel to cancel your reservation</span>
	</p>	
</form:form>
