package task2_CardsWarGame;

public class Card {

	private String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
	private String[] suits = {"♣", "♦", "♥", "♠"};
	
	private String rank;
	private String suit;
	
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

	public String getRank() {
		return this.rank;
	}

	public String getSuit() {
		return this.suit;
	}
	
	public void showCard() {
		System.out.print(this.getRank()+this.getSuit()); // Prints the rank and suit of the card
	}
	
//	public void compareCards(Card card1, Card card2) { // Compares the ranks of the cards (war between two cards)
//		gfdg
//	}

}
