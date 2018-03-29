package listaurant.orm;

import java.sql.ResultSet;
import java.sql.SQLException;

import listaurant.model.MenuItem;
import listaurant.model.factory.ModelFactory;

import org.springframework.jdbc.core.RowMapper;

public class MenuMapper implements RowMapper<MenuItem>{

	@Override
	public MenuItem mapRow(ResultSet rs, int rowNum) throws SQLException {
		MenuItem item = ModelFactory.getInstance().createMenuItem(rs.getInt("item_code"));
		item.setItemName(rs.getString("item_name"));
		item.setDescription(rs.getString("description"));
		item.setItemCategory(rs.getString("item_category"));
		item.setPrice(rs.getFloat("price"));
		
		return item;
	}

}
