package videoPoker;

public class InteractiveMode implements IGameType{
	
	@Override
	public ParentGame select(IGame l1, IGame l2, IGame l3, Object x) {
		return l1.apply(x);
	}
	
}
