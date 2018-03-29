package listaurant.model;

import java.io.Serializable;

import listaurant.orm.MenuMapper;

import org.springframework.jdbc.core.RowMapper;

public class MenuItem extends ListaurantModel implements Serializable {

	private static final long serialVersionUID = 956632516054308291L;

	private Integer itemCode;

	private String itemName;

	private String description;

	private float price;

	private String itemCategory;
	
	private Integer quantity;

	public Integer getItemCode() {
		return itemCode;
	}

	public void setItemCode(Integer itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public RowMapper<MenuItem> map() {
		return new MenuMapper();
	}
	
	@Override
	public String toString() {
		return itemCode + ":" + itemName + ":" + price + ":" + itemCategory;
	}
	
}
