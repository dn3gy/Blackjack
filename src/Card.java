
/**
 * A class that represents 1 of the 52 cards in a 
 * standard deck of playing cards. Each card has a suit
 * and a value.
 * @author Dion Niazi
 */
public class Card 
{
	// suites of regular playing cards
	public final static int DIAMONDS = 0,
							HEARTS = 1,
							CLUBS = 2,
							SPADES = 3;
	
	// values of special cards
	public final static int ACE = 1,
							JACK = 11,
							QUEEN = 12,
							KING = 13;
	
	private final int rank;
	private final int suit;
	// the value of an ace card either 1 or 11
	private int aceValue;
	// whether or not the ace value was already determined or not
	private boolean isAceValueChecked;
	public Card(int rank, int suit)
	{
		this.rank = rank;
		this.suit = suit;
		this.aceValue = -1;
		this.isAceValueChecked = false;
	}
	
	public int getRank()
	{
		return this.rank;
	}
	
	public int getSuit()
	{
		return this.suit;
	}
	
	/**
	 * Returns the actual value of the card to 
	 * determine the score for the game Blackjack
	 * @return the actual value of the card to 
	 * determine the score for the game Blackjack
	 */
	public int cardValue()
	{
		int value = 0;
		switch(this.rank)
		{
			case 1:	value = aceValue(); break;
			case 11: value = 10; break;
			case 12: value = 10; break;
			case 13: value = 10; break;
			default: value = this.rank; break;
		}
		return value;
	}
	
	/**
	 * This method checks and sees which value the
	 * player wants the ace to be
	 * @return the actual value of the ace
	 */
	public int aceValue()
	{
		if(this.isAceValueChecked == false)
		{
			System.out.println("What value do you want the ace to count as 1 or 11");
			int value = 0;
			while(true)
			{
				if(Blackjack.input.hasNextInt())
				{
					value = Blackjack.input.nextInt();
					if(value == 1 || value == 11)
						break;
					else
						System.out.println("You inputed a wrong value. Try again");
				}
				else if(Blackjack.input.hasNext()){
					System.out.println("You inputed something other than a number. Try again");
					Blackjack.input.next();
				}
			}
			this.isAceValueChecked = true;
			aceValue = value;
		}
		return aceValue;
	}
	
	// cards are equal if and only if they have same rank and same suit
	@Override
	public boolean equals(Object obj)
	{
		if(obj instanceof Card)
			if(((Card)obj).getRank() == this.rank)
				if(((Card)obj).getSuit() == this.suit)
					return true;
		return false;
		
	}
	
	// String representation of this card
	@Override
	public String toString()
	{
		String cardNum = "";
		String suitName = "";
		switch(this.rank)
		{
			case ACE: cardNum = "Ace of "; break;
			case JACK: cardNum = "Jack of "; break;
			case QUEEN: cardNum = "Queen of "; break;
			case KING: cardNum = "King of "; break;
			default: cardNum= this.rank + " of "; break;
		}
		switch(this.suit)
		{
			case DIAMONDS: suitName = "Diamonds"; break;
			case HEARTS: suitName = "Hearts"; break;
			case CLUBS: suitName = "Clubs"; break;
			case SPADES: suitName = "Spades"; break;
		}
		return cardNum + suitName;
	}
}
