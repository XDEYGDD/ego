package cn.gzsxt.search.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.gzsxt.common.pojo.SerachResult;
import cn.gzsxt.search.dao.SearchDao;
import cn.gzsxt.search.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {
	
	@Value("${PAGESIZE}")
	private Integer PAGESIZE;
	
	@Autowired
	private SearchDao searchDao;
	
	@Override
	public SerachResult query(String q, Integer page) {
		SolrQuery query = new SolrQuery();
		if(null !=q && !"".equals(q)) {
			query.setQuery("item_keywords:"+q);
		}else {
			query.setQuery("*.*");
		}
		
		
		if(null == page) {
			page = 1;
		}

		//设置分页
		query.setStart((page-1)*PAGESIZE);
		query.setRows(PAGESIZE);
		
		//设置高亮信息
		query.setHighlight(true);
		query.setHighlightSimplePre("<font style='color:red'>");
		query.setHighlightSimplePost("</font>");
		query.addHighlightField("item_title");
		
		SerachResult result = searchDao.query(query);
		Long recordCount = result.getRecordCount();
		int pageCount = (int) (recordCount/PAGESIZE);
		if(recordCount % PAGESIZE >0) {
			pageCount++;
		}
		result.setPageCount(pageCount);
		result.setCurPage(page);
		return result;
	}

}
