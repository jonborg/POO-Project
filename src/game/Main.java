package game;

public class Main {
	
	/*Interactive Mode*/
	public static void interactive(int credit){
		Deck deck =new Deck();
		User user=new User(credit);
		Player pc=new Player();
		
		while(true){
			deck.shuffle();
			
		}
		
		
	}
	
	
	/*Debug Mode*/
	public static void debug(int credit, String cmdFile, String cardFile){
		
	}
	
	
	/*Simulation Mode*/
	public static void simulation(int credit, int bet, int nbdeals){
	
	}
	
	
	
	public static void main(String[] args){
		System.out.println(args[0]);
		switch(args[0]){
			case "-i":
				System.out.println("Interactive Mode\n");
				interactive(Integer.parseInt(args[1]));
				break;
			case "-d":
				System.out.println("Debug Mode\n");
				debug(Integer.parseInt(args[1]),args[2],args[3]);
				break;
			case "-s":
				System.out.println("Simulation Mode\n");
				simulation(Integer.parseInt(args[1]),Integer.parseInt(args[2]),Integer.parseInt(args[3]));
				break;
			default:
				System.out.println("ERROR: Wrong Input for Mode:");
				System.out.println("Interactive Mode: -i");
				System.out.println("Debug Mode: -d");
				System.out.println("Simulation Mode: -s");
		}
		
		
	}

}
