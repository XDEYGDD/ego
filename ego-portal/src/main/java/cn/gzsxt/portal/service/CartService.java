package cn.gzsxt.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.gzsxt.manager.pojo.Item;

public interface CartService {
	
	
	/**
	 * 添加商品到购物车
	 * @param num 商品数量
	 * @param itemId 商品id
	 * @param response
	 * @param request
	 * @return 更新之后的购物车
	 */
	public List<Item> addItem(Integer num,Long itemId,
			HttpServletResponse response,HttpServletRequest request);
	
	public List<Item> update(Long itemId,Integer num,HttpServletRequest request,HttpServletResponse response);
	
	
	public List<Item> delete(Long itemId,HttpServletRequest request,HttpServletResponse response);
	
	public List<Item> show(HttpServletRequest request);
	
	
	
}
