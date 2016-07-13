package task2_CardsWarGame;

import java.util.ArrayList;
import java.util.Scanner;

public class GameDemo {

	public static void main(String[] args) {
		// Prints the name of the game: 
		System.out.println("#######################################################################################################################################");
		System.out.println("xxxxxxxxxxxxx                                            WAR GAME (CARDS HURT)                                            xxxxxxxxxxxxx");
		System.out.println("#######################################################################################################################################\n\n");
		
		// Scanner:
		Scanner sc = new Scanner(System.in);
		
		// The names of the two players:
		String namePlayer1=new String();
		String namePlayer2=new String();
		
		// Message in the console asking for the two players' names:	
		// Player 1:
		System.out.println("Enter the name of the first player!");
		do {
			System.out.print("Player 1 name: ");
			String inputName=sc.nextLine();
			if (inputName.matches("^^[A-Z].*[a-z+ ]+")) { // Validating the name - at least the first Letter must be UpperCase
				namePlayer1=inputName;
			}
			else {
				System.out.println("Invalid name! Please try again! (The name must start with an uppercase letter and must contain letters only)");
			}
		}
		while (namePlayer1.isEmpty());
		
		// Player 2:
		System.out.println("Now enter the name of the second player!");
		do {
			System.out.print("Player 2 name: ");
			String inputName=sc.nextLine();
			if (inputName.matches("^^[A-Z].*[a-z+ ]+")) { // Validating the name - at least the first Letter must be UpperCase
				namePlayer2=inputName;
			}
			else {
				System.out.println("Invalid name! Please try again! (The name must start with an uppercase letter and must contain letters only)");
			}
		}
		while (namePlayer2.isEmpty());
	
		sc.close();
		
		// Creates the two players:
		Player player1 = new Player(namePlayer1);
		Player player2 = new Player(namePlayer2);
		
		// Creates the Deck:
		Deck deck = new Deck(); // New Deck (sorted and containing all 52 cards)
		deck.shuffle(); // Shuffles the deck
		deck.deal(player1, player2); // Deals the cards to the two players
		
		// Game: ..........................................
		while (true) {
			// Prints the stats for the game: ..............
			printStatsForGame(player1, player2); // Prints the stats

			// Prints Battle: ..............
			System.out.println("**************************************************************************************************************************************");
			System.out.print("[[ BATTLE: ");
			// Prints decks:
			
			// Player 1:
			player1.getDeck().getDeck().get(0).showCard();
			System.out.print(" vs. ");
			// Player 2:
			player2.getDeck().getDeck().get(0).showCard();
			System.out.println(" ]]\n");
			System.out.println("**************************************************************************************************************************************");
			
			// Player 1 battles Player 2: ..............
			player1.battle(player2, player1.putOneCard(), player2.putOneCard(), new ArrayList<Card>(), 1);
	
			// If the two players' decks are played (no cards in either one of them): ..............
			if (player1.getDeck().getDeck().size()==0 && player2.getDeck().getDeck().size()==0) { // No more cards in decks
				if (player1.getCardsWon().size()>player2.getCardsWon().size()) { // If the first player won more cards
					printCardsWonFinal(player1, player2); // Printing the final stats (cards won)
					player1.assignAsWinner(); // Player 1 becomes the winner!!!
					return; // Ends the game
				}
				else {
					if (player1.getCardsWon().size()<player2.getCardsWon().size()) { // If the second player won more cards
						printCardsWonFinal(player1, player2); // Printing the final stats (cards won)
						player2.assignAsWinner(); // Player 2 becomes the winner!!!
						return; // Ends the game
					}
					else { // If the two players won equal amount of cards:
						printCardsWonFinal(player1, player2); // Printing the final stats (cards won)
						System.out.println("\n\n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n");
						System.out.println("                                                         IT'S A DRAW!!"); // The game is a draw!
						return; // Ends the game
					}
				}
			}
		}
	}
	static void printStatsForGame(Player player1, Player player2) {
		System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Stats for the two players <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n");
		// Cards Won: 
		System.out.println("------------------------------------------------------------- Cards won: -------------------------------------------------------------\n");
		// Player 1:
		player1.printCardsWon(); // Prints Player1's cards won
		System.out.println();
		// Player 2:
		player2.printCardsWon(); // Prints Player1's cards won
		
		// Decks:
		System.out.println("\n\n--------------------------------------------------------------- Decks: ---------------------------------------------------------------\n");
		// Player 1:
		System.out.print(player1.getName()+": "); // Prints the player's name
		for (int i = 0; i < player1.getDeck().getDeck().size(); i++) { // Prints Player1's deck
			player1.getDeck().getDeck().get(i).showCard();
			if (i!=player1.getDeck().getDeck().size()-1) { // The ", " will not appear after the last element
				System.out.print(", ");
			}
		}
		// Player 2:
		System.out.println("\n====================");
		System.out.print(player2.getName()+": "); // Prints the player's name
		for (int i = 0; i < player2.getDeck().getDeck().size(); i++) { // Prints Player2's deck
			player2.getDeck().getDeck().get(i).showCard();
			if (i!=player2.getDeck().getDeck().size()-1) { // The ", " will not appear after the last element
				System.out.print(", ");
			}
		}
		System.out.println("\n====================\n");
	}
	
	static void printCardsWonFinal(Player player1, Player player2) {
		System.out.println("\n--------------------------------------------------------- Cards won (final): ---------------------------------------------------------\n");
		// Player 1:
		player1.printCardsWon();
		System.out.println();
		// Player 2:
		player2.printCardsWon();
	}
}