package videoPoker;

public class Debug extends ParentGame implements IGame {
	
	public Debug(int initCredit){
		super(initCredit);
	}
	
	@Override
	public ParentGame apply(Object x) {
		System.out.println("Running on" + x + "mode...");
		return null;
	}
}
