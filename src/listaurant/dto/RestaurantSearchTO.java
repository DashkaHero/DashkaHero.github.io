package listaurant.dto;

import java.util.List;

import listaurant.model.Cuisine;

public class RestaurantSearchTO {

	private String restName;

	private Integer cuisine;

	private String near;

	private List<Cuisine> cuisineList;
	
	private String sortOption;
	
	private String filterOption;

	public String getRestName() {
		return restName;
	}

	public void setRestName(String restName) {
		this.restName = restName;
	}

	public Integer getCuisine() {
		return cuisine;
	}

	public void setCuisine(Integer cuisine) {
		this.cuisine = cuisine;
	}

	public String getNear() {
		return near;
	}

	public void setNear(String near) {
		this.near = near;
	}

	public List<Cuisine> getCuisineList() {
		return cuisineList;
	}

	public void setCuisineList(List<Cuisine> cuisineList) {
		this.cuisineList = cuisineList;
	}

	public String getSortOption() {
		return sortOption;
	}

	public void setSortOption(String sortOption) {
		this.sortOption = sortOption;
	}

	public String getFilterOption() {
		return filterOption;
	}

	public void setFilterOption(String filterOption) {
		this.filterOption = filterOption;
	}

}
