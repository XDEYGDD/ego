package cn.gzsxt.search.service;

import cn.gzsxt.common.pojo.SerachResult;

public interface SearchService {
	/**
	 * 根据关键词搜索
	 * @param q
	 * @param page
	 * @return
	 */
	public SerachResult query(String q,Integer page);
}
