<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<c:url var="submitURL" value="/list/togo/order/submit" />
<form:form modelAttribute="togoOrderTo" action="${submitURL}" method="post" id="orderForm" name="orderForm">
	<p id="orderError" class="ui-state-error ui-corner-all">
		<span class="ui-icon ui-icon-alert"></span>
		<span></span>
	</p>
	<br/><br/>
	<div id="orderSheet" class="ui-widget-content ui-corner-all ui-helper-reset">
		<h3 class="ui-widget-header ui-corner-all ui-helper-reset">Your Order</h3>
		<table cellspacing="10px" cellpadding="10px">
			<tr>
				<td class="subheading">Item</td>
				<td class="subheading" style="text-align: right;">Price</td>
				<td class="subheading">Qty</td>
			</tr>
			<c:forEach var="item" items="${togoOrderTo.orderItems}" varStatus="status">
				<tr>
					<td>
						${item.itemName}
						<form:hidden path="orderItems[${status.index}].itemCode" />
					</td>
					<fmt:formatNumber var="itemPrice" type="currency" value="${item.price}" />
					<td class="priceTD">${itemPrice}</td>
					<td>
						<form:select path="orderItems[${status.index}].quantity" class="qtyTD" id="qty${status.index}">
							<c:forEach begin="1" end="10" step="1" var="i">
								<form:option value="${i}" label="${i}"></form:option>
							</c:forEach>
						</form:select>
						<form:hidden path="orderItems[${status.index}].price" />
					</td>
				</tr>
			</c:forEach>
			<tr><td colspan="3">&nbsp;</td></tr>
			<fmt:formatNumber var="subTotal" type="currency" value="${togoOrderTo.totalPrice}" />
			<tr>
				<td class="subheading">Subtotal</td>
				<td id="subtotal">${subTotal}</td>
				<td>&nbsp;</td>
			</tr>
			<c:set var="fTax" value="${0.085 * togoOrderTo.totalPrice}" />	
			<fmt:formatNumber var="tax" type="currency" value="${fTax}" />	
			<tr>
				<td class="subheading">Tax</td>
				<td id="tax">${tax}</td>
				<td>&nbsp;</td>
			</tr>			
			<tr>
				<td class="subheading">TOTAL</td>
				<td colspan="2">
					<fmt:formatNumber var="total" type="number" maxFractionDigits="2" value="${togoOrderTo.totalPrice + fTax}" />
					&#36;<form:input path="totalPrice" id="total" value="${total}" readonly="true" size="5"/>	
				</td>
			</tr>
		</table>
	</div>

	<div>
		<fieldset>
			<legend>
				Payment Information
			</legend>
			<table cellpadding="10" cellspacing="5">
				<tr>
					<td>Card Number*</td>
					<td>
						<form:input id="cNumber" path="member.ccNumber" maxlength="19" size="22" />
					</td>
					<td><span class="tiny">(xxxx-xxxx-xxxx-xxxx)</span></td>
				</tr>
				<tr>
					<td>Cardholder's Name*</td>
					<td>
						<form:input id="cName" path="member.ccHolderName" maxlength="35" size="35"/>
					</td>
					<td><span class="tiny">(as it appears on the credit card)</span></td>
				</tr>
				<tr>
					<td>Expiration Date*</td>
					<td>
						<form:select id="cMonth" path="member.ccExpiryMonth">
							<c:forEach var="mm" begin="1" end="12" step="1">
								<form:option value="${mm}" label="${mm}"></form:option>
							</c:forEach>
						</form:select>
						&nbsp;&nbsp;
						<form:select id="cYear" path="member.ccExpiryYear">
							<c:forEach var="yy" begin="2012" end="2022" step="1">
								<form:option value="${yy}" label="${yy}"></form:option>
							</c:forEach>
						</form:select>
					</td>
					<td><span class="tiny">(only month and year required)</span></td>
				</tr>
			</table>
		</fieldset>
		<br /><br />
		<fieldset>
			<legend>
				Contact Information
			</legend>
			<p style="padding: 10px;">
				${togoOrderTo.member.firstName}&nbsp;
				<c:if test="${!empty togoOrderTo.member.middleName}">
					<c:out value="${togoOrderTo.member.middleName}" />&nbsp;
				</c:if>
				${togoOrderTo.member.lastName}<br />
				${togoOrderTo.member.streetAddress}<br/>
				${togoOrderTo.member.city}, ${togoOrderTo.member.state}&nbsp;${togoOrderTo.member.zip}<br />
				<c:if test="${!empty togoOrderTo.member.phone}">
					<span class="subheading">Ph: </span>${togoOrderTo.member.phone}<br/>
				</c:if>				
				<span class="subheading">Email: </span>${togoOrderTo.member.email}								
			</p>
		</fieldset>
	</div>	
	<br />
	<p style="text-align:center;">
		<input type="submit" id="confirmButton" name="confirmButton" value="Confirm Order" />
		&nbsp;&nbsp;&nbsp;
		<input type="button" id="orderCancelBtn" name="orderCancelBtn" value="Cancel" />
	</p>
	<form:hidden path="restaurantId" id="restaurantId" />
</form:form>
