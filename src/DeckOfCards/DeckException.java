package DeckOfCards;
/**
 * Class to implement a DeckException, used whenever an error occurs
 * in the DeckOfCards class
 * @author Timothy
 * @version 1.0
 * Last edited: January 6, 2016
 */
public class DeckException extends RuntimeException{
	
	public DeckException(String err){
		super(err);
	}	
}
