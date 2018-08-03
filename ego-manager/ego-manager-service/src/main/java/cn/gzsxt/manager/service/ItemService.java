package cn.gzsxt.manager.service;

import cn.gzsxt.common.pojo.EUDataGrid;
import cn.gzsxt.common.pojo.EgoResult;
import cn.gzsxt.manager.pojo.Item;

public interface ItemService {
	public Item getById(Long id);
	
	/**
	 * 根据分页信息，查询商品列表
	 * @param page
	 * @param rows
	 * @return
	 */
	public EUDataGrid list(Integer page,Integer rows);
	
	public EgoResult save(Item item,String desc,String paramData);
	
}
