package listaurant.orm;

import java.sql.ResultSet;
import java.sql.SQLException;

import listaurant.model.Reservation;
import listaurant.model.factory.ModelFactory;

import org.springframework.jdbc.core.RowMapper;

public class ReservationMapper implements RowMapper<Reservation> {

	@Override
	public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
		Reservation reservation = ModelFactory.getInstance().createReservation(rs.getInt("reservation_number"));
		reservation.setReserveDate(rs.getString("reserve_date"));
		reservation.setReserveTime(rs.getString("reserve_time"));
		reservation.setRestaurantName(rs.getString("restaurant_name"));
		reservation.setRestaurantId(rs.getInt("restaurant_id"));
		reservation.setTableType(rs.getInt("type_id"));
		reservation.setExpired(rs.getBoolean("expired"));
		
		return reservation;
	}

}
