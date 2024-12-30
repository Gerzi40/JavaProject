package chain_of_responsibility;

public abstract class PurchaseHandler {

	private PurchaseHandler nextHandler;
	
	public void setNextHandler(PurchaseHandler nextHandler) {
		this.nextHandler = nextHandler;
	}
	
	public void handle(PurchaseRequest request) {
		if(process(request)) {			
			if(nextHandler != null) {
				nextHandler.handle(request);
			}
		}
	}
	
	public abstract boolean process(PurchaseRequest request);

}
