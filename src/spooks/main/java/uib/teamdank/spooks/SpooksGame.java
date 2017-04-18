package uib.teamdank.spooks;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import uib.teamdank.common.Game;
import uib.teamdank.spooks.gui.GameScreen;
import uib.teamdank.spooks.gui.PauseMenuScreen;
import uib.teamdank.spooks.gui.StartMenuScreen;

/**
 * The main game class for Spooks.
 */
public class SpooksGame extends Game {
	StartMenuScreen startMenuScreen;
	GameScreen gameScreen;
	PauseMenuScreen pauseMenuScreen;

    public static void main(String[] args){
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.fullscreen = true;
        config.title = "Spooks";
        config.width = 1280;
        config.height = 720;
        new LwjglApplication(new SpooksGame(), config) ;
    }

	@Override
	public void create() {
		startMenuScreen = new StartMenuScreen();
		gameScreen = new GameScreen();
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
