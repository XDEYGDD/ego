package cn.gzsxt.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	
	@RequestMapping("/")
	public String showIndex() {
		System.out.println("请求到首页");
		 return "index";
	}
	
	@RequestMapping("/{page}")
	public String showPage(@PathVariable("page") String page) {
		System.out.println("跳转到page");
		return page;
	}
}
