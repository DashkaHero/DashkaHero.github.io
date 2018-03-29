package listaurant.dto;

import java.util.ArrayList;
import java.util.List;

import listaurant.model.Member;
import listaurant.model.MenuItem;

public class TogoOrderTO {

	private Integer restaurantId;
	
	private Member member;
	
	private float totalPrice;
	
	private String pickupDateTime;
	
	private List<MenuItem> orderItems = new ArrayList<MenuItem>();
	
	private Integer orderNumber;

	public Integer getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public String getPickupDateTime() {
		return pickupDateTime;
	}

	public void setPickupDateTime(String pickupDateTime) {
		this.pickupDateTime = pickupDateTime;
	}

	public List<MenuItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<MenuItem> orderItems) {
		this.orderItems = orderItems;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}	
	
}
