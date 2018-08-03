package cn.gzsxt.portal.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.gzsxt.common.pojo.SerachResult;
import cn.gzsxt.common.utils.HttpClientUtil;
import cn.gzsxt.common.utils.JsonUtils;
import cn.gzsxt.portal.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {

	@Value("${SEARCH_BASE_URL}")
	private String SEARCH_BASE_URL;
		
	@Override
	public SerachResult query(String q, Integer page) {
		Map<String, String> param  = new HashMap<>();
		param.put("q", q);
		param.put("page", page+"");
		
		String jsonData = HttpClientUtil.doGet(SEARCH_BASE_URL,param);
		
		SerachResult result = null;
		if(null !=jsonData && !"".equals(jsonData)) {
			result = JsonUtils.jsonToPojo(jsonData,SerachResult.class);
		}else {
			result = new SerachResult();
		}
		
		return result;
	}
	
}
