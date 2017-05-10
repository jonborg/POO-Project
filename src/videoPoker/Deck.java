package videoPoker;

import java.util.Random;
import java.io.*;
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
	
	//{ HEARTS, SPADES, DIAMONDS, CLUBS };
	//{ ACE, KING, QUEEN, JACK, TEN, NINE, EIGHT, SEVEN, SIX, FIVE, FOUR, THREE, TWO };

	
	Deck(String inputDeck){
		int rank,suit;
		cards = new ArrayList<Card>();

		for (int i=0;i<inputDeck.length()-2;i=i+3){
			switch (inputDeck.charAt(i+1)){
			case 'H':suit=0;break;
			case 'S':suit=1;break;
			case 'D':suit=2;break;
			case 'C':suit=3;break;
			default:;return;
			}
			switch (inputDeck.charAt(i)){
			case 'A':rank=0;break;
			case 'K':rank=1;break;
			case 'Q':rank=2;break;
			case 'J':rank=3;break;
			case 'T':rank=4;break;
			case '9':rank=5;break;
			case '8':rank=6;break;
			case '7':rank=7;break;
			case '6':rank=8;break;
			case '5':rank=9;break;
			case '4':rank=10;break;
			case '3':rank=11;break;
			case '2':rank=12;break;
			default:return; 
			}
			cards.add( new Card(suit, rank) );

		}
				
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
			out += cards.get(i).toString() + "\n";;
		}
	
		return out + "\n";
	}	 
	
	public static void main(String[] args){
		Deck deck=new Deck();
		System.out.println(deck.toString());
	}
}
