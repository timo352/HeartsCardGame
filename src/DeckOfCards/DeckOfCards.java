package DeckOfCards;

import PlayingCard.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

public class DeckOfCards {

// ****** PUBLIC STATIC VARIABLES *******
	
	public final static int TRADITIONAL_DECK = 1;
	public final static int DOUBLE_DECK_ONESUIT = 2;
	public final static int DOUBLE_DECK_TWOSUIT = 3;
	public final static int DOUBLE_DECK_FOURSUIT = 4;
	public final static int DOUBLE_DECK = 5;
	public final static int PINOCHLE_DECK = 6;
	public final static int CUSTOM_DECK = 7;

// ****** INSTANCE VARIABLES *******
	
	private Card[] deck;
	private int size;
	private int deckType;

// ****** CONSTRUCTORS *******
	
	// constructor that takes a boolean to shuffle the deck
	public DeckOfCards(int type, boolean shuffle){
				
		switch(type){
			case TRADITIONAL_DECK:
				setTraditional();
				break;
			case DOUBLE_DECK_ONESUIT:
				//setDoubleOneSuit();
				break;
			case DOUBLE_DECK_TWOSUIT:
				//setDoubleTwoSuit();
				break;
			case DOUBLE_DECK_FOURSUIT:
			case DOUBLE_DECK:
				//setDoubleFourSuit();
				break;
			case PINOCHLE_DECK:
				//setPinochle();
				break;
			default:
				throw new DeckException("Unspecified deck type.");
		}
		
		deckType = type;
		if(shuffle){
			shuffleDeck();
		}		
	}
	
	// default the boolean to false and deck to traditional
	public DeckOfCards(){
		this(TRADITIONAL_DECK, false);
	}
	
	public DeckOfCards(boolean shuffle){
		this(TRADITIONAL_DECK, shuffle);
	}
	
	public DeckOfCards(int deckType){
		this(deckType, false);
	}
	
// ****** CONSTRUCTOR HELPTERS *******
	
	private void setTraditional(){
		deck = new PlayingCard[52];
		for (int i=0; i<4; i++) {
			for(int j=0; j<13; j++){
				deck[i*13 + j] = new PlayingCard(i, j+1);
			}
		}
		size = 52;
	}
	/*
	private void setDoubleOneSuit(){
		deck = new PlayingCard[104];
		for(int i=0; i<13; i++){
			PlayingCard card = new PlayingCard(PlayingCard.Suits.SPADES, PlayingCard.FaceValues.values()[i+1]);
			deck[i] = deck[i+13] = deck[i+26] = deck[i+39] = deck[i+52] = deck[i+65] = deck[i+78] = deck[i+91] = card;
		}
		
		size = 104;
	}
	
	private void setDoubleTwoSuit(){
		deck = new PlayingCard[104];
		for(int i=0; i<13; i++){
			for(int j=0; j<2; j++){
				PlayingCard card = new PlayingCard(PlayingCard.Suits.values()[j], PlayingCard.FaceValues.values()[i+1]);
				deck[j*52 + i] = deck[j*52 + i + 13] = deck[j*52 + i + 26] = deck[j*52 + i + 39] = card;
			}
		}
		
		size = 104;
	}
	
	private void setDoubleFourSuit(){
		deck = new PlayingCard[104];
		for (int i=0; i<4; i++) {
			for(int j=0; j<13; j++){
				deck[i*13 + j] = deck[i*13 + j + 52] = new PlayingCard(PlayingCard.Suits.values()[i], PlayingCard.FaceValues.values()[j+1]);
			}
		}
		size = 104;
	}
	
	private void setPinochle(){
		deck = new PlayingCard[48];
		for(int i=0; i<4; i++) {
			PlayingCard nine = new PlayingCard(PlayingCard.Suits.values()[i], PlayingCard.FaceValues.NINE);
			PlayingCard ten = new PlayingCard(PlayingCard.Suits.values()[i], PlayingCard.FaceValues.TEN);
			PlayingCard jack = new PlayingCard(PlayingCard.Suits.values()[i], PlayingCard.FaceValues.JACK);
			PlayingCard queen = new PlayingCard(PlayingCard.Suits.values()[i], PlayingCard.FaceValues.QUEEN);
			PlayingCard king = new PlayingCard(PlayingCard.Suits.values()[i], PlayingCard.FaceValues.KING);
			PlayingCard ace = new PlayingCard(PlayingCard.Suits.values()[i], PlayingCard.FaceValues.ACE);
			
			deck[i*6] = deck [i*6 + 24] = nine;
			deck[i*6 + 1] = deck [i*6 + 25] = ten;
			deck[i*6 + 2] = deck [i*6 + 26] = jack;
			deck[i*6 + 3] = deck [i*6 + 27] = queen;
			deck[i*6 + 4] = deck [i*6 + 28] = king;
			deck[i*6 + 5] = deck [i*6 + 29] = ace;
		}
		
		size = 48;
	}
	*/
// ****** PRIVATE METHODS *******
	
