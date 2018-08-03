package cn.gzsxt.portal.service;

import cn.gzsxt.common.pojo.SerachResult;

public interface SearchService {
	public SerachResult query(String q, Integer page);
}
