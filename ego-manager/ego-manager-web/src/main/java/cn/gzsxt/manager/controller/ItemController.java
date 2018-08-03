package cn.gzsxt.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gzsxt.common.pojo.EUDataGrid;
import cn.gzsxt.common.pojo.EgoResult;
import cn.gzsxt.manager.pojo.Item;
import cn.gzsxt.manager.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;

	@RequestMapping("/query")
	@ResponseBody
	public Item getById(Long id) {
		Item item = itemService.getById(id);
		return item;
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public EUDataGrid list(Integer page,Integer rows) {
		EUDataGrid dataGrid = itemService.list(page, rows);
		return dataGrid;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public EgoResult save(Item item,String desc,String itemParams) {
		EgoResult result = itemService.save(item, desc, itemParams);
		return result;
	}
}
