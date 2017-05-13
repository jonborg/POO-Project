package gameObjects;

import java.util.ArrayList;

public class HandEval{
	ArrayList<Card> cards;
	int[] hands;
	String shand;
	
	public HandEval(ArrayList<Card> card, int[] hand){
		cards = card; 
		hands = hand;
		shand = "";
	}
		
	public int evaluate(double[] statistics){
		int index = -1;
		int hand = 0;
		
		for(int i=0; i<hands.length; i++){
			hand = hand | hands[i];
		}
		
		index = Math.abs(isStraight(hand) - isFlush(hand));
		if(index == 2){ 			
			if((hand & 0xFF000) == 0x1F000){
				statistics[8]++;
				shand = "ROYAL FLUSH";
				return 250;
			}
			statistics[7]++;
			shand = "STRAIGHT FLUSH";
			return 50;
		}else{
			if(index != 0){
				if(index == 7){
					statistics[4]++;
				}else{
					statistics[3]++;
				}
				return index;
			}else{
				return isRanked(statistics);
			}
		}	
		
	}
	
	public int isRanked(double[] statistics)/*throws aceRankException*/{
		int nrPairs = 0;
		int[] count = {0, 0};
		int[] ranked = new int[2];
		
		for(int i = 0; i<(cards.size() - 1); i++){
			if((cards.get(i).getBitMask() & cards.get(i+1).getBitMask() & 0xFFFF0) != 0){
				count[nrPairs]++;
				ranked[nrPairs] = ranked[nrPairs] | cards.get(i).getBitMask() | cards.get(i+1).getBitMask();
			}else if(count[nrPairs] != 0){
				nrPairs++;
			}
		}
		return typeOfRanked(count, (ranked[0] | ranked[1]), statistics);	
	}
			
	
	public int typeOfRanked(int[] count, int ranked, double[] statistics){
		int c = count[0] + count[1];
		
		if(c == 1){
			if((ranked & 0xFE000) != 0){
				statistics[0]++;
				shand = "JACKS OR BETTER";
				return 1;
			}
		}else if(c == 2){
			if(count[0] == 1){
				statistics[1]++;
				shand = "TWO PAIR";
				return 1;
			}
			statistics[2]++;
			shand = "THREE OF A KIND";
			return 1;
		}else if(c == 3){
			if(count[0] < 3){
				statistics[5]++;
				shand = "FULL HOUSE";
				return 10;
			}else{
				statistics[6]++;
				if((ranked & 0xF0000) != 0){
					shand = "FOUR ACES";
					return 160;
				}else if((ranked & 0x00070) != 0){
					shand = "FOUR 2-4";
					return 80;
				}else{
					shand = "FOUR 5-K";
					return 50;
				}
			}
		}
		statistics[9]++;
		return 0;		
	}
	
	public int isFlush(int hand){
		if(Integer.bitCount(hand & 0x0000F) == 1){
			shand = "FLUSH";
			return 7;
		}
		return 0;
	}
	
	public int isStraight(int hand){/*throws Ace Low*/
		
		int start = Integer.highestOneBit(hand);
		
		for(int i=0; i<5; i++){
			if((start & hand) == 0){
				return 0;
			}
			start = start >>> 1;
		}
		shand = "STRAIGHT";
		return 5;
	}
	
	@Override
	public String toString() {
		return shand;
	}

	
}
