package videoPoker;

import java.io.*;
import java.util.ArrayList;

public class Debug implements IGame {
	
	public static void main(String[] args){
		Debug Game = new Debug();
		Player player = new Player(500);
		Deck deck;
		deck = new Deck(readFile("card-file.txt").split(" "));
		System.out.println(deck.toString());
		Game.run(player, deck);			
	}
	
		
	public static String readFile(String file){
		try{
			BufferedReader line = new BufferedReader(new FileReader(file));
			return line.readLine();
		}catch(IOException e){
			System.out.println(e.getMessage());
			System.exit(1);
		}
		return null;
		
	}
	
	public String[] separateCommands(String line){
		String[] commands=new String[line.length()];
		int previousIndex=0;
		int index=0;
		int i=1;
		while(i<line.length()){
			if (line.charAt(i)=='b' || line.charAt(i)=='$' || line.charAt(i)=='d' || line.charAt(i)=='h' || line.charAt(i)=='a' || line.charAt(i)=='s'){
				commands[index]=line.substring(previousIndex,i-1);
				System.out.println(commands[index]);
				previousIndex=i;
				index++;
			}
			i++;
		}		
		return commands;
	}
		
	@Override
	public void run(Player player, Deck deck) {
		String[] commands;
		String c;
		commands = separateCommands(readFile("cmd-file.txt"));
		int toBet;
		int i=0;
		
		while (commands[i]!=null){
			c=commands[i].substring(0,1);
			System.out.println(commands[i]);
			
			switch(c){ 
			case "t":
				break;
				
			case "b":
				if(commands[i].length() > 1){
					try{
					toBet = Integer.parseInt(commands[i].substring(2));
					}catch(NumberFormatException e){
						System.out.println("Error: Invalid Bet Value... Try Again!\n");
						break;
					}
				}else{
					toBet = 5;
				}
				
				bet(player, toBet);
				System.out.println("Betting: " + toBet);
				break;
				
			case "$":
				credit(player);
				System.out.println("Credit: ");
				break;
				
			case "d":
				deal(player, deck);
				
				break;
				
			case "h":
				int[] toHold = {0};
				if(commands[i].length() > 1){
					String[] toKeep = commands[i].substring(2).split(" ");
					toHold = new int[toKeep.length];
					int j = 0;
					for(String index : toKeep){
						toHold[j++] = Integer.parseInt(index);
					}
				}
				hold(player, deck, toHold);
				System.out.println("Deal: ");
				
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
			i++;
		}
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
