package videoPoker;

public class DebugMode implements IGameType{
	
	@Override
	public Object select(IGame l1, IGame l2, IGame l3, Object x) {
		return l2.apply(x);
	}

}
