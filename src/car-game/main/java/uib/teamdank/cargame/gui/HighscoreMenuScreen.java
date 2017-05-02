package uib.teamdank.cargame.gui;

import java.util.Arrays;
import java.util.List;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
	private static final String HIGHSCORE = "Images/Buttons/cg_highscore.png";
	private static final String SCORES = "TeamDank/Carl the Crasher/highscore.json";
	
	private Stage stage;
	private ImageButton backButton;
	private ImageButton highscoreButton;
	private Table menu;
	private CarGame game;
	private BitmapFont font;
	private SpriteBatch batch;
	private String names;
	private String scorePoints;

	public HighscoreMenuScreen(CarGame game) {
		this.game = game;
		stage = new Stage(new FitViewport(1920, 1080));
		backButton = setupButton(BACK);
		highscoreButton = setupButton(HIGHSCORE);
		menu = new Table();
		menu.add(highscoreButton).width((float) (highscoreButton.getWidth() / 4)).height((float) (highscoreButton.getHeight() / 4)).pad(0, 0, 0, 0);
		menu.row();
		menu.add(backButton).width((float) (backButton.getWidth() / 4)).height((float) (backButton.getHeight() / 4)).pad(700, 0, 0, 0);
		
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.getData().setScale(2);
		getAndSetScoresFromFile();
		
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
				Stage myStage = event.getTarget().getStage();
				Vector2 mouse = myStage.screenToStageCoordinates(new Vector2(Gdx.input.getX(), Gdx.input.getY()));

				if (myStage.hit(mouse.x, mouse.y, true) == event.getTarget()) {
					game.setScreen(game.getStartMenuScreen());
				}
			}
		});
	}

	private void getAndSetScoresFromFile() {
		FileHandle handle = Gdx.files.external(SCORES);
		if(!handle.exists())
			handle = Gdx.files.internal("Data/highscore.json");
		setScores(Arrays.asList(Score.createFromJson(handle)));
	}
	
	public ImageButton setupButton(String imageString) {
		Texture myTexture = new Texture(Gdx.files.internal(imageString));
		TextureRegion myTextureRegion = new TextureRegion(myTexture);
		TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
		return new ImageButton(myTexRegionDrawable);
	}


	@Override
	public void dispose() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
		
		batch.begin();
		float width = (float) Gdx.graphics.getWidth()/4;
		float height = (float) Gdx.graphics.getHeight()*3/4;
		font.draw(batch, names, width, height);
		font.draw(batch, scorePoints, width*3, height);
		batch.end();
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
		StringBuilder nameBuilder = new StringBuilder();
		StringBuilder scoreBuilder = new StringBuilder();
		
		for(int i = 0; i < 10 && i < scores.size(); i++){
			Score score = scores.get(i);
			
			if(score.getName().length() > 40)
				nameBuilder.append(score.getName().substring(0, 37) + "...");
			else
				nameBuilder.append(score.getName());
			scoreBuilder.append(score.getScore());
			
			nameBuilder.append("\n");
			scoreBuilder.append("\n");
		}
		
		names = nameBuilder.toString();
		this.scorePoints = scoreBuilder.toString();
	}

	@Override
	public void show() {
		getAndSetScoresFromFile();
		Gdx.input.setInputProcessor(stage);
	}

}
