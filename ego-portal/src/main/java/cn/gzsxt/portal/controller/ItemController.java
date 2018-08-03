package cn.gzsxt.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.gzsxt.manager.pojo.Item;
import cn.gzsxt.portal.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/{itemId}")
	public String shoItem(@PathVariable("itemId") Long id,ModelMap map) {
		Item item = itemService.getItemById(id);
		if( null!=item) {
			map.addAttribute("item",item);
			return "item";
		}else {
			map.addAttribute("message", "该商品已下架.点击返回首页");
			return "error/exception";
		}
	}
}
