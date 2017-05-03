package uib.teamdank.cargame.gui;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

import uib.teamdank.cargame.CarGame;
import uib.teamdank.common.Score;
import uib.teamdank.common.util.AssetManager;

public class EndingScreen implements uib.teamdank.common.gui.HighscoreMenuScreen {
	private static final String BACK = "Images/Buttons/bs_back.png";
	private static final String GAMEOVER = "Images/gameOver.png";
	private static final String SCORES = "TeamDank/Carl the Crasher/highscore.json";
	
	private static final String CRASH_SOUND = "Sounds/car_crash.mp3";
	
	private final AssetManager assets;
	
	private Stage stage;
	private ImageButton backButton;
	private Table menu;
	private CarGame game;
	private ImageButton gameOverButton;
	private TextButton highscore;
	private BitmapFont font;
	private TextButtonStyle textButtonStyle;
	private List<Score> score;

	public EndingScreen(CarGame game) {
		this.game = game;
		
		this.assets = new AssetManager();
		assets.getAudio().preloadSounds(CRASH_SOUND);
		
		stage = new Stage(new FitViewport(1920, 1080));
		
		backButton = setupButton(BACK);
		gameOverButton = setupButton(GAMEOVER);
		
		font = new BitmapFont();
		textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = font;
		highscore = new TextButton("Your score:\n" + String.valueOf(game.getPlayer().getScore().getScore()), textButtonStyle);
		highscore.getLabel().setFontScale(10, 10);
		
		menu = new Table();
		menu.add(gameOverButton).width(gameOverButton.getWidth() / 2).height((float) (gameOverButton.getHeight() / 2)).pad(0, 0, 100, 0);
		menu.row();
		menu.add(highscore);
		menu.row();
		menu.add(backButton).width((float) (backButton.getWidth() / 4)).height((float) (backButton.getHeight() / 4)).pad(100, 0, 0, 0);

		menu.setFillParent(true);
		stage.addActor(menu);
		Gdx.input.setInputProcessor(stage);
		
		FileHandle handle = Gdx.files.external(SCORES);
		if(!handle.exists())
			handle = Gdx.files.internal("Data/highscore.json");
		score = new LinkedList<>(Arrays.asList(Score.createFromJson(handle)));
		
		if(score.get(score.size()-1).getScore() < this.game.getPlayer().getScore().getScore()){
			NameInputListener listener = new NameInputListener();
			Gdx.input.getTextInput(listener, "Top 10 score, congratulations!", "", "Your name");
		}
		
		backButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Stage myStage = event.getTarget().getStage();
				Vector2 mouse = myStage.screenToStageCoordinates(new Vector2(Gdx.input.getX(), Gdx.input.getY()));

				if (myStage.hit(mouse.x, mouse.y, true) == event.getTarget()) {
					game.setScreen(game.getStartMenuScreen());
				}
			}
		});
	}
	
	public class NameInputListener implements TextInputListener {
		   @Override
		   public void input(String name) {
				Score playerScore = game.getPlayer().getScore();
				if(name != "")
					playerScore.setName(name);
				score.add(playerScore);
				
				Score.writeToJson(Gdx.files.external(SCORES), score.toArray(new Score[0]));
		   }

		   @Override
		   public void canceled() {
			   // Do nothing
		   }
		}
	
	public ImageButton setupButton(String imageString) {
		Texture myTexture = new Texture(Gdx.files.internal(imageString));
		TextureRegion myTextureRegion = new TextureRegion(myTexture);
		TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
		return new ImageButton(myTexRegionDrawable);
	}

	@Override
	public void dispose() {
		assets.dispose();
	}

	@Override
	public void goBack() {
		game.setScreen(game.getStartMenuScreen());
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void pause() {
		// Don't pause crash sound
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
		// Nothing to do
	}

	@Override
	public void setScores(List<Score> scores) {
		// TODO Auto-generated method stub
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		assets.getAudio().playSound(CRASH_SOUND);
	}
	
}
