package videoPoker;

public class Debug extends ParentGame implements IGame {
	
	@Override
	public ParentGame apply(Object x) {
		System.out.println("Running on" + x + "mode...");
		return null;
	}


}
