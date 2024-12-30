package state;

import model.Item;

public class PendingState implements ItemState {

	@Override
	public void handleAction(String itemId) {
		Item.ApproveItem(itemId);
	}

}
