package videoPoker;

public interface IGame {
	int[] statistics = new int[12];
	
	void run(Player player, Deck deck);
	void bet(Player player, int toBet);
	void credit(Player player);
	void deal(Player player, Deck deck);
	void hold(Player player, Deck deck, int[] toHold);
	void advice(Player player);
	void statistics(int credit);

}
