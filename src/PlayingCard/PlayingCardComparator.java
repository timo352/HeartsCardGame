package PlayingCard;

import java.util.Comparator;

public class PlayingCardComparator implements Comparator {
	
	private boolean highAce;
	
	/**
	 * Constructor for PlayingCardComparator that takes an int. If
	 * int equals PlayingCard.HIGH_ACE, the aces will be compared high,
	 * otherwise, the ace is compared low.
	 * @param ace decides whether to compare the ace as high or low
	 * 
	 */
	public PlayingCardComparator(int ace){
		if(ace == PlayingCard.HIGH_ACE){
			highAce = true;
		} else {
			highAce = false;
		}
	}
	
	private void checkComparable(PlayingCard c) /*throws InvalidObjectException*/ {
		if (!(c instanceof PlayingCard)) {
			//throw new InvalidObjectException("This object is not a PlayingCard. Cannot compare.");
			System.out.println("This object is not a PlayingCard. Cannot compare.");
			System.exit(1);
		}
	}

	private boolean isGreaterThanOrEqualTo(PlayingCard c1, PlayingCard c2) {

		int face1 = c1.getFaceValue();
		int face2 = c2.getFaceValue();
		
		if(highAce){
			if (face1 == PlayingCard.ACE) {
				face1 = PlayingCard.HIGH_ACE;
			}
			if (face2 == PlayingCard.ACE) {
				face2 = PlayingCard.HIGH_ACE;
			}
		}
		if (c1.getSuit() == c2.getSuit()) {
			return face1 >= face2;
		} else {
			return (c1.getSuit() <= c2.getSuit());
		}
	}

	private boolean isEqualTo(PlayingCard c1, PlayingCard c2) {
		return c1.getFaceValue() == c2.getFaceValue() && c1.getSuit() == c2.getSuit();
	}

	private boolean isLessThan(PlayingCard c1, PlayingCard c2) {
		return !isGreaterThanOrEqualTo(c1, c2);
	}

	private boolean isGreaterThan(PlayingCard c1, PlayingCard c2) {
		return !isLessThan(c1, c2) && !isEqualTo(c1, c2);
	}

	public int compare(Object o1, Object o2) {

		checkComparable((PlayingCard) o1);
		checkComparable((PlayingCard) o2);

		if (isLessThan((PlayingCard) o1, (PlayingCard) o2)) {
			return -1;
		} else if (isGreaterThan((PlayingCard) o1, (PlayingCard) o2)) {
			return 1;
		} else {
			return 0;
		}
	}
	
	public boolean greaterFaceValue(Object o1, Object o2) {
		
		checkComparable((PlayingCard) o1);
		checkComparable((PlayingCard) o2);
		
		int card1 = ((PlayingCard) o1).getFaceValue();
		int card2 = ((PlayingCard) o2).getFaceValue();
		
		if(highAce){
			if (card1 == PlayingCard.ACE) {
				card1 = PlayingCard.HIGH_ACE;
			}
			if (card2 == PlayingCard.ACE) {
				card2 = PlayingCard.HIGH_ACE;
			}
		}
		
		return  card1 > card2;
	}
}