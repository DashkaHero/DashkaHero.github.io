package listaurant.facade;

import java.util.List;

import javax.annotation.Resource;

import listaurant.common.ListaurantException;
import listaurant.dto.RestaurantSearchTO;
import listaurant.model.Cuisine;
import listaurant.model.Member;
import listaurant.model.Restaurant;
import listaurant.service.LoginService;
import listaurant.service.OrderService;
import listaurant.service.ReservationService;
import listaurant.service.RestaurantService;
import listaurant.service.ReviewService;

import org.springframework.stereotype.Component;

@Component
public class ListaurantFacade {

	@Resource
	private RestaurantService restaurantService;
	
	@Resource
	private LoginService loginService;
	
	@Resource
	private ReservationService reservationService;
	
	@Resource
	private ReviewService reviewService;
	
	@Resource
	private OrderService orderService;
		
	public RestaurantSearchTO populateCuisines(RestaurantSearchTO searchTO) throws ListaurantException {
		List<Cuisine> cuisineList = restaurantService.getCuisineList();
		searchTO.setCuisineList(cuisineList);
		
		return searchTO;
	}
	
	public List<Restaurant> findRestaurants(RestaurantSearchTO searchTO) throws ListaurantException {
		return restaurantService.findRestaurants(searchTO);
	}

	public LoginService getLoginService() {
		return loginService;
	}
	
	public ReservationService getReservationService() {
		return reservationService;
	}
	
	public ReviewService getReviewService() {
		return reviewService;
	}
	
	public OrderService getOrderService() {
		return orderService;
	}
	
	public Member getUserBillingInfo(String userName) throws ListaurantException {
		return loginService.getUserBillingInfo(userName);
	}
	
}
