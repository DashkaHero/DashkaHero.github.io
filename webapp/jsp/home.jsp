<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<c:url var="mouse" value="../images/food_mouse.jpg" />
<form action="" method="get" id="home" name="home" target="_blank">
	<h3> Welcome to Listaurant - your online restaurant reservation system. </h3>
	<br/>
	<div class="homeBox">
		<p>
			Want to dine out today at your favorite restaurant? But worried about the long queues and waiting times.
			We, at Listaurant, have a solution for you - Reserve your table through our online, easy to 
			use restaurant reservation system!
			<br /><br />
			Search for popular restaurants in your neighbourhood, check out restaurant reviews from our members
			and make your reservation today.
			<br /><br />
			Are you bored of visiting that same old restaurant? Want to try something new? - Check out our user reviews. If you don't even have to sign up 
			to read restaurant reviews!
			<br /><br />
			Want your favorite chinese food or pizza delivered to your door step? Check out the menu and order your takeout 
			through Listaurant. It is that simple!
			<br /><br />
			What are you waiting for? Sign up today - to manage your reservations and share your dining experience! You can even have your 
			comfort food delivered to your door step!
		</p>
		<br/><br />
		<h3>Don't forget to sign up - it is free!</h3>
		<br />
		<h4 style="color:#a68115;">Now, delicious food is just a mouse click away! Yum!</h4>		
	</div>
	<img src="${mouse}" alt="food" height="250px" width="297px"/>
</form>
