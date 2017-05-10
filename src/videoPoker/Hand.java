package videoPoker;

import java.util.ArrayList;

public class Hand {
	ArrayList<Card> cards; //each card on hand
	int[] hand = {0b0, 0b0, 0b0, 0b0};	//vector to the bitmask for each suit of the hand
	
	public void genHand(){
		int suit;
		//exception suit invalid;
		//exception too many cards or not enough
		for(int i = 0; i<5; i++){
			suit = cards.get(i).getBitMask() & 0x0000F;
			if((suit & 0b1000) != 0){
				hand[0] = (hand[0] | cards.get(i).getBitMask());
			}else if((suit & 0b0100) != 0){
				hand[1] = (hand[1] | cards.get(i).getBitMask());
			}else if((suit & 0b0010) != 0){
				hand[2] = (hand[2] | cards.get(i).getBitMask());
			}else if((suit & 0b0001) != 0){
				hand[3] = (hand[3] | cards.get(i).getBitMask());
			}
		}
	}	
	public void replace(int i, Card card){
		cards.set(i, card);
	}
	public void add(Card card){
		
		if(cards.get(0) == null){
			cards.add(card);
			return;
		}else{
			for(int i = 0; i<cards.size(); i++){
				if(cards.get(i).getRank() < card.getRank()){
					cards.add(i, card);
					return;
				}
			}
			if(cards.size() < 5){
				cards.add(card);
				return;
			}
		}
	}
}
