
/**
 * A class that represents an ordinary deck of 52 playing cards.
 * This deck can be shuffled, and cards can be dealt from the deck.
 * @author Dion Niazi
 *
 */
public class Deck 
{
	// an array of 52 cards (a deck)
	private Card[] deck;
	// The number of cards dealt from the deck
	private int cardsUsed;
	
	public Deck()
	{
		this.deck = new Card[52];
		int cardCount = 0;
		for(int i = 0; i <= 3; i++)
		{
			for( int j = 1; j <= 13; j++)
			{
				this.deck[cardCount] = new Card(j,i);
				cardCount++;
			}
		}
		cardsUsed = 0;
	}
	
	public Card[] getDeck()
	{
		return this.deck;
	}
	
	/**
	 * Deals one card from the deck and returns it
	 * @return a card from this deck
	 */
	public Card dealCard()
	{
		if(this.cardsUsed == 52)
			shuffle();
		this.cardsUsed++;
		return this.deck[this.cardsUsed - 1];
	}
	
	/**
	 * This method randomly shuffles cards in a deck 
	 */
	public void shuffle()
	{
		for(int i = 51; i > 0; i--)
		{
			int rand = (int)(Math.random()*(i+1));
			Card temp = this.deck[i];
			this.deck[i] = this.deck[rand];
			this.deck[rand] = temp;
		}
		this.cardsUsed = 0;
	}
	
	// prints out all the cards in the deck
	public String toString()
	{
		String printDeck = "";
		int addNewLine = 0;
		for(Card card: deck)
		{
			printDeck+=card+", ";
			if(addNewLine == 12)
			{
				// printDeck is a substring of itself without the trailing comma
				printDeck = printDeck.substring(0, printDeck.length()-2);
				printDeck+="\n";
				addNewLine = 0;
				
			}
			else
				addNewLine++;
		}
		return printDeck;
	}
}
