package PlayingCard;

import CardGame.Hearts;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.*;

/**
 * Swing class designed to show a PlayingCard. It is meant to be used on a
 * JLayeredPane and in line with a CardGame.
 *
 * @author Timothy
 */
public class CardGraphics extends JPanel {

// ******* STATIC VARIABLES *******
	private static final ImageIcon[] cardSpadeImages = {
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/aceSpades.png"),
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/twoSpades.png"),
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/threeSpades.png"),
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/fourSpades.png"),
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/fiveSpades.png"),
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/sixSpades.png"),
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/sevenSpades.png"),
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/eightSpades.png"),
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/nineSpades.png"),
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/tenSpades.png"),
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/jackSpades.png"),
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/queenSpades.png"),
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/kingSpades.png")
	};

	private static final ImageIcon[] cardClubImages = {
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/aceClubs.png"),
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/twoClubs.png"),
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/threeClubs.png"),
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/fourClubs.png"),
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/fiveClubs.png"),
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/sixClubs.png"),
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/sevenClubs.png"),
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/eightClubs.png"),
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/nineClubs.png"),
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/tenClubs.png"),
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/jackClubs.png"),
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/queenClubs.png"),
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/kingClubs.png")
	};

	private static final ImageIcon[] cardHeartImages = {
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/aceHearts.png"),
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/twoHearts.png"),
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/threeHearts.png"),
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/fourHearts.png"),
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/fiveHearts.png"),
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/sixHearts.png"),
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/sevenHearts.png"),
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/eightHearts.png"),
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/nineHearts.png"),
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/tenHearts.png"),
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/jackHearts.png"),
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/queenHearts.png"),
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/kingHearts.png")
	};

	private static final ImageIcon[] cardDiamondImages = {
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/aceDiamonds.png"),
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/twoDiamonds.png"),
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/threeDiamonds.png"),
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/fourDiamonds.png"),
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/fiveDiamonds.png"),
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/sixDiamonds.png"),
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/sevenDiamonds.png"),
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/eightDiamonds.png"),
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/nineDiamonds.png"),
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/tenDiamonds.png"),
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/jackDiamonds.png"),
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/queenDiamonds.png"),
		new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/kingDiamonds.png")
	};
	
	private static final ImageIcon cardHiddenImage = new ImageIcon("C:/Users/Timothy/Documents/NetBeansProjects/HeartsCardGame/images/cardBack.png");

	// Rounded corner and card dimension variables
	// (change these to change the appearance of the card)
	private static final int width = 125;
	private static final int height = 175;
	private static final int shadowGap = 3;
	private static final int shadowOffset = 10;

	private static final Color backgroundColor = Color.WHITE;
	private static final Color shadowColor = Color.BLACK;
	private static final Dimension arcs = new Dimension(10, 10);
	
	// Listener variables	
	private static MouseListener mouseEvent;
	private static MouseMotionListener motionEvent;

	// Draggable direction constants (do not edit!)
	public static final int BOTH = 0;
	public static final int LEFTRIGHT = 1;
	public static final int UPDOWN = -1;

	// Rotated direction constants (do not edit!)
	public static final int TO_SIDEWAYS = -1;
	public static final int TO_STRAIGHT = 0;

	// Selectable direction constants (do not edit!)
	public static final int UP = 10;
	public static final int DOWN = 11;
	public static final int RIGHT = 12;
	public static final int LEFT = 13;

// ******* INSTANCE VARIABLES *******	
	// JLayeredPane and graphic variables
	private boolean hidden;
	private volatile int prevX;
	private volatile int prevY;
	private int previousLayerIndex;
	private ImageIcon currentImage;
	private JLabel imageLabel;
	
	// draggable variables
	private boolean draggable;
	private int draggableDirection;
	
	// selectable variabels
	private boolean selectable;
	private boolean selected;
	private int selectDir;
	
	// rotate variables
	private boolean sideways;

	/**
	 * The PlayingCard the CardGraphics implements. This variable must be
	 * initialized using one of the constructors
	 */
	private PlayingCard baseCard;	
	

// ******* CONSTRUCTOR *******
	
	/**
	 * Constructor for CardGraphics that takes the PlayingCard to implement,
	 * a boolean to decide whether to hide the card or not, and an int to
	 * select the direction of the card
	 * @param card the PlayingCard the CardGraphics should implement
	 * @param hide decide whether to hide the card or show it
	 * @param selectDirection decide what direction the card should pop when selected
	 */
	public CardGraphics(PlayingCard card, boolean hide, int selectDirection) {

		initializeListeners();
		initializeVariables(card, hide, selectDirection);
		implementDesign();

		addMouseListener(mouseEvent);
		addMouseMotionListener(motionEvent);
	}

	/**
	 * Constructor for CardGraphics that takes the PlayingCard to
	 * implement (defaults the card to viewable and the selectable direction
	 * to up)
	 * @param card the PlayingCard the CardGraphics should implement
	 */
	public CardGraphics(PlayingCard card) {
		this(card, false, UP);
	}
	
