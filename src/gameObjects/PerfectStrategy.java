package gameObjects;

import java.util.ArrayList;

public class PerfectStrategy {
	ArrayList<Card> cards;
	int[] hand = {0b0, 0b0, 0b0, 0b0};
	
	
	public PerfectStrategy(ArrayList<Card> cards, int[] hand){
		this.cards = cards;
		this.hand = hand;
	}
	
	public int[] toRemove(int bitMask){
		int[] toRemove = new int[5];
		int j = 0;
		int compare = 0xFFFF0;

		if((bitMask & 0x0000F) != 0){
			compare = 0xFFFFF;
		}
		
		for(int i = 0; i<5; i++){
			if((cards.get(i).getBitMask() & bitMask) == (cards.get(i).getBitMask() & compare)){
				toRemove[j] = 1;
			}
			j++;
		}
	
		return toRemove;
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
	
	public int isOutside(int sequence, int nrCards){
		int first = Integer.highestOneBit(sequence);
		for(int i = 0; i < nrCards; i++){
			first = first | (first >>> 1);
		}
		if((first & sequence) == sequence){
			return 1;
		}
		return 0;
	}
	
	public int[] sequence(int hand){
		int first = 0b10000000000000000; 
				//Integer.highestOneBit(hand);
		int current = 0b0;
		int newHand, bitCount = 0;
		int[] sequence = {0b0, 0};
		int newOut;
		
		
		for(int i = 0; i<5; i++){
			current = current | first;
			first = first >>> 1;
		}
		
		while(current != 0x000F8){
		
			newHand = hand & current;
			
			newOut = isOutside(newHand, Integer.bitCount(newHand));
			
			if(Integer.bitCount(newHand) > bitCount){
				sequence[0] = newHand;
				sequence[1] = newOut;
				bitCount = Integer.bitCount(newHand);
				
			}else if((Integer.bitCount(newHand) == bitCount) && ((sequence[1] == 0)  && (newOut == 1))){
				sequence[0] = newHand;
				sequence[1] = newOut;
				bitCount = Integer.bitCount(newHand);
			}
			
			current = current >>> 1;
		}
		
		if(Integer.bitCount(hand & 0x100F0) >= bitCount){
			if(Integer.bitCount(hand & 0x10000) != 0){
				if((hand & 0x000F0) == 0x00070 || (hand & 0x000F0) == 0x0030){
					sequence[1] = 1;
					sequence[0] = hand & 0x100F0;
				}else if(Integer.bitCount(hand & 0x100F0) == bitCount && (sequence[1] == 0)){
					sequence[1] = 0;
					sequence[0] = hand & 0x100F0;
				}
			}
		}
		
		return sequence; 
	}
	
	public int suitedSequence(){
		int tryIt, bitCount = 0;
		int st = 0;
		int suit = 0;
		for(int i = 0; i<4; i++){
			tryIt = Integer.bitCount(hand[i] & 0xFFFF0);
			
			if((tryIt != 0) && (tryIt> bitCount)){
				suit = hand[i] & 0x0000F;
				st = sequence(hand[i] & 0xFFFF0)[0];
				st = st | suit;
				bitCount = Integer.bitCount(st);
			}
		}
		return st;
	}
	
	public int[] ranked()/*throws aceRankException*/{
		int nrPairs = 0;
		int count = 0;
		int[] ranked = new int[2];
		
		for(int i = 0; i<(cards.size() - 1); i++){
			if(((cards.get(i).getBitMask() & cards.get(i+1).getBitMask()) & 0xFFFF0) != 0){
				count++;
				ranked[nrPairs] = (ranked[nrPairs] | cards.get(i).getBitMask() | cards.get(i+1).getBitMask()) & 0xFFFF0;
			}else if(count != 0){
				count = 0;
				nrPairs++;
			}
		}
		return ranked;
	}
	
	public int grid(){
		
		int suitedSequence = suitedSequence();
		//System.out.println(" = " + Integer.toBinaryString(suitedSequence));
		int[] unsuitedSequence = sequence((hand[0] | hand[1] | hand[2] | hand[3]) & 0xFFFF0);
		//System.out.println(" = " + Integer.toBinaryString(unsuitedSequence[0]));
		int suited = suited();
		//System.out.println(" = " + Integer.toBinaryString(suited));
		int[] ranked = ranked();
		//System.out.println(" = " + Integer.toBinaryString(ranked[0]));
		
		if((Integer.bitCount(suitedSequence) == 6) || (Integer.bitCount(ranked[0]) == 3) ){
			//System.out.println("1");
			return 0xFFFFF;
			
		}else if((Integer.bitCount(suitedSequence) == 5) && ((suitedSequence & 0xFF00F) == suitedSequence)){ //not sure ifdoing 0xFFFF0	
			//System.out.println("2");
			return suitedSequence;
			
		}else if((Integer.bitCount(ranked[0]) == 2) && ((ranked[0] & 0xF0000) == 0x10000)){
			//System.out.println("3");
			return ranked[0];
			
		}else if((Integer.bitCount(unsuitedSequence[0]) == 5) || (Integer.bitCount(suited) == 6) || (Integer.bitCount(ranked[0]) + Integer.bitCount(ranked[1]) == 3)){
			//System.out.println("4");
			return 0xFFFFF;
			
		}else if(Integer.bitCount(ranked[0]) == 2){
			//System.out.println("5");
			return ranked[0];
			
		}else if(Integer.bitCount(suitedSequence) == 5){
			//System.out.println("6");
			return suitedSequence;
			
		}else if(Integer.bitCount(ranked[0]) + Integer.bitCount(ranked[1]) == 2){
			//System.out.println("7");
			return (ranked[0] | ranked[1]);
			
		}else if((Integer.bitCount(ranked[0]) == 1) && ((ranked[0] & 0xFE000) != 0)){
			//System.out.println("8");
			return ranked[0];
			
		}else if(Integer.bitCount(suited) == 5){
			//System.out.println("9");
			return suited;
			
		}else if(Integer.bitCount(suitedSequence) == 4 && ((suitedSequence & 0xFF00F) == suitedSequence)){
			//System.out.println("10");
			return suitedSequence;
			
		}else if((Integer.bitCount(unsuitedSequence[0]) == 4) && (unsuitedSequence[1] == 1)){
			//System.out.println("11");
			return unsuitedSequence[0];
		}else if((Integer.bitCount(ranked[0]) == 1)){
			//System.out.println("12");
			return ranked[0];
		//AKQJ unsuited??? -> 4 to outside unsuited sequence, and can never be low pair
		}else if(((Integer.bitCount(suitedSequence) == 4) && (Integer.bitCount(suitedSequence & 0xFE00F) >= 3))){
			//System.out.println("14");
			return suitedSequence;
			
		}else if((Integer.bitCount(unsuitedSequence[0]) == 4) && (Integer.bitCount(unsuitedSequence[0] & 0xFE000) == 3)){
			//System.out.println("15");
			return unsuitedSequence[0];
			
		}else if(((hand[0] & 0x0F000) == 0x06000) || ((hand[1] & 0x0F000) == 0x06000) || ((hand[2] & 0x0F000) == 0x06000) || ((hand[3] & 0x0F000) == 0x06000)){
			//System.out.println("16");
			return 0x06000;
			
		}else if((Integer.bitCount(suited) == 4) && (Integer.bitCount(suited & 0xFE00F) == 3)){
			//System.out.println("17");
			return suited;
			
		}else if(Integer.bitCount(hand[0] & 0x1E000) == 2){
			//System.out.println("18");
			return hand[0] & 0x1E00F;
		}else if(Integer.bitCount(hand[1] & 0x1E000) == 2){
			//System.out.println("18");
			return hand[1] & 0x1E00F;
		}else if(Integer.bitCount(hand[2] & 0x1E000) == 2){
			//System.out.println("18");
			return hand[2] & 0x1E00F;
		} else if(Integer.bitCount(hand[3] & 0x1E000) == 2){
			//System.out.println("18");
			return hand[3] & 0x1E00F;
			
		}else if((Integer.bitCount(unsuitedSequence[0]) == 4) && (Integer.bitCount(unsuitedSequence[0] & 0xFE000) == 2)){
			//System.out.println("19");
			return unsuitedSequence[0];
			
		}else if(((Integer.bitCount(suitedSequence) == 4) && (Integer.bitCount(suitedSequence & 0xFE00F) == 2))){
			//System.out.println("20");
			return suitedSequence;
			
		}else if((Integer.bitCount(unsuitedSequence[0]) == 4) && (Integer.bitCount(unsuitedSequence[0] & 0xFE000) == 1)){
			//System.out.println("21");
			return unsuitedSequence[0];
			
		}else if(((hand[0] | hand[1] | hand[2] | hand[3]) & 0x0F000) == 0x0E000){
			//System.out.println("22");
			return 0x0E000;
			
		}else if((hand[0] & 0x0F000) == 0x03000){
			//System.out.println("23");
			return hand[0] & 0x0F00F;
		}else if((hand[1] & 0x0F000) == 0x03000){
			//System.out.println("23");
			return hand[1] & 0x0F00F;
		}else if((hand[2] & 0x0F000) == 0x03000){
			//System.out.println("23");
			return hand[2] & 0x0F00F;
		}else if((hand[3] & 0x0F000) == 0x03000){
			//System.out.println("23");
			return hand[2] & 0x0F00F;
			
		}else if(((hand[0] | hand[1] | hand[2] | hand[3]) & 0x06000) == 0x06000){
			//System.out.println("24");
			return 0x06000;
			
		}else if((Integer.bitCount(suited) == 4) && Integer.bitCount(suited & 0xFE00F) == 2 ){
			//System.out.println("25");
			return suited;
					
		}else if((hand[0] & 0x0F000) == 0x05000){
			//System.out.println("26");
			return hand[0] & 0x0F00F;
		}else if((hand[1] & 0x0F000) == 0x05000){
			//System.out.println("26");
			return hand[1] & 0x0F00F;
		}else if((hand[2] & 0x0F000) == 0x05000){
			//System.out.println("26");
			return hand[2] & 0x0F00F;
		}else if((hand[3] & 0x0F000) == 0x05000){
			//System.out.println("26");
			return hand[3] & 0x0F00F; 
			
		}else if(((Integer.bitCount(suitedSequence) == 4) && (Integer.bitCount(suitedSequence & 0xFE00F) == 1))){
			//System.out.println("27");
			return suitedSequence;
			
		}else if(Integer.bitCount((hand[0] | hand[1] | hand[2] | hand[3]) & 0x0E000) == 2){
			//System.out.println("28");
			return 0x0E000;
			
		}else if(((hand[0] | hand[1] | hand[2] | hand[3]) & 0x10000) != 0){
			//System.out.println("29");
			return 0x10000;
			
		}else if((hand[0] & 0x0F000) == 0x09000){
			//System.out.println("30");
			return hand[0] & 0x0F00F;
		}else if((hand[1] & 0x0F000) == 0x09000){
			//System.out.println("30");
			return hand[1] & 0x0F00F;
		}else if((hand[2] & 0x0F000) == 0x09000){
			//System.out.println("30");
			return hand[2] & 0x0F00F;
		}else if((hand[3] & 0x0F000) == 0x09000){
			//System.out.println("30");
			return hand[3] & 0x0F00F;
			
		}else if(((hand[0] | hand[1] | hand[2] | hand[3]) & 0x0E000) != 0){
			//System.out.println("31");
			return 0x0E000;
			
			
		}else if(Integer.bitCount(unsuitedSequence[0]) == 4){
			//System.out.println("32");
			return unsuitedSequence[0];
			
		}else if(Integer.bitCount(suited) == 4){
			//System.out.println("33");
			return suited;
		}
		return 0;
			
	}
}
