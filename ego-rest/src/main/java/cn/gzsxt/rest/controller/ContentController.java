package cn.gzsxt.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gzsxt.manager.pojo.Content;
import cn.gzsxt.rest.service.ContentService;

@Controller
@RequestMapping(value="/content")
public class ContentController {


	@Autowired
	private ContentService contentService;
	
	
	
	@RequestMapping("/{categoryId}")
	@ResponseBody
	public List<Content> list(@PathVariable("categoryId") Long categoryId){
		List<Content> list = contentService.getByContentCatId(categoryId);
		return list;
	}
	
	
}
