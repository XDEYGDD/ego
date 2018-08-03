package cn.gzsxt.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.gzsxt.common.pojo.EUDataGrid;
import cn.gzsxt.common.pojo.EgoResult;
import cn.gzsxt.manager.mapper.ContentMapper;
import cn.gzsxt.manager.pojo.Content;
import cn.gzsxt.manager.pojo.ContentExample;
import cn.gzsxt.manager.pojo.ContentExample.Criteria;
import cn.gzsxt.manager.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {
	@Autowired
	private ContentMapper contentMapper;
	
	@Override
	public EUDataGrid list(Long categoryId, Integer page, Integer rows) {
		
		ContentExample example = new ContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		
		//开启拦截器
		PageHelper.startPage(page, rows);
		List<Content> list = contentMapper.selectByExample(example);
		
		PageInfo<Content> info = new PageInfo<>(list);
		EUDataGrid grid = new EUDataGrid();
		grid.setRows(list);
		grid.setTotal(info.getTotal());
		
		return grid;
	}

	@Override
	public EgoResult update(Content content) {
		contentMapper.updateByPrimaryKey(content);
		return EgoResult.ok();
	}
	
	
	

}
