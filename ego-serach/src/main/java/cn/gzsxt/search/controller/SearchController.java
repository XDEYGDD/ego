package cn.gzsxt.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gzsxt.common.pojo.SerachResult;
import cn.gzsxt.search.service.SearchService;

@Controller
public class SearchController {
	@Autowired
	private SearchService searchService;
	
	@RequestMapping("/query")
	@ResponseBody
	public SerachResult query(String q,Integer page) {
//		q = new String(q.getBytes("iso-8859-1"),"UTF-8");
		
		
		SerachResult result = searchService.query(q, page);
		
		return result;
	}
}
