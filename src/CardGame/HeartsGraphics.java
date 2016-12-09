package CardGame;

import PlayingCard.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.border.BevelBorder;

/**
 * Class that helps create the game of hearts using Swing. It is meant to be
 * used directly with the Hearts class extended from CardGame. The two
 * communicate to create the game.
 *
 * @author Timothy
 */
public final class HeartsGraphics {

// ******* STATIC VARIABLES *******
	private static final int windowWidth = 1600;
	private static final int windowHeight = 900;
	private static final Color gameBoardColor = new Color(0, 100, 0);
	
	private static final Point PLAYER_1_LOCATION = new Point(725,450);
	private static final Point PLAYER_2_LOCATION = new Point(575,350);
	private static final Point PLAYER_3_LOCATION = new Point(725,250);
	private static final Point PLAYER_4_LOCATION = new Point(875,350);
	
	private static final WindowListener exitListener  = new WindowListener(){

		public void windowOpened(WindowEvent e) {}
		public void windowClosing(WindowEvent e) {}
		public void windowClosed(WindowEvent e) {
			System.exit(0);
		}
		public void windowIconified(WindowEvent e) {}
		public void windowDeiconified(WindowEvent e) {}
		public void windowActivated(WindowEvent e) {}
		public void windowDeactivated(WindowEvent e) {}		
	};

// ******* INSTANCE VARIABLES *******
	private JFrame gameWindow;
	private JLayeredPane gameBoard;
	private JLayeredPane tempBoard;
	private JMenuBar gameBar;
	
	private JMenu fileMenu;
	private JMenu statsMenu;
	private JMenu helpMenu;
	private JMenuItem resetItem = new JMenuItem("Reset...");
	private JMenuItem loadItem = new JMenuItem("Load...");
	private JMenuItem saveItem = new JMenuItem("Save...");
	private JMenuItem scoreItem = new JMenuItem("Score...");
	private JMenuItem playersItem = new JMenuItem("Players...");
	private JMenuItem tutorialItem = new JMenuItem("Tutorial...");
	private JMenuItem aboutItem = new JMenuItem("About...");
	
	private final CardGraphics[][] hands;
	private final CardGraphics[] played;

// ******* CONSTRUCTOR *******
	public HeartsGraphics() {
		createWindow();
		
		hands = new CardGraphics[4][];
		played = new CardGraphics[4];
	}

// ******* CONSTRUCTOR HELPERS *******
	private void createWindow() {

		gameWindow = new JFrame("HEARTS");
		gameBoard = new JLayeredPane();
		gameBar = new JMenuBar();
		
		fileMenu = new JMenu("File");
		statsMenu = new JMenu("Stats");
		helpMenu = new JMenu("Help");
		
		
		fileMenu.add(resetItem);
		fileMenu.add(saveItem);
		fileMenu.add(loadItem);
		
		statsMenu.add(scoreItem);
		statsMenu.add(playersItem);
		
		helpMenu.add(tutorialItem);
		helpMenu.add(aboutItem);
				
		gameBar.add(fileMenu);
		gameBar.add(statsMenu);
		gameBar.add(helpMenu);
		
		// make the layered pane the content pane
		gameBoard.setOpaque(true);
		gameWindow.setContentPane(gameBoard);
		gameWindow.setLayout(null);
		
		gameWindow.setJMenuBar(gameBar);
		
		gameBar.setVisible(true);
		
		// set the width and height of the screen and center it
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double screenWidth = screenSize.getWidth();
		double screenHeight = screenSize.getHeight();

		gameWindow.setSize(windowWidth, windowHeight);
		gameWindow.setLocation((int) ((screenWidth - windowWidth) / 2), (int) ((screenHeight - windowHeight) / 2));
		
		// format the window
		gameBoard.setBackground(gameBoardColor);

		// edit frame properties
		gameWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		gameWindow.setUndecorated(false);
		gameWindow.setResizable(false);
		gameWindow.setTitle("Hearts");
		gameWindow.setVisible(true);
		
		gameWindow.addWindowListener(exitListener);
	}

// ******* PUBLIC METHODS *******
	public void warnPlayer(PlayingCard card){
		String cardString = card.getFaceValueString() + " of " + card.getSuitString();
		
		JOptionPane.showMessageDialog(gameWindow,"You cannot play the " + cardString + "\nPlease try again.", "", JOptionPane.OK_OPTION);
	}	
	
	public void displayScores(HeartsPlayer[] players){
		String scoreString0 = players[0].getName() + ": " + players[0].getScore();
		String scoreString1 = players[1].getName() + ": " + players[1].getScore();
		String scoreString2 = players[2].getName() + ": " + players[2].getScore();
		String scoreString3 = players[3].getName() + ": " + players[3].getScore();
		
		JOptionPane.showMessageDialog(gameWindow, "SCORES\n" + scoreString0 + "\n" + scoreString1 + "\n" + scoreString2 + "\n" + scoreString3, "", JOptionPane.OK_OPTION);
	}
	
