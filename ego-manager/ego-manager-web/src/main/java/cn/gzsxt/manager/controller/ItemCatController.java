package cn.gzsxt.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gzsxt.common.pojo.EUTreeNode;
import cn.gzsxt.manager.service.ItemCatService;

@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
	@Autowired
	private ItemCatService itemCatService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<EUTreeNode> initTree(@RequestParam(value="id",defaultValue="0") Long parentID){
		List<EUTreeNode> list = itemCatService.getNodeByParentId(parentID);
		return list;
	}
}
