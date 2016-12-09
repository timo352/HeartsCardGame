package CardGame;

/**
 * Abstract class that defines the methods needed
 * for a Card Game.
 * @author Timothy
 * @version 1.0
 * Last edited: January 6, 2016
 */
public abstract class CardGame {
	
	protected int playerCount;
	protected int turnCount;
	
	protected CardGame(int pc){
		turnCount = 0;
		playerCount = pc;
	}
	
	protected abstract void start();	
	
	protected abstract boolean end();
	
	protected abstract void turn();	
	protected abstract void initialize();
	protected abstract void close();	
	protected abstract void save();
	protected abstract void load();
	protected abstract void restart();
}