	public void displayWinner(HeartsPlayer[] players, int winner){
		String winMessage = players[winner].getName() + " has won the game!\nThanks for playing!";
		
		JOptionPane.showMessageDialog(gameWindow, winMessage, players[winner].getName() + " won!", JOptionPane.OK_OPTION);
	}
	
	public void displayCard(PlayingCard card, int playerNumber){
		
		Point p = null;
		int pos = 4;
		
		switch(playerNumber){
			case HeartsPlayer.PLAYER_ONE:
				p = PLAYER_1_LOCATION;
				pos = 0;
				break;
			case HeartsPlayer.PLAYER_TWO:
				p = PLAYER_2_LOCATION;
				pos = 1;
				break;
			case HeartsPlayer.PLAYER_THREE:
				p = PLAYER_3_LOCATION;
				pos = 2;
				break;
			case HeartsPlayer.PLAYER_FOUR:
				p = PLAYER_4_LOCATION;
				pos = 3;
				break;			
		}
		
		CardGraphics c = new CardGraphics(card, false);		
		c.setLocation(p);
		
		played[pos] = c;
		gameBoard.add(c);		
	}
	
	public void clearPlayed(){
		
		for(int i=0; i<4; i++){
			gameBoard.remove(played[i]);
			played[i] = null;
		}
	}
	
	public void displayHand(PlayingCard[] hand, int playerNumber, boolean hidden) {

		clearHand(playerNumber);

		hands[playerNumber - 1] = new CardGraphics[hand.length];
		int direction = 0;
		
		switch(playerNumber){
			case HeartsPlayer.PLAYER_ONE:
				direction = CardGraphics.UP;
				break;
			case HeartsPlayer.PLAYER_TWO:
				direction = CardGraphics.RIGHT;
				break;
			case HeartsPlayer.PLAYER_THREE:
				direction = CardGraphics.DOWN;
				break;
			case HeartsPlayer.PLAYER_FOUR:
				direction = CardGraphics.LEFT;
		}
		
		// add the cards
		for (int i = 0; i < hand.length; i++) {
			
			CardGraphics c = new CardGraphics(hand[i], hidden, direction);
			hands[playerNumber - 1][i] = c;
			if(playerNumber == HeartsPlayer.PLAYER_ONE){
				c.setSelectable(true);
			}

			int xLocation;
			int yLocation;

			// left or right
			if (playerNumber % 2 == 0) {
				c.rotateCard();
				xLocation = 350 + (playerNumber - 2) * 350;
				yLocation = windowHeight / 2 + (c.getHeight() + (hand.length - 2) * 27) / 2 - c.getHeight() - i * 27;

			} else {
				xLocation = windowWidth / 2 + (c.getWidth() + (hand.length - 2) * 27) / 2 - c.getWidth() - i * 27;
				yLocation = windowHeight - c.getHeight() - 75 - (playerNumber - 1) * 300;
			}
			c.setLocation(xLocation, yLocation);

		}

		for (int i = 0; i < hand.length; i++) {
			gameBoard.add(hands[playerNumber - 1][i]);
		}
		
		gameWindow.repaint();
	}
	
	public void displayHand(LinkedList<PlayingCard> llHand, int playerNumber, boolean collection){
		PlayingCard[] hand = new PlayingCard[llHand.size()];

		for (int i = 0; i < llHand.size(); i++) {
			hand[i] = llHand.get(i);
		}
		
		if(collection){
			displayHand(hand, playerNumber, false);
		}
		else {
			displayHand(hand, playerNumber, playerNumber != HeartsPlayer.PLAYER_ONE);
		}
	}

	public void removeCard(PlayingCard card, int playerNumber, boolean viewable) {

		int count = 0;
		PlayingCard[] tempHand = new PlayingCard[hands[playerNumber - 1].length - 1];

		for (int i = 0; i < hands[playerNumber - 1].length; i++) {
			if (hands[playerNumber - 1][i].getCard().equals(card)) {
				gameBoard.remove(hands[playerNumber - 1][i]);
			} else {
				tempHand[count++] = hands[playerNumber - 1][i].getCard();
			}
		}

		displayHand(tempHand, playerNumber, viewable);
	}

	public void clearHand(int playerNumber) {
		if (hands[playerNumber - 1] != null) {
			for (int i = 0; i < hands[playerNumber - 1].length; i++) {
				gameBoard.remove(hands[playerNumber - 1][i]);
				hands[playerNumber - 1][i] = null;
			}
		}
	}

	public void makeViewable(int playerNumber, boolean b) {
		for (int i = 0; i < hands[playerNumber - 1].length; i++) {
			hands[playerNumber - 1][i].setViewable(b);
		}
	}
	
	public void deselectCard(PlayingCard c){
		for(int i=0; i<hands[0].length; i++){
			if(hands[0][i].getCard().equals(c)){
				hands[0][i].deselectCard();
			}
		}
	}
	
	public void destruct(){
		gameWindow.removeWindowListener(exitListener);
		gameWindow.dispose();
	}
}
