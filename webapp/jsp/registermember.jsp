<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<c:url var="submitURL" value="/list/newuser/submit" />
<form:form modelAttribute="registrationTo" action="${submitURL}" method="post" id="registerForm" name="registerForm">
	<p id="registerError" class="ui-state-error ui-corner-all">
		<span class="ui-icon ui-icon-alert"></span>
		<span>Fields marked with * are required</span>
	</p>
	<br/> <br/>
	<fieldset title="Profile Information">
		<legend>
			Profile Information
		</legend>
		<table summary="profile">
			<tr>
				<td><label for="firstName">First Name*</label></td>
				<td><form:input path="member.firstName" id="firstName" size="20" maxlength="20"/></td>
				<td><label for="middleName">Middle Name</label></td>
				<td><form:input path="member.middleName" id="middleName" size="20" maxlength="20"/></td>
				<td><label for="lastName">Last Name*</label></td>
				<td><form:input path="member.lastName" id="lastName" size="20" maxlength="20"/></td>
			</tr>
			<tr>
				<td><label for="streetAddress">Street Address*</label></td>
				<td colspan="3"><form:input path="member.streetAddress" id="streetAddress" size="60" maxlength="60"/></td>
				<td><label for="city">City*</label></td>
				<td><form:input path="member.city" id="city" size="20" maxlength="20"/></td>
			</tr>
			<tr>
				<td><label for="state">State*</label></td>
				<td>
					<form:input path="member.state" id="state" size="3" maxlength="2"/>
					<span class="tiny">(state code)</span>
				</td>
				<td><label for="zip">Zip Code*</label></td>
				<td>
					<form:input path="member.zip" id="zip" size="5" maxlength="5"/>
					<span class="tiny">(xxxxx)</span>
				</td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><label for="email">Email*</label></td>
				<td><form:input path="member.email" id="email" size="40" maxlength="40"/></td>
				<td colspan="2"><span class="tiny">(abc@xyz.com)</span></td>
				<td><label for="phone">Phone</label></td>
				<td>
					<form:input path="member.phone" id="phone" size="13" maxlength="12"/>
					<span class="tiny">(xxx-xxx-xxxx)</span>
				</td>
			</tr>
		</table>
	</fieldset>
	<br />
	<fieldset title="Account Information">
		<legend>
			Account Information
		</legend>
		<table summary="account">
			<tr>
				<td><label for="userId">User Name*</label></td>
				<td><form:input path="member.userId" id="userId" size="20" maxlength="20"/></td>
				<td><span class="tiny">(Allowed a-z,0-9,_)&nbsp;&nbsp;&nbsp;</span></td>
				<td><label for="userPwd">Password*</label></td>
				<td><form:password path="member.userPwd" id="userPwd" size="20" maxlength="20"/></td>
				<td></td>
			</tr>
		</table>
	</fieldset>
	<br />
	<fieldset title="Billing Information">
		<legend>
			Billing Information
		</legend>
		<table summary="billing">
			<tr>
				<td><label for="cardType">Card Type&nbsp;</label></td>
				<td>
					<form:select id="cardType" path="member.cardType">
						<form:option value="Master Card" label="Master Card"></form:option>
						<form:option value="VISA" label="VISA"></form:option>
					</form:select>
				</td>
				<td><label for="ccHolderName">Card Holder's Name</label></td>
				<td>
					<form:input path="member.ccHolderName" id="ccHolderName" size="40" maxlength="40"/>
					<span class="tiny">(as it appears on the credit card)</span>
				</td>
			</tr>
			<tr>
				<td><label for="ccNumber">Card Number&nbsp;</label></td>
				<td>
					<form:input path="member.ccNumber" id="ccNumber" size="22" maxlength="19"/>
					<span class="tiny">(xxxx-xxxx-xxxx-xxxx)</span>
				</td>
				<td><label for="ccExpiryMonth">Expiration Date&nbsp;</label></td>
				<td>
					<form:select id="ccExpiryMonth" path="member.ccExpiryMonth">
						<c:forEach var="mm" begin="1" end="12" step="1">
							<form:option value="${mm}" label="${mm}"></form:option>
						</c:forEach>
					</form:select>
					<form:select id="ccExpiryYear" path="member.ccExpiryYear">
						<c:forEach var="yy" begin="2012" end="2022" step="1">
							<form:option value="${yy}" label="${yy}"></form:option>
						</c:forEach>
					</form:select>
					<span class="tiny">(only month and year required)</span>
				</td>
			</tr>
		</table>
	</fieldset>
</form:form>
