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
		
		// Creates the Deck:
		Deck deck = new Deck(); // New Deck (sorted and containing all 52 cards)
		deck.shuffle(); // Shuffles the deck
		
		ArrayList<Card> handPlayer1=new ArrayList<Card>(); // Player 1's hand (cards in hand)
		ArrayList<Card> handPlayer2=new ArrayList<Card>(); // Player 2's hand (cards in hand)
		
		deal(deck.getDeck(), handPlayer1, handPlayer2); // Divides the cards in two stacks for the two players
		deck.clearDeck(); // Clears the deck (there have to be no cards left in it)
		
		// Creates the two players and deals them their cards:
		Player player1=new Player(namePlayer1, handPlayer1);
		Player player2=new Player(namePlayer2, handPlayer2);
	
		// Game: ..........................................
		while (true) {
			// Prints the stats for the game: ..............
			printStatsForGame(player1, player2); // Prints the stats

			// Prints Battle: ..............
			System.out.println("**************************************************************************************************************************************");
			System.out.print("[[ BATTLE: ");
			// Prints decks:
			
			// Player 1:
			player1.getHand().get(0).showCard();
			System.out.print(" vs. ");
			// Player 2:
			player2.getHand().get(0).showCard();
			System.out.println(" ]]\n");
			System.out.println("**************************************************************************************************************************************");
			
			// Player 1 battles Player 2: ..............
			player1.battle(player2, player1.putOneCard(), player2.putOneCard(), new ArrayList<Card>(), 1);
	
			// If the two players' decks are played (no cards in either one of them): ..............
			if (player1.getHand().size()==0 && player2.getHand().size()==0) { // No more cards in decks
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
		for (int i = 0; i < player1.getHand().size(); i++) { // Prints Player1's deck
			player1.getHand().get(i).showCard();
			if (i!=player1.getHand().size()-1) { // The ", " will not appear after the last element
				System.out.print(", ");
			}
		}
		// Player 2:
		System.out.println("\n====================");
		System.out.print(player2.getName()+": "); // Prints the player's name
		for (int i = 0; i < player2.getHand().size(); i++) { // Prints Player2's deck
			player2.getHand().get(i).showCard();
			if (i!=player2.getHand().size()-1) { // The ", " will not appear after the last element
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
	
	static void deal(ArrayList<Card> deck, ArrayList<Card> handPlayer1, ArrayList<Card> handPlayer2) { 
		if (deck!=null && !(deck.isEmpty()) && handPlayer1!=null &&  handPlayer2!=null) { // If the deck and the players exist, and the deck is not empty
			for (int i = 0; i < deck.size(); i++) {
				if (i%2==0) { // deals the even cards to player 1
					handPlayer1.add(deck.get(i));
				}
				else { // deals the odd cards to player 2
					handPlayer2.add(deck.get(i));
				}
			}
		}	
	}
}