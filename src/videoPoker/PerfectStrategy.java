package videoPoker;

import java.util.LinkedList;

public final class PerfectStrategy {
	int[] cards;
	int[] hand;
	
	PerfectStrategy(int[] card) /*throws too many cards*/{
		this.cards = card;
		this.hand = handGen();		
	}
	
	
	public int[] handGen(){
		int[] hand = {0b0, 0b0, 0b0, 0b0};
		int suit;
		//exception suit invalid;
		//exception too many cards or not enough
		for(int i = 0; i<cards.length; i++){
			suit = cards[i] & 0x0000F;
			
			if((suit & 0b1000) != 0){
				hand[0] = (hand[0] | cards[i]);
				
			}else if((suit & 0b0100) != 0){
				hand[1] = (hand[1] | cards[i]);
				
			}else if((suit & 0b0010) != 0){
				hand[2] = (hand[2] | cards[i]);
				
			}else if((suit & 0b0001) != 0){
				hand[3] = (hand[3] | cards[i]);
			}
		}
			
		return hand;
	}
		
	public int suited(){
		int bestSuited = hand[0];
		
		for(int i = 0; i<(hand.length-1); i++){
			if(Integer.bitCount(bestSuited) < Integer.bitCount(hand[i+1])){
				bestSuited = hand[i+1];
			}
		}
		
		return bestSuited;
	}
	
	public int isSuited(int hand){
		if(Integer.bitCount(hand & 0x0000F) == 1){
			return 1;
		}
		return 0;
	}
	
	public int straight() /*throws aceLowException*/{
		int ranks = 0;
		int missing = 0;
		int pos = 0;
		int result = 0;
		int sequence; 
		int prevMissing = 4;
		int[] info = new int[2];
		
		for(int i = 0; i<hand.length; i++){
			ranks = ranks | hand[i];
		} 
		ranks = ranks & 0xFFFF0;
		
		for(int j = 0; j<cards.length; j++){
			pos = (cards[j] & 0xFFFF0);
			sequence = 0;
			
			for(int i = 0; i<4;i++){
				pos = pos >>> 1; 
				if((pos & ranks) == 0){
					missing++;
				}else{
					sequence = sequence | pos;
				}
				if (missing > 2){
					info[1] = 0;
					break;
				} 
			}
			
			if(missing == 0 )
			if(missing < prevMissing){
				prevMissing = missing;
				result = sequence | (cards[j] & 0xFFFF0); 	
			}
		}
		return result;
	}
	
	public int[] ranked()/*throws aceRankException*/{
		int nrPairs = 0;
		int count = 0;
		int[] ranked = new int[2];
		
		for(int i = 0; i<(cards.length - 1); i++){
			if((cards[i] & cards[i+1] & 0xFFFF0) != 0){
				count++;
				ranked[nrPairs] = ranked[nrPairs] | cards[i] | cards[i+1];
			}else if(count != 0){
				count = 0;
				nrPairs++;
			}
		}
		return ranked;
	}
	
	public boolean highCard(){
		int fin = 0xFFFFFF; 
		for(int i = 0; i<hand.length; i++){
			fin = fin & hand[i];
		}
		if((fin & 0xF00000) != 0xF00000){ //royal
			return true;
		}
		return false;
	}

	public String discard(){
		String decision = "";
		int bestSequence = straight();
		int bestSuited = suited();
		int[] bestRanked = ranked();
		
		
		return decision;
	}
	
	public int isOutside(int sequence){
		int nrCards = Integer.bitCount(sequence);
		int pos = Integer.highestOneBit(sequence);
		
		for(int i = 0; i<nrCards; i++){
			if((sequence & pos) == 0){
				return 0;
			}
			pos = pos >>> 1;
		}
		return 1;		
	}
	
	public int straightFlushDraw(int sequence){
		int nrCards = Integer.bitCount(sequence);
		int nrHigh = Integer.bitCount(sequence & 0xFF000);
		int gaps = 5-nrCards;
		
		if(nrHigh >= gaps){
			return 1;
		}else if((nrHigh == 0) && (gaps == 2)){
			return 3;
		}else{
			return 2;
		}
	}
	
	
	public static void main(String[] args){
				
		int c1 = 0b10000000000000000001;
		int c2 = 0b01000000000000000001;
		int c3 = 0b00100000000000000001;
		int c4 = 0b00010000000000000001;
		int c5 = 0b00001000000000000001;
		int[] card = {c1, c2, c3, c4, c5};
		
		PerfectStrategy Player = new PerfectStrategy(card);
				
		for(int i = 0; i<4; i++){
			System.out.println(Integer.toBinaryString(Player.hand[i]));
		}
		
		  
	}
}
