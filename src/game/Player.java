package game;

import java.util.ArrayList;

public  class Player {
	
	private ArrayList<Card> hand=new ArrayList<Card>();
	
	public Player(){}
	
	void showHand(){
		System.out.println(hand.toString());
	}
	
	void draw(Deck deck){
		while (hand.size()<5){
			hand.add(deck.draw());
		}
	}
	
	void discard (int index){
		hand.remove(index);	
	}
	

}
