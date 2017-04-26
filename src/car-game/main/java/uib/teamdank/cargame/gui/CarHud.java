package uib.teamdank.cargame.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class CarHud {
	private static final String FUELTANK = "Images/Game/gastank.png";

	private int fuel;
	private Stage stage;
	private Table menu;
	private ImageButton fuelImage;
	private long score;

	private ProgressBar bar;

	private BitmapFont font;

	private TextButtonStyle textButtonStyle;

	private TextButton highscore;

	private Table first;

	private Table second;

	public CarHud() {
		stage = new Stage(new FitViewport(1920, 1080));

		fuelImage = setupImage(FUELTANK);
		
		Skin skin = new Skin();
		Pixmap pixmap = new Pixmap(40, 110, Format.RGBA8888);
		pixmap.setColor(Color.RED);
		pixmap.fill();
		skin.add("red", new Texture(pixmap));
		
		pixmap = new Pixmap(40, 110, Format.RGBA8888);
		pixmap.setColor(Color.GREEN);
		pixmap.fill();
		skin.add("green", new Texture(pixmap));

		ProgressBar.ProgressBarStyle style = new ProgressBar.ProgressBarStyle();
		style.background = skin.getDrawable("red");
		style.knobBefore = skin.getDrawable("green");

		bar = new ProgressBar(0, 100, 1, false, style);
		
		font = new BitmapFont();
		textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = font;
		
		highscore = new TextButton(String.valueOf(score), textButtonStyle);
		highscore.getLabel().setFontScale(10, 10);
		
		menu = new Table();
		first = new Table();
		first.add(highscore).width(40).pad(900, 20, 0, 0);
		
		second = new Table();
		second.add(fuelImage).width((float) (fuelImage.getWidth() / 2)).height((float) (fuelImage.getHeight() / 2)).pad(900, 1100, 0, 0);
		second.add(bar).width(bar.getWidth()*3).pad(900, 10, 0, 0);
		
		menu.add(first);
		menu.add(second);
		first.debug();
		
		
		
		menu.setFillParent(true);
		stage.addActor(menu);
		Gdx.input.setInputProcessor(stage);
	}

	private ImageButton setupImage(String imageString) {
		Texture myTexture = new Texture(Gdx.files.internal(imageString));
		TextureRegion myTextureRegion = new TextureRegion(myTexture);
		TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
		return new ImageButton(myTexRegionDrawable);
	}

	public void render(float delta) {
		bar.act(delta);
		highscore.act(delta);
		stage.act(delta);
		stage.draw();
	}

	public void setCurrentFuel(int fuel, int max) {
		this.fuel = fuel;
		bar.setValue(fuel);
		bar.setRange(0, max);
	}

	public int getCurrentFuel() {
		return fuel;
	}
	
	public void setScore (long l) {
		this.score = l;
		highscore.setText(String.valueOf(l));
	}
	
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}
	
}