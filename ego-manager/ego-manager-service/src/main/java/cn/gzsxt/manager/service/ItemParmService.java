package cn.gzsxt.manager.service;

import cn.gzsxt.common.pojo.EUDataGrid;
import cn.gzsxt.common.pojo.EgoResult;

public interface ItemParmService {
	/**
	 * 根据商品类目，查询规格模板
	 * @param itemcatId
	 * @return
	 */
	public EgoResult getByItemCatId(Long itemcatId);
	
	public EUDataGrid list(Integer page,Integer rows);
	/**
	 * 保存商品的类目，保存规格模板
	 * @param itemCatId
	 * @param paramData
	 * @return
	 */
	public EgoResult save(Long itemCatId,String paramData);
	
	/**
	 * 
	 * @return
	 */
//	public EgoResult delete(Long itemCatId);
	
}
