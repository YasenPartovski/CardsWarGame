package task2_CardsWarGame;

import java.util.ArrayList;
import java.util.Random;

public class Deck {

	private String[] rank = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"}; // Reference for the possible card ranks
	private String[] suit = {"♣", "♦", "♥", "♠"}; // Reference for the possible card suits
	private ArrayList<Card> deck;
	
	// Constructors:
	public Deck() { // The default sorted deck
		this.deck=new ArrayList<Card>();
		for (int i = 0; i < rank.length; i++) {
			for (int j = 0; j < suit.length; j++) {
				deck.add(new Card(rank[i], suit[j]));
			}
		}

	}
	
	public Deck (ArrayList<Card> deck) { // Custom deck represented by a certain ArrayList
		if (deck!=null && !deck.isEmpty()) {
			this.deck=deck;
		}
		
	}

	// Setters & Getters:
	public ArrayList<Card> getDeck() {
		return this.deck;
	}

	// Methods:
	public void shuffle() { // Shuffles the deck
		ArrayList<Card> tempDeck = new ArrayList<Card>();
		while (!this.deck.isEmpty()) {
			int tempIndex = new Random().nextInt(this.deck.size());
			tempDeck.add(this.deck.get(tempIndex));
			this.deck.remove(tempIndex);	
		}
		this.deck=tempDeck; // The old deck becomes the new shuffled deck
	}
	
	public void deal(final Player player1, final Player player2) { // Splits the main deck in two and deals the two halves to the players
		if (player1!=null && player2!=null) { // If the players exist
			ArrayList<Card> tempHalfDeck1 = new ArrayList<Card>(); // Temporary ArrayList for the player 1 cards (first half of main Deck)
			ArrayList<Card> tempHalfDeck2 = new ArrayList<Card>(); // Temporary ArrayList for the player 2 cards (second half of main Deck)
			for (int i = 0; i < this.deck.size()/2; i++) {
				tempHalfDeck1.add(this.deck.get(i));
			}
			player1.setDeck(new Deck(tempHalfDeck1)); // Player 1 takes the first half of the cards in the main deck
			
			for (int i = this.deck.size()/2; i < this.deck.size(); i++) {
				tempHalfDeck2.add(this.deck.get(i));
			}
			player2.setDeck(new Deck(tempHalfDeck2)); // Player 1 takes the second half of the cards in the main deck
			this.deck.clear(); // The main deck is cleared (there are no cards in it anymore)
		}
	}
}