<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<c:url var="searchURL" value="/list/review/result" />
<form:form modelAttribute="restaurantSearchTo" action="${searchURL}" method="post" id="srchReviewForm" name="srchReviewForm" >
	<p id="srchReviewError" class="ui-state-error ui-corner-all">
		<span class="ui-icon ui-icon-alert"></span>
		<span>Search for restaurants in your neighbourhood to see reviews from our members</span>
	</p>
	<table border="0" summary="search" width="100%" cellspacing="10px">
		<tr>
			<td width="35%">Name of Restaurant</td>
			<td width="15%">Cuisine</td>
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
			<td>
				<form:input path="near" id="near" size="35" maxlength="35"/>
			</td>
			<td>
				<button id="srchRvButton" type="submit" name="srchRvButton">
					<span class="ui-icon ui-icon-search"></span>Find
				</button>
			</td>
		</tr>
	</table>
	
	<c:if test="${!empty restaurantList}">
		<table border="0" summary="result" width="100%" cellspacing="10px">			
			<tr>
				<td colspan="3">				
					<div id="sortradio">
						<span class="subheading" style="float: left;">Sort by:</span>&nbsp;
						<form:radiobutton class="sortradio" id="sortradio1" path="sortOption" value="review_count" />
						<label for="sortradio1">Most Reviewed</label>
						<form:radiobutton class="sortradio" id="sortradio2" path="sortOption" value="avg_rating" />
						<label for="sortradio2">Highest Rated</label>
					</div>
				</td>
				<td colspan="2">				
					<div id="filterradio">
						<span class="subheading" style="float: left;">Filter by:</span>&nbsp;
						<form:radiobutton class="filterradio" id="filterradio1" path="filterOption" value="3" />
						<label for="filterradio1">
							<c:forEach begin="1" end="3" step="1">
								<img src="../images/star2.png" alt="star" />
							</c:forEach>
							&amp; above
						</label>
						<form:radiobutton class="filterradio" id="filterradio2" path="filterOption" value="5" />
						<label for="filterradio2">
							<c:forEach begin="1" end="5" step="1">
								<img src="../images/star2.png" alt="star" />
				 			</c:forEach>
				 			only			
						</label>
					</div>
				</td>
			</tr>
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
							<br/>
							(${rs.reviewCount}&nbsp;reviews)
						</c:if>
					</td>
					<td width="20%">
						<button class="reviewBtn" name="reviewBtn${rs.restaurantId}" id="reviewBtn${rs.restaurantId}">
							Read Reviews
						</button>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>	
</form:form>
