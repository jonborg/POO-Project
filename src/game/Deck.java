package game;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


public class Deck {
	
	public HashMap<Integer,Card> cards;
	public ArrayList<Integer> order;
	
	public Deck(){
		
		cards=new HashMap<Integer,Card>();
		for (short naipe=0;naipe<4;naipe++){
			for (short number=0;number<13;number++){
				cards.put(naipe*13+(number+1),new Card(naipe,number));
				
				
			}
		}
		
	}
	
	public void shuffle(){
		order=new ArrayList<Integer>();
		for (int i=1;i<=52;i++){
			order.add(i);
		}
		Collections.shuffle(order);
	}
	
	public Card draw(){
		Card drawCard=cards.get(order.get(0));
		order.remove(0);
		return drawCard;
	}
	
	
	public String toString(){
		String deck = "";
		System.out.println(order.size());
		for (int i=0;i<order.size();i++){
			deck=deck+String.valueOf(i+1)+"º-" +cards.get(order.get(i)).toString()+ " ";
		}
		return deck;
	}
	
	public static void main(String[] args){
		Deck deck=new Deck();
		User user = new User(5000);
		
		deck.shuffle();
		System.out.println(deck.toString());
		
		user.draw(deck);
		System.out.println(deck.toString());
		
		user.showHand();
		user.showCredit();
		
		user.discard(3);
		user.discard(2);
		user.draw(deck);
		user.showHand();
		
	}
	
}
