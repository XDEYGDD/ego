package cn.gzsxt.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gzsxt.common.utils.JsonUtils;
import cn.gzsxt.rest.pojo.Menu;
import cn.gzsxt.rest.service.MenuService;

@Controller
public class ItemCatController {
	@Autowired
	private MenuService menuService; 
	
	@RequestMapping(value = "/item/all",produces=MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public String initMenu(String callback) {
		Menu menu = menuService.initMenu();
		String jsonMenu = JsonUtils.objectToJson(menu);
		return callback+"("+jsonMenu+")";
	}
}
