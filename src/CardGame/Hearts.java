package CardGame;

import PlayingCard.*;
import DeckOfCards.*;
import java.util.LinkedList;

/**
 * Class that allows user to play Hearts through
 * running a static method called play(). The program
 * prints to System.out and reads from System.in;
 * 
 * @author Timothy Smith II
 * @extends CardGame
 * 
 * @version 0.5
 * ALPHA: Currently in testing
 */
public class Hearts extends CardGame{

	private static HeartsPlayer currentPlayer;
	
	private static final boolean yesCollection = true;
	private static final boolean noCollection = false;
	
// ******* INSTANCE VARIABLES *******
	
	private static volatile DeckOfCards deck;
	private static HeartsPlayer[] players;
	private static Hearts heartsGame;
	private static HeartsGraphics graphics;
	private static volatile boolean heartsBroken;

	static boolean playing;
	
// ******* CONSTRUCTOR *******
	
	protected Hearts(){
		super(4);
	}

// ******* PUBLIC METHOD *******
	
	/**
	 * Public static method that plays the game Hearts.
	 */
	public static final void play(){		
		if(!playing){
			heartsGame = new Hearts();
		}
		heartsGame.initialize();
		heartsGame.start();	
	}

// ******* OVERRIDDEN PROTECTED CARDGAME METHODS *******
	
	// creates players and loads previous game
	protected final void initialize(){
		
		graphics = new HeartsGraphics();
		players = new HeartsPlayer[4];
		players[0] = new HeartsPlayer("Player #1", HeartsPlayer.PLAYER_ONE);
		players[1] = new HeartsPlayer("Player #2", HeartsPlayer.PLAYER_TWO);
		players[2] = new HeartsPlayer("Player #3", HeartsPlayer.PLAYER_THREE);
		players[3] = new HeartsPlayer("Player #4", HeartsPlayer.PLAYER_FOUR);
		currentPlayer = null;
		playing = true;
		
		// add option to load game
	}
	
	// welcome user and begin
	protected final void start(){		
		System.out.println("------ WELCOME TO HEARTS ------");
		turn();
	}
	
