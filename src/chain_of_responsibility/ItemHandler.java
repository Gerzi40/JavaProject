package chain_of_responsibility;

import controller.ItemController;

public class ItemHandler extends PurchaseHandler {

	@Override
	public boolean process(PurchaseRequest request) {
		return ItemController.UpdateItemInfo(request.getItemId());
	}

}
