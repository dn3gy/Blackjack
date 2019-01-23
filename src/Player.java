
/**
 * Represents a player involved in the game of Blackjack.
 * Each player has a hand of cards.
 * @author Dion Niazi
 */
public class Player 
{
	private Hand hand;
	public Player()
	{
		this.hand = new Hand();
	}
	
	public Hand getHand()
	{
		return this.hand;
	}
	
	/**
	 * Adds a card to the player's hand
	 * @param card The selected card to be added to the player's hand
	 */
	public void addCard(Card card)
	{
		hand.addCard(card);
	}
	
	/**
	 * Returns the total value of the player's hand
	 * @return the total value of the player's hand
	 */
	public int handCount()
	{
		int count = 0;
		for(Card iter: this.hand.getHand())
		{
			count += iter.cardValue();
		}
		return count;
	}
}
