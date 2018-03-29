package listaurant.dto;

import listaurant.model.Member;

public class UserSessionTO {

	private String userName;

	private String userPwd;

	private String firstName;

	private String middleName;

	private String lastName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserPwd() {
		return userPwd;
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

	public static UserSessionTO createUser(Member member) {
		UserSessionTO user = new UserSessionTO();

		user.setUserName(member.getUserId());
		user.setFirstName(member.getFirstName());
		user.setMiddleName(member.getMiddleName());
		user.setLastName(member.getLastName());

		return user;
	}

}
