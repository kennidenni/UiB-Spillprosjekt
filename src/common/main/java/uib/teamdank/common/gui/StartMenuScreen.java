package uib.teamdank.common.gui;

import com.badlogic.gdx.Screen;

import uib.teamdank.common.Score;

/**
 * Screen and the graphical user interface for a start menu.
 */
public interface StartMenuScreen extends Screen {

	/**
	 * Exits the game.
	 */
	public void exitGame();

	/**
	 * Starts a new game.
	 */
	public void newGame();

	/**
	 * Shows a {@link HighscoreMenuScreen} with the relevant {@link Score}s.
	 */
	public void viewHighscores();

}