	/**
	 * Constructor for CardGraphics that takes the PlayingCard to 
	 * implement and a boolean to decide whether the card should 
	 * be hidden or not (defaults the selectable direction to up)
	 * @param card the PlayingCard the CardGraphics should implement
	 * @param hide boolean to determine whether to hide the card or not
	 */
	public CardGraphics(PlayingCard card, boolean hide) {
		this(card, hide, UP);
	}

// ******* OVERRIDDEN METHODS *******
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D graphics = (Graphics2D) g;
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		graphics.setColor(shadowColor);

		// draw shadow
		int wShadow = width - shadowOffset;
		int hShadow = height - shadowOffset;
		int wCard = width - shadowGap;
		int hCard = height - shadowGap;

		// if the card is sidways, we need to 
		if (sideways) {
			int t = wShadow;
			wShadow = hShadow;
			hShadow = t;

			t = wCard;
			wCard = hCard;
			hCard = t;
		}
	
		graphics.fillRoundRect(shadowOffset, shadowOffset, wShadow, hShadow, arcs.width, arcs.height);

		// draw card
		graphics.setColor(backgroundColor);
		graphics.fillRoundRect(0, 0, wCard, hCard, arcs.width, arcs.height);

		// draw border
		graphics.setColor(shadowColor);
		graphics.drawRoundRect(0, 0, wCard, hCard, arcs.width, arcs.height);
	}

// ******* CONSTRUCTOR HELPERS *******
	// constructs the elements and initializes variables
	private void initializeVariables(PlayingCard card, boolean hide, int selectDirection) {

		// initialize variables
		prevX = 0;
		prevY = 0;

		draggable = false;
		draggableDirection = BOTH;

		selectable = false;
		selected = false;
		selectDir = selectDirection;

		sideways = false;

		baseCard = card;
		hidden = hide;

		// construct images
		imageLabel = new JLabel();
		assignImage();
	}

	// creates the mouse listeners
	private void initializeListeners() {

		// construct event listeners		
		mouseEvent = new MouseListener() {
			public void mouseClicked(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
				mousePressedEvent(e);
			}

			public void mouseReleased(MouseEvent e) {
				mouseReleasedEvent(e);
			}

			public void mouseEntered(MouseEvent e) {
				mouseEnteredEvent(e);
			}

			public void mouseExited(MouseEvent e) {
				mouseExitedEvent(e);
			}
		};

		motionEvent = new MouseMotionListener() {

			public void mouseDragged(MouseEvent e) {
				mouseDraggedEvent(e);
			}

			public void mouseMoved(MouseEvent e) {
			}
		};
	}

	// adds label and formats the card
	private void implementDesign() {
		setBounds(0, 0, width, height);

		setOpaque(false);
		add(imageLabel);
	}

