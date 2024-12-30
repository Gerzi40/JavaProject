package proxy;


public class TransactionManagerProxy implements ITransactionManager {

	private TransactionManager transactionManager;
	private String userRole;
	
	public TransactionManagerProxy(TransactionManager transactionManager, String userRole) {
		this.transactionManager = transactionManager;
		this.userRole = userRole;
	}

	@Override
	public void processItemPurchase() {
		if(userRole.equals("Buyer")) {
			transactionManager.processItemPurchase();
		}
	}

	@Override
	public void processAcceptOffer() {
		if(userRole.equals("Seller")) {
			transactionManager.processAcceptOffer();
		}		
	}
	
}
