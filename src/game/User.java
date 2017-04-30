package game;

public class User extends Player{
	
	public int credit;
	
	public User(int credit){
		this.credit=credit;
	}
	
	public void addCredit(int add){
		credit = credit + add;
	}
	
	public void showCredit(){
		System.out.println(String.valueOf(credit));
	}
	

}
