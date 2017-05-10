package videoPoker;

import java.io.*;
import java.util.ArrayList;

import exceptions.*;

public class Interactive implements IGame {
	public static void main(String[] args){
		Interactive Game = new Interactive();
		Player player = new Player(500);
		Deck deck = new Deck();
		
		Game.run(player, deck);
	}
	public String checkExceptions(BufferedReader br, boolean d, boolean b, boolean h) throws IOException {
		String command = "";
		char c;
		try{
			command = br.readLine();
			if(command.length() != 0){ 
				c = command.charAt(0);
			}else{
				return "t";
			}
			System.out.println(c);
			
			if((command.length() > 2)){ 
				if(command.charAt(1) != ' ') throw new IllegalCommandException("?");
			}
			if((c!='b') && (c!='$') && (c!='d') && (c!='h') && (c!='a') && (c!='s') && (c!='q')) throw new IllegalCommandException("1");
			if((c=='b')&&d) throw new IllegalCommandException("2");
			if((c=='d')&&(!b || d)) throw new IllegalCommandException("3");
			if((c=='a')&&(!b || !d || h)) throw new IllegalCommandException("4");
			
		}catch (IllegalCommandException e){
			System.out.println(e);
			return "t";
		}
		
		return command;
	}

	@Override
	public void run(Player player, Deck deck) {
		String command = "";
		String c = "";
		int toBet = 5;
		boolean d = false;
		boolean b = false;
		boolean h = false;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println();
		do{
			try{
				command = checkExceptions(br, d, b, h);
				c = command.substring(0, 1);
			}catch (IOException e){
				System.out.println(e);
				c = "t";
			}
			
			switch(c){ 
				case "t":
					break;
					
				case "b":
					if(command.length() > 1){
						try{
						toBet = Integer.parseInt(command.substring(2));
						}catch(NumberFormatException e){
							System.out.println("Error: Invalid Bet Value... Try Again!\n");
							break;
						}
					}else{
						toBet = 5;
					}
					b = true;
					bet(player, toBet);
					System.out.println("Betting: " + toBet);
					break;
					
				case "$":
					credit(player);
					System.out.println("Credit: ");
					break;
					
				case "d":
					deal(player, deck);
					d = true;
					break;
					
				case "h":
					int[] toHold = {0};
					if(command.length() > 1){
						String[] toKeep = command.substring(1).split(" ");
						toHold = new int[toKeep.length];
						int i = 0;
						for(String index : toKeep){
							toHold[i++] = Integer.parseInt(index);
						}
					}
					hold(player, deck, toHold);
					System.out.println("Deal: ");
					h = true;
					break;
					
				case "a":
					//advice();
					System.out.println("Advice: ");
					break;
					
				case "s":
					//statistics();
					System.out.println("Statistics: ");
					break;
				
				default: 
					break;
			}
				
		}while(c != "q"); 	
	}

	@Override
	public void bet(Player player, int toBet) {
		player.removeCredit(toBet);
	}

	@Override
	public void credit(Player player) {
		System.out.println(player.credit);
	}

	@Override
	public void deal(Player player, Deck deck) {
		deck.shuffle();
		//dealing...
		for(int i = 0; i<5; i++){
			player.playerHand.cards.add(deck.drawFromDeck());
		}
	}

	@Override
	public void hold(Player player, Deck deck, int[] toHold) {
		ArrayList<Card> toReturn = new ArrayList<Card>();
		
		if(toHold.length != 5){
			if(toHold[0] != 0){
				for(int i = 0; i<5; i++){
					for(int card : toHold){
						if(i != card){
							toReturn.add(player.playerHand.cards.get(i));
							player.playerHand.replace(i, deck.drawFromDeck());	
						}
					}
				}
			}else{
				for(int i = 0; i<5; i++){
					toReturn.add(player.playerHand.cards.get(i));
					player.playerHand.replace(i, deck.drawFromDeck());	
				}
			}
		
			for(int i = 0; i<toReturn.size(); i++){
				deck.add(toReturn.get(i));
			}
		}
	}

	@Override
	public void advice(Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void statistics(int credit) {
		System.out.println(" Hand                    Nb");
		System.out.println("_______________________________");
		System.out.println("Jacks or Better         "+ statistics[0]);
		System.out.println("Two Pair                "+ statistics[1]);
		System.out.println("Three of a Kind         "+ statistics[2]);
		System.out.println("Straight                "+ statistics[3]);
		System.out.println("Flush                   "+ statistics[4]);
		System.out.println("Full House              "+ statistics[5]);
		System.out.println("Four of a Kind          "+ statistics[6]);
		System.out.println("Straight Flush          "+ statistics[7]);
		System.out.println("Royal Flush             "+ statistics[8]);
		System.out.println("Other                   "+ statistics[9]);
		System.out.println("_______________________________");
		System.out.println("Total                   "+ statistics[10]);
		System.out.println("_______________________________");
		System.out.println("Credit                  "+ statistics[11] + "(" + (Math.abs(statistics[11]-credit)/statistics[11]) + ")");

	}
}
