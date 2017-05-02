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
	private static final String TITLE = "Food Feud";
	private StartMenuScreen startMenuScreen;
    private GameScreen gameScreen;
	private PauseMenuScreen pauseMenuScreen;
	private SpriteBatch batch;

	private Player player;

    public static void main(String[] args){
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = TITLE;
        config.width = 1280;
        config.height = 720;
        new LwjglApplication(new FoodFeud(), config) ;
    }

	@Override
	public void create() {
        player = new Player();

		startMenuScreen = new StartMenuScreen(this);
		pauseMenuScreen = new PauseMenuScreen(this);

		batch = new SpriteBatch();

		setScreen(startMenuScreen);
	}

	@Override
	public GameScreen newGame() {
		gameScreen = new GameScreen(this);
		return gameScreen;
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
