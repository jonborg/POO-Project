package videoPoker;

public final class Card extends CardMask{
	private int rank, suit, bitMask;
	
	Card(int suit, int rank){
		this.rank = rank;
		this.suit = suit;
		this.bitMask = ranks[rank] | suits[suit];
	}
	
	@Override
	public String toString() {
		return ranksToString[rank] + " of " + suitsToString[suit];
	}
	
	public int getBitMask(){
		return bitMask;
	}
	
	public int getRank(){
		return rank;
	}
	
	public int getSuit(){
		return suit;
	}
}
