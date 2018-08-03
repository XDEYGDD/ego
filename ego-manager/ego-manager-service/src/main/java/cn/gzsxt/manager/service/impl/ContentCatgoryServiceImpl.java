package cn.gzsxt.manager.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gzsxt.common.pojo.EUTreeNode;
import cn.gzsxt.common.pojo.EgoResult;
import cn.gzsxt.manager.mapper.ContentCategoryMapper;
import cn.gzsxt.manager.pojo.ContentCategory;
import cn.gzsxt.manager.pojo.ContentCategoryExample;
import cn.gzsxt.manager.pojo.ContentCategoryExample.Criteria;
import cn.gzsxt.manager.service.ContentCatgoryService;

@Service
public class ContentCatgoryServiceImpl implements ContentCatgoryService {

	@Autowired
	private ContentCategoryMapper contentCategoryMapper;
	
	@Override
	public List<EUTreeNode> getNodeByParentId(Long id) {
		ContentCategoryExample example = new ContentCategoryExample();
		
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(id);
		
		List<EUTreeNode> nodes = new ArrayList<>();
		List<ContentCategory> list = contentCategoryMapper.selectByExample(example);
		EUTreeNode node = null;
		if( null!=list && list.size()>0) {
			for (ContentCategory contentCategory : list) {
				node = new EUTreeNode();
				node.setId(contentCategory.getId());
				node.setText(contentCategory.getName());
				node.setState(contentCategory.getIsParent()?"closed":"open");
				nodes.add(node);
			}
		}
		
		return nodes;
	}

	@Override
	public EgoResult addNodes(String name, Long parentId) {
		ContentCategory contentCat = new ContentCategory();
		contentCat.setParentId(parentId);
		contentCat.setName(name);
		contentCat.setIsParent(false);
		contentCat.setCreated(new Date());
		contentCat.setUpdated(new Date());
		contentCat.setSortOrder(1);
		contentCategoryMapper.insert(contentCat);
		
		
//		contentCategoryMapper.deleteByPrimaryKey(parentId);
//		contentCategoryMapper.updateByPrimaryKeySelective(contentCat);
		
		//判断新添加的节点的父节点是否是目录
		ContentCategory category = contentCategoryMapper.selectByPrimaryKey(parentId);;
		
		if(!category.getIsParent()) {
			
			/*contentCategory.setIsParent(true);
			contentCatMapper.updateByPrimaryKey(contentCat);*/
			
			ContentCategory contentCatTemp = new ContentCategory();
			contentCatTemp.setId(parentId);
			contentCatTemp.setIsParent(true);
			contentCatTemp.setUpdated(new Date());
			contentCategoryMapper.updateByPrimaryKeySelective(contentCatTemp);
		}
		
		
		
		return EgoResult.ok(contentCat);
	}

}
