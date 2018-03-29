package listaurant.model;

import java.io.Serializable;

import listaurant.orm.RestaurantMapper;

import org.springframework.jdbc.core.RowMapper;

public class Restaurant extends ListaurantModel implements Serializable {

	private static final long serialVersionUID = 6046660088184381484L;

	private Integer restaurantId;

	private String restaurantName;

	private String description;

	private String streetAddress;

	private String city;

	private String state;

	private Integer zip;

	private String website;

	private String phone;

	private String cuisine;

	private String restaurantImage;

	private Integer avgRating;
	
	private Integer reviewCount;

	public Integer getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddr) {
		this.streetAddress = streetAddr;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getZip() {
		return zip;
	}

	public void setZip(Integer zip) {
		this.zip = zip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCuisine() {
		return cuisine;
	}

	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}

	public String getRestaurantImage() {
		return restaurantImage;
	}

	public void setRestaurantImage(String restaurantImage) {
		this.restaurantImage = restaurantImage;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getWebsite() {
		return website;
	}

	public void setAvgRating(Integer avgRating) {
		this.avgRating = avgRating;
	}

	public Integer getAvgRating() {
		return avgRating;
	}

	public void setReviewCount(Integer reviewCount) {
		this.reviewCount = reviewCount;
	}

	public Integer getReviewCount() {
		return reviewCount;
	}

	public RowMapper<Restaurant> map() {
		return new RestaurantMapper();
	}
}
