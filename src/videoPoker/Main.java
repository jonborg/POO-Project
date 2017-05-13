package videoPoker;

public class Main {
	public static void main(String[] args){
		IGameType mode;
		ParentGame game = new ParentGame(0);
		int[] arg = new int[3];
		
		
		switch(args[0]){
			case "-i":
				System.out.println("Interactive mode: " + Integer.parseInt(args[1]));
				mode = new InteractiveMode();
				game = mode.select(new Interactive(0), new Debug(0), new Simulation(arg), Integer.parseInt(args[1]));
				break;
				
			case "-d":
				System.out.println("Debug mode: Credit = " + Integer.parseInt(args[1]) + "; CMD-File = " + args[2] + "; Card-File = " + args[3]);		
				break;
				
			case "-s":
				System.out.println("Simulation mode: Credit = " + Integer.parseInt(args[1]) + "; Bet = " + args[2] + "; NbDeals= " + args[3]);
				arg[0] = Integer.parseInt(args[1]);
				arg[1] = Integer.parseInt(args[2]);
				arg[2] = Integer.parseInt(args[3]);
				mode = new SimulationMode();
				game = mode.select(new Interactive(0), new Debug(0), new Simulation(arg), arg);
				break;
			
			case "-g":
				//gui
				break;
		}
		
		game.run();
		
	}
}
