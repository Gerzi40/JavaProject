package chain_of_responsibility;

import controller.TransactionController;

public class TransactionHandler extends PurchaseHandler {

	@Override
	public boolean process(PurchaseRequest request) {
		return TransactionController.PurchaseItems(request.getBuyerId(), request.getItemId());
	}

}
