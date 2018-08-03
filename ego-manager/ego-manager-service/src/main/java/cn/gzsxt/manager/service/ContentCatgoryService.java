package cn.gzsxt.manager.service;

import java.util.List;

import cn.gzsxt.common.pojo.EUTreeNode;
import cn.gzsxt.common.pojo.EgoResult;

public interface ContentCatgoryService {
	
	/**
	 * 根据父目录id，查询子目录
	 * @param id
	 * @return
	 */
	public List<EUTreeNode> getNodeByParentId(Long id);
	
	
	/**
	 * 添加内容节点
	 * @param name
	 * @param parentId
	 * @return
	 */
	public EgoResult addNodes(String name,Long parentId);
}
