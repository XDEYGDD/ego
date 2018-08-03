package cn.gzsxt.manager.service;

import java.util.List;

import cn.gzsxt.common.pojo.EUTreeNode;

public interface ItemCatService {
	public List<EUTreeNode> getNodeByParentId(Long id);
}
