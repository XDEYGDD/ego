package cn.gzsxt.manager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gzsxt.common.pojo.EUTreeNode;
import cn.gzsxt.manager.mapper.ItemCatMapper;
import cn.gzsxt.manager.pojo.ItemCat;
import cn.gzsxt.manager.pojo.ItemCatExample;
import cn.gzsxt.manager.pojo.ItemCatExample.Criteria;
import cn.gzsxt.manager.service.ItemCatService;

@Service
public class ItemCatServiceimpl implements ItemCatService {
	
	@Autowired
	private ItemCatMapper itemCatMapper;

	@Override
	public List<EUTreeNode> getNodeByParentId(Long id) {
		
		ItemCatExample example = new ItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(id);
		
		List<ItemCat> list = itemCatMapper.selectByExample(example);
		List<EUTreeNode> treeNodes = null;
		if(null != list && list.size() >0) {
			treeNodes = new ArrayList<>(); 
			EUTreeNode node = null;
			for (ItemCat itemCat : list) {
				node = new EUTreeNode();
				node.setId(itemCat.getId());
				node.setText(itemCat.getName());
				node.setState(itemCat.getIsParent()?"closed":"open");
				treeNodes.add(node);
			}
		}
		
		return treeNodes;
	}

}
