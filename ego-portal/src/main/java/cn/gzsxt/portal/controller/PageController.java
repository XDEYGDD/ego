package cn.gzsxt.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.gzsxt.common.utils.JsonUtils;
import cn.gzsxt.portal.pojo.ADPicture;
import cn.gzsxt.portal.service.ADService;

@Controller
public class PageController {
	@Autowired
	private ADService ADService;
	
	@RequestMapping("/index")
	public String showIndex(ModelMap map) {
		System.out.println("跳转到首页");
		List<ADPicture> list = ADService.getADS();
		map.addAttribute("ads",JsonUtils.objectToJson(list));
		return "index";
	}

}
