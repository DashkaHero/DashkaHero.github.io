package listaurant.controller;

import javax.annotation.Resource;

import listaurant.common.ListaurantException;
import listaurant.dto.RegistrationTO;
import listaurant.dto.UserSessionTO;
import listaurant.facade.ListaurantFacade;
import listaurant.model.factory.ModelFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("userDetail")
public class LoginController extends ProxyController {

	@Resource
	private ListaurantFacade listaurantFacade;

	@RequestMapping(value = "/showlogin", method = RequestMethod.GET)
	public String getLogin(Model model, SessionStatus status) throws ListaurantException {
		status.setComplete();
		model.addAttribute("userTo", new UserSessionTO());
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String submitLogin(@ModelAttribute("memberAccount") UserSessionTO credentials, Model model)
			throws ListaurantException {
		UserSessionTO userDetailTO = listaurantFacade.getLoginService().login(credentials);

		model.addAttribute("userDetail", userDetailTO);
		return "logout";
	}

	@RequestMapping(value = "/newuser", method = RequestMethod.GET)
	public String getRegistration(Model model) throws ListaurantException {
		RegistrationTO registrationTo = new RegistrationTO(ModelFactory.getInstance().createMember(null,
				null));
		model.addAttribute("registrationTo", registrationTo);
		return "registermember";
	}

	@RequestMapping(value = "/newuser/submit", method = RequestMethod.POST)
	public @ResponseBody
	String submitRegistration(@ModelAttribute("registrationTo") RegistrationTO registrationTo)
			throws ListaurantException {
		String userName = listaurantFacade.getLoginService().registerUser(registrationTo);
		return "Thank you for registering with Listaurant. Your username is "
				.concat(userName)
				.concat(". Please log in to make your reservations and write reviews about restaurants.");
	}

}
