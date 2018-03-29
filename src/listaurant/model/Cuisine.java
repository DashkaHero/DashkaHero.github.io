package listaurant.model;

import java.io.Serializable;

import listaurant.orm.CuisineMapper;

import org.springframework.jdbc.core.RowMapper;

public class Cuisine extends ListaurantModel implements Serializable {

	private static final long serialVersionUID = 4829665876833655322L;

	private Integer cuisineId;

	private String cuisineName;

	public Integer getCuisineId() {
		return cuisineId;
	}

	public void setCuisineId(Integer cuisineID) {
		this.cuisineId = cuisineID;
	}

	public String getCuisineName() {
		return cuisineName;
	}

	public void setCuisineName(String cuisineName) {
		this.cuisineName = cuisineName;
	}
	
	public RowMapper<Cuisine> map() {
		return new CuisineMapper();
	}

}
