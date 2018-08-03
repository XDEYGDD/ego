package cn.gzsxt.manager.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.fabric.xmlrpc.base.Data;

import cn.gzsxt.common.pojo.EUDataGrid;
import cn.gzsxt.common.pojo.EgoResult;
import cn.gzsxt.common.pojo.IDUtils;
import cn.gzsxt.manager.mapper.ItemDescMapper;
import cn.gzsxt.manager.mapper.ItemMapper;
import cn.gzsxt.manager.mapper.ItemParamItemMapper;
import cn.gzsxt.manager.mapper.ItemParamMapper;
import cn.gzsxt.manager.pojo.Item;
import cn.gzsxt.manager.pojo.ItemDesc;
import cn.gzsxt.manager.pojo.ItemExample;
import cn.gzsxt.manager.pojo.ItemExample.Criteria;
import cn.gzsxt.manager.pojo.ItemParam;
import cn.gzsxt.manager.pojo.ItemParamItem;
import cn.gzsxt.manager.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private ItemDescMapper itemDescMapper;
	
	@Autowired
	private ItemParamItemMapper itemParamItemMapper;
	
	@Override
	public Item getById(Long id) {
//		Item item = itemMapper.selectByPrimaryKey(id);
		
		ItemExample example = new ItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(id);
		
		List<Item> list = itemMapper.selectByExample(example);
		if( !list.isEmpty()) {
			return list.get(0);
		}else {
			return null;
		}
	}

	@Override
	public EUDataGrid list(Integer page, Integer rows) {
		/**
		 * 拦截要执行的sql语句
		 */
		PageHelper.startPage(page,rows);//开启sql拦截
		
		List<Item> list = itemMapper.selectByExample(new ItemExample());
		
		
		PageInfo<Item> info = new PageInfo<>(list);
		
		EUDataGrid grid = new EUDataGrid(); 
		
		grid.setTotal(info.getTotal());
		grid.setRows(list);
		return grid;
		
	}

	@Override
	public EgoResult save(Item item, String desc, String paramData) {
		long itemId = IDUtils.genItemId();
		try {
			
			System.out.println(itemId);
			Date date = new Date();
			
			item.setId(itemId);
			item.setCreated(date);
			item.setUpdated(date);
			item.setStatus((byte) 1);
			itemMapper.insert(item);
			
			if(paramData!=null && !"".equals(paramData)) {
				ItemParamItem itemParamItem = new ItemParamItem();
				itemParamItem.setItemId(itemId);
				itemParamItem.setParamData(paramData);
				itemParamItem.setCreated(date);
				itemParamItem.setUpdated(date);
				itemParamItemMapper.insert(itemParamItem);
			}
			
			if(desc!=null && !"".equals(desc)) {
				ItemDesc itemDesc = new ItemDesc();
				itemDesc.setItemId(itemId);
				itemDesc.setCreated(date);
				itemDesc.setUpdated(date);
				itemDesc.setItemDesc(desc);
				itemDescMapper.insert(itemDesc);
			}
			
			return EgoResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return EgoResult.build(400, "当前服务器忙，请稍后再试");
		}
		
		
		
	}

}
