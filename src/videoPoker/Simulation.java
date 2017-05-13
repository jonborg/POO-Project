package videoPoker;

public class Simulation extends ParentGame implements IGame {
	private int nbDeals, toBet;
	
	public Simulation(int[] args){
		super(args[0]);
		this.toBet = args[1];
		this.nbDeals = args[2];
	}
	
	@Override
	public ParentGame apply(Object x) {
		System.out.println("Running on Simulation mode...");
		return new Simulation((int[]) x);
	}
	
	public void run(){
		while((nbDeals > 0)){
			nbDeals--;
			
			if(player.getCredit()-toBet <= 0){
				System.out.println("Player ran out of Credit...\nLeaving Game!");
				break;
			}
			
			bet(toBet);
			deal();
			System.out.println("_______________________________________");
			System.out.println("Deal: " + player.getHand());
			hold(advice());
			System.out.println("After Descard turn: " + player.getHand());
			System.out.println(play(toBet));
			System.out.println("_______________________________________");
		}
		System.out.println(statistics());
	}

}
