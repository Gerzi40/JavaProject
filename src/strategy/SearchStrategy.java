package strategy;

import javafx.collections.ObservableList;

public interface SearchStrategy<T> {

	public ObservableList<T> search(String query);
	
}
