package videoPoker;

import java.util.Random;
import java.lang.*;
import java.util.ArrayList;

public class Deck {
	private ArrayList<Card> cards;
	private final int SHUFFLES = 100;
	
	Deck(){
		cards = new ArrayList<Card>();
		
		for(int a = 0; a < 4; a++){
			for(int b = 0; b < 13; b++){
				cards.add( new Card(a, b) );
			}
		}
		//this.shuffle();
	}
	
	public void shuffle(){
		Random shuffler = new Random();
		for(int i = 0; i <= SHUFFLES; i++){
			int index_1 = shuffler.nextInt(cards.size()-1);
			int index_2 = shuffler.nextInt(cards.size()-1);
			
			Card temp = cards.get(index_2);
			cards.set(index_2, cards.get(index_1));
			cards.set(index_1, temp);
		}
	}
	
	public Card drawFromDeck(){
		return cards.remove(0);
	}
	
	public void add(Card card){
		cards.add(card);
	}
	
	
	@Override
	public String toString() {
		String out = "Deck:\n\n";
		
		for(int i = 0; i <= cards.size()-1; i++){
			out += cards.get(i).toString() + "\n";
		}
	
		return out + "\n";
	}	 
}