	// a round in the game of hearts
	protected final void turn(){
		
	// ******* DEAL HANDS *******
		System.out.println("\n\n");
		// get new deck of cards and shuffle it
		System.out.println("Shuffling deck...");
		deck = new DeckOfCards(DeckOfCards.TRADITIONAL_DECK, true);
		
		// set heartsBroken to false
		heartsBroken = false;
		
		// deal cards to four players
		System.out.println("Dealing cards...");
		for(int i=0; i<52; i++){
			players[i%4].addToHand((PlayingCard)deck.drawCard());
		}
		
		for(int i=0; i<4; i++){
			players[i].sortHand();
			
			// graphical elements
			graphics.displayHand(players[i].getHand(), players[i].getNumber(), noCollection);
		}
		
	// ******* TRADE CARDS *******
		// decide which cards to pass (skip every fourth turn)
		if(turnCount % 4 != 3){
			System.out.println("Choose three cards to pass...");
		
			for(int i=0; i<4; i++){
					players[i].printHand();
					
					// graphical elements
					PlayingCard[] cardsToPass = players[i].choosePassingCards(graphics);
					players[i].addToPassing(cardsToPass);
					
					graphics.displayHand(players[i].getHand(), players[i].getNumber(), noCollection);
			}
		
		
			// decide which direction to pass
			int passingMod;
			switch(turnCount % 4){
				case 0: // passing right
					passingMod = 3;
					break;
				case 1: // passing left
					passingMod = 1;
					break;
				case 2:	// passing across
					passingMod = 2;
					break;
				default: // not passing
					passingMod = 0;
					break;				
			}

			// pass cards
			System.out.println("\nPassing cards...");
			for(int i=0; i<4; i++){
				for(int j=0; j<3; j++){
					players[(i+passingMod)%4].addToHand(players[i].getPassing().pop());
				}
				players[(i+passingMod)%4].sortHand();
			}
		}
	
	// ******* DECIDE WHO GOES FIRST *******	
		// locate Two of Clubs
		System.out.println("Player with the TWO of CLUBS goes first...");
		int startPlayer = 0;
		for(int i=0; i<4; i++){
			for(int j=0; j<13; j++){
				if(players[i].getHand().get(j).getSuit() == PlayingCard.CLUBS && players[i].getHand().get(j).getFaceValue() == PlayingCard.TWO){
					startPlayer = i;
					i=4;
					j=13;
				}
			}
		}
	
	// ******* PLAY HANDS *******
		// play the game
		for(int i=0; i<13; i++){
			
			// each player plays one card
			for(int j=startPlayer; j<startPlayer + 4; j++){
				System.out.println("\nSelect a card to play...");
				players[j%4].printHand();
				
				
				graphics.displayHand(players[j%4].getHand(), players[j%4].getNumber(), noCollection);
				PlayingCard tempPlayed = players[j%4].chooseRoundCard(graphics);
				
				// make sure the TWO of CLUBS is played first in the round
				if(i == 0 && j == startPlayer){
					while(tempPlayed.getSuit() != PlayingCard.CLUBS || tempPlayed.getFaceValue() != PlayingCard.TWO){
						if(j == 0){
							System.out.println("The TWO of CLUBS must be played first...");
							graphics.warnPlayer(tempPlayed);
							graphics.deselectCard(tempPlayed);
						}
						tempPlayed = players[j%4].chooseRoundCard(graphics);
					}
				}
				
				// make sure the move is valid					
				while((j > startPlayer && !checkValidPlay(players[j%4], tempPlayed, players[startPlayer].getPlayed()))
						|| (j == startPlayer && !heartsBroken && tempPlayed.getSuit() == PlayingCard.HEARTS && !players[j%4].hasOnlyHearts())){
					if(j%4 == 0){
						System.out.println("Please choose a card with the proper suit.");
						graphics.warnPlayer(tempPlayed);
						graphics.deselectCard(tempPlayed);
					}
					tempPlayed = players[j%4].chooseRoundCard(graphics);
				}
				
				// play the card and display it
				players[j%4].playCard(tempPlayed);
				
				if(j%4 != 0){
					try {
						Thread t = new Thread();
						synchronized(t){
							t.wait(500);
						}					
					} catch (InterruptedException ex) {
						System.exit(1);
					}
				}
				
				graphics.displayCard(players[j%4].getPlayed(), players[j%4].getNumber());
				
				// break hearts if necessary
				if(!heartsBroken && tempPlayed.getSuit() == PlayingCard.HEARTS){
					heartsBroken = true;
				}
				
				graphics.displayHand(players[j%4].getHand(), players[j%4].getNumber(), noCollection);
			}
			
			// decide which player won
			int winner = getHandWinner(startPlayer);
			System.out.println("\n\n" + players[winner].getName() + " wins the hand.");
			
			// print out the cards played
			for(int j=0; j<4; j++){
				System.out.println(players[j].getName() + ": ");
				players[j].getPlayed().printCard();
			}
			
			// add cards to collection if it's the Queen of Spades or a Heart
			for(int j=startPlayer; j<startPlayer + 4; j++){
				PlayingCard card = players[j%4].getPlayed();
				
				if(card.getSuit() == PlayingCard.HEARTS || (card.getSuit() == PlayingCard.SPADES && card.getFaceValue() == PlayingCard.QUEEN)){
					players[winner].addToCollected(card);
				}
				players[j%4].clearPlayed();				
			}
			
			graphics.clearPlayed();
			
			startPlayer = winner;
		}
	
	// ******* CLEANUP *******
		// give points accordingly
		for(int i=0; i<4; i++){
			int points = 0;
			LinkedList<PlayingCard> collection = players[i].getCollected();
			
			// Display the cards collected during the round
			graphics.displayHand(collection, players[i].getNumber(), yesCollection);
			
			while(!collection.isEmpty()){
				if(collection.peek().getSuit() == PlayingCard.HEARTS){
					points++;
				} else if(collection.peek().getSuit() == PlayingCard.SPADES && collection.peek().getFaceValue() == PlayingCard.QUEEN){
					points += 13;
				}
				collection.pop();
			}
			
			players[i].clearCollected();
			
			// check to see if they shot the moon
			if(points == 26){
				for(int j=i+1; j<i+4; j++){
					players[j%4].addScore(26);
				}
			} else {
				players[i].addScore(points);
			}
		}
		
		// add turn and show the score
		printScores();
		graphics.displayScores(players);
		
		turnCount++;
		if(end()){
			declareWinner();
			restart();
		} else {
			turn();
		}
		
		
	}	

