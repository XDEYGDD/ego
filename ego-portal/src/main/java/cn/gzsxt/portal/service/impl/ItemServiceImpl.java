package cn.gzsxt.portal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.gzsxt.common.utils.HttpClientUtil;
import cn.gzsxt.common.utils.JsonUtils;
import cn.gzsxt.manager.pojo.Item;
import cn.gzsxt.portal.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	
	@Override
	public Item getItemById(Long itemId) {
		String jsonData = HttpClientUtil.doGet(REST_BASE_URL+"item/"+itemId);
		
		if( null !=jsonData && !"".equals(jsonData)) {
			Item item = JsonUtils.jsonToPojo(jsonData, Item.class);
			return item;
		}
		return null;
	}

}
