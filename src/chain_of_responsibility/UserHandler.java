package chain_of_responsibility;

import controller.UserController;

public class UserHandler extends PurchaseHandler {

	@Override
	public boolean process(PurchaseRequest request) {
		return UserController.updateBuyerSellerBalance(request.getBuyerId(), request.getSellerId(), request.getAmount());
	}

}
