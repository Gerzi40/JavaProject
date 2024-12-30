package strategy;

import controller.ItemController;
import javafx.collections.ObservableList;
import model.Item;

public class ItemNameSearch implements SearchStrategy<Item> {

	@Override
	public ObservableList<Item> search(String query) {
		return ItemController.BrowseItem(query);
	}

}
