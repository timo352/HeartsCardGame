package CardGame;

import PlayingCard.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

/**
 * Class that works together with Hearts to create a
 * Hearts Player that stores the score, name, and various 
 * card data structures for a single player. Eventually,
 * there will be A.I. to decide moves.
 * 
 * @author Timothy Smith II
 * 
 * @version 1.2
 * Last edited: January 12, 2016
 */
public final class HeartsPlayer {

// ******* INSTANCE VARIABLES *******
	
	private PlayingCard chosenCard;
	private int score;
	private String name;
	private PlayingCard played;
	private final int number;

	public static final int PLAYER_ONE = 1;
	public static final int PLAYER_TWO = 2;
	public static final int PLAYER_THREE = 3;
	public static final int PLAYER_FOUR = 4;
	
	private static final Thread lock = new Thread();
	
	private final LinkedList<PlayingCard> hand;
	private final LinkedList<PlayingCard>  collected;
	private final LinkedList<PlayingCard> passing;

// ******* CONSTRUCTOR *******
	
	/**
	 * Constructor for the HeartsPlayer class that takes a String
	 * @param nm String assigned to the name of the player
	 * @param num Number of the player (Choose from the provided static ints)
	 */
	public HeartsPlayer(String nm, int num){
		played = null;
		name = nm;
		number = num;
		hand = new LinkedList<PlayingCard>();
		
		passing = new LinkedList<PlayingCard>();
		collected = new LinkedList<PlayingCard>();
	}
	
// ******* PRIVATE HELPER METHODS *******
	
	/**
	 * Method to choose a card. If from user input, it will pass to
	 * getCard, otherwise it will run an algorithm for the computer's
	 * to make the best possible play
	 * @return the card associated with the chosen index
	 */
	private PlayingCard chooseCard(HeartsGraphics g){		
		Hearts.setCurrentPlayer(this);
		
		if(number != PLAYER_ONE){
			// A.I. code
			int index;
			
			Random r = new Random();
			index = r.nextInt(hand.size());			
			
			return hand.get(index);			
		} else {
			return getCard(g);
		}		
	}
	
	/**
	 * Method to return the card the user has chosen. The thread is 
	 * synchronized and told to wait. The thread will wake up when
	 * notifyPlayer is run.
	 * @param g
	 * @return the card the user has chosen
	 */
	private PlayingCard getCard(HeartsGraphics g){
		
		synchronized(lock){
			try {			
				lock.wait();
			} catch (InterruptedException ex) {
				System.exit(1);
			}
		}
		
		return chosenCard;
	}
	
	
// ******* PUBLIC METHODS *******
	
	/**
	 * Method to add a card to the hand of the player
	 * @param card PlayingCard to be added to the hand
	 */
	public void addToHand(PlayingCard card){
		hand.add(card);
	}
	
	/**
	 * Method to sort the hand of the player (Ace is high)
	 */
	public void sortHand(){
		hand.sort(PlayingCard.PlayingCardComparator_ACEHIGH);
	}
	
	/**
	 * Method to return the LinkedList hand of the player
	 * @return the hand of the player
	 */
	public LinkedList<PlayingCard> getHand(){
		return hand;
	}
	
	/**
	 * Method to determine if a player has a card that isn't a heart
	 * This is used in the Hearts program to check if a play is valid
	 * If only hearts are left but hearts hasn't been broken, this function
	 * will be referenced.
	 * @return 
	 */
	public boolean hasOnlyHearts(){
		
		for(int i=0; i<hand.size(); i++){
			if(hand.get(i).getSuit() != PlayingCard.HEARTS){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Method to print the hand of the player
	 */
	public void printHand(){
		System.out.println(name + "'s hand:");
		for(int i=0; i<hand.size(); i++){
			System.out.print(i+1 + ".\t");
			hand.get(i).printCard();
		}
	}
	
	/**
	 * Method to add a card to the passing variable of the player
	 * @param cards PlayingCard to be added to the passing variable
	 */
	public void addToPassing(PlayingCard[] cards){
		passing.addAll(Arrays.asList(cards));
	}
	
	/**
	 * Method to sort the passing variable of the player (Ace is high)
	 */
	public void sortPassing(){
		passing.sort(PlayingCard.PlayingCardComparator_ACEHIGH);
	}
	
	/**
	 * Method to return the passing variable Stack of the player
	 * @return the passing variable of the player
	 */
	public LinkedList<PlayingCard> getPassing(){
		return passing;
	}
	
	/**
	 * Method to choose a card to pass
	 * @param g The graphics associated with the Hearts game
	 * @return the PlayingCard wanting to the passed
	 */
	public final PlayingCard[] choosePassingCards(HeartsGraphics g){
		LinkedList<PlayingCard> tempList = new LinkedList<PlayingCard>();
		PlayingCard[] cards = new PlayingCard[3];
		
		while(tempList.size() < 3){
			PlayingCard temp = chooseCard(g);
			
			// check to make sure the card can be removed (this enables the user
			// to deselect cards if they want to)
			if(!hand.remove(temp)){
				hand.add(temp);
				tempList.remove(temp);
				
			} else {
				tempList.add(temp);
			}		
		}
		
		for(int i=0; i<3; i++){
			cards[i] = tempList.get(i);
		}
		
		return cards;
	}
	
	/**
	 * Method to add a card to the collected "won" cards of the player
	 * @param card the card to be added to collected
	 */
	public void addToCollected(PlayingCard card){
		collected.add(card);		
	}
	
	/**
	 * Method to return the collected "won" cards of the player
	 * @return the collected Stack of the player
	 */
	public LinkedList<PlayingCard> getCollected(){
		return collected;
	}
	
	/**
	 * Method to clear the collected cards of the player
	 */
	public void clearCollected(){
		collected.clear();
	}
	
	/**
	 * Method to choose a card to play in round
	 * @param g The graphics associated with the Hearts game
	 * @return the PlayingCard wanting to the played
	 */
	public final PlayingCard chooseRoundCard(HeartsGraphics g){
		return hand.get(hand.indexOf(chooseCard(g)));
	}
	
	/**
	 * Method to assign a card from the hand to the played card of the player
	 * @param card card to be removed from hand and assigned to played
	 */
	public void playCard(PlayingCard card){
		played = hand.remove(hand.indexOf(card));		
	}
	
	/**
	 * Method to clear the played card of the player
	 */
	public void clearPlayed(){
		played = null;
	}
	
	/**
	 * Method to return the most recently played card of the player
	 * @return the most recently played card
	 */
	public PlayingCard getPlayed(){
		return played;
	}
	
	/**
	 * Method to add to the score of the player
	 * @param n amount of points to be added to the score
	 */
	public void addScore(int n){
		score += n;
	}
	
	/**
	 * Method to return the score of the player
	 * @return the score of the player
	 */
	public int getScore(){
		return score;
	}

	/**
	 * Method to assign a String to the name of the player
	 * @param nm String to be assigned to the name
	 */
	public void setName(String nm){
		name = nm;
	}
	
	/**
	 * Method to return the name of the player
	 * @return the name of the player
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Method to return the number of the player
	 * @return the number of the player
	 */
	public int getNumber(){
		return number;
	}
	
	/**
	 * Function acting as a listener/event that will inform the player that a card 
	 * was chosen through the graphic interface
	 * @param card that the player chose
	 */
	public void notifyPlayer(CardGraphics card){
		chosenCard = card.getCard();
		
		synchronized(lock){
			lock.notify();
		}
	}	
}
