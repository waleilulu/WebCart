package model.entity;

//對應 product 資料表中的每一筆紀錄，區域變數
public class Product {
	private Integer productId; // 對應 product_id 欄位
	private String productName; // 對應 product_name 欄位
	private Integer price; // 對應 price 欄位
	private Integer qty; // 對應 qty 欄位
	private String imageBase64; // 圖片編碼

	// 以下使用精靈產生，右鍵->source->generate getters and setters

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public String getImageBase64() {
		return imageBase64;
	}

	public void setImageBase64(String imageBase64) {
		this.imageBase64 = imageBase64;
	}

}
