package cn.gzsxt.portal.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.gzsxt.common.pojo.EgoResult;
import cn.gzsxt.common.utils.HttpClientUtil;
import cn.gzsxt.common.utils.JsonUtils;
import cn.gzsxt.portal.pojo.Order;
import cn.gzsxt.portal.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Value("${ORDER_BASE_URL}")
	private String ORDER_BASE_URL;
	
	@Override
	public EgoResult save(Order order) {
		
		Map<String, String> param = new HashMap<>();
		param.put("orderJson", JsonUtils.objectToJson(order));
		
		String doPost = HttpClientUtil.doPost(ORDER_BASE_URL+"/create",param);
		
		if( null != doPost && !"".equals(doPost)) {
			EgoResult result = EgoResult.format(doPost);
			return result;
			
		}else {
			return EgoResult.build(500,"请求接口路径");
		}
		
	}

}
