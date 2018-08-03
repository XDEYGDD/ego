package cn.gzsxt.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gzsxt.common.pojo.EUDataGrid;
import cn.gzsxt.common.pojo.EgoResult;
import cn.gzsxt.manager.pojo.Content;
import cn.gzsxt.manager.service.ContentService;

@Controller
@RequestMapping("/content")
public class ContentController {
	
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/query/list")
	@ResponseBody
	public EUDataGrid list(Long categoryId,Integer page,Integer rows) {
		EUDataGrid dataGrid = contentService.list(categoryId, page, rows);
		return  dataGrid;
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public EgoResult update(Content content) {
		EgoResult egoResult = contentService.update(content);
		return egoResult;
	}
}
