package model.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import model.dao.OrderDao;
import model.dao.OrderDaoImpl;
import model.dto.OrderDto;
import model.dto.OrderItemDto;
import model.entity.Order;
import model.entity.OrderItem;

public class OrderService {
	
	private OrderDao orderDao = new OrderDaoImpl();
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	// 取得歷史訂單檔
	public List<OrderDto> findAllOrderHistoryByUserId(Integer userId) {
		// 得到所有訂單資料(含: Pending, Finished, Cancel)
		List<Order> orders = orderDao.findAllOrdersByUserId(userId);
		// 將 orders 的訂單濾掉 "Pending"
		List<Order> orderHistories = orders.stream()
								.filter(order -> !order.getOrderStatus().equals("Pending"))
								.collect(Collectors.toList());
		// Order 轉 OrderDto
		List<OrderDto> orderDtos = new ArrayList<>();
		orderHistories.forEach(order -> {
			OrderDto orderDto = new OrderDto();
			orderDto.setOrderId(order.getOrderId());
			orderDto.setOrderDate(order.getOrderDate());
			orderDto.setOrderStatus(order.getOrderStatus());
			orderDto.setTotalPrice(order.getTotalPrice());
			orderDto.setUserId(order.getUserId());
			// 注入到 orderDtos
			orderDtos.add(orderDto);
		});
		
		return orderDtos;
		
	}
	
	// 修改訂單狀態-Finished
	public void orderFinished(Integer orderId) {
		orderDao.changeOrderStatus(orderId, "Finished");
	}
	
	// 修改訂單狀態-Cancel
	public void orderCancel(Integer orderId) {
		orderDao.changeOrderStatus(orderId, "Cancel");
		// 3.庫存回補
		
	}
	
	// 新增訂單到購物車
	public void addOrder(Integer userId, String[] productIds, String[] prices, String[] amounts) {
		// 建立訂單主檔物件
		Order order = new Order();
		order.setOrderDate(sdf.format(new Date()));
		order.setOrderStatus("Pending");
		//order.setTotalPrice(0.0);
		order.setUserId(userId);
		System.out.println(order);
		// total 自行加總 -----------------------------------
		double total = 0.0;
		for(int i=0;i<productIds.length;i++) {
			Double price = Double.parseDouble(prices[i]);
			Integer amount = Integer.parseInt(amounts[i]);
			if(amount == 0) {
				continue;
			}
			total += price * amount;
		}
		order.setTotalPrice(total);
		// ------------------------------------------------
		
		// 新增訂單主檔並得到 orderId
		Integer orderId = orderDao.addOrder(order);
		System.out.println("orderId: " + orderId);
		
		// 新增訂單明細檔
		for(int i=0;i<productIds.length;i++) {
			Integer productId = Integer.parseInt(productIds[i]);
			Double price = Double.parseDouble(prices[i]);
			Integer amount = Integer.parseInt(amounts[i]);
			if(amount == 0) {
				continue;
			}
			
			// 1.檢查商品庫存是否足夠
			
			// 2.預扣庫存
			
			
			// 建立訂單明細物件
			OrderItem orderItem = new OrderItem();
			orderItem.setOrderId(orderId);
			orderItem.setProductId(productId);
			orderItem.setQuantity(amount);
			orderItem.setUnitPrice(price);
			// 新增訂單明細檔
			orderDao.addOrderItem(orderItem);
		}
		
	}
	
}