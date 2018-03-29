package listaurant.service;

import java.util.List;

import javax.annotation.Resource;

import listaurant.common.ListaurantException;
import listaurant.dao.ListaurantDAO;
import listaurant.dto.RestaurantSearchTO;
import listaurant.model.Cuisine;
import listaurant.model.Restaurant;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RestaurantService {

	@Resource
	private ListaurantDAO listaurantDAO;

	@Transactional
	public List<Cuisine> getCuisineList() throws ListaurantException {
		List<Cuisine> cuisineList = listaurantDAO.getCuisineList();

		return cuisineList;
	}

	@Transactional
	public List<Restaurant> findRestaurants(RestaurantSearchTO searchTO) throws ListaurantException {
		List<Restaurant> restaurantList = listaurantDAO.getRestaurants(searchTO.getRestName(),
				searchTO.getCuisine(), searchTO.getNear());

		return restaurantList;
	}
}
