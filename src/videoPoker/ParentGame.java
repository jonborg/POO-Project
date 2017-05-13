package videoPoker;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import gameObjects.*;

public class ParentGame implements IGame{
	double[] statistics = new double[12];
	public Player player;
	Deck deck;
	
	public ParentGame(int initCredit){
		this.player = new Player(initCredit);
		this.statistics[11] = initCredit;
		this.deck = new Deck();
	}
	
	public ParentGame apply(Object x) {
		return null;
	}
	
	public void run(){
		System.out.println("Undefined gameType!");
	}
	
	public String play(int toBet){
		double reward;
		HandEval evaluator = new HandEval(player.getHand().getcards(), player.getHand().gethand());
		
		reward = evaluator.evaluate(statistics);
		cleanHand();
		if(reward > 0){
			if((reward == 250) && (toBet == 5)){
				player.addCredit(4000);
			}else{
			player.addCredit(toBet*reward);
			}
			return "player wins with a " + evaluator.toString() + " and his credit is " + player.getCredit();
		}else{
			return "player loses and his credit is " + player.getCredit();
		}
		 
	}
			
	public void bet(int toBet){
		player.removeCredit(toBet);
	}
	
	public void deal(){
		deck.shuffle();
		
		for(int i = 0; i<5; i++){
			player.getHand().add(deck.drawFromDeck());
		}
		player.getHand().genHand();
	}
	
	public int credit(){
		return (int) player.getCredit();
	}

	public void hold(int[] toHold){
		ArrayList<Card> toRemove = new ArrayList<Card>();
		
		for(int i = 0; i<5; i++){
			if(toHold[i] == 0){
				toRemove.add(player.getHand().getcards().get(i));
			}
		}
		
		while(toRemove.size() > 0){
			deck.add(toRemove.get(0));
			player.getHand().getcards().remove(toRemove.get(0));
			player.getHand().add(deck.drawFromDeck());
			deck.add(toRemove.remove(0));
		}
	}
	
	public int[] advice(){
		PerfectStrategy adVice = new PerfectStrategy(player.getHand().getcards(), player.getHand().gethand());
		int bit = adVice.grid();
		return adVice.toRemove(bit);
	}
	
	public String statistics(){
		String out = "";
		double percent = ((((player.getCredit())-(statistics[11]))/(statistics[11])))*100;
		
		for(int i = 0; i<10; i++){
			statistics[10] += statistics[i];
		}
		
		
		out += " Hand                   Nb\n";
		out += "_______________________________\n";
		out += "Jacks or Better       "+ (int) Math.round(statistics[0]) + "\n";
		out += "Two Pair              "+ (int) Math.round(statistics[1]) + "\n";
		out += "Three of a Kind       "+ (int) Math.round(statistics[2]) + "\n";
		out += "Straight              "+ (int) Math.round(statistics[3]) + "\n";
		out += "Flush                 "+ (int) Math.round(statistics[4]) + "\n";
		out += "Full House            "+ (int) Math.round(statistics[5]) + "\n";
		out += "Four of a Kind        "+ (int) Math.round(statistics[6]) + "\n";
		out += "Straight Flush        "+ (int) Math.round(statistics[7]) + "\n";
		out += "Royal Flush           "+ (int) Math.round(statistics[8]) + "\n";
		out += "Other                 "+ (int) Math.round(statistics[9]) + "\n";
		out += "_____________________________\n";
		out += "Total                 "+ (int) Math.round(statistics[10]) + "\n";
		out += "_____________________________\n";
		try{
			out += "Credit                  "+ player.getCredit() + "(" + percent + ")\n";
		}catch(ArithmeticException e){
			out += "Credit                  "+ player.getCredit() + "(N/A)\n";
		}
		return out;
	}

	public void cleanHand(){
		while(player.getHand().getcards().size() > 0){
			deck.add(player.getHand().getcards().get(0));
			player.getHand().getcards().remove(0);
		}
	}
}
