package listaurant.controller;

import java.util.List;

import javax.annotation.Resource;

import listaurant.common.ListaurantException;
import listaurant.dto.RestaurantSearchTO;
import listaurant.dto.RestaurantReviewsTO;
import listaurant.dto.ReviewDetailTO;
import listaurant.dto.UserSessionTO;
import listaurant.facade.ListaurantFacade;
import listaurant.model.Restaurant;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("userDetail")
public class ReviewController extends ProxyController {

	@Resource
	private ListaurantFacade listaurantFacade;

	@RequestMapping(value = "/review/search", method = RequestMethod.GET)
	public String getSearch(Model model) throws ListaurantException {
		RestaurantSearchTO searchTO = new RestaurantSearchTO();
		listaurantFacade.populateCuisines(searchTO);

		model.addAttribute("restaurantSearchTo", searchTO);
		return "searchreview";
	}

	@RequestMapping(value = "/review/result", method = RequestMethod.POST)
	public String getSearchResult(
			@ModelAttribute("restaurantSearchTo") RestaurantSearchTO searchTO, Model model)
			throws ListaurantException {
		List<Restaurant> restaurantList = listaurantFacade.findRestaurants(searchTO);
		listaurantFacade.populateCuisines(searchTO);
		searchTO.setSortOption("");
		searchTO.setFilterOption("");

		model.addAttribute("restaurantSearchTo", searchTO);
		model.addAttribute("restaurantList", restaurantList);
		return "searchreview";
	}

	@RequestMapping(value = "/review/filtersort", method = RequestMethod.POST)
	public String sortAndFilterReviews(
			@ModelAttribute("restaurantSearchTo") RestaurantSearchTO searchTO, Model model)
			throws ListaurantException {
		List<Restaurant> restaurantList = listaurantFacade.getReviewService().sortAndFilterReviews(
				searchTO);
		listaurantFacade.populateCuisines(searchTO);

		model.addAttribute("restaurantSearchTo", searchTO);
		model.addAttribute("restaurantList", restaurantList);
		return "searchreview";
	}

	@RequestMapping(value = "/review/viewall", method = RequestMethod.GET)
	public String getReviews(@RequestParam("rest") Integer restaurantId, Model model)
			throws ListaurantException {
		RestaurantReviewsTO dto = listaurantFacade.getReviewService().getReviews(restaurantId);

		model.addAttribute("reviewTo", dto);
		return "viewreview";
	}

	@RequestMapping(value = "/review/new", method = RequestMethod.POST)
	public String getNewReview(@ModelAttribute("userDetail") UserSessionTO userDetail,
			@RequestParam("rest") Integer restaurantId, Model model) throws ListaurantException {
		ReviewDetailTO dto = new ReviewDetailTO();
		dto.setRestaurantId(restaurantId);

		model.addAttribute("reviewDetailTo", dto);
		return "newreview";
	}

	@RequestMapping(value = "/review/new/submit", method = RequestMethod.POST)
	public String submitNewReview(@ModelAttribute("userDetail") UserSessionTO userDetail,
			@ModelAttribute("reviewDetailTo") ReviewDetailTO reviewDetailTo, Model model)
			throws ListaurantException {
		reviewDetailTo.setUserId(userDetail.getUserName());
		listaurantFacade.getReviewService().postReview(reviewDetailTo);
		String restaurantId = Integer.toString(reviewDetailTo.getRestaurantId());

		return "redirect:/list/review/viewall?rest=".concat(restaurantId);
	}
}
