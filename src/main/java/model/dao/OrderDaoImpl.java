package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.entity.Order;
import model.entity.OrderItem;

public class OrderDaoImpl extends BaseDao implements OrderDao { // 記得要先繼承BaseDao，裡面有資料庫連結

	@Override
	public List<Order> findAllOrdersByUserId(Integer userId) {
		List<Order> orders = new ArrayList<>();
		String sql = "select order_id, user_id, order_date, total_price, order_status from orders where user_id = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, userId);

			try (ResultSet rs = pstmt.executeQuery()) {
				// 逐筆將每一筆紀錄放到 Order 物件中
				while (rs.next()) {
					Order order = new Order();
					order.setOrderId(rs.getInt("order_id"));
					order.setUserId(rs.getInt("user_id"));
					order.setOrderDate(rs.getString("order_date"));
					order.setTotalPrice(rs.getDouble("total_price"));
					order.setOrderStatus(rs.getString("order_status"));
					// 加入到 orders 集合中
					orders.add(order);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}

	@Override
	public List<OrderItem> findAllOrderItemsByOrderId(Integer orderId) {
		String sql = "select item_id, order_id, product_id, quantity, unit_price from order_items where order_id = ?";
		List<OrderItem> orderItems = new ArrayList<>();

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, orderId);

			try (ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					OrderItem orderItem = new OrderItem();
					orderItem.setItemId(rs.getInt("item_id"));
					orderItem.setOrderId(rs.getInt("order_id"));
					orderItem.setProductId(rs.getInt("product_id"));
					orderItem.setQuantity(rs.getInt("quantity"));
					orderItem.setUnitPrice(rs.getDouble("unit_price"));
					orderItems.add(orderItem);
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderItems;
	}

	@Override
	public Integer addOrder(Order order) {
		String sql = "insert into orders(user_id, order_date, total_price, order_status) values(?, ?, ?, ?)";
		Integer orderId = null; // 得到新增 orders 後的 order_id 資料
		
		//因為後續要取得新增後自動生成的 order_id 所以要加上Statement.RETURN_GENERATED_KEYS參數設定
		try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			pstmt.setInt(1, order.getUserId()); // 新增資料會被封裝到Order裡，人家給Order物件，我要塞回去資料庫
			pstmt.setString(2, order.getOrderDate());
			pstmt.setDouble(3, order.getTotalPrice());
			pstmt.setString(4, order.getOrderStatus());

			pstmt.executeUpdate(); // 更新

			// 取得新增後自動生成的 order_id
			ResultSet generateKeys = pstmt.getGeneratedKeys(); //上面有加上Statement.RETURN_GENERATED_KEYS參數設定，這條代碼才會成功
			if (generateKeys.next()) { // 假設有一筆資料
				orderId = generateKeys.getInt(1); // 取得新增後自動生成的 order_id
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderId;
	}

	@Override
	public void addOrderItem(OrderItem orderItem) { 
		String sql = "insert into order_items(order_id, product_id, quantity, unit_price) values(?, ?, ? ,?)";

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, orderItem.getOrderId());
			pstmt.setInt(2, orderItem.getProductId());
			pstmt.setInt(3, orderItem.getQuantity());
			pstmt.setDouble(4, orderItem.getUnitPrice());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void changeOrderStatus(Integer orderId, String orderStatus) {
		String sql = "update orders set order_status = ? where order_id = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, orderStatus);
			pstmt.setInt(2, orderId);

			pstmt.executeUpdate(); // 更新

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<OrderItem> findAllOrderItemsByUserId(Integer userId) {
		String sql = "select item_id, order_id, product_id, quantity, unit_price from order_items " +
					 "where order_id in (SELECT order_id FROM orders where user_id = ?)";
		
		List<OrderItem> orderItems = new ArrayList<>();
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setInt(1, userId);
			
			try(ResultSet rs = pstmt.executeQuery()) {
				
				while (rs.next()) {
					OrderItem orderItem = new OrderItem();
					orderItem.setItemId(rs.getInt("item_id"));
					orderItem.setOrderId(rs.getInt("order_id"));
					orderItem.setProductId(rs.getInt("product_id"));
					orderItem.setQuantity(rs.getInt("quantity"));
					orderItem.setUnitPrice(rs.getDouble("unit_price"));
					orderItems.add(orderItem);
				}
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return orderItems;
	}

}
