package videoPoker;

import java.io.*;

public class Debug implements IGame {
	
	public static void main(String[] args){
		Debug Game = new Debug();
		Player player = new Player(500);
		Deck deck = new Deck();	
		Game.run(player, deck);
	}
	
		
	public BufferedReader loadFile(String file){
		try{
			BufferedReader line = new BufferedReader(new FileReader(file));
			return line;
		}catch(FileNotFoundException exception){
			System.out.println(exception.getMessage());
		}
		return null;
		
	}
	
		
	@Override
	public void run(Player player, Deck deck) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bet(Player player, int toBet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void credit(Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deal(Player player, Deck deck) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hold(Player player, Deck deck, int[] toHold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void advice(Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void statistics(int credit) {
		// TODO Auto-generated method stub
		
	}


	
}