package videoPoker;

public class ParentGame implements IGame{
	int[] statistics = new int[12]; 
	
	public ParentGame apply(Object x) {
		return null;
	}
	
	public void bet(){}
	public void credit(){}
	public void hold(){}
	public void advice(){}
	public void statistics(int initCredit){
		for(int i = 0; i<10; i++){
			statistics[10] += statistics[i];
		}
		
		System.out.println(" Hand                      Nb");
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
		try{
		System.out.println("Credit                  "+ statistics[11] + "(" + (Math.abs(statistics[11]-initCredit)/initCredit) + ")");
		}catch(ArithmeticException e){
			System.out.println("trying to divide by zero!");
			System.out.println("Credit                  "+ statistics[11] + "(N/A)");
		}
	}
}
