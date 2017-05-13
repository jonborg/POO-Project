package gameObjects;

public class Player {
	public Hand playerHand;
	double credit;
	
	
	public Player(int credit){
		this.credit = credit;
		this.playerHand = new Hand();
	}
	
	public Hand getHand(){
		return playerHand;
	}
	
	public double getCredit(){
		return credit;
	}
		
	public void addCredit(double d){
		credit += d;
	}
	
	public void removeCredit(int toRemove){
		credit -= toRemove;
	}
	
	public void showCredit(){
		System.out.println(String.valueOf(credit));
	}
}
