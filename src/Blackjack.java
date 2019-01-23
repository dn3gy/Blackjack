
import java.io.InputStream;
import java.util.Scanner;
/**
 * A class where the main Blackjack game is ran.
 * The player is more like a "human" player and
 * the dealer is more like a computer player.
 * @author Dion Niazi
 */
public class Blackjack {
	
    // added InputStream to help with testing between files and user input
	static InputStream inStream = System.in;
	static Scanner input;
	public static void main(String[] args) {

		// A player and dealer object is created for use of the game
		Player player = new Player(); 
		Player dealer = new Player();
		// loop that keeps the game running until player doesn't want to play a new game
		while(true)
		{
			// New game created with shuffled deck
			System.out.println("Starting a New Game");
			Deck deck = new Deck();
			deck.shuffle();
            // method lets user to play and returns the result as a String
			String result = playBlackJack(player, dealer, deck, inStream);
			System.out.println(result + "Wanna play a new game?");
			String outerChoice = "";
            // loop to determine what user wants to do with a new game
			while(true)
			{
				String choice = input.next();
				if(choice.equals("y"))
				{
					player.getHand().clearHand();
					dealer.getHand().clearHand();
					break;
				}
				else if(choice.equals("n"))
				{
					outerChoice = choice;
					break;
				}
				else
					System.out.println("Sorry wrong input. Try again");
			}
			if(outerChoice.equals("n"))
				break;
		}
		input.close();
	}
	
    /**
     * Returns the result of the game in a String
     * @return the result of the game in a String
     * @param player "human" player
     * @param dealer the "human" player opposition
     * @param deck the standard playing cards in a random order when shuffled
     * @param in the InputStream to determine whether all input is System input (when actually playing)
     * or input from files (testing purposes)
     */
	public static String playBlackJack(Player player, Player dealer, Deck deck, InputStream in)
	{
        // Each player is dealt 2 cards
		input = new Scanner(in);
		player.addCard(deck.dealCard());
		dealer.addCard(deck.dealCard());
		player.addCard(deck.dealCard());
		dealer.addCard(deck.dealCard());
		System.out.println("Player starts with cards "+ player.getHand());
		System.out.println("Dealer starts with " + dealer.getHand());
        String output = "";
		// variables that determine if player or dealer has busted (gone over 21)
		boolean isPlayerBust = false;
		boolean isDealerBust = false;
		if(player.handCount() == 21)
			if(dealer.handCount() == 21)
                output += "Both players are tied\n";
			else
                output += "Player wins\n";
		else if(dealer.handCount() == 21)
            output += "Dealer wins\n";
		else
		{
			// player can hit or stand by typing 'h' to hit or any other key plus enter to stand
			System.out.println("Player do you want to hit or stand");
			String keyPressed = input.next();
			if(keyPressed.trim().equals("h"))
			{
				boolean hit = true;
				int cardPos = 2;
				// As long as hit is true, a card is dealt from the deck and added to Player's hand
				while(hit)
				{
					player.addCard(deck.dealCard());
					System.out.println("Player receives a "+player.getHand().getHand().get(cardPos));
					System.out.println("Player now has "+player.handCount()+" in their hand");
					if(player.handCount() > 21)
					{
						System.out.println("Player bust. Your turn is over");
						isPlayerBust = true;
						break;
					}
					System.out.println("Do you wish to hit again?");
					String choice = input.next();
					if(choice.equals("n"))
						hit = false;
					cardPos++;
				}
				cardPos=0;
			}
			// while dealer's hand is less than 17, then Dealer must hit
			if(dealer.handCount() < 17)
			{
				System.out.println("Dealer must hit");
				int cardPos = 2;
				while(dealer.handCount() < 17)
				{
					dealer.addCard(deck.dealCard());
					System.out.println("Dealer receives a "+dealer.getHand().getHand().get(cardPos));
					System.out.println("Dealer now has "+dealer.handCount()+" in their hand");
					cardPos++;
				}
				if(dealer.handCount() > 21)
				{
					System.out.println("Dealer busts");
					isDealerBust = true;
				}
			}
			else if(dealer.handCount() <= 21)
			{
				System.out.println("Dealer has "+dealer.handCount()+" in their hand");
				System.out.println("Dealer stands");
			}
			// This case only happens when the dealer decides to make both aces initially equal 11
			else
			{
				System.out.println("Dealer busts");
				isDealerBust = true;
			}
			if(isDealerBust && isPlayerBust)
                output += "Dealer wins\n";
			else if(dealer.handCount() == player.handCount())
                output += "Both players are tied\n";
			else if((dealer.handCount() > player.handCount() && !isDealerBust) || isPlayerBust )
                output += "Dealer wins\n";
			else
                output += "Player wins\n";
		}
        return output;
	}
}
