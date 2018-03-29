package listaurant.dto;

import java.util.List;

import listaurant.model.Reservation;
import listaurant.model.Restaurant;
import listaurant.model.TableType;

public class ReservationTO {

	private Restaurant restaurant;

	private Reservation reservation;

	private List<TableType> partySizeList;

	public ReservationTO() {
		
	}
	
	public ReservationTO(Restaurant restaurant, Reservation reservation) {
		this.restaurant = restaurant;
		this.reservation = reservation;
	}
	
	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public List<TableType> getPartySizeList() {
		return partySizeList;
	}

	public void setPartySizeList(List<TableType> partySizeList) {
		this.partySizeList = partySizeList;
	}

}
