package uib.teamdank.cargame.gui;

import java.util.Arrays;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
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
	private FileHandle handle;
	private Table menu;
	private CarGame game;	
	private Label nameLabel;
	private Label scoreLabel;

	public HighscoreMenuScreen(CarGame game) {
		this.game = game;
		stage = new Stage(new FitViewport(1920, 1080));
		
		handle = Gdx.files.external(SCORES);
		if (!handle.exists())
			handle = Gdx.files.internal("Data/highscore.json");
				
		menu = new Table();
		menu.setFillParent(true); 
		
		highscoreButton = setupButton(HIGHSCORE);
		menu.add(highscoreButton)
			.width(highscoreButton.getWidth() / 4)
			.height(highscoreButton.getHeight() / 4)
			.expand().align(Align.top).padTop(Gdx.graphics.getHeight() / 16);
		
		menu.row();

		BitmapFont font = new BitmapFont();
		font.getData().setScale(3);
		
		nameLabel = new Label("", new LabelStyle(font, Color.WHITE));
		scoreLabel = new Label("", new LabelStyle(font, Color.WHITE));
		
		HorizontalGroup hg = new HorizontalGroup();
		hg.space(Gdx.graphics.getWidth() / 4);
		hg.addActor(nameLabel);
		hg.addActor(scoreLabel);
		
		menu.add(hg).expand().align(Align.center);
		menu.row();
		
		backButton = setupButton(BACK);
		menu.add(backButton)
			.width(backButton.getWidth() / 4)
			.height(backButton.getHeight() / 4)
			.expand().align(Align.bottom).padBottom(Gdx.graphics.getHeight() / 16);

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
		
		stage.addActor(menu);
		Gdx.input.setInputProcessor(stage);
	}

	private ImageButton setupButton(String imgPath) {
		return new ImageButton(
				new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(imgPath)))));
	}

	@Override
	public void dispose() {
		//TODO
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
		//TODO
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
		nameLabel.act(delta); //TODO: Needed?
		scoreLabel.act(delta); //TODO: Needed?
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void resume() {
		//TODO
	}

	@Override
	public void setScores(List<Score> scores) {
		StringBuilder nameBuilder = new StringBuilder();
		StringBuilder scoreBuilder = new StringBuilder();

		for (int i = 0; i < 10 && i < scores.size(); i++) {
			Score score = scores.get(i);

			if (score.getName().length() > 40)
				nameBuilder.append(score.getName().substring(0, 37) + "...");
			else
				nameBuilder.append(score.getName());
			scoreBuilder.append(score.getScore());

			nameBuilder.append("\n");
			scoreBuilder.append("\n");
		}
		nameLabel.setText(nameBuilder.toString());
		scoreLabel.setText(scoreBuilder.toString());
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		setScores(Arrays.asList(Score.createFromJson(handle)));
	}

}
