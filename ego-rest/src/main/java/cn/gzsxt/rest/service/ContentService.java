package cn.gzsxt.rest.service;

import java.util.List;

import cn.gzsxt.manager.pojo.Content;

public interface ContentService {

	/**
	 * 根据内容分类Id，查询内容列表
	 * @param contentCatId
	 * @return
	 */
	public List<Content> getByContentCatId(Long contentCatId);
	
	
	
}
