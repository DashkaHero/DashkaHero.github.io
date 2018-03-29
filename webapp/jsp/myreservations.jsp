<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<c:url var="editURL" value="/list/reserve/edit" />
<form:form action="${editURL}" method="post" id="myreserveForm" name="myreserveForm" >
	<p style="margin-left: 0px;" class="ui-state-highlight ui-corner-all">
		<span class="ui-icon ui-icon-info"></span>
		<span>Click on a reservation number under Current Reservations to view/edit"</span>
	</p>
	<br /><br />
	<div id="reserveList">
		<h3><a href="#">Current Reservations</a></h3>
		<div>
			<table border="0" summary="open_reservations" width="95%" cellspacing="10px">
				<c:set var="found" value="false"/>
				<c:forEach items="${reservationList}" var="rsv">
					<c:if test="${!rsv.expired}">
						<c:set var="found" value="true"/>
						<tr>
							<td width="33%">
								<a href="" class="reserveLink">Reservation #<c:out value="${rsv.reservationNumber}" /></a>								
							</td>
							<td width="33%">${rsv.restaurantName}</td>
							<td width="33%">${rsv.reserveDate}&nbsp;${rsv.reserveTime}</td>
						</tr>							
					</c:if>
				</c:forEach>
				<c:if test="${!found}">
					<tr><td colspan="3">You do not have any current reservations</td></tr>
				</c:if>
			</table>
		</div>		
		<h3><a href="#">Old Reservations</a></h3>
		<div>		
			<table border="0" summary="past_reservations" width="95%" cellspacing="10px">
				<c:set var="found" value="false"/>
				<c:forEach items="${reservationList}" var="rsv">
					<c:if test="${rsv.expired}">
						<c:set var="found" value="true"/>
						<tr>
							<td width="33%">Reservation #<c:out value="${rsv.reservationNumber}" /></td>							
							<td width="33%">${rsv.restaurantName}</td>
							<td width="33%">${rsv.reserveDate}&nbsp;${rsv.reserveTime}</td>	
						</tr>
					</c:if>
				</c:forEach>
				<c:if test="${!found}">
					<tr><td colspan="3">You do not have any old reservations</td></tr>
				</c:if>
			</table>
		</div>
	</div>
</form:form>
