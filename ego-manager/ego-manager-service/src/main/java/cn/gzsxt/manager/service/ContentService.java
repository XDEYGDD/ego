package cn.gzsxt.manager.service;

import java.util.List;

import cn.gzsxt.common.pojo.EUDataGrid;
import cn.gzsxt.common.pojo.EgoResult;
import cn.gzsxt.manager.pojo.Content;

public interface ContentService {
	/**
	 * 根据内容Id，查询对应区域下的内容对象
	 * 
	 * @param categoryId
	 * @param page
	 * @param rows
	 * @return
	 */
	public EUDataGrid list(Long categoryId,Integer page,Integer rows);
	
	/**
	 * 更新内容
	 * @param content
	 * @return
	 */
	public EgoResult update(Content content);
}
