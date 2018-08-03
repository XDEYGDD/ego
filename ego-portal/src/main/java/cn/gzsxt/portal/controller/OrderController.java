package cn.gzsxt.portal.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.gzsxt.common.pojo.EgoResult;
import cn.gzsxt.common.utils.JsonUtils;
import cn.gzsxt.manager.pojo.Item;
import cn.gzsxt.portal.pojo.Order;
import cn.gzsxt.portal.service.OrderService;
import cn.gzsxt.portal.utils.CookieUtils;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Value("${EGO_ITEM_CART}")
	private String EGO_ITEM_CART;
	
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/order-cart")
	public String showOrder(HttpServletRequest request,ModelMap map) {
		
		List<Item> jsonToList = null;
		String cookieValue = CookieUtils.getCookieValue(request, EGO_ITEM_CART);
		if( null!=cookieValue && !"".equals(cookieValue)) {
			jsonToList= JsonUtils.jsonToList(cookieValue,Item.class);
		}else {
			jsonToList = new ArrayList<>();
		}
		map.addAttribute("cartList", jsonToList);
		return "order-cart";
	}
	
	@RequestMapping("/create")
	public String save(Order order,ModelMap map) {
		EgoResult result = orderService.save(order);
		if( 200 ==result.getStatus()) {
			map.addAttribute("orderId",result.getData());
			map.addAttribute("payment", order.getPayment());
			Calendar c = Calendar.getInstance();
			SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd/E");
			c.add(Calendar.DATE, 3);
			map.addAttribute("date", df.format(c.getTime()));
			return "success";
		}else {
			map.addAttribute("message", "失败");
			return "error/exception";
		}
		
		
	}
	
	
}
