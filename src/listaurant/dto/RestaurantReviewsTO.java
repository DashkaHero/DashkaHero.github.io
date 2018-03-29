package listaurant.dto;

import java.util.List;

import listaurant.model.Restaurant;
import listaurant.model.Review;

public class RestaurantReviewsTO {

	private Restaurant restaurant;

	private List<Review> reviews;
	
	public RestaurantReviewsTO() {
		
	}
	
	public RestaurantReviewsTO(Restaurant restaurant, List<Review> reviewList) {
		this.restaurant = restaurant;
		this.setReviews(reviewList);
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public List<Review> getReviews() {
		return reviews;
	}
	
}
