package listaurant.orm;

import java.sql.ResultSet;
import java.sql.SQLException;

import listaurant.model.Restaurant;
import listaurant.model.factory.ModelFactory;

import org.springframework.jdbc.core.RowMapper;

public class RestaurantMapper implements RowMapper<Restaurant> {

	@Override
	public Restaurant mapRow(ResultSet rs, int rowNum) throws SQLException {
		Restaurant restaurant = ModelFactory.getInstance().createRestaurant(rs.getInt("restaurant_id"));
		restaurant.setRestaurantName(rs.getString("restaurant_name"));
		restaurant.setStreetAddress(rs.getString("street_address"));
		restaurant.setCity(rs.getString("city"));
		restaurant.setState(rs.getString("state"));
		restaurant.setZip(rs.getInt("zip"));
		restaurant.setPhone(rs.getString("phone_number"));
		restaurant.setWebsite(rs.getString("website"));
		restaurant.setRestaurantImage(rs.getString("restaurant_image"));
		restaurant.setCuisine(rs.getString("cuisine_name"));
		restaurant.setAvgRating(Math.round(rs.getFloat("avg_rating")));
		restaurant.setReviewCount(rs.getInt("review_count"));

		return restaurant;
	}

}
