package cn.gzsxt.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gzsxt.common.pojo.EgoResult;
import cn.gzsxt.common.utils.JsonUtils;
import cn.gzsxt.order.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
	@ResponseBody
	public EgoResult save(String orderJson) {
		if(null != orderJson && !"".equals(orderJson)) {
			cn.gzsxt.order.pojo.Order order = JsonUtils.jsonToPojo(orderJson,cn.gzsxt.order.pojo.Order.class);
			EgoResult egoResult = orderService.save(order,order.getOrderItems(),order.getOrderShipping());
			return egoResult;
		}else {
			return EgoResult.build(400,"订单数据不能为空");
		}
	}
	
	
}
