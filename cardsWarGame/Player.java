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
	
	public ArrayList<Card> putThreeCards() { // Returns the first three cards from the Player's deck
		ArrayList<Card> threeCards = new ArrayList<Card>(); // The array with the three cards
		for (int i = 2; i >= 0; i--) { // 3 times for three cards
			threeCards.add(this.putOneCard()); // The three-card deck take the first from the player's deck and puts it last in the three-card array
		}
		return threeCards; // Returns the three-card deck
	}
	
	public void assignAsWinner() {
		this.setAWinner(true); 
		System.out.println("\n\n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n");
		System.out.println("                                                         "+this.getName()+" WINS!!!");
	}

	public int compareCards(Card card1, Card card2) { // Compares the ranks of the cards (war between two cards)
		if (card1!=null & card2!=null) {
			if (card1.getRank().equals(card2.getRank())) { // If the two cards have equal Ranks
				return 0; // Equal ranks
			}
			else {
				int card1RankIndex=0;
				int card2RankIndex=0;
				int cnt=0;
				for (int i=0; i<card1.getRanks().length; i++) {
					if (card1.getRanks()[i].equals(card1.getRank())) { // If the card rank of card1 matches one of the ranks
						card1RankIndex=i;
						cnt++;
					}
					if (card1.getRanks()[i].equals(card2.getRank())) { // If the card rank of card2 matches one of the ranks
						card2RankIndex=i;
						cnt++;
					}
					if (cnt==2) { // breaks the loop if the two cards have been identified
						break;
					}
				}
				// Comparing the two cards after finding the value of their ranks:
				if (card1RankIndex>card2RankIndex) {
					return 1; // The first card is greater
				}
				else {
					return 2; // The second card is greater
				}
			}
		}
		else { // If one or both of the cards are null (invalid input)
			return -1; // Error (at least one of the input cards is invalid)
		}
	}
	
	public void battle(Player enemy, Card card1, Card card2, ArrayList<Card> warLoot, int numBattles) {
		if (enemy!=null && enemy!=this) { // If there is an enemy and they are not the player calling the method
			
			switch (this.compareCards(card1, card2)) {
				case 1:
					if (numBattles==1) { // The war loot will take the two compared cards only in the first iteration (when case 0: the player puts three cards on the table and they automatically append to the war loot array)
						warLoot.add(card1); warLoot.add(card2); // The two cards are now on the table (and in the war loot)
					}
					this.collectCards(warLoot); // The player who calls the method wins the battle and collects the loot
					break;
					
				case 2:
					if (numBattles==1) { // The war loot will take the two compared cards only in the first iteration
						warLoot.add(card1); warLoot.add(card2); // The two cards are now on the table (and in the war loot)
					}
					enemy.collectCards(warLoot); // The other player wins the battle and collects the cards on the table
					break;
					
				case 0: // The two cards have equal ranks
					if (numBattles==1) {
						warLoot.add(card1); warLoot.add(card2); // The two cards are now on the table (and in the war loot)	
						warLoot.addAll(this.putThreeCards()); // The 1-st player puts three more cards and they are added to the table (war loot)
						card1=warLoot.get(warLoot.size()-1); // The last card on the table becomes the new Player 1's card (card1)
						warLoot.addAll(enemy.putThreeCards()); // The 2-nd player puts three more cards and they are added to the table (war loot)
						card2=warLoot.get(warLoot.size()-1); // The last card on the table becomes the new Player 2's card (card2)
						battle(enemy, card1, card2, warLoot, ++numBattles); // They battle again
					}
					else {
						warLoot.add(this.putOneCard());
						card1=warLoot.get(warLoot.size()-1);
						warLoot.add(enemy.putOneCard());
						card2=warLoot.get(warLoot.size()-1);
						battle(enemy, card1, card2, warLoot, ++numBattles); // They battle again
					}
	
				default: // If the input of the cards is invalid (value= -1 )
					break;
				}

		}
	}
	
	public void printCardsWon() {
		System.out.print(this.getName()+": ");
		if (this.getCardsWon().size()!=0) {
			for (int i = 0; i < this.getCardsWon().size(); i++) {
				this.getCardsWon().get(i).showCard();
				if (i!=this.getCardsWon().size()-1) { // The ", " will not appear after the last element
					System.out.print(", ");
				}
			}
		}
		else {
			System.out.print("No cards won yet!");
		}
	}

	
}
