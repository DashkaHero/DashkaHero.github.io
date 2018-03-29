package listaurant.controller;

import java.beans.PropertyEditorSupport;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import listaurant.common.ListaurantException;
import listaurant.common.ListaurantUtil;
import listaurant.dto.RestaurantSearchTO;
import listaurant.dto.TogoOrderTO;
import listaurant.dto.UserSessionTO;
import listaurant.facade.ListaurantFacade;
import listaurant.model.MenuItem;
import listaurant.model.Restaurant;
import listaurant.model.factory.ModelFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("userDetail")
public class OrderController extends ProxyController {

	@Resource
	private ListaurantFacade listaurantFacade;

	@RequestMapping(value = "/togo/search", method = RequestMethod.GET)
	public String getSearch(Model model) throws ListaurantException {
		RestaurantSearchTO searchTO = new RestaurantSearchTO();
		listaurantFacade.populateCuisines(searchTO);

		model.addAttribute("restaurantSearchTo", searchTO);
		return "searchtogo";
	}

	@RequestMapping(value = "/togo/result", method = RequestMethod.POST)
	public String getSearchResult(
			@ModelAttribute("restaurantSearchTo") RestaurantSearchTO searchTO, Model model)
			throws ListaurantException {
		List<Restaurant> restaurantList = listaurantFacade.findRestaurants(searchTO);
		listaurantFacade.populateCuisines(searchTO);

		model.addAttribute("restaurantSearchTo", searchTO);
		model.addAttribute("restaurantList", restaurantList);
		return "searchtogo";
	}

	@RequestMapping(value = "/togo/menu", method = RequestMethod.POST)
	public String getMenu(@ModelAttribute("userDetail") UserSessionTO userDetail,
			@RequestParam("rest") Integer restaurantId, Model model) throws ListaurantException {
		Map<String, List<MenuItem>> menuMap = listaurantFacade.getOrderService().getMenu(
				restaurantId);
		TogoOrderTO togoOrderTo = new TogoOrderTO();
		togoOrderTo.setRestaurantId(restaurantId);

		model.addAttribute("menuMap", menuMap);
		model.addAttribute("togoOrderTo", togoOrderTo);
		return "viewmenu";
	}

	@RequestMapping(value = "/togo/order", method = RequestMethod.POST)
	public String getNewOrder(@ModelAttribute("userDetail") UserSessionTO userDetail,
			@ModelAttribute("togoOrderTo") TogoOrderTO togoOrderTo, Model model)
			throws ListaurantException {
		togoOrderTo.setMember(listaurantFacade.getUserBillingInfo(userDetail.getUserName()));

		float totalPrice = 0.0f;
		for (MenuItem item : togoOrderTo.getOrderItems()) {
			totalPrice += item.getPrice();
		}
		togoOrderTo.setTotalPrice(totalPrice);

		model.addAttribute("togoOrderTo", togoOrderTo);
		return "ordertogo";
	}

	@RequestMapping(value = "/togo/order/submit", method = RequestMethod.POST)
	public @ResponseBody
	String submitNewOrder(@ModelAttribute("userDetail") UserSessionTO userDetail,
			@ModelAttribute("togoOrderTo") TogoOrderTO togoOrderTo, Model model)
			throws ListaurantException {
		togoOrderTo.setMember(ModelFactory.getInstance().createMember(userDetail.getUserName(), null));
		togoOrderTo = listaurantFacade.getOrderService().addOrder(togoOrderTo);

		return "Your order has been processed successfully. Your order confirmation number is "
				.concat(togoOrderTo.getOrderNumber().toString())
				.concat(". You can pick up your order at ").concat(togoOrderTo.getPickupDateTime());
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(MenuItem.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				String[] props = text.split(":");
				MenuItem item = null;
				try {
					item = ModelFactory.getInstance().createMenuItem(ListaurantUtil.parseInt(props[0]));
					item.setItemName(props[1]);
					item.setPrice(Float.parseFloat(props[2]));
					item.setItemCategory(props[3]);
				} catch (ListaurantException e) {
					item = null;
				}
				if (item != null)
					setValue(item);
			}
		});
	}
}
