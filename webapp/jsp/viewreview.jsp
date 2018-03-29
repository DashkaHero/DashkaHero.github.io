<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<c:url var="submitURL" value="/list/review/new" />
<form:form modelAttribute="reviewTo" action="${submitURL}" method="post" id="viewReviewForm" name="viewReviewForm" >
	<table border="0" summary="restaurant" width="100%" cellspacing="10px">
		<tr>
			<td width="20%"><img src="../images/${reviewTo.restaurant.restaurantImage}" alt="" /></td>
			<td width="30%">${reviewTo.restaurant.restaurantName}
				<br />
				<span class="subheading"> Cuisine: </span>${reviewTo.restaurant.cuisine}
				<br />
				<form:hidden path="restaurant.restaurantId" id="restaurantId" />
			</td>
			<td width="30%">
				${reviewTo.restaurant.streetAddress}<br />${reviewTo.restaurant.city}, ${reviewTo.restaurant.state}&nbsp;${reviewTo.restaurant.zip}
				<br />
				<span class="subheading">Ph: </span>${reviewTo.restaurant.phone}
				<br />
				<br />
				<span class="subheading">Website: </span>${reviewTo.restaurant.website}
			</td>
			<td>
				<c:if test="${empty reviewTo.restaurant.avgRating}">Not yet rated</c:if>
				<c:if test="${!empty reviewTo.restaurant.avgRating}">
					<c:forEach begin="1" end="${reviewTo.restaurant.avgRating}">
						<img src='../images/star.png' alt='star' />&nbsp;
					</c:forEach>
					<br/>
					(${reviewTo.restaurant.reviewCount}&nbsp;reviews)
				</c:if>
				<br/><br/>
				<button id="newRvBtn" type="submit" name="newRvBtn">
					<span class="ui-icon ui-icon-comment"></span>Write a review
				</button>
			</td>
		</tr>
		<tr><td colspan="4"><hr /></td></tr>
	</table>

	<c:if test="${!empty reviewTo.reviews}">
		<table border="0" summary="reviews" width="70%" cellspacing="10px">
		<c:forEach items="${reviewTo.reviews}" var="rv">
			<tr>
				<td>
					<c:forEach begin="1" end="${rv.rating}">
						<img src='../images/star.png' alt='star' />&nbsp;
					</c:forEach>
					Reviewed on <span class='subheading'>${rv.reviewDateTime}</span> by <span class='subheading'>${rv.userId}</span>
					<br /><br />
					${rv.comments}
				</td>
			</tr>
			<tr><td><hr /></td></tr>
		</c:forEach>
		</table>
	</c:if>
</form:form>
