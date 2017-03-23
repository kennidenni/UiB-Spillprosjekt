package uib.teamdank.common;

import uib.teamdank.common.gui.GameScreen;
import uib.teamdank.common.gui.PauseMenuScreen;
import uib.teamdank.common.gui.StartMenuScreen;

/**
 * Interface for a game.
 */
public abstract class Game extends com.badlogic.gdx.Game {
	public abstract GameScreen getGameScreen();

	public abstract PauseMenuScreen getPauseMenuScreen();

	public abstract StartMenuScreen getStartMenuScreen();

	public abstract String getTitle();
}
