package listaurant.model.factory;

import listaurant.common.ListaurantUtil;
import listaurant.model.Cuisine;
import listaurant.model.Member;
import listaurant.model.MenuItem;
import listaurant.model.Reservation;
import listaurant.model.Restaurant;
import listaurant.model.Review;
import listaurant.model.TableType;

public final class ModelFactory {
	
	private static final ModelFactory _instance = new ModelFactory();
	
	private ModelFactory() {
		// only exists to thwart instantiation using constructors
	}
	
	public static ModelFactory getInstance() {
		return _instance;
	}

	public Cuisine createCuisine(Integer cuisineId) {
		Cuisine cuisine = new Cuisine();
		cuisine.setCuisineId(cuisineId);

		return cuisine;
	}

	public Restaurant createRestaurant(Integer restaurantId) {
		Restaurant restaurant = new Restaurant();
		restaurant.setRestaurantId(restaurantId);
		
		return restaurant;
	}
	
	public TableType createTableType(Integer typeId) {
		TableType tType = new TableType();
		tType.setTypeId(typeId);

		return tType;
	}	

	public Member createMember(String userId, String pwd) {
		Member member = new Member();
		member.setUserId(ListaurantUtil.isNull(userId)?"":userId);
		member.setUserPwd(ListaurantUtil.isNull(pwd)?"":pwd);
		
		return member;
	}

	public Reservation createReservation(Integer reservationNumber) {
		Reservation reservation = new Reservation();
		reservation.setReservationNumber(reservationNumber);
		
		return reservation;
	}
	
	public Review createReview(Integer reviewNumber) {
		Review review = new Review();
		review.setReviewNumber(reviewNumber);
		
		return review;
	}
	
	public MenuItem createMenuItem(Integer itemCode) {
		MenuItem menuItem = new MenuItem();
		menuItem.setItemCode(itemCode);
		
		return menuItem;
	}

}
