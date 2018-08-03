package cn.gzsxt.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gzsxt.common.pojo.EUDataGrid;
import cn.gzsxt.common.pojo.EgoResult;
import cn.gzsxt.manager.service.ItemParmService;

@Controller
@RequestMapping("/item/param")
public class ItemParmController {
	@Autowired
	private ItemParmService parmService;

	@RequestMapping("/query/itemcatid/{itemCatId}")
	@ResponseBody
	public EgoResult getParmByItemCatId(@PathVariable("itemCatId") Long itemCatId) {
		EgoResult result = parmService.getByItemCatId(itemCatId);
		return result;
	}
	
	
	
	
	@RequestMapping("/list")
	@ResponseBody
	public EUDataGrid list(Integer page,Integer rows) {
		EUDataGrid dataGrid = parmService.list(page, rows);
		return dataGrid;
	}
	
	@RequestMapping("/save/{itemCatId}")
	@ResponseBody
	public EgoResult save(@PathVariable("itemCatId")Long itemCatId,String paramData) {
		EgoResult result = parmService.save(itemCatId, paramData);
		return result;
	}
	

}
