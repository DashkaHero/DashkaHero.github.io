package listaurant.dao;

import static listaurant.common.ListaurantConstants.SQL_PERCENT;
import static listaurant.common.ListaurantUtil.isNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import listaurant.common.ListaurantUtil;
import listaurant.model.Cuisine;
import listaurant.model.Member;
import listaurant.model.MenuItem;
import listaurant.model.Reservation;
import listaurant.model.Restaurant;
import listaurant.model.Review;
import listaurant.model.TableType;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class ListaurantDAO {
	
	@Resource(name = "jdbcConfig")
	private JdbcConfig jdbcConfig;
	
	@PostConstruct
	public void initConfig() {
		jdbcConfig.init();
	}
	
	private NamedParameterJdbcTemplate getJdbcTemplate() {
		return jdbcConfig.getJdbcTemplate();
	}

	private String getQuery(String queryName) {
		return jdbcConfig.getQuery(queryName);
	}
	
	public List<Cuisine> getCuisineList() {
		String sql = "listaurant.cuisine.select";
		return getJdbcTemplate().getJdbcOperations().query(getQuery(sql), new Cuisine().map());
	}

	public List<Restaurant> getRestaurants(String restName, Integer cuisineId, String location) {
		List<Restaurant> restaurantList = new ArrayList<Restaurant>();

		String sql = "listaurant.restaurant.select";
		Map<String, Object> parameters = new HashMap<String, Object>();
		if (!isNull(restName)) {
			parameters.put("restName", restName.concat(SQL_PERCENT));
		} else {
			parameters.put("restName", SQL_PERCENT);
		}

		if (cuisineId != 0) {
			parameters.put("cuisineId", cuisineId);
		} else {
			parameters.put("cuisineId", SQL_PERCENT);
		}

		if (!isNull(location)) {
			parameters.put("loc", SQL_PERCENT.concat(location).concat(SQL_PERCENT));
			parameters.put("state", location.concat(SQL_PERCENT));
			parameters.put("zip", location);
		} else {
			parameters.put("loc", SQL_PERCENT);
			parameters.put("state", SQL_PERCENT);
			parameters.put("zip", SQL_PERCENT);
		}

		restaurantList = getJdbcTemplate().query(getQuery(sql), parameters, new Restaurant().map());
		return restaurantList;
	}

	public Member getMemberRecord(String userName, String pwd) {
		Member member = null;

		String sql = "listaurant.member.select";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("userName", userName);
		parameters.put("pwd", pwd);
		try {
			member = getJdbcTemplate().queryForObject(getQuery(sql), parameters, new Member().map());
		} catch (EmptyResultDataAccessException dae) {
			// return null
		}

		return member;
	}

	public Member getMemberBillingRecord(String userName) {
		String sql = "listaurant.member.billing.select";
		SqlParameterSource parameters = new MapSqlParameterSource("userName", userName);

		return getJdbcTemplate().queryForObject(getQuery(sql), parameters, new Member().mapBilling());
	}

	public Restaurant getRestaurantRecord(Integer restaurantId) {
		String sql = "listaurant.restaurant.header.select";
		SqlParameterSource parameters = new MapSqlParameterSource("restId", restaurantId);

		return getJdbcTemplate().queryForObject(getQuery(sql), parameters, new Restaurant().map());
	}

	public List<TableType> getTableTypes() {
		String sql = "listaurant.tabletype.select";
		return getJdbcTemplate().getJdbcOperations().query(getQuery(sql), new TableType().map());
	}

	public Integer findAvailableTables(Integer restaurantId, Integer partySize,
			String reserveDateTime) {
		String sql = "listaurant.tabletype.available.select";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("restId", restaurantId);
		parameters.put("typeId", partySize);
		parameters.put("reserveDT", reserveDateTime);

		return getJdbcTemplate().queryForInt(getQuery(sql), parameters);
	}

	public Integer insertReservation(Reservation reservation) {
		String sql = "listaurant.reservation.insert";

		SqlParameterSource parameters = new BeanPropertySqlParameterSource(reservation);
		KeyHolder keyHolder = new GeneratedKeyHolder();

		getJdbcTemplate().update(getQuery(sql), parameters, keyHolder);
		return keyHolder.getKey().intValue();
	}

	public List<Reservation> getUserReservations(String userName) {
		String sql = "listaurant.reservation.select";
		SqlParameterSource parameters = new MapSqlParameterSource("userName", userName);

		return getJdbcTemplate().query(getQuery(sql), parameters, new Reservation().map());
	}

	public Reservation getReservationRecord(Integer reservationNumber) {
		String sql = "listaurant.reservation.existing.select";
		SqlParameterSource parameters = new MapSqlParameterSource("reservationNumber",
				reservationNumber);

		return getJdbcTemplate().queryForObject(getQuery(sql), parameters, new Reservation().map());
	}

	public void updateReservation(Reservation reservation) {
		String sql = "listaurant.reservation.update";

		SqlParameterSource parameters = new BeanPropertySqlParameterSource(reservation);
		getJdbcTemplate().update(getQuery(sql), parameters);
	}

	public void deleteReservation(Reservation reservation) {
		String sql = "listaurant.reservation.delete";

		SqlParameterSource parameters = new MapSqlParameterSource("reservationNumber",
				reservation.getReservationNumber());
		getJdbcTemplate().update(getQuery(sql), parameters);
	}

	public boolean doesUserExists(String userName) {
		boolean exists = false;

		String sql = "listaurant.member.exists.select";
		SqlParameterSource parameter = new MapSqlParameterSource("userName", userName);
		try {
			getJdbcTemplate().queryForInt(getQuery(sql), parameter);
			exists = true;
		} catch (EmptyResultDataAccessException dae) {
			// no record found
			exists = false;
		}

		return exists;
	}

	public void insertMember(Member member) {
		String sql = "listaurant.member.insert";

		SqlParameterSource userParam = new BeanPropertySqlParameterSource(member);
		getJdbcTemplate().update(getQuery(sql), userParam);

		if (!ListaurantUtil.isNull(member.getCcNumber())) {
			sql = "listaurant.member.billing.update";
			SqlParameterSource creditParam = new BeanPropertySqlParameterSource(member);
			getJdbcTemplate().update(getQuery(sql), creditParam);
		}
	}

	public List<Restaurant> getRestaurantsByReviews(String restName, Integer cuisineId,
			String location, String sortOption, String filterOption) {
		List<Restaurant> restaurantList = new ArrayList<Restaurant>();

		StringBuffer sql = new StringBuffer(getQuery("listaurant.review.restaurant.select"));
		Map<String, Object> parameters = new HashMap<String, Object>();

		if (!isNull(restName)) {
			parameters.put("restName", restName.concat(SQL_PERCENT));
		} else {
			parameters.put("restName", SQL_PERCENT);
		}

		if (cuisineId != 0) {
			parameters.put("cuisineId", cuisineId);
		} else {
			parameters.put("cuisineId", SQL_PERCENT);
		}

		if (!isNull(location)) {
			parameters.put("loc", SQL_PERCENT.concat(location).concat(SQL_PERCENT));
			parameters.put("state", location.concat(SQL_PERCENT));
			parameters.put("zip", location);
		} else {
			parameters.put("loc", SQL_PERCENT);
			parameters.put("state", SQL_PERCENT);
			parameters.put("zip", SQL_PERCENT);
		}

		if (!isNull(filterOption)) {
			sql.append(" WHERE round(temp.avg_rating) >= :filterOption");
			parameters.put("filterOption", filterOption);
		}

		if (!isNull(sortOption)) {
			sql.append(" ORDER BY ").append(sortOption).append(" DESC");
		}

		restaurantList = getJdbcTemplate().query(sql.toString(), parameters, new Restaurant().map());
		return restaurantList;
	}

	public List<Review> getRestaurantReviews(Integer restaurantId) {
		String sql = "listaurant.review.select";

		SqlParameterSource parameters = new MapSqlParameterSource("restId", restaurantId);

		return getJdbcTemplate().query(getQuery(sql), parameters, new Review().map());
	}

	public void insertReview(Review review) {
		String sql = "listaurant.review.insert";

		SqlParameterSource parameters = new BeanPropertySqlParameterSource(review);
		getJdbcTemplate().update(getQuery(sql), parameters);
	}

	public List<MenuItem> getMenu(Integer restaurantId) {
		String sql = "listaurant.menu.select";
		SqlParameterSource parameters = new MapSqlParameterSource("restId", restaurantId);

		return getJdbcTemplate().query(getQuery(sql), parameters, new MenuItem().map());
	}

	public Integer insertOrder(float totalPrice, String pickupDateTime, String userId) {
		String sql = "listaurant.togoorder.insert";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("totalPrice", totalPrice);
		paramMap.put("pickupDateTime", pickupDateTime);
		paramMap.put("userId", userId);
		SqlParameterSource parameters = new MapSqlParameterSource(paramMap);

		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(getQuery(sql), parameters, keyHolder);

		return keyHolder.getKey().intValue();
	}

	public void insertOrderItems(List<MenuItem> orderItems, Integer orderNumber,
			Integer restaurantId) {
		String sql = "listaurant.togoorder.item.insert";
		
		SqlParameterSource[] batchParams = new SqlParameterSource[orderItems.size()];
		int iter = 0;
		for (MenuItem item : orderItems) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("orderNumber", orderNumber);
			paramMap.put("restaurantId", restaurantId);
			paramMap.put("itemCode", item.getItemCode());
			paramMap.put("quantity", item.getQuantity());
			
			batchParams[iter++] = new MapSqlParameterSource(paramMap);			
		}
		
		getJdbcTemplate().batchUpdate(getQuery(sql), batchParams);		
	}
	
}
