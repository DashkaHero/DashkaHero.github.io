package listaurant.model;

import java.io.Serializable;

import listaurant.orm.ReservationMapper;

import org.springframework.jdbc.core.RowMapper;

public class Reservation extends ListaurantModel implements Serializable {

	private static final long serialVersionUID = 4926814813492328556L;

	private Integer reservationNumber;

	private Integer restaurantId;

	private Integer tableType;

	private String userId;

	private String reserveDate;
	
	private String reserveTime;

	private String restaurantName;

	private boolean isExpired;

	public Integer getReservationNumber() {
		return reservationNumber;
	}

	public void setReservationNumber(Integer reservationNumber) {
		this.reservationNumber = reservationNumber;
	}

	public Integer getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}

	public Integer getTableType() {
		return tableType;
	}

	public void setTableType(Integer tableType) {
		this.tableType = tableType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getReserveDate() {
		return reserveDate;
	}

	public void setReserveDate(String reserveDate) {
		this.reserveDate = reserveDate;
	}

	public String getReserveTime() {
		return reserveTime;
	}

	public void setReserveTime(String reserveTime) {
		this.reserveTime = reserveTime;
	}
	
	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public boolean isExpired() {
		return isExpired;
	}

	public void setExpired(boolean isExpired) {
		this.isExpired = isExpired;
	}

	
	public RowMapper<Reservation> map() {
		return new ReservationMapper();
	}
}
