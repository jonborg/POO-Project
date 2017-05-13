package videoPoker;

public class SimulationMode implements IGameType {
	
	@Override
	public ParentGame select(IGame l1, IGame l2, IGame l3, Object x) {
		return l3.apply(x);
	}

}
