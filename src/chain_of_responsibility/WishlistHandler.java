package chain_of_responsibility;

import controller.WishlistController;

public class WishlistHandler extends PurchaseHandler {

	@Override
	public boolean process(PurchaseRequest request) {
		return WishlistController.RemoveWishlistWithItemId(request.getItemId());
	}

}
