package proxy;

import chain_of_responsibility.ItemHandler;
import chain_of_responsibility.PurchaseHandler;
import chain_of_responsibility.PurchaseRequest;
import chain_of_responsibility.TransactionHandler;
import chain_of_responsibility.UserHandler;
import chain_of_responsibility.WishlistHandler;

public class TransactionManager implements ITransactionManager {

	private String buyerId;
	private String sellerId;
	private String itemId;
	private int amount;

	public TransactionManager(String buyerId, String sellerId, String itemId, int amount) {
		super();
		this.buyerId = buyerId;
		this.sellerId = sellerId;
		this.itemId = itemId;
		this.amount = amount;
	}
	
	public int getAmount() {
		return amount;
	}

	public void process() {
		
		PurchaseHandler transactionHandler = new TransactionHandler();
		PurchaseHandler itemHandler = new ItemHandler();
		PurchaseHandler wishlistHandler = new WishlistHandler();
		PurchaseHandler userHandler = new UserHandler();
		
		transactionHandler.setNextHandler(itemHandler);
		itemHandler.setNextHandler(wishlistHandler);
		wishlistHandler.setNextHandler(userHandler);
		
		PurchaseRequest request = new PurchaseRequest(buyerId, sellerId, itemId, amount);
		
		transactionHandler.handle(request);

	}

	@Override
	public void processItemPurchase() {
		process();
	}

	@Override
	public void processAcceptOffer() {
		process();
	}

}
