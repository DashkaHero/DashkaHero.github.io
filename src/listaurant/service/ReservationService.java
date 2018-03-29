package listaurant.service;

import java.util.List;

import javax.annotation.Resource;

import listaurant.common.ListaurantException;
import listaurant.dao.ListaurantDAO;
import listaurant.dto.ReservationTO;
import listaurant.model.Reservation;
import listaurant.model.Restaurant;
import listaurant.model.TableType;
import listaurant.model.factory.ModelFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservationService {
	
	@Resource
	private ListaurantDAO listaurantDAO;

	@Transactional
	public ReservationTO getNewReservationInfo(Integer restaurantId) throws ListaurantException {
		Restaurant restaurant = listaurantDAO.getRestaurantRecord(restaurantId);
		Reservation reservation = ModelFactory.getInstance().createReservation(null);
		reservation.setRestaurantId(restaurantId);
		ReservationTO reservationTo = new ReservationTO(restaurant, reservation);

		List<TableType> tTypes = listaurantDAO.getTableTypes();
		reservationTo.setPartySizeList(tTypes);

		return reservationTo;
	}

	@Transactional
	public Integer addReservation(ReservationTO reservationTo) throws ListaurantException {
		Reservation reservation = reservationTo.getReservation();
		String reserveDateTime = reservation.getReserveDate().concat(" ")
				.concat(reservation.getReserveTime());

		Integer availTables = listaurantDAO.findAvailableTables(reservation.getRestaurantId(),
				reservation.getTableType(), reserveDateTime);
		if (availTables <= 0) {
			throw new ListaurantException(
					"Sorry, but no tables are available for this party size at this "
							+ "reservation time. Please choose a different date/time.");
		}

		Integer reservationNum = listaurantDAO.insertReservation(reservation);

		return reservationNum;
	}

	@Transactional
	public List<Reservation> getUserReservations(String userName) {
		List<Reservation> reservations = listaurantDAO.getUserReservations(userName);

		return reservations;
	}

	@Transactional
	public ReservationTO getReservationDetail(Integer reservationNumber) {
		Reservation reservation = listaurantDAO.getReservationRecord(reservationNumber);
		Restaurant restaurant = listaurantDAO.getRestaurantRecord(reservation.getRestaurantId());
		ReservationTO reservationTo = new ReservationTO(restaurant, reservation);
		
		List<TableType> tTypes = listaurantDAO.getTableTypes();
		reservationTo.setPartySizeList(tTypes);

		return reservationTo;
	}

	@Transactional
	public void editReservation(ReservationTO reservationTo) throws ListaurantException {
		Reservation reservation = reservationTo.getReservation();
		listaurantDAO.updateReservation(reservation);
	}

	@Transactional
	public void cancelReservation(ReservationTO reservationTo) throws ListaurantException {
		Reservation reservation = reservationTo.getReservation();
		listaurantDAO.deleteReservation(reservation);
	}
}
