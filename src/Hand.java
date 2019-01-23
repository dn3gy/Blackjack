import java.util.ArrayList;
/**
 * A class that represents a hand of cards.
 * A card can be added to a hand and a hand of 
 * cards can be cleared every new game.
 * @author Dion Niazi
 */
public class Hand 
{
	private ArrayList<Card> hand;
	public Hand()
	{
		this.hand = new ArrayList<Card>();
	}
	
	public ArrayList<Card> getHand()
	{
		return this.hand;
	}
	
	/**
	 * Adding a card to this hand
	 * @param card The card being added 
	 * to this hand
	 */
	public void addCard(Card card)
	{
		hand.add(card);
	}
	
	/**
	 * Removing all the cards from this
	 * hand 
	 */
	public void clearHand()
	{
		hand.clear();
	}
	
	// toString() prints out all the cards in the hand
	public String toString()
	{
		String printHand = "" ;
		for(Card card: this.hand)
			printHand+=card+" and ";
		// made printHand equal to this substring to get rid of the last ' and '
		printHand = printHand.substring(0, printHand.length()-5);
		return printHand;
	}
}
