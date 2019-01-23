import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class BlackjackTest {

    /**
     * Checks to see if normal created card matches
     * it's suit and rank (tests Card toString())
     */
	@Test
	public void testNormalCardName() {
		Card card = new Card(2,0);
		boolean isEqual = card.toString().equals("2 of Diamonds");
		assertEquals(isEqual, true);
	}
	
    /**
     * Checks to see if special created card matches
     * it's suit and rank (tests Card toString())
     */
	@Test
	public void testSpecialCardName() {
		Card card = new Card(12,2);
		boolean isEqual = card.toString().equals("Queen of Clubs");
		assertEquals(isEqual, true);
	}
	
    /**
     * Checks to see if normal created card matches
     * it's correct value (tests Card cardValue())
     */
	@Test
	public void testNormalCardValue() {
		Card card = new Card(2,3);
		assertEquals(card.cardValue(), 2);
	}
	
    /**
     * Checks to see if special created card matches
     * it's correct value (tests Card cardValue())
     */
	@Test
	public void testSpecialCardValue() {
		Card card = new Card(11,1);
		assertEquals(card.cardValue(), 10);
	}
	
    /**
     * Tests whether a card was added to the hand 
     * (tests Hand addCard(Card card))
     */
	@Test
	public void testAddCard() {
		Hand hand = new Hand();
		Card card = new Card(8,2);
		int prevSize = hand.getHand().size();
		hand.addCard(card);
		assertEquals(prevSize+1, hand.getHand().size());
		assertEquals(hand.getHand().get(prevSize),card);
	}
	
    /**
     * Tests to see if the deck has all the cards
     * a standard playing cards deck has
     */
	@Test
	public void testAllCards() {
		Deck deck = new Deck();
		ArrayList<Card> cards = new ArrayList<Card>(Arrays.asList(deck.getDeck()));
		boolean isCardThere = true;
		for(int i = 3; i >= 0; i--)
			for(int j = 13; j > 0; j--)
				if(!cards.contains(new Card(j, i)) )
				{
					isCardThere = false;
					break;
				}
		assertEquals(true, isCardThere);
	}
	
    /**
     * Checks to see if card from deck is dealt and
     * equal to the card it's supposed 
     * to be (tests Deck dealCard())
     */
	@Test
	public void testDealCard() {
		Deck deck = new Deck();
		Card card = deck.dealCard();
		assertEquals(card, new Card(1,0));
		Card nextCard = deck.dealCard();
		assertEquals(nextCard, new Card(2,0));
	}
	
    /**
     * Tests to see if deck is all shuffled up and all
     * and not in any particular order (tests Deck shuffle())
     */
	@Test
	public void testShuffle() {
		Deck deck = new Deck();
		Deck oldDeck = new Deck();
		deck.shuffle();
		assertNotSame(deck.toString(), oldDeck.toString());
	}
	
    /**
     * Checks to see if Cards are equal to other cards
     * or not (tests Card equals(Object obj))
     */
	@Test
	public void testIsCardEquals() {
		Card card = new Card(2,0);
		Card otherCard = new Card(3,1);
		assertNotSame(card, otherCard);
		Card card2 = new Card(2,1);
		assertNotSame(card, card2);
		Card card3 = new Card(3,0);
		assertNotSame(card, card3);
		Card card4 = new Card(2,0);
		assertEquals(card, card4);
	}
	
    /**
     * Checks to see if the whole hand of cards are cleared
     * (tests Hand clearHand())
     */
	@Test
	public void testClearHand() {
		Hand hand = new Hand();
		Card card = new Card(1,0);
		Card otherCard = new Card(2,0);
		hand.addCard(card);
		hand.addCard(otherCard);
		assertEquals(2,hand.getHand().size());
		hand.clearHand();
		assertEquals(0,hand.getHand().size());
	}
	
    /**
     * Checks to see if the score in the hand
     * matches the score from the sum of each card
     * (tests Player handCount())
     */
	@Test
	public void testHandCount() {
		Player player = new Player();
		Card card = new Card(3,0);
		player.addCard(card);
		assertEquals(3, player.handCount());
		Card othercard = new Card(6,3);
		player.addCard(othercard);
		assertEquals(9, player.handCount());
		Card card3 = new Card(12,2);
		player.addCard(card3);
		assertEquals(19, player.handCount());
	}
	
	
	/**
	 * Checks to see that if player busts only, then dealer wins,
	 * but dealer's hand is 21
	 */
	@Test
	public void testCase() {
		Player player = new Player();
		Player dealer = new Player();
		Deck deck = new Deck();
		FileInputStream i = null;
		try{
			i = new FileInputStream("../case0.txt");
		}
		catch(FileNotFoundException e){
			System.out.println("File not found");
		}
		Blackjack.playBlackJack(player, dealer, deck,i);
		assertEquals(player.handCount() > 21, dealer.handCount() <= 21 && dealer.handCount() > 16);
	}
	
    /**
     * Helper method for the Junit test class that helps 
     * make it easier for cards in a deck to be swapped
     */
    public void changeCards(Card [] cards, int oldPos, int newPos) {
        Card temp = cards[oldPos];
        cards[oldPos] = cards[newPos];
        cards[newPos] = temp;
    }
    
	/**
	 * Checks to see that if player busts only, then dealer wins
	 */
	@Test
	public void testCase0() {
		Player player = new Player();
		Player dealer = new Player();
		Deck deck = new Deck();
        Card [] cards = deck.getDeck();
        changeCards(cards, 3, 15);
		FileInputStream i = null;
		try{
			i = new FileInputStream("../case0.txt");
		}
		catch(FileNotFoundException e){
			System.out.println("File not found");
		}
		Blackjack.playBlackJack(player, dealer, deck,i);
		assertEquals(player.handCount() > 21, dealer.handCount() <= 21 && dealer.handCount() > 16);
	}
	
	
	/**
	 * Tests for whether player wins when the player's
	 * hand is equal to 21 and the dealer's hand is < 21 
	 */
	@Test
	public void testCase1() {
		Player player = new Player();
		Player dealer = new Player();
		Deck deck = new Deck();
		Card [] cards = deck.getDeck();
        changeCards(cards, 2, 9);
		FileInputStream i = null;
		try{
			i = new FileInputStream("../case1.txt");
		}
		catch(FileNotFoundException e){
			System.out.println("File not found");
		}
		String result = Blackjack.playBlackJack(player, dealer, deck,i);
        assertEquals("Player wins\n", result);
	}
	
	/**
	 * Checks to see that both players tie when both
	 * players initially get a hand count of 21 
	 */
	@Test
	public void testCase2() {
		Player player = new Player();
		Player dealer = new Player();
		Deck deck = new Deck();
		Card [] cards = deck.getDeck();
        changeCards(cards, 2, 9);
        changeCards(cards, 1, 11);
        changeCards(cards, 3, 13);
		FileInputStream i = null;
		try{
			i = new FileInputStream("../case2-3.txt");
		}
		catch(FileNotFoundException e){
			System.out.println("File not found");
		}
		String result = Blackjack.playBlackJack(player, dealer, deck,i);
        assertEquals(result, "Both players are tied\n");
	}
	
	/**
	 * Checks that the Dealer wins when the dealer's
	 * hand is equal to 21 and the player's hand is less
	 * than 21
	 */
	@Test
	public void testCase3() {
		Player player = new Player();
		Player dealer = new Player();
		Deck deck = new Deck();
		Card [] cards = deck.getDeck();
        changeCards(cards, 1, 11);
        changeCards(cards, 3, 13);
		FileInputStream i = null;
		try{
			i = new FileInputStream("../case2-3.txt");
		}
		catch(FileNotFoundException e){
			System.out.println("File not found");
		}
		String result = Blackjack.playBlackJack(player, dealer, deck,i);
        assertEquals("Dealer wins\n", result);
	}
	
	/**
	 * Checks whether the dealer wins when the player
	 * has initially less than 21 points and then hits
	 * and busts and the dealer initially standing
	 */
	@Test
	public void testCase4() {
		Player player = new Player();
		Player dealer = new Player();
		Deck deck = new Deck();
		Card [] cards = deck.getDeck();
		changeCards(cards, 2, 19);
		changeCards(cards, 1, 7);
		changeCards(cards, 3, 10);
		changeCards(cards, 4, 6);
		FileInputStream i = null;
		try{
			i = new FileInputStream("../case4.txt");
		}
		catch(FileNotFoundException e){
			System.out.println("File not found");
		}
		String result = Blackjack.playBlackJack(player, dealer, deck,i);
        assertEquals(result, "Dealer wins\n");
	}
	
	/**
	 * Same test as testCase4() except player hits twice
	 */
	@Test
	public void testCase5() {
		Player player = new Player();
		Player dealer = new Player();
		Deck deck = new Deck();
		Card [] cards = deck.getDeck();
		changeCards(cards, 2, 19);
		changeCards(cards, 1, 7);
		changeCards(cards, 3, 10);
		changeCards(cards, 4, 14);
		changeCards(cards, 5, 15);
		FileInputStream i = null;
		try{
			i = new FileInputStream("../case5.txt");
		}
		catch(FileNotFoundException e){
			System.out.println("File not found");
		}
		String result = Blackjack.playBlackJack(player, dealer, deck,i);
        assertEquals(result, "Dealer wins\n");
	}
	
    /**
     * Checks to see player wins when player hits and dealer stands
     * and player's hand is more than dealer's
     */
	@Test
	public void testCase6() {
		Player player = new Player();
		Player dealer = new Player();
		Deck deck = new Deck();
		Card [] cards = deck.getDeck();
		changeCards(cards, 2, 19);
		changeCards(cards, 1, 7);
		changeCards(cards, 3, 10);
		changeCards(cards, 4, 14);
		FileInputStream i = null;
		try{
			i = new FileInputStream("../case6.txt");
		}
		catch(FileNotFoundException e){
			System.out.println("File not found");
		}
		String result = Blackjack.playBlackJack(player, dealer, deck,i);
        assertEquals("Player wins\n", result);
	}
	
    /**
     * Checks to see player wins when both player and dealer stand
     * and player's hand is more than dealer's
     */
	@Test
	public void testCase7() {
		Player player = new Player();
		Player dealer = new Player();
		Deck deck = new Deck();
		Card [] cards = deck.getDeck();
		changeCards(cards, 2, 21);
		changeCards(cards, 1, 7);
		changeCards(cards, 3, 10);
		FileInputStream i = null;
		try{
			i = new FileInputStream("../case7-9.txt");
		}
		catch(FileNotFoundException e){
			System.out.println("File not found");
		}
		String result = Blackjack.playBlackJack(player, dealer, deck,i);
        assertEquals("Player wins\n", result);
	}
	
    /**
     * Same as testCase7() except dealer is expected to win
     * because dealer's hand is more than player's hand
     */
	@Test
	public void testCase8() {
		Player player = new Player();
		Player dealer = new Player();
		Deck deck = new Deck();
		Card [] cards = deck.getDeck();
		changeCards(cards, 2, 19);
		changeCards(cards, 1, 8);
		changeCards(cards, 3, 10);
		FileInputStream i = null;
		try{
			i = new FileInputStream("../case7-9.txt");
		}
		catch(FileNotFoundException e){
			System.out.println("File not found");
		}
		String result = Blackjack.playBlackJack(player, dealer, deck,i);
        assertEquals(result, "Dealer wins\n");
	}
	
    /**
     * Checks to see player wins when dealer busts
     */
	@Test
	public void testCase9() {
		Player player = new Player();
		Player dealer = new Player();
		Deck deck = new Deck();
		Card [] cards = deck.getDeck();
		changeCards(cards, 2, 21);
		changeCards(cards, 1, 7);
		changeCards(cards, 3, 20);
		changeCards(cards, 4, 24);
		FileInputStream i = null;
		try{
			i = new FileInputStream("../case7-9.txt");
		}
		catch(FileNotFoundException e){
			System.out.println("File not found");
		}
		String result = Blackjack.playBlackJack(player, dealer, deck,i);
        assertEquals("Player wins\n", result);
	}
	
    /**
     * Checks to see dealer wins when both player and dealer bust
     */
	@Test
	public void testCase10() {
		Player player = new Player();
		Player dealer = new Player();
		Deck deck = new Deck();
		Card [] cards = deck.getDeck();
		changeCards(cards, 0, 15);
		changeCards(cards, 2, 25);
		changeCards(cards, 1, 9);
		changeCards(cards, 3, 5);
		changeCards(cards, 4, 8);
		changeCards(cards, 5, 13);
		FileInputStream i = null;
		try{
			i = new FileInputStream("../case10.txt");
		}
		catch(FileNotFoundException e){
			System.out.println("File not found");
		}
        String result = Blackjack.playBlackJack(player, dealer, deck,i);
        assertEquals(result, "Dealer wins\n");
	}
	
    /**
     * Checks to see dealer wins when player busts and dealer doesn't
     */
	@Test
	public void testCase11() {
		Player player = new Player();
		Player dealer = new Player();
		Deck deck = new Deck();
		Card [] cards = deck.getDeck();
		changeCards(cards, 0, 15);
		changeCards(cards, 2, 25);
		changeCards(cards, 1, 16);
		changeCards(cards, 3, 19);
		changeCards(cards, 4, 8);
		changeCards(cards, 5, 14);
		FileInputStream i = null;
		try{
			i = new FileInputStream("../case11.txt");
		}
		catch(FileNotFoundException e){
			System.out.println("File not found");
		}
        String result = Blackjack.playBlackJack(player, dealer, deck,i);
        assertEquals(result, "Dealer wins\n");
	}
	
    /**
     * Checks to see both players tied because their hands have the same score
     */
	@Test
	public void testCase12() {
		Player player = new Player();
		Player dealer = new Player();
		Deck deck = new Deck();
		Card [] cards = deck.getDeck();
		changeCards(cards, 0, 15);
		changeCards(cards, 2, 16);
		changeCards(cards, 1, 12);
		changeCards(cards, 3, 27);
		changeCards(cards, 4, 10);
		changeCards(cards, 5, 43);
		FileInputStream i = null;
		try{
			i = new FileInputStream("../case12.txt");
		}
		catch(FileNotFoundException e){
			System.out.println("File not found");
		}
        String result = Blackjack.playBlackJack(player, dealer, deck,i);
        assertEquals(result, "Both players are tied\n");
	}
	
    /**
     * Checks to see dealer wins when player has less score than
     * dealer
     */
	@Test
	public void testCase13() {
		Player player = new Player();
		Player dealer = new Player();
		Deck deck = new Deck();
		Card [] cards = deck.getDeck();
		changeCards(cards, 0, 18);
		changeCards(cards, 2, 14);
		changeCards(cards, 3, 7);
		changeCards(cards, 4, 9);
		changeCards(cards, 5, 10);
		FileInputStream i = null;
		try{
			i = new FileInputStream("../case13.txt");
		}
		catch(FileNotFoundException e){
			System.out.println("File not found");
		}
        String result = Blackjack.playBlackJack(player, dealer, deck,i);
        assertEquals(result, "Dealer wins\n");
	}
}
