package task2_CardsWarGame;

public class Card {

	private String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"}; // Reference for the possible card ranks
	private String[] suits = {"♣", "♦", "♥", "♠"}; // Reference for the possible card suits
	private String rank; // The card's rank
	private String suit; // The card's suit
	
	// Constructors:
	public Card (String rank, String suit) {
		for (int i = 0; i < ranks.length; i++) {
			for (int j = 0; j < suits.length; j++) {
				if (rank.equals(ranks[i])) { // If the input rank is an available rank
					this.rank=rank;
				}
				if (suit.equals(suits[j])) { // If the input suit is an available suit
					this.suit=suit;
				}
				if (this.rank!=null && this.suit!=null) { // If the two fields of Card are assigned - breaks the loop
					break;
				}
			}
		}
	}
	
	// Getters:
	public String[] getRanks() { // Returns a String array with all the sorted ranks for reference
		String[] tempRanks = new String[13];
		for (int i = 0; i < this.ranks.length; i++) {
			tempRanks[i]=this.ranks[i];
		}
		return tempRanks; // Returns a duplicate of the original String array
	}

	public String getRank() {
		return this.rank;
	}

	public String getSuit() {
		return this.suit;
	}
	
	// Methods:
	public void showCard() {
		if (this!=null) {
			System.out.print(this.getRank()+this.getSuit()); // Prints the rank and suit of the card
		}
	}
}