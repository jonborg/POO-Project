package videoPoker;

public interface IGame {
	ParentGame apply(Object x);	
	
	public void bet();
	public void credit();
	public void hold();
	public void advice();
	public void statistics(int initCredit);
	
}
