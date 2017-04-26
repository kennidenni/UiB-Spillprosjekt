package uib.teamdank.cargame.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
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

public class PauseMenuScreen implements uib.teamdank.common.gui.PauseMenuScreen {
	private static final String LOGO = "images/CarGameLogo.png";
	private static final String PAUSE = "images/Buttons/bs_pause.png";
	private static final String BACK = "images/Buttons/bs_back.png";
	private static final String EXIT = "images/Buttons/bs_quit.png";

	private Stage stage;
	private Table menu;
	private ImageButton logoButton;
	private ImageButton pauseButton;
	private ImageButton backButton;
	private ImageButton exitButton;
	private Array<Button> buttons;
	private Game game;

	public PauseMenuScreen(CarGame game) {
		this.game = game;
		stage = new Stage(new FitViewport(1920, 1080));

		logoButton = setupButton(LOGO);
		pauseButton = setupButton(PAUSE);
		backButton = setupButton(BACK);
		exitButton = setupButton(EXIT);

		menu = new Table();
		buttons = new Array<Button>();

		buttons.add(backButton);
		buttons.add(exitButton);

		menu.add(pauseButton).pad(0, 0, 20, 0);
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
		backButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Stage stage = event.getTarget().getStage();
				Vector2 mouse = stage.screenToStageCoordinates(new Vector2(Gdx.input.getX(), Gdx.input.getY()));

				if (stage.hit(mouse.x, mouse.y, true) == event.getTarget()) {
					resume();
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
					exitToStartMenu();
				}
			}
		});
	}

	// Setting the buttons up
	public ImageButton setupButton(String imageString) {
		Texture myTexture = new Texture(Gdx.files.internal(imageString));
		TextureRegion myTextureRegion = new TextureRegion(myTexture);
		TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
		ImageButton logo = new ImageButton(myTexRegionDrawable);
		return logo;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}

	@Override
	public void exitToStartMenu() {
		game.setScreen(game.getStartMenuScreen());
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(float delta) {
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			resume();
			return;
		}
		
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
		game.setScreen(game.getGameScreen());
	}

	@Override
	public void resumeGame() {
		// TODO Auto-generated method stub
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
	}

}