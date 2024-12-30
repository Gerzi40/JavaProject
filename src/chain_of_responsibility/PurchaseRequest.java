package chain_of_responsibility;

public class PurchaseRequest {

	private String buyerId;
	private String sellerId;
	private String itemId;
	private int amount;
	
	public PurchaseRequest(String buyerId, String sellerId, String itemId, int amount) {
		super();
		this.buyerId = buyerId;
		this.sellerId = sellerId;
		this.itemId = itemId;
		this.amount = amount;
	}
	
	public String getBuyerId() {
		return buyerId;
	}
	
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	
	public String getSellerId() {
		return sellerId;
	}
	
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	
	public String getItemId() {
		return itemId;
	}
	
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
}
