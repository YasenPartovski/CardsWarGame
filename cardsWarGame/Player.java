package task2_CardsWarGame;

import java.util.ArrayList;

public class Player {

	private String name;
	private Deck deck;
	private ArrayList<Card> cardsWon;
	private boolean isAWinner=false; // by default
	
	// Constructors:
	public Player(String name) {
		if (name!=null && name.matches("^^[A-Z].*[a-z+ ]+")) { // Validating the name - at least the first Letter must be UpperCase
			this.name=name;
		}
		cardsWon=new ArrayList<Card>(); // initializes the cardsWon ArrayList
	}
	
	// Setters & Getters:
	public String getName() {
		return this.name;
	}
	
	public Deck getDeck() {
		return this.deck;
	}
	
	public void setDeck(Deck deck) {
		if (deck!=null) {
			this.deck=deck;
		}
	}
	
	public ArrayList<Card> getCardsWon() {
		ArrayList<Card> tempCardsWon = new ArrayList<Card>(); // A temporary ArrayList to return as a result
		for (int i = 0; i < cardsWon.size(); i++) {
			tempCardsWon.add(cardsWon.get(i)); // Duplicating the ArrayList
		}
		return tempCardsWon; // Returning the duplicate so that the original remains the same
	}

	public void addCardsWon(ArrayList<Card> cardsWon) { // Adds cards to the cardsWon filed of the Player (works as a setter, but it appends cards)
		if (cardsWon!=null && !cardsWon.isEmpty()) {
			this.cardsWon.addAll(cardsWon);
		}
	}
	
	public boolean isAWinner() {
		return this.isAWinner;
	}

	public void setAWinner(boolean isAWinner) {
		if (isAWinner==true) { // Sets the field if the player wins
			this.isAWinner = isAWinner;
		}
	}
	


	// Methods:
	public void collectCards(ArrayList<Card> warLoot) { 
		if (warLoot!=null && !warLoot.isEmpty()) {
			this.addCardsWon(warLoot); // Adds the warLoot to the cardsWon Array List of the Player
		}
	}
	
	public Card putOneCard() { // Returns the first card from the Player's deck
		Card oneCard = this.deck.getDeck().get(0); // The one card gets the first card in the Player's deck
		this.deck.getDeck().remove(0); // Removes it form the Player's deck
		return oneCard; // Returns the card
	}
	
	public Card[] putThreeCards() { // Returns the first three cards from the Player's deck
		Card[] threeCards = new Card[3]; // The array with the three cards
		for (int i = 2; i >= 0; i--) {
			threeCards[i]=this.deck.getDeck().get(0); // The three-card deck take the first from the player's deck and puts it last in the three-card array
			this.deck.getDeck().remove(0); // Than the card is removed from the Player's deck
		}
		return threeCards; // Returns the three-card deck
	}
	
	public void assignAsWinner() {
		this.setAWinner(true); 
		System.out.println(this.getName()+" WINS!!!");
	}


	
}
