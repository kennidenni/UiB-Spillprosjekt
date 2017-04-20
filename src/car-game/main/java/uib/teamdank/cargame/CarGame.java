package uib.teamdank.cargame;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import uib.teamdank.cargame.gui.GameScreen;
import uib.teamdank.cargame.gui.PauseMenuScreen;
import uib.teamdank.cargame.gui.StartMenuScreen;
import uib.teamdank.common.Game;


/**
 * The main game class for Car Game.
 */
public class CarGame extends Game {
	private static final String TITLE = "Carl the Crasher";
	private StartMenuScreen startMenuScreen;
	private GameScreen gameScreen;
	private PauseMenuScreen pauseMenuScreen;
	private SpriteBatch batch;

    public static void main(String[] args){
    	LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = TITLE;
        config.width = 1280;
        config.height = 720;
        new LwjglApplication(new CarGame(), config) ;
    }
	
	@Override
	public void create() {
		startMenuScreen = new StartMenuScreen(this);
		gameScreen = new GameScreen(this);
		pauseMenuScreen = new PauseMenuScreen();

		batch = new SpriteBatch();
		
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
		return TITLE;
	}

	@Override
	public SpriteBatch getSpriteBatch() {
		return batch;
	}
	
	@Override 
	public void dispose() {
		super.dispose();
		batch.dispose();
		screen.dispose();
	}
}
