package cn.gzsxt.order.service.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gzsxt.common.pojo.EgoResult;
import cn.gzsxt.common.pojo.IDUtils;
import cn.gzsxt.manager.mapper.OrderItemMapper;
import cn.gzsxt.manager.mapper.OrderMapper;
import cn.gzsxt.manager.mapper.OrderShippingMapper;
import cn.gzsxt.manager.pojo.Order;
import cn.gzsxt.manager.pojo.OrderItem;
import cn.gzsxt.manager.pojo.OrderShipping;
import cn.gzsxt.order.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private OrderItemMapper OrderItemMapper;

	@Autowired
	private OrderShippingMapper OrderShippingMapper;

	@Override
	public EgoResult save(Order order, List<OrderItem> orderItems, OrderShipping orderShipping) {
		
		/**
		 * 保存订单信息
		 * 
		 * 1初始化Id
		 * 
		 * 
		 */
		Long orderId = IDUtils.genItemId();
		
		try {
			order.setOrderId(orderId+"");
			order.setStatus(1);
			order.setCreateTime(new Date());
			order.setUpdateTime(new Date());
			orderMapper.insert(order);
			
			if( null !=orderItems && orderItems.size()>0) {
				int i=1;
				for (OrderItem orderItem : orderItems) {
					orderItem.setOrderId(orderId+"");
					orderItem.setId(orderId+i+"");
					OrderItemMapper.insert(orderItem);
					i++;
				}
			}
			//3 保存收件人信息
			orderShipping.setOrderId(orderId+"");
			orderShipping.setCreated(new Date());
			orderShipping.setUpdated(new Date());
			OrderShippingMapper.insert(orderShipping);
			return EgoResult.ok(orderId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return EgoResult.build(400,"保存订单失败");
		}
		
		
		
	}

}
