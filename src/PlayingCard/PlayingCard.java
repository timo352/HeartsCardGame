package PlayingCard;

import java.util.Comparator;

public final class PlayingCard implements Card{
	
// ****** STATIC VARIABLES *******
	
	// static Suit int variables
	public final static int SPADES = 0;
	public final static int HEARTS = 1;
	public final static int CLUBS = 2;
	public final static int DIAMONDS = 3;
	public final static int BLANK_SUIT = 4;
	
	// static FaceValue int variables
	public final static int ACE = 1;
	public final static int TWO = 2;
	public final static int THREE = 3;
	public final static int FOUR = 4;
	public final static int FIVE = 5;
	public final static int SIX = 6;
	public final static int SEVEN = 7;
	public final static int EIGHT = 8;
	public final static int NINE = 9;
	public final static int TEN = 10;
	public final static int JACK = 11;
	public final static int QUEEN = 12;
	public final static int KING = 13;
	public final static int HIGH_ACE = 14;
	public final static int BLANK_FACE = 0;
	
	public final static Comparator PlayingCardComparator_ACEHIGH = new PlayingCardComparator(PlayingCard.HIGH_ACE);
	public final static Comparator PlayingCardComparator_ACELOW = new PlayingCardComparator(PlayingCard.ACE);
	
// ****** INSTANCE VARIABLES *******
	
	private final int cardSuit;
	private final int cardFaceValue;
	
	
// ****** CONSTRUCTORS *******

	// public constructor that will use the static variables for the cards
	public PlayingCard(int suit, int val){		
		cardSuit = suit;
		cardFaceValue = val;
	}	
	
// ****** PUBLIC METHODS *******
	
	// returns equality based on same suit and face value
	public boolean equals(PlayingCard card){
		return cardSuit == card.cardSuit && cardFaceValue == card.cardFaceValue;
	}
	
	// returns the int constant associated with the suit of the card
	public int getSuit(){
	
		return cardSuit;
	}

	// returns the int constant associated with the face value of the card
	public int getFaceValue(){
	
		return cardFaceValue;
	}
	
	// returns the string character associated with this playing card 
	public String getFaceValueString(){
		
		String str;
		
		switch(cardFaceValue){
			case 1:
				str = "Ace";
				break;
			case 2:
				str = "Two";
				break;
			case 3:
				str = "Three";
				break;
			case 4:
				str = "Four";
				break;
			case 5:
				str = "Five";
				break;
			case 6:
				str = "Six";
				break;
			case 7:
				str = "Seven";
				break;
			case 8:
				str = "Eight";
				break;
			case 9:
				str = "Nine";
				break;
			case 10:
				str = "Ten";
				break;
			case 11:
				str = "Jack";
				break;
			case 12:
				str = "Queen";
				break;
			case 13:
				str = "King";
				break;
			default:
				str = "Blank";
				break;
		}
		
		return str;
	}
	
	public String getSuitString(){
		
		String str;
		
		switch(cardSuit){
			case SPADES:
				str = "Spades";
				break;
			case HEARTS:
				str = "Hearts";
				break;
			case CLUBS:
				str = "Clubs";
				break;
			case DIAMONDS:
				str = "Diamonds";
				break;
			default:
				str = "Blank";
				break;
		}
		
		return str;
	}
	
	// public instance method to print out the card stats in a meaningful way
	public void printCard(){
		
		if(cardFaceValue == BLANK_FACE || cardSuit == BLANK_SUIT){
			System.out.println("Cannot print out a blank card.");
		} else {
			System.out.println(getFaceValueString() + " of " + getSuitString());
		}
	}
	
	// public static method that calls the instance method 
	// so the user can decide which one he likes
	public static void printCard(PlayingCard card){
		card.printCard();
	}
}
