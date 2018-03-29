package listaurant.controller;

import javax.servlet.http.HttpServletResponse;

import listaurant.common.ListaurantException;

import org.springframework.stereotype.Controller;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProxyController {

	@ExceptionHandler(ListaurantException.class)
	public @ResponseBody
	String handleException(ListaurantException le, HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		return le.getMessage();
	}

	@ExceptionHandler(HttpSessionRequiredException.class)
	public @ResponseBody
	String handleException(HttpSessionRequiredException le, HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		return "Please log in or sign up with Listaurant to make dining reservations and post reviews";
	}
}
