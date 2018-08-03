package cn.gzsxt.search.dao;

import org.apache.solr.client.solrj.SolrQuery;

import cn.gzsxt.common.pojo.SerachResult;

public interface SearchDao {
	/**
	 * 根据条件 查询索引库
	 * @param query
	 * @return
	 */
	public SerachResult query(SolrQuery query);
}
