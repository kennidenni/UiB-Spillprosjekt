package uib.teamdank.foodfeud;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import uib.teamdank.common.Game;
import uib.teamdank.foodfeud.gui.GameScreen;
import uib.teamdank.foodfeud.gui.PauseMenuScreen;
import uib.teamdank.foodfeud.gui.StartMenuScreen;

/**
 * The main game class for Food Feud.
 */
public class FoodFeud extends Game {
	private StartMenuScreen startMenuScreen;
	private GameScreen gameScreen;
	private PauseMenuScreen pauseMenuScreen;

    public static void main(String[] args){
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.fullscreen = true;
        config.title = "FoodFeud";
        config.width = 1280;
        config.height = 720;
        new LwjglApplication(new FoodFeud(), config) ;
    }

	@Override
	public void create() {
		startMenuScreen = new StartMenuScreen();
		gameScreen = new GameScreen(this);
		pauseMenuScreen = new PauseMenuScreen();
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
