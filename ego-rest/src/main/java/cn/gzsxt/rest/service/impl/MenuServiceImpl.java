package cn.gzsxt.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gzsxt.manager.mapper.ItemCatMapper;
import cn.gzsxt.manager.pojo.ItemCat;
import cn.gzsxt.manager.pojo.ItemCatExample;
import cn.gzsxt.manager.pojo.ItemCatExample.Criteria;
import cn.gzsxt.rest.pojo.Menu;
import cn.gzsxt.rest.pojo.MenuNode;
import cn.gzsxt.rest.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private ItemCatMapper itemCatMapper;
	
	@Override
	public Menu initMenu() {
		/**
		 * 1取出根节点， parent_id = 0
		 * 
		 * 2.遍历根节点，找根节
		 * 点的子节点 parent_id=0 当前的节点的id
		 * 
		 * 3继续找子节点 ，直到子节点 为叶子节点
		 */
		List list = getNodeByParentId(0L);
		Menu menu = new Menu();
		menu.setData(list);
		return menu;
	}

	
	public List getNodeByParentId(Long parentId) {
		ItemCatExample example = new ItemCatExample();
		 Criteria criteria = example.createCriteria();
		 criteria.andParentIdEqualTo(parentId);
		 List<ItemCat> list = itemCatMapper.selectByExample(example);
		 
		 MenuNode node = null;
		 List nodes = new ArrayList<>();
		 
		 if(null !=list && list.size()>0) {
			 for (ItemCat itemCat : list) {
				 if(itemCat.getIsParent()) {
					 node = new MenuNode();
					 node.setU("/products/"+itemCat.getId()+".html");
					 node.setN("<a href='"+node.getU()+"'>"+itemCat.getName()+"</a>");
					 node.setI(getNodeByParentId(itemCat.getId()));
					 nodes.add(node);
				 }else {
					 nodes.add("/products/"+itemCat.getId()+".html|"+itemCat.getName());
				 }
			}
		 }
		 
		
		return nodes;
	}
	
}
