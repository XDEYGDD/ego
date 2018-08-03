package cn.gzsxt.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gzsxt.common.utils.JsonUtils;
import cn.gzsxt.manager.mapper.ContentMapper;
import cn.gzsxt.manager.pojo.Content;
import cn.gzsxt.manager.pojo.ContentExample;
import cn.gzsxt.manager.pojo.ContentExample.Criteria;
import cn.gzsxt.rest.service.ContentService;
import redis.clients.jedis.JedisCluster;
@Service
public class ContentServiceImpl implements ContentService {
	@Autowired
	private ContentMapper contentMapper;
		
	@Autowired
	private  JedisCluster jedisCluster;

	private List<Content> jsonToList;
	
	@Override
	public List<Content> getByContentCatId(Long contentCatId) {
		List<Content> list = null;
		String jsonData = jedisCluster.get("Content"+contentCatId);
		
		
		if( null!=jsonData && !"".equals(jsonData)) {
			list = JsonUtils.jsonToList(jsonData,Content.class);
		}else {
			
			ContentExample example = new ContentExample();
			Criteria criteria = example.createCriteria();
			criteria.andCategoryIdEqualTo(contentCatId);
			list = contentMapper.selectByExample(example);
			jedisCluster.set("Content"+contentCatId, JsonUtils.objectToJson(list));
			
		}
		return list;
	}

}
