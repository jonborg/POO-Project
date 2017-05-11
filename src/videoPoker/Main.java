package videoPoker;

public class Main {
	public static void main(String[] args){
		IGameType mode;
		ParentGame game = new ParentGame();
		switch(args[0]){
			case "-i":
				System.out.println("Interactive mode: " + Integer.parseInt(args[1]));
				mode = new InteractiveMode();
				game = mode.select(new Interactive(), new Debug(), new Simulation(), "Interactive");
				break;
				
			case "-d":
				System.out.println("Debug mode: Credit = " + Integer.parseInt(args[1]) + "; CMD-File = " + args[2] + "; Card-File = " + args[3]);		
				break;
				
			case "-s":
				System.out.println("Simulation mode: Credit = " + Integer.parseInt(args[1]) + "; Bet = " + args[2] + "; NbDeals= " + args[3]);
				break;
		}
		
		game.statistics(50);
	}
}
