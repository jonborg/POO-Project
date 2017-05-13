package gameObjects;

public class Card extends CardMask implements Comparable<Card>{
	private int rank, suit, bitMask;
	
	public Card(int suit, int rank){
		super();
		this.rank = rank;
		this.suit = suit;
		this.bitMask = (ranks[rank] | suits[suit]);
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
	
	@Override
	public String toString(){
		return ranksToString[rank] + suitsToString[suit];
	}
	
	@Override
	public int compareTo(final Card o){
		return Integer.compare(this.rank, o.rank);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + rank;
		result = prime * result + suit;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Card other = (Card) obj;
		if (bitMask != other.bitMask)
			return false;
		if (rank != other.rank)
			return false;
		if (suit != other.suit)
			return false;
		return true;
	}

}
