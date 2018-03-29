package listaurant.controller;

import java.util.List;

import javax.annotation.Resource;

import listaurant.common.ListaurantException;
import listaurant.dto.ReservationTO;
import listaurant.dto.RestaurantSearchTO;
import listaurant.dto.UserSessionTO;
import listaurant.facade.ListaurantFacade;
import listaurant.model.Reservation;
import listaurant.model.Restaurant;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("userDetail")
public class ReservationController extends ProxyController {
	
	@Resource
	private ListaurantFacade listaurantFacade;

	@RequestMapping(value = "/reserve/search", method = RequestMethod.GET)
	public String getSearch(Model model) throws ListaurantException {
		RestaurantSearchTO searchTO = new RestaurantSearchTO();
		listaurantFacade.populateCuisines(searchTO);

		model.addAttribute("restaurantSearchTo", searchTO);
		return "searchrestaurant";
	}

	@RequestMapping(value = "/reserve/result", method = RequestMethod.POST)
	public String getSearchResult(@ModelAttribute("restaurantSearchTo") RestaurantSearchTO searchTO,
			Model model) throws ListaurantException {
		List<Restaurant> restaurantList = listaurantFacade.findRestaurants(searchTO);
		listaurantFacade.populateCuisines(searchTO);

		model.addAttribute("restaurantSearchTo", searchTO);
		model.addAttribute("restaurantList", restaurantList);
		return "searchrestaurant";
	}

	@RequestMapping(value = "/reserve/new", method = RequestMethod.GET)
	public String getNewReservation(@ModelAttribute("userDetail") UserSessionTO userDetail,
			@RequestParam("rest") Integer restaurantId, Model model) throws ListaurantException {
		ReservationTO dto = listaurantFacade.getReservationService().getNewReservationInfo(restaurantId);

		model.addAttribute("reservationTo", dto);
		return "makereservation";
	}

	@RequestMapping(value = "/reserve/new/submit", method = RequestMethod.POST)
	public @ResponseBody
	String submitNewReservation(@ModelAttribute("userDetail") UserSessionTO userDetail,
			@ModelAttribute("reservationTo") ReservationTO reservationTo)
			throws ListaurantException {
		Reservation reservation = reservationTo.getReservation();
		reservation.setUserId(userDetail.getUserName());
		reservationTo.setReservation(reservation);
		Integer reservationNum = listaurantFacade.getReservationService().addReservation(reservationTo);

		return "Your table has been reserved. Your reservation number is ".concat(
				Integer.toString(reservationNum)).concat(
				". Please use this reservation number to change or cancel your reservation.");
	}

	@RequestMapping(value = "/reserve/viewall", method = RequestMethod.GET)
	public String getUserReservations(@ModelAttribute("userDetail") UserSessionTO userDetail, Model model)
			throws ListaurantException {
		List<Reservation> reservationList = listaurantFacade.getReservationService().getUserReservations(userDetail
				.getUserName());

		model.addAttribute("reservationList", reservationList);
		return "myreservations";
	}

	@RequestMapping(value = "/reserve/edit", method = RequestMethod.POST)
	public String getReservationForEdit(@ModelAttribute("userDetail") UserSessionTO userDetail,
			@RequestParam("rsvNo") Integer reservationNumber, Model model) throws ListaurantException {
		ReservationTO dto = listaurantFacade.getReservationService().getReservationDetail(reservationNumber);

		model.addAttribute("reservationTo", dto);
		return "editreservation";
	}
	
	@RequestMapping(value = "/reserve/edit/submit", method = RequestMethod.POST)
	public @ResponseBody
	String editReservation(@ModelAttribute("userDetail") UserSessionTO userDetail,
			@ModelAttribute("reservationTo") ReservationTO reservationTo)
			throws ListaurantException {
		Reservation reservation = reservationTo.getReservation();
		reservation.setUserId(userDetail.getUserName());
		reservationTo.setReservation(reservation);
		listaurantFacade.getReservationService().editReservation(reservationTo);

		return "Your reservation has been updated successfully";
	}
	
	@RequestMapping(value = "/reserve/cancel", method = RequestMethod.POST)
	public @ResponseBody
	String cancelReservation(@ModelAttribute("userDetail") UserSessionTO userDetail,
			@ModelAttribute("reservationTo") ReservationTO reservationTo)
			throws ListaurantException {
		Reservation reservation = reservationTo.getReservation();
		reservation.setUserId(userDetail.getUserName());
		reservationTo.setReservation(reservation);
		listaurantFacade.getReservationService().cancelReservation(reservationTo);

		return "Your reservation has been cancelled successfully";
	}
}
