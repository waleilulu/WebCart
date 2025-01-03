package model.dao;

import java.util.List;

import model.entity.Product;

// product 資料表 CRUD
public interface ProductDao {
	
	//新增
	void add(Product product); //新增一筆紀錄，用物件方式傳過 (這種方式比較好)
	//void add(Integer productId, String productName, Integer price, Integer qty); //傳對應表格的參數過來
	
	//修改
	//void update(Product product); //修改一筆紀錄，單一修改的方法
	//void update(Integer productId, Product product); //修改一筆紀錄，根據指定productId。根據指定的 productId 更新一筆紀錄，這需要提供產品的詳細信息
	void update(Integer productId, Product newProduct);
	void updateQty(Integer productId, Integer newQty); //修改庫存，ewQty 是新的庫存數。根據 productId 修改該產品的庫存數量，newQty 是新的庫存數量。
	
	//刪除
	void delete(Integer productId); //刪除庫存紀錄，根據指定 productId
	
	//全部查詢
	List<Product> findAll();
	
	//多筆查詢
	//List<Product> findGetherThenPrice(Integer price);
	//List<Product> findLessThenPrice(Integer price);
	//List<Product> findBetweenPrice(Integer fromPrice, Integer toPrice);
	
	//單筆查詢
	Product getOne(Integer productId);
}