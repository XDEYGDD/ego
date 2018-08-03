package cn.gzsxt.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gzsxt.common.pojo.EUTreeNode;
import cn.gzsxt.common.pojo.EgoResult;
import cn.gzsxt.manager.service.ContentCatgoryService;

@Controller
@RequestMapping("/content")
public class ContentCategoryController {

	@Autowired
	private ContentCatgoryService contentCatgoryService;
	
	@RequestMapping("/category/list")
	@ResponseBody
	public List<EUTreeNode> getNodes(@RequestParam(value="id",defaultValue="0") Long parent_id){
		List<EUTreeNode> list = contentCatgoryService.getNodeByParentId(parent_id);
		return list;
	}
	
	@RequestMapping("/category/create")
	@ResponseBody
	public EgoResult addNodes(String name,Long parentId) {
		EgoResult result = contentCatgoryService.addNodes(name, parentId);
			return result;
	}
	
	
}
