package videoPoker;

public class Interactive extends ParentGame implements IGame {

	@Override
	public ParentGame apply(Object x) {
		System.out.println("Running on" + x + "mode...");
		return new Interactive();
	}
	
	Interactive(){
		super();
	}

	
	 
}
