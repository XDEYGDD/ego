package cn.gzsxt.search.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.gzsxt.common.pojo.SearachItem;
import cn.gzsxt.common.pojo.SerachResult;
import cn.gzsxt.search.dao.SearchDao;

@Repository
public class SearchDaoImpl implements SearchDao {
	@Autowired
	private HttpSolrServer solrServer;
	
	@Override
	public SerachResult query(SolrQuery query) {
		
		SerachResult result = new SerachResult();
		
		try {
			QueryResponse response = solrServer.query(query);
			
			int status = response.getStatus();
			if(0==status){
				SolrDocumentList solrDocumentList = response.getResults();
				long numFound = solrDocumentList.getNumFound();
				System.out.println("共找到"+numFound+"条满足条件的数据!");
				result.setRecordCount(numFound);
				
				Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
				
				SearachItem item = null;
				List<SearachItem> itemList = new ArrayList<>();
				for (SolrDocument doc : solrDocumentList) {
					
					item = new SearachItem();
					
					String id = (String) doc.get("id");
					
					String title = "";
					boolean flag = true;
					if(null!=highlighting && highlighting.size()>0){
						Map<String, List<String>> map = highlighting.get(id);
						if(null!=map && map.size()>0){
							List<String> list = map.get("item_title");
							if(null!=list && list.size()>0){
								title = list.get(0);
								flag = false;
							}
						}
					}
					if(flag){
						
						title = (String) doc.get("item_title");
					}
					String sell_point = (String) doc.get("item_sell_point");
					String image = (String) doc.get("item_image");
					String category_name = (String) doc.get("item_category_name");
					Long price = (Long) doc.get("item_price");
					
					item.setId(Long.valueOf(id));
					item.setCategory_name(category_name);
					item.setImage(image);
					item.setPrice(price);
					item.setSell_point(sell_point);
					item.setTitle(title);
					
					itemList.add(item);
				}
				result.setItemList(itemList);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return result;
	}
	
}
