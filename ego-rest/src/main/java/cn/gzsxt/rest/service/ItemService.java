package cn.gzsxt.rest.service;

import cn.gzsxt.manager.pojo.Item;

/**
 * rest系统商品 service
 * @author 47097
 *
 */
public interface ItemService {

	/**
	 * 根据商品Id 查询商品信息
	 * @param 
	 * @return
	 */
	public Item getItemById(Long itemId);
}
