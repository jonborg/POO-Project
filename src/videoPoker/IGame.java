package videoPoker;

public interface IGame {
	ParentGame apply(Object x);	
	
	public void bet(int toBet);
	public void deal();
	public int credit();
	public void hold(int[] toHold);
	public int[] advice();
	public String statistics();
	
}
