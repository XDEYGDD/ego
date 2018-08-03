package cn.gzsxt.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.gzsxt.manager.pojo.Item;
import cn.gzsxt.portal.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService; 
	
	@RequestMapping("/add/{num}/{itemId}")
	public String addItem(@PathVariable("num") Integer num,@PathVariable("itemId") Long itemId,
			HttpServletRequest request,HttpServletResponse response,ModelMap map) {
		List<Item> list = cartService.addItem(num, itemId, response, request);
		map.addAttribute("cartList",list);
		return "cart";
	}
	
	
	@RequestMapping("/update/{itemId}/{num}")
	public String update(@PathVariable("num") Integer num,@PathVariable("itemId") Long itemId,
			HttpServletRequest request,HttpServletResponse response,ModelMap map) {
		List<Item> list = cartService.update(itemId, num, request, response);
		map.addAttribute("cartList",list);
		return "cart";
	}
	
	
	@RequestMapping("/delete/{itemId}")
	public String update(@PathVariable("itemId") Long itemId,
			HttpServletRequest request,HttpServletResponse response,ModelMap map) {
		List<Item> list = cartService.delete(itemId, request, response);
		map.addAttribute("cartList",list);
		return "cart";
	}
	
	
	@RequestMapping("/cart")
	public String show(
			HttpServletRequest request,ModelMap map) {
		List<Item> list = cartService.show(request);
		map.addAttribute("cartList",list);
		return "order-cart";
	}
	
}
