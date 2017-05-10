package videoPoker;

import java.util.Scanner;

public class User extends Player{
	
	int credit;
	int currentBet=5;
	
	public User(int credit){
		this.credit=credit;
	}
	
	public void addCredit(int add){
		credit = credit + add;
	}
	
	public void subtractCredit(int add){
		if(credit - add<0){
			System.out.println("ERROR: Not enough credits.");
			return;
		}
		credit = credit - add;
	}
	
	public void showCredit(){
		System.out.println(String.valueOf(credit));
	}
	
	void bet(){
		this.subtractCredit(currentBet);
		
	}
	void bet(int newBet){
		this.subtractCredit(newBet);
		currentBet=newBet;
		
	}
	
	void turn(){
		Scanner scanner = new Scanner(System.in);
		String command=scanner.nextLine();
		
		System.out.println(command);
		if (command.contentEquals("b")){
			this.bet();
		}else{
			if(command.contains("b") && command.length()>1){
				this.bet(Integer.parseInt(command.substring(2)));
			}
		}
		System.out.println(this.currentBet);
		System.out.println(this.credit);
		
	}
	

}