	// method to check the end condition of the game
	protected final boolean end(){
		
		for(int i=0; i<4; i++){
			if(players[i].getScore() >= 100){
				return true;
			}
		}
		
		return false;
	}
	
	// saves and exits the game
	protected final void close(){
		boolean save = false;
		
		if(save){
			save();
		}
		System.exit(0);
	}
	
	protected final void restart(){
		graphics.destruct();
		play();
	}
	
	protected final void save(){
		
	}
	
	protected final void load(){
		
	}	
	
	
// ******* PRIVATE HELPERS *******
	
	// makes sure the card played is valid
	private boolean checkValidPlay(HeartsPlayer player, PlayingCard playedCard, PlayingCard leadCard){
		
		if(playedCard.getSuit() != leadCard.getSuit()){
			for(int i=0; i<player.getHand().size(); i++){
				if(player.getHand().get(i).getSuit() == leadCard.getSuit()){
					return false;
				}
			}
		}		
		return true;
	}
	
	// prints out the current scores of the game in the System.out
	private void printScores(){
		System.out.println("--------------------------------");
		System.out.println("SCORES AFTER ROUND " + (turnCount + 1) + ":");
		for(int i=0; i<4; i++){
			System.out.println(players[i].getName() + ": " + players[i].getScore());
		}
		System.out.println("--------------------------------");
	}
	
	// returns the index of player who won the hand
	private int getHandWinner(int startPlayer){
		
		PlayingCard max = players[startPlayer].getPlayed();
		PlayingCardComparator comp = new PlayingCardComparator(PlayingCard.HIGH_ACE);
		
		for(int i=1; i<4; i++){
			if(players[(i+startPlayer)%4].getPlayed().getSuit() == max.getSuit()){
				if(comp.greaterFaceValue(players[(i+startPlayer)%4].getPlayed(), max)){
					max = players[(i+startPlayer)%4].getPlayed();
				}
			}
		}
		
		int winner;
		for(winner=0; winner<4; winner++){
			if(players[winner].getPlayed().equals(max)){
				break;
			}
		}
		return winner;		
	}
	
	// returns the index of the player who won the game
	private int getGameWinner(){
		
		int max = 130;
		int winner = 0;
		
		for(int i=0; i<4; i++){
			if(players[i].getScore() < max){
				max = players[i].getScore();
				winner = i;
			}
		}
		
		return winner;
	}
	
	private void declareWinner(){
		
		int winner = getGameWinner();
		
		graphics.displayWinner(players, winner);
		
		System.out.println(players[winner].getName() + " has won the game!\n Thanks for playing!");
		printScores();
	}
	
// ******* PUBLIC ACCESSORS AND MUTATORS *******
	public static HeartsPlayer getCurrentPlayer(){
		return currentPlayer;
	}
	
	public static void setCurrentPlayer(HeartsPlayer p){
		currentPlayer = p;
	}
	
	public static void main(String[] args){
		Hearts.play();
	}
}
