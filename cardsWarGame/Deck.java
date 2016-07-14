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

	// Getters:
	public ArrayList<Card> getDeck() {
		ArrayList<Card> tempDeck = new ArrayList<Card>(); // A temporary ArrayList to return as a result
		tempDeck.addAll(this.deck); // Duplicating the ArrayList
		return tempDeck; // Returning the duplicate so that the original remains the same
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
	
	public void clearDeck() { // Clears the deck
		this.deck.clear();
	}
}