package listaurant.orm;

import java.sql.ResultSet;
import java.sql.SQLException;

import listaurant.model.TableType;
import listaurant.model.factory.ModelFactory;

import org.springframework.jdbc.core.RowMapper;

public class TableTypeMapper implements RowMapper<TableType>{

	@Override
	public TableType mapRow(ResultSet rs, int rowNum) throws SQLException {
		TableType tType = ModelFactory.getInstance().createTableType(rs.getInt("type_id"));
		tType.setTypeName(rs.getString("type_name"));
		return tType;
	}
}
