package cn.gzsxt.portal.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.druid.support.json.JSONUtils;
import com.mysql.fabric.xmlrpc.base.Array;

import cn.gzsxt.common.utils.HttpClientUtil;
import cn.gzsxt.common.utils.JsonUtils;
import cn.gzsxt.manager.pojo.Item;
import cn.gzsxt.portal.service.CartService;
import cn.gzsxt.portal.utils.CookieUtils;

@Service
public class CartServiceImpl implements CartService {

	@Value("${EGO_ITEM_CART}")
	private String EGO_ITEM_CART;

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;

	@Override
	public List<Item> addItem(Integer num, Long itemId, HttpServletResponse response, HttpServletRequest request) {
		/**
		 * 1获取购物车
		 * 
		 * 2.判断购物车是否有当前未添加的商品
		 * 
		 * 3，如果有直接修改商品数量
		 * 
		 * 4 如果没有，将商品添加到购物车中
		 * 
		 * 5 将更新后的购物车重新响应出去
		 * 
		 */

		List<Item> list = getCartFromCookie(request);
		boolean flag = true;
		if (list.size() > 0) {

			for (Item item : list) {
				if (item.getId().longValue() == itemId.longValue()) {
					item.setNum(item.getNum() + num);
					flag = false;
					break;
				}
			}
			/*
			 * //购物车中没有该商品 添加到购物车中 if( !flag) { Item item = getItemById(itemId);
			 * item.setNum(num); list.add(item); } //购物车为空直接添加到购物车中 }else{ Item item =
			 * getItemById(itemId); item.setNum(num); list.add(item); }
			 */
		}

		if (flag) {
			Item item = getItemById(itemId);
			item.setNum(num);
			list.add(item);
		}
		
		CookieUtils.setCookie(request, response, EGO_ITEM_CART, JsonUtils.objectToJson(list));
		
//		Cookie c = new Cookie("EGO_ITEM_CART",JsonUtils.objectToJson(list));
//		 response.addCookie(c);
		return list;
	}

	/**
	 * 从Cookie获取购物车
	 * 
	 * @param request
	 * @return
	 */
	public List<Item> getCartFromCookie(HttpServletRequest request) {
		List<Item> cart = null;
		
		String cookieValue = CookieUtils.getCookieValue(request, EGO_ITEM_CART);
		if( null != cookieValue && !"".equals(cookieValue)) {
			cart = JsonUtils.jsonToList(cookieValue, Item.class);
		}else {
			cart = new ArrayList<>();
		}
		
		
//		boolean flag = true;
//		Cookie[] cookies = request.getCookies();
//		if (null != cookies && cookies.length > 0) {
//			for (Cookie cookie : cookies) {
//				if ("EGO_ITEM_CART".equals(cookie.getName())) {
//					cart = JsonUtils.jsonToList(cookie.getValue(), Item.class);
//					flag = false;
//					break;
//				}
//			}
//		}
//
//		if (flag) {
//			cart = new ArrayList<>();
//		}

		return cart;
	}

	public Item getItemById(Long itemId) {
		String jsonData = HttpClientUtil.doGet(REST_BASE_URL + "item/" + itemId);
		Item item = JsonUtils.jsonToPojo(jsonData, Item.class);
		return item;
	}

	/**
	 * 更新购物车
	 */
	@Override
	public List<Item> update(Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response) {
		List<Item> list = getCartFromCookie(request);
		for (Item item : list) {
			
			if(item.getId().longValue() == itemId.longValue()) {
				item.setNum(num);
				break;
			}
		}
		CookieUtils.setCookie(request, response,EGO_ITEM_CART, JsonUtils.objectToJson(list));
		return list;
	}

	@Override
	public List<Item> delete(Long itemId, HttpServletRequest request, HttpServletResponse response) {
		List<Item> list = getCartFromCookie(request);
		for (Item item : list) {
			if(item.getId().longValue() == itemId.longValue()) {
				list.remove(item);
				break;
			}
		}
		CookieUtils.setCookie(request, response, EGO_ITEM_CART,JsonUtils.objectToJson(list));
		return list;
	}

	@Override
	public List<Item> show(HttpServletRequest request) {
		List<Item> list = getCartFromCookie(request);
		return list;
	}

}
