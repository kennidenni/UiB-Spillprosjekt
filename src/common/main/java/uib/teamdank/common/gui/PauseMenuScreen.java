package uib.teamdank.common.gui;

import com.badlogic.gdx.Screen;

/**
 * Screen and the graphical user interface for a pause menu.
 */
public interface PauseMenuScreen extends Screen {

	/**
	 * Exits the game to a {@link StartMenuScreen}.
	 */
	public void exitToStartMenu();

	/**
	 * Returns to a {@link GameScreen}.
	 */
	public void resumeGame();

}
