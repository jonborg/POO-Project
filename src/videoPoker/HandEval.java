package videoPoker;

public class HandEval {
	int[] cards;
	int[] hands;
	
	HandEval(int[] card){
		cards = card; 
		hands = handGen();	
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
		
		
	public int evaluate(){
		int index = -1;
		int hand = 0;
		
		for(int i=0; i<hands.length; i++){
			hand = hand | hands[i];
		}
		
		index = Math.abs(isStraight(hand) - isFlush(hand));
		if(index == 2){ 			
			if((hand & 0xFF000) == 0x1F000){
				return 250;
			}
			return 50;
		}else{
			if(index != 0){
				return index;
			}else{
				return isRanked();
			}
		}	
		
	}
	
	public int isRanked()/*throws aceRankException*/{
		int nrPairs = 0;
		int[] count = {0, 0};
		int[] ranked = new int[2];
		
		for(int i = 0; i<(cards.length - 1); i++){
			if((cards[i] & cards[i+1] & 0xFFFF0) != 0){
				count[nrPairs]++;
				ranked[nrPairs] = ranked[nrPairs] | cards[i] | cards[i+1];
			}else if(count[nrPairs] != 0){
				nrPairs++;
			}
		}
		return typeOfRanked(count, (ranked[0] | ranked[1]));	
	}
			
	
	public int typeOfRanked(int[] count, int ranked){
		int c = count[0] + count[1];
		
		if(c == 1){
			if((ranked & 0xFE000) != 0){
				return 10;
			}
		}else if(c == 2){
			if(count[0] == 1){
				return 1;
			}
			return 1;
		}else if(c == 3){
			if(count[0] < 3){
				return 10;
			}else{
				if((ranked & 0xF0000) != 0){
					return 160;
				}else if((ranked & 0x00070) != 0){
					return 80;
				}else{
					return 50;
				}
			}
		}
		return 0;		
	}
	
	public int isFlush(int hand){
		if(Integer.bitCount(hand & 0x0000F) == 1){
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
		return 5;
	}

	public static void main(String[] args){
		int c1 = 0b10000000000000001;
		int c2 = 0b01000000000000001;
		int c3 = 0b00100000000000001;
		int c4 = 0b00010000000000001;
		int c5 = 0b00001000000000001;
		int[] card = {c1, c2, c3, c4, c5};
		
		HandEval Player = new HandEval(card);
		System.out.println(Player.evaluate());
	}
}
