package videoPoker;

public class CardMask {
	static int ACE = 0b00010000000000000000;
	static int KING = 0b00001000000000000000;
	static int QUEEN = 0b00000100000000000000;
	static int JACK = 0b00000010000000000000;
	static int TEN  = 0b00000001000000000000;
	static int NINE = 0b00000000100000000000;
	static int EIGHT = 0b00000000010000000000;
	static int SEVEN = 0b00000000001000000000;
	static int SIX = 0b00000000000100000000;
	static int FIVE = 0b00000000000010000000;
	static int FOUR = 0b00000000000001000000;
	static int THREE = 0b00000000000000100000;
	static int TWO = 0b00000000000000010000;
	
	static int HEARTS = 0b00000000000000001000;
	static int SPADES = 0b00000000000000000100;
	static int DIAMONDS = 0b00000000000000000010; 
	static int CLUBS = 0b00000000000000000001;
	
	static String[] suitsToString = { "H", "S", "D", "C" };
	static int[] suits = { HEARTS, SPADES, DIAMONDS, CLUBS };
	static String[] ranksToString = { "A", "K", "Q", "J", "T", "9", "8", "7", "6", "5", "4", "3", "2" };
	static int[] ranks = { ACE, KING, QUEEN, JACK, TEN, NINE, EIGHT, SEVEN, SIX, FIVE, FOUR, THREE, TWO };
}
