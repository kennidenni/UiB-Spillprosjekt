package uib.teamdank.cargame.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import uib.teamdank.cargame.CarGame;
import uib.teamdank.common.Game;

public class StartMenuScreen implements uib.teamdank.common.gui.StartMenuScreen {
	private static final String LOGO = "Images/CarGameLogo.png";
	private static final String PLAY = "Images/Buttons/start.png";
	private static final String HIGHSCORE = "Images/Buttons/cg_highscore.png";
	private static final String EXIT = "Images/Buttons/bs_quit.png";

	private Stage stage;
	private Texture myTexture;
	private Table menu;
	private ImageButton logoButton;
	private ImageButton playButton;
	private ImageButton highscoreButton;
	private ImageButton exitButton;
	private Array<Button> buttons;
	private HighscoreMenuScreen highscoreMenuScreen;
	private Game game;

	public StartMenuScreen(CarGame game) {
		this.game = game;
		stage = new Stage(new FitViewport(1920, 1080));
		highscoreMenuScreen = new HighscoreMenuScreen(game);

		logoButton = setupButton(LOGO);
		playButton = setupButton(PLAY);
		highscoreButton = setupButton(HIGHSCORE);
		exitButton = setupButton(EXIT);

		menu = new Table();
		buttons = new Array<Button>();

		buttons.add(playButton);
		buttons.add(highscoreButton);
		buttons.add(exitButton);

		menu.add(logoButton).pad(0, 0, 20, 0);
		menu.row();
		menu.pad(50);
		for (Button button : buttons) {
			menu.add(button).width((float) (button.getWidth() / 4)).height((float) (button.getHeight() / 4)).pad(5);
			menu.row();
		}

		menu.setFillParent(true);
		stage.addActor(menu);
		Gdx.input.setInputProcessor(stage);

		// Her kommer alle lytterene for input
		playButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Stage stage = event.getTarget().getStage();
				Vector2 mouse = stage.screenToStageCoordinates(new Vector2(Gdx.input.getX(), Gdx.input.getY()));

				if (stage.hit(mouse.x, mouse.y, true) == event.getTarget()) {
					game.setScreen(game.getGameScreen());
				}
			}
		});

		highscoreButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Stage stage = event.getTarget().getStage();
				Vector2 mouse = stage.screenToStageCoordinates(new Vector2(Gdx.input.getX(), Gdx.input.getY()));

				if (stage.hit(mouse.x, mouse.y, true) == event.getTarget()) {
					viewHighscores();
				}
			}
		});

		exitButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Stage stage = event.getTarget().getStage();
				Vector2 mouse = stage.screenToStageCoordinates(new Vector2(Gdx.input.getX(), Gdx.input.getY()));

				if (stage.hit(mouse.x, mouse.y, true) == event.getTarget()) {
					exitGame();
				}
			}
		});
	}

	// Setting the buttons up
	public ImageButton setupButton(String imageString) {
		myTexture = new Texture(Gdx.files.internal(imageString));
		TextureRegion myTextureRegion = new TextureRegion(myTexture);
		TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
		ImageButton logo = new ImageButton(myTexRegionDrawable);
		return logo;
	}

	@Override
	public void dispose() {
		// screen.dispose();
	}

	@Override
	public void exitGame() {
		Gdx.app.exit();
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void newGame() {
		game.newGame();
	}

	@Override
	public void pause() { // TODO
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void viewHighscores() {
		game.setScreen(highscoreMenuScreen);
	}
}