package videoPoker;

import java.io.*;
import exceptions.IllegalCOmmandException;

public class Interactive extends ParentGame implements IGame {

	@Override
	public ParentGame apply(Object x) {
		System.out.println("Running on Interactive mode...");
		return new Interactive((int) x);
	}
	
	public Interactive(int initCredit){
		super(initCredit);
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
				if(command.charAt(1) != ' ') throw new IllegalCOmmandException("?");
			}
			if((c!='b') && (c!='$') && (c!='d') && (c!='h') && (c!='a') && (c!='s') && (c!='q')) throw new IllegalCOmmandException("?");
			if((c=='b') && (d || b)) throw new IllegalCOmmandException("b");
			if((c=='d')&&(!b || d)) throw new IllegalCOmmandException("d");
			if((c=='a')&&(!b || !d || h)) throw new IllegalCOmmandException("ab");
			
		}catch (IllegalCOmmandException e){
			System.out.println(e);
			return "t";
		}
		
		return command;
	}

	public void run(){
		String command = "";
		String c = "";
		int toBet = 5;
		boolean d = false;
		boolean b = false;
		boolean h = false;
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Interactive: \n\n");
		do{
			try{
				command = checkExceptions(br, d, b, h);
				c = command.substring(0, 1);
			}catch (IOException e){
				System.out.println(e);
				c = "t";
			}
			
			switch(c){
				case "q":
					System.out.println("leaving game!");
					return;
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
					}
					b = true;
					bet(toBet);
					break;
					
				case "$":
					System.out.println("Credit: " + credit());
					break;
					
				case "d":
					deal();
					System.out.println(player.getHand());
					d = true;
					break;
					
				case "h":
					int[] toHold = new int[5];
					if(command.length() > 1){
						String[] toKeep = command.substring(2).split(" ");
						for(int j = 0; j<toKeep.length; j++){
							toHold[Integer.parseInt(toKeep[j])-1] = 1; 
						}
					}else{
						System.out.println("loose it all");
					}					
				
					hold(toHold);
					System.out.println(player.getHand());
					System.out.println(play(toBet));
					d = false;
					b = false;
					h = false;
					System.out.println("\n           -New round!-           ");
					break;
					
				case "a":
					int[] adv = advice();
					String out = "";
					for(int i = 0; i<5; i++){
						if(adv[i] == 1){
							out += (i+1) + " ";
						}
					}
					if(out == ""){
						System.out.println("Discard all cards");
					}else{
						System.out.println("Keep card(s) " + out);
					}
					break;
					
				case "s":
					System.out.println(statistics());
					break;
				
				default: 
					break;
			}
				
		}while(player.getCredit()>(player.getCredit()-toBet)); 	
	}
	
}
