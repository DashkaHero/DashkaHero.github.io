package listaurant.model;

import java.io.Serializable;

import listaurant.orm.TableTypeMapper;

import org.springframework.jdbc.core.RowMapper;

public class TableType extends ListaurantModel implements Serializable {

	private static final long serialVersionUID = 6959444175421613347L;

	private Integer typeId;

	private String typeName;

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public RowMapper<TableType> map() {
		return new TableTypeMapper();
	}
}
