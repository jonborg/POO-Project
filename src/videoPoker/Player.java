package videoPoker;

public class Player {
	Hand playerHand;
	int credit;
	
	
	Player(int credit){
		this.credit = credit;
		this.playerHand = new Hand();
	}
		
	public void addCredit(int toAdd){
		credit += toAdd;
	}
	
	public void removeCredit(int toRemove){
		credit -= toRemove;
	}
	
	public void showCredit(){
		System.out.println(String.valueOf(credit));
	}
}
