package uib.teamdank.common;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import uib.teamdank.common.gui.GameScreen;
import uib.teamdank.common.gui.PauseMenuScreen;
import uib.teamdank.common.gui.StartMenuScreen;

/**
 * Interface for a game.
 */
public abstract class Game extends com.badlogic.gdx.Game {
	public abstract void newGame();

	public abstract GameScreen getGameScreen();

	public abstract PauseMenuScreen getPauseMenuScreen();

	public abstract StartMenuScreen getStartMenuScreen();

	public abstract String getTitle();
	
	public abstract SpriteBatch getSpriteBatch();
}
