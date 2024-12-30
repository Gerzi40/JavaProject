package template;

public abstract class View {

	protected void initializeHeader() {
		
	}
	
	protected void initializeBody() {
		
	}
	
	protected void initializeLayout() {
		
	}
	
	protected void initializeAction() {
		
	}
	
	final protected void initialize() {
		initializeHeader();
		initializeBody();
		initializeLayout();
		initializeAction();
	}
	
}
