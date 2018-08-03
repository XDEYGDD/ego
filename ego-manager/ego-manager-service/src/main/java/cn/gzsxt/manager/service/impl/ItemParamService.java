package cn.gzsxt.manager.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.gzsxt.common.pojo.EUDataGrid;
import cn.gzsxt.common.pojo.EgoResult;
import cn.gzsxt.manager.mapper.ItemParamMapper;
import cn.gzsxt.manager.pojo.ItemParam;
import cn.gzsxt.manager.pojo.ItemParamExample;
import cn.gzsxt.manager.pojo.ItemParamExample.Criteria;
import cn.gzsxt.manager.service.ItemParmService;


@Service
public class ItemParamService implements ItemParmService {
	@Autowired
	private ItemParamMapper itemParamMapper;

	@Override
	public EgoResult getByItemCatId(Long itemcatId) {
		ItemParamExample example = new ItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(itemcatId);
//		List<ItemParam> list = itemParamMapper.selectByExample(example);
		List<ItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		
		if(null !=list && list.size()>0) {
			return EgoResult.ok(list.get(0));
		}else {
			return EgoResult.build(400, "未添加");
		}
		
		
	}

	@Override
	public EUDataGrid list(Integer page, Integer rows) {
		PageHelper.startPage(page, rows);
//		List<ItemParam> list = itemParamMapper.selectByExample(new ItemParamExample());
		List<ItemParam> list = itemParamMapper.selectByExampleWithBLOBs(new ItemParamExample());
		PageInfo<ItemParam> info = new PageInfo<>(list);
		EUDataGrid grid = new EUDataGrid();
		grid.setRows(list);
		grid.setTotal(info.getTotal());
		return grid;
	}

	 
	@Override
	public EgoResult save(Long itemCatId, String paramData) {
		ItemParam itemParm = new ItemParam();
		
		itemParm.setItemCatId(itemCatId);
		itemParm.setParamData(paramData);
		itemParm.setCreated(new Date());
		itemParm.setCreated(new Date());
		
		try {
			itemParamMapper.insert(itemParm);
			return EgoResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return EgoResult.build(400, "添加模板失败");
		}
				
	}

}
