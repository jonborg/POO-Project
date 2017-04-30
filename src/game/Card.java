package game;

public class Card {
	
	public short naipe;
	public short number;
	
	public String[] naipes={"Diamonds","Hearts","Spades","Clubs"};
	public String[] numbers={"Ace","2","3","4","5","6","7","8","9","10","Jack","Queen","King"};
	
	public Card(short naipe, short number){
		this.naipe=naipe;
		this.number=number;
	}
	
	public String toString(){
		return numbers[number] + " of " + naipes[naipe] ;
	}

}
