package uib.teamdank.foodfeud;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import uib.teamdank.common.Game;
import uib.teamdank.common.gui.GameScreen;
import uib.teamdank.common.gui.PauseMenuScreen;
import uib.teamdank.common.gui.StartMenuScreen;

/**
 * The main game class for Food Feud.
 */
public class FoodFeud extends Game {
	StartMenuScreen startMenuScreen;
	GameScreen gameScreen;
	PauseMenuScreen pauseMenuScreen;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	@Override
	public void create() {
		startMenuScreen = new uib.teamdank.foodfeud.gui.StartMenuScreen();
		setScreen(startMenuScreen);
	}

	@Override
	public GameScreen getGameScreen() {
		return gameScreen;
	}

	@Override
	public PauseMenuScreen getPauseMenuScreen() {
		return pauseMenuScreen;
	}

	@Override
	public StartMenuScreen getStartMenuScreen() {
		return startMenuScreen;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SpriteBatch getSpriteBatch() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
