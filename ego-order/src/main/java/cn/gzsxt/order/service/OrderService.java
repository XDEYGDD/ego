package cn.gzsxt.order.service;

import java.util.List;

import cn.gzsxt.common.pojo.EgoResult;
import cn.gzsxt.manager.pojo.Order;
import cn.gzsxt.manager.pojo.OrderItem;
import cn.gzsxt.manager.pojo.OrderShipping;

public interface OrderService {
	
	public EgoResult save(Order order,List<OrderItem> orderItems,OrderShipping orderShipping); 
}
