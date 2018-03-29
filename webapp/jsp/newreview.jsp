<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<c:url var="submitURL" value="/list/review/new/submit" />
<form:form modelAttribute="reviewDetailTo" action="${submitURL}" method="post" id="newReviewForm" name="newReviewForm">
	<p id="newReviewError" class="ui-state-error ui-corner-all">
		<span class="ui-icon ui-icon-alert"></span>
		<span>Fields marked with * are required</span>
	</p>
	<br /><br />
	<table summary="postreview">
		<tr>
			<td><label for="rating">Rating*</label></td>
		</tr>
		<tr>
			<td>
				<c:forEach begin="1" end="5" step="1" var="i">
					<form:radiobutton path="rating" id="ratingVal${i}" value="${i}" />
					<label for="ratingVal${i}">
						<c:forEach begin="1" end="${i}" step="1">
							<img src="../images/star2.png" alt="star" />
			 			</c:forEach>			 						
					</label>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</c:forEach>
			</td>
		</tr>
		<tr>
			<td><br /><br /><label for="comments">Comments</label></td>
		</tr>
		<tr>
			<td>
				<form:textarea path="comments" id="comments" rows="3" cols="50"></form:textarea>
			</td>			
		</tr>
	</table>
	<form:hidden path="restaurantId" id="restaurantId"/>
</form:form>
