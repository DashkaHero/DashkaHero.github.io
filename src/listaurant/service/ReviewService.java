package listaurant.service;

import java.util.List;

import javax.annotation.Resource;

import listaurant.dao.ListaurantDAO;
import listaurant.dto.RestaurantSearchTO;
import listaurant.dto.RestaurantReviewsTO;
import listaurant.dto.ReviewDetailTO;
import listaurant.model.Restaurant;
import listaurant.model.Review;
import listaurant.model.factory.ModelFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {

	@Resource
	private ListaurantDAO listaurantDAO;

	@Transactional
	public List<Restaurant> sortAndFilterReviews(RestaurantSearchTO searchTO) {
		List<Restaurant> restaurantList = listaurantDAO.getRestaurantsByReviews(
				searchTO.getRestName(), searchTO.getCuisine(), searchTO.getNear(),
				searchTO.getSortOption(), searchTO.getFilterOption());
		
		return restaurantList;
	}

	public RestaurantReviewsTO getReviews(Integer restaurantId) {
		Restaurant restaurant = listaurantDAO.getRestaurantRecord(restaurantId);
		List<Review> reviews = listaurantDAO.getRestaurantReviews(restaurantId);
		RestaurantReviewsTO reviewTo = new RestaurantReviewsTO(restaurant, reviews);
		
		return reviewTo;
	}

	public void postReview(ReviewDetailTO reviewDetailTo) {		
		Review review = ModelFactory.getInstance().createReview(null);
		review.setRating(reviewDetailTo.getRating());
		review.setRestaurantId(reviewDetailTo.getRestaurantId());
		review.setUserId(reviewDetailTo.getUserId());
		review.setComments(reviewDetailTo.getComments());
		
		listaurantDAO.insertReview(review);
		
		return;
	}

}