// ******* PRIVATE HELPER FUNCTIONS *******
	// method to run when the mouse is released from the CardGraphics
	private void mouseReleasedEvent(MouseEvent e) {
		if (draggable) {
			((JLayeredPane) getParent()).setPosition(this, previousLayerIndex);
		}
	}

	// method to run when the mouse is clicked on the CardGraphics
	private void mousePressedEvent(MouseEvent e) {

		// ******* CLICKING CODE *******
		// code to activate when the code is "selected" (clicked)
		if (selectable) {
			selectOrDeselectCard();
		}

		// ******* DRAGGING CODE *******
		// store starting point for dragging
		if (draggable) {
			// bring the clicked card to the top of its current layer (on top of all other cards)
			previousLayerIndex = ((JLayeredPane) getParent()).getPosition(this);
			((JLayeredPane) getParent()).moveToFront(this);

			prevX = e.getXOnScreen();
			prevY = e.getYOnScreen();
		}
	}

	// method to run when the CardGraphics is dragged
	private void mouseDraggedEvent(MouseEvent e) {

		// ******* DRAGGING CODE *******
		if (draggable) {
			int dx = 0;
			int dy = 0;

			if (draggableDirection != UPDOWN) {
				dx = e.getXOnScreen() - prevX;
			}
			if (draggableDirection != LEFTRIGHT) {
				dy = e.getYOnScreen() - prevY;
			}

			prevX = e.getXOnScreen();
			prevY = e.getYOnScreen();

			// Code to make sure the card stays on the screen
			if (getX() + dx < 1 || getX() + dx > getParent().getWidth() - getWidth()) {
				dx = 0;
			}
			if (getY() + dy < 1 || getY() + dy > getParent().getHeight() - getHeight()) {
				dy = 0;
			}

			setLocation(getX() + dx, getY() + dy);

			System.out.println("X: " + getX() + ", Y: " + getY());
		}
	}

	// method to run when the mouse starts hovering over the card
	private void mouseEnteredEvent(MouseEvent e) {
	}

	// method to run when the mouse no longer is hovering over the card
	private void mouseExitedEvent(MouseEvent e) {
	}

	// sets the icon on the image to be the right one
	private void assignImage() {

		boolean fix = false;
		// fix image if the card should be sideways
		if (sideways) {
			fix = true;
			rotateCard(TO_STRAIGHT);
		}

		if (!hidden) {
			switch (baseCard.getSuit()) {
				case PlayingCard.SPADES:
					currentImage = cardSpadeImages[baseCard.getFaceValue() - 1];
					imageLabel.setIcon(currentImage);
					break;
				case PlayingCard.CLUBS:
					currentImage = cardClubImages[baseCard.getFaceValue() - 1];
					imageLabel.setIcon(currentImage);
					break;
				case PlayingCard.HEARTS:
					currentImage = cardHeartImages[baseCard.getFaceValue() - 1];
					imageLabel.setIcon(currentImage);
					break;
				case PlayingCard.DIAMONDS:
					currentImage = cardDiamondImages[baseCard.getFaceValue() - 1];
					imageLabel.setIcon(currentImage);
					break;
			}
		} else {
			currentImage = cardHiddenImage;
			imageLabel.setIcon(cardHiddenImage);
		}

		if (fix) {
			rotateCard(TO_SIDEWAYS);
		}

	}

	// selects or deselects the card (toggles between the two)
	private void selectOrDeselectCard() {
		int sign = (!selected) ? -1 : 1;

		if (selectDir == DOWN || selectDir == RIGHT) {
			sign *= -1;
		}

		if (!sideways) {
			setLocation(getX(), getY() + sign * 75);
		} else {
			setLocation(getX() + sign * 75, getY());
		}
		if (getY() < 0) {
			setLocation(getX(), 0);
		} else if (getY() > this.getParent().getHeight() - height) {
			setLocation(getX(), this.getParent().getHeight() - height);
		}

		if (getX() < 0) {
			setLocation(0, getY());
		} else if (getX() > this.getParent().getWidth() - width) {
			setLocation(this.getParent().getWidth() - width, getY());
		}

		selected = !selected;

		Hearts.getCurrentPlayer().notifyPlayer(this);
	}

	// rotates the card the degree given (will not make it rotate the wrong way)
	private void rotateCard(int degree) {
		if (degree == TO_SIDEWAYS && !sideways) {
			setBounds(getX(), getY(), getHeight(), getWidth());
			int w = imageLabel.getIcon().getIconWidth();
			int h = imageLabel.getIcon().getIconHeight();
			int type = BufferedImage.TYPE_INT_RGB;

			BufferedImage image = new BufferedImage(h, w, type);
			Graphics2D g2 = image.createGraphics();

			double x = (h - w) / 2.0;
			double y = (w - h) / 2.0;

			AffineTransform at = AffineTransform.getTranslateInstance(x, y);
			at.rotate(Math.toRadians(degree * 90), w / 2.0, h / 2.0);
			g2.drawImage(((ImageIcon) imageLabel.getIcon()).getImage(), at, imageLabel);
			g2.dispose();

			imageLabel.setIcon(new ImageIcon(image));
			sideways = true;

		} else {
			setBounds(getX(), getY(), width, height);
			imageLabel.setIcon(currentImage);
			sideways = false;
		}
	}

// ******* PUBLIC METHODS *******	
	/**
	 * Method to select a card. If the card is already selected
	 * nothing will be done.
	 */
	public void selectCard() {
		if (!selected) {
			selectOrDeselectCard();
		}
	}
	
	/**
	 * Method to deselect a card. If the card is already deselected, 
	 * nothing will be done.
	 */
	public void deselectCard() {
		if (selected) {
			selectOrDeselectCard();
		}
	}

	/**
	 * Method to allow the card to be dragged in a specific direction
	 * @param b boolean to allow the card to be draggable or not
	 * @param direction int value to decide the draggable direction(s)
	 */
	public void setDraggable(boolean b, int direction) {
		draggable = b;
		if (direction != BOTH && direction != LEFTRIGHT && direction != UPDOWN) {
			direction = BOTH;
		}
		draggableDirection = direction;
	}
	
	/**
	 * Method to allow the card to be dragged in any direction
	 * @param b boolean to allow the card to be draggable or not
	 */
	public void setDraggable(boolean b) {
		setDraggable(b, BOTH);
	}

	/**
	 * Method to make the card selectable or not
	 * @param b boolean to decide whether the card may be selected
	 */
	public void setSelectable(boolean b) {
		selectable = b;
	}

	/**
	 * Method to show or hide the card
	 * @param b boolean to decide whether to show or hide the card
	 */
	public void setViewable(boolean b) {
		hidden = !b;
		assignImage();
	}

	/**
	 * Method to rotate the card. If the card is sideways, this
	 * function will return it to being straight up.
	 */
	public void rotateCard() {
		if (sideways) {
			rotateCard(TO_STRAIGHT);
		} else {
			rotateCard(TO_SIDEWAYS);
		}
	}

	/**
	 * Method to get the PlayingCard associated with this CardGraphics
	 * @return the PlayingCard stored by this CardGraphics
	 */
	public PlayingCard getCard() {
		return baseCard;
	}
}
