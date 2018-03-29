package listaurant.service;

import javax.annotation.Resource;

import listaurant.common.ListaurantException;
import listaurant.dao.ListaurantDAO;
import listaurant.dto.RegistrationTO;
import listaurant.dto.UserSessionTO;
import listaurant.model.Member;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginService {
	
	@Resource
	private ListaurantDAO listaurantDAO;

	@Transactional
	public UserSessionTO login(UserSessionTO credentials) throws ListaurantException {
		Member member = listaurantDAO.getMemberRecord(credentials.getUserName(),
				credentials.getUserPwd());
		if (member == null) {
			throw new ListaurantException("Username/password is incorrect");
		}

		UserSessionTO userTO = UserSessionTO.createUser(member);
		return userTO;
	}

	@Transactional
	public String registerUser(RegistrationTO registrationTo) throws ListaurantException {
		Member member = registrationTo.getMember();
		String userName = member.getUserId();

		boolean userExists = listaurantDAO.doesUserExists(userName);
		if (userExists) {
			throw new ListaurantException(
					"Username already exists. Please choose a different user name.");
		}

		listaurantDAO.insertMember(member);

		return userName;
	}

	@Transactional
	public Member getUserBillingInfo(String userName) throws ListaurantException {
		Member member = listaurantDAO.getMemberBillingRecord(userName);

		return member;
	}
}