	// checks to see if a certain card is allowed to be in the deck (based on size and duplicates)
	private boolean checkDuplicate(PlayingCard card){
		
		int maxSame = 0;
		int maxSize = 0;
		int duplicates = 0;
		
		if(deckType == CUSTOM_DECK){
			return false;
		}
		
		switch(deckType){			
			case TRADITIONAL_DECK:
				maxSize = 52;
				maxSame = 1;
				break;
			case DOUBLE_DECK_ONESUIT:
				maxSize = 104;
				maxSame = 8;
				break;
			case DOUBLE_DECK_TWOSUIT:
				maxSize = 104;
				maxSame = 4;
				break;
			case DOUBLE_DECK_FOURSUIT:
			case DOUBLE_DECK:
				maxSize = 104;
				maxSame = 2;
				break;
			case PINOCHLE_DECK:
				maxSize = 48;
				maxSame = 2;
				break;		
		}
		
		if(size == maxSize){
			throw new DeckException("Deck is full. Cannot insert card.");
		}
		
		for(int i=0; i<size; i++){
			if(deck[i].equals(card)){
				duplicates++;
			}
		}
		return duplicates >= maxSame;
	}
	
// ****** PUBLIC METHODS *******
	
	// shuffles the deck
	public final void shuffleDeck(){
		Random r = new Random();
		int index;
		Card temp;
		
		for(int i=0; i<5; i++){
			for(int j=0; j<size; j++){
				index = r.nextInt(size);

				temp = deck[j];
				deck[j] = deck[index];
				deck[index] = temp;
			}
		}
	}
	
	// returns true if the deck is empty
	public boolean isEmpty(){
		return size == 0;
	}
	
	// returns the top card of the deck
	public Card revealCard(){
		
		// throw exception if the deck is empty
		if(size == 0){
			throw new DeckException("Cannot reveal top card from empty deck.");
		} 
		
		return deck[size-1];				
	}
	
	// returns and removes the top card of the deck
	public Card drawCard(){
		
		// throw exception if the deck is empty
		if(size == 0){
			throw new DeckException("Cannot draw card from empty deck.");
		}
		
		Card card = deck[--size];
		deck[size] = null;
		return card;
	}
	
	// inserts a new card at the bottom of the deck
	public void insertCard(PlayingCard card){
		
		if(checkDuplicate(card)){
			throw new DeckException("Cannot insert duplicate card into this deck.");
		}
		
		for(int i=size; i>0; i--){
			deck[i] = deck[i-1];
		}
		deck[0] = card;
		size++;
	}
	
	// inserts a new card on top of the deck
	public void returnCard(PlayingCard card){
		
		if(checkDuplicate(card)){
			throw new DeckException("Cannot return duplicate card into this deck.");					
		}
		
		deck[size++] = card;
	}
	
	// prints out the deck from top card to bottom card
	public void printDeck(){
		printDeck(deck);
	}
	
	// static method to print out a deck from top card to bottom card
	public static void printDeck(Card[] deck){
		for(int i=deck.length-1; i>=0; i--){
			deck[i].printCard();
		}
	}
	
	// sorts the deck (needs a comparator)
	public DeckOfCards sortDeck(Comparator comp){
		return sortDeck(this, comp);
	}
	
	public static Card[] sortDeck(Card[] deck, Comparator comp){
		
		LinkedList<Card> deckLL = new LinkedList<Card>();
		
		// add the entire deck array into a vector
		deckLL.addAll(Arrays.asList(deck));
		deckLL.sort(comp);
		
		for(int i=0; i<deckLL.size(); i++){
			deck[i] = deckLL.get(i);
		}
		
		return deck;
	}
	
	// static method to sort a deck
	public static DeckOfCards sortDeck(DeckOfCards deck, Comparator comp){
		
		LinkedList<Card> deckLL = new LinkedList<Card>();
		
		// add the entire deck array into the vector
		deckLL.addAll(Arrays.asList(deck.deck));
		deckLL.sort(comp);
		
		for(int i=0; i<deckLL.size(); i++){
			deck.deck[i] = deckLL.get(i);
		}
		
		return deck;
	}
}
