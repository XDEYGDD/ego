package cn.gzsxt.order.pojo;

import java.util.List;

import cn.gzsxt.manager.pojo.OrderItem;
import cn.gzsxt.manager.pojo.OrderShipping;

public class Order extends cn.gzsxt.manager.pojo.Order {
	
	private List<OrderItem> orderItems;
	
	private OrderShipping orderShipping;

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public OrderShipping getOrderShipping() {
		return orderShipping;
	}

	public void setOrderShipping(OrderShipping orderShipping) {
		this.orderShipping = orderShipping;
	}

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}
