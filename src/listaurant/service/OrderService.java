package listaurant.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import listaurant.common.ListaurantException;
import listaurant.dao.ListaurantDAO;
import listaurant.dto.TogoOrderTO;
import listaurant.model.MenuItem;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
	
	@Resource
	private ListaurantDAO listaurantDAO;

	@Transactional
	public Map<String, List<MenuItem>> getMenu(Integer restaurantId) {
		List<MenuItem> menuItems = listaurantDAO.getMenu(restaurantId);

		HashMap<String, List<MenuItem>> menuMap = new HashMap<String, List<MenuItem>>();
		for (MenuItem item : menuItems) {
			List<MenuItem> itemValues = menuMap.get(item.getItemCategory());
			if (itemValues == null) {
				// create new category to add item
				itemValues = new ArrayList<MenuItem>();
			}
			// add item to category
			itemValues.add(item);
			menuMap.put(item.getItemCategory(), itemValues);
		}

		return menuMap;
	}

	public TogoOrderTO addOrder(TogoOrderTO togoOrderTo) throws ListaurantException {
		// set order pickup time
		Calendar pickupDateTime = Calendar.getInstance();
		pickupDateTime.add(Calendar.MINUTE, 30);
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm a");
		togoOrderTo.setPickupDateTime(sdf.format(pickupDateTime.getTime()));

		// insert order
		Integer orderNumber = listaurantDAO.insertOrder(togoOrderTo.getTotalPrice(),
				togoOrderTo.getPickupDateTime(), togoOrderTo.getMember().getUserId());
		togoOrderTo.setOrderNumber(orderNumber);

		// insert items in order
		listaurantDAO.insertOrderItems(togoOrderTo.getOrderItems(), togoOrderTo.getOrderNumber(),
				togoOrderTo.getRestaurantId());

		return togoOrderTo;
	}
}
