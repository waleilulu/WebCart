package model.dto;

public class OrderItemDto {
	private Integer itemId;
	private String productName; // 利用 productId 可查到
	private Integer quantity;
	private Double unitPrice;
	
	/*精靈產生: 右鍵➔Source➔getter & setter*/
	
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	/*精靈產生: 右鍵➔Source➔Generate toString*/
	@Override
	public String toString() {
		return "OrderItemDto [itemId=" + itemId + ", productName=" + productName + ", quantity=" + quantity
				+ ", unitPrice=" + unitPrice + "]";
	}
	
}
