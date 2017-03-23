package uib.teamdank.common.gui;

import com.badlogic.gdx.Screen;

/**
 * Screen for the main gameplay of a game.
 * <p>
 * Weather data, procedural generation and other nifty classes can be found in
 * the {@link uib.teamdank.common.util}-package.
 */
public interface GameScreen extends Screen {

	public void addLayer(Layer layer);
	
	/**
	 * Pauses the game and shows a {@link PauseMenuScreen}.
	 */
	public void pause();
	
}