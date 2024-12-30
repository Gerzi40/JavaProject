package state;

import model.Item;

public class ApprovedState implements ItemState {

	@Override
	public void handleAction(String itemId) {
		Item.AcceptOffer(itemId);
	}

}
