package listaurant.orm;

import java.sql.ResultSet;
import java.sql.SQLException;

import listaurant.model.Cuisine;
import listaurant.model.factory.ModelFactory;

import org.springframework.jdbc.core.RowMapper;

public class CuisineMapper implements RowMapper<Cuisine> {

	@Override
	public Cuisine mapRow(ResultSet rs, int rowNum) throws SQLException {
		Cuisine cuisine = ModelFactory.getInstance().createCuisine(rs.getInt("cuisine_id"));
		cuisine.setCuisineName(rs.getString("cuisine_name"));
		return cuisine;
	}

}
