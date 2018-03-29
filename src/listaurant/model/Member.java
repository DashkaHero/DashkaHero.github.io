package listaurant.model;

import java.io.Serializable;

import listaurant.orm.MemberBillingMapper;
import listaurant.orm.MemberMapper;

import org.springframework.jdbc.core.RowMapper;

public class Member extends ListaurantModel implements Serializable {

	private static final long serialVersionUID = -9175281480564523747L;

	private String userId;

	private String userPwd;

	private String firstName;

	private String middleName;

	private String lastName;

	private String streetAddress;

	private String city;

	private String state;

	private Integer zip;

	private String phone;

	private String email;

	private String ccNumber;

	private String cardType;

	private Integer ccExpiryYear;

	private Integer ccExpiryMonth;

	private String ccHolderName;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddr) {
		this.streetAddress = streetAddr;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getZip() {
		return zip;
	}

	public void setZip(Integer zip) {
		this.zip = zip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCcNumber() {
		return ccNumber;
	}

	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public Integer getCcExpiryYear() {
		return ccExpiryYear;
	}

	public void setCcExpiryYear(Integer ccExpiryYear) {
		this.ccExpiryYear = ccExpiryYear;
	}

	public Integer getCcExpiryMonth() {
		return ccExpiryMonth;
	}

	public void setCcExpiryMonth(Integer ccExpiryMonth) {
		this.ccExpiryMonth = ccExpiryMonth;
	}

	public String getCcHolderName() {
		return ccHolderName;
	}

	public void setCcHolderName(String ccHolderName) {
		this.ccHolderName = ccHolderName;
	}
	
	public RowMapper<Member> map() {
		return new MemberMapper();
	}
	
	public RowMapper<Member> mapBilling() {
		return new MemberBillingMapper();
	}
}
