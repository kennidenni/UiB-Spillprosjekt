package uib.teamdank.cargame.gui;

import java.util.List;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

import uib.teamdank.cargame.CarGame;
import uib.teamdank.common.Score;

public class HighscoreMenuScreen implements uib.teamdank.common.gui.HighscoreMenuScreen {
	private static final String BACK = "Images/Buttons/bs_back.png";
	
	private Stage stage;
	private Texture myTexture;
	private ImageButton backButton;
	private Table menu;
	private Object startMenuScreen;
	private Game game;

	public HighscoreMenuScreen(CarGame game) {
		this.game = game;
		stage = new Stage(new FitViewport(1920, 1080));
		startMenuScreen = new StartMenuScreen(game);
		
		backButton = setupButton(BACK);
		menu = new Table();
		menu.add(backButton).pad(0, 0, 20, 100);
		
		
		menu.setFillParent(true);
		stage.addActor(menu);
		Gdx.input.setInputProcessor(stage);
		
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
					goBack();
				}
			}
		});
	}
	
	public ImageButton setupButton(String imageString) {
		myTexture = new Texture(Gdx.files.internal(imageString));
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
	public void goBack() {
		game.setScreen(startMenuScreen);
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
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
	public void setScores(List<Score> scores) {
		// TODO Auto-generated method stub
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
	}

}
