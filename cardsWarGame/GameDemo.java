package task2_CardsWarGame;

public class GameDemo {

	public static void main(String[] args) {
		
		
		Player player1 = new Player("John Green"); // New Players
		Player player2 = new Player("Merry Allen");
		
		Deck deck = new Deck(); // New Deck (sorted and containing all 52 cards)
		
		deck.shuffle(); // Shuffles the deck
		
		deck.deal(player1, player2); // Deals the cards to the two players
		
		// Game:
		
		for (int i = 0; i < player1.getDeck().getDeck().size(); i++) {
			player1.getDeck().getDeck().get(i).showCard();
			System.out.print(", ");
		}
		System.out.println("\n====================");
		for (int i = 0; i < player2.getDeck().getDeck().size(); i++) {
			player2.getDeck().getDeck().get(i).showCard();
			System.out.print(", ");
		}
		
		
	}
}
