package listaurant.model;

import java.io.Serializable;

import listaurant.orm.ReviewMapper;

import org.springframework.jdbc.core.RowMapper;

public class Review extends ListaurantModel implements Serializable {

	private static final long serialVersionUID = -3650020561147373965L;

	private Integer reviewNumber;

	private Integer restaurantId;

	private String userId;

	private Integer rating;

	private String comments;

	private String reviewDateTime;

	public Integer getReviewNumber() {
		return reviewNumber;
	}

	public void setReviewNumber(Integer reviewNumber) {
		this.reviewNumber = reviewNumber;
	}

	public Integer getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getReviewDateTime() {
		return reviewDateTime;
	}

	public void setReviewDateTime(String reviewDateTime) {
		this.reviewDateTime = reviewDateTime;
	}
	
	public RowMapper<Review> map() {
		return new ReviewMapper();
	}

}
