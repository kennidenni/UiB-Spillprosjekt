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

import uib.teamdank.common.util.AssetManager;
import uib.teamdank.common.util.TextureAtlas;

public class CarHud {
	
	private Stage stage;
	private AssetManager assets;
	private ImageButton fuelImage;
	private ImageButton coinImage;
	private ProgressBar bar;
	
	private BitmapFont font;
	private TextButtonStyle textButtonStyle;
	private TextButton highscore;
	private TextButton coinsCount;
	
	private Table menu;
	private Table first;
	private Table second;
	private Table coinsTable;
	

	public CarHud() {
		stage = new Stage(new FitViewport(1920, 1080));
		
		this.assets = new AssetManager();
		TextureAtlas roadEntityTextures = assets.getAtlas("Images/road_entity_sheet.json");

		fuelImage = setupImage(roadEntityTextures.getRegion("gastank"));
		coinImage = setupImage(roadEntityTextures.getRegion("coin"));
		
		Skin skin = new Skin();
		Pixmap pixmap = new Pixmap(40, 110, Format.RGBA8888);
		pixmap.setColor(Color.RED);
		pixmap.fill();
		skin.add("red", new Texture(pixmap));
		
		pixmap = new Pixmap(1, 110, Format.RGBA8888);
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
		
		highscore = new TextButton("0", textButtonStyle);
		highscore.getLabel().setFontScale(10, 10);
		
		coinsCount = new TextButton("0", textButtonStyle);
		coinsCount.getLabel().setFontScale(10, 10);
		
		menu = new Table();
		first = new Table();
		first.add(highscore).width(300).pad(900, 20, 0, 0);
		
		second = new Table();
		second.add(fuelImage).width((float) (fuelImage.getWidth() / 2)).height((float) (fuelImage.getHeight() / 2)).pad(900, 1000, 0, 0);
		second.add(bar).width(bar.getWidth()*3).pad(900, 10, 0, 0);
		
		coinsTable = new Table();
		coinsTable.add(coinImage).pad(0, 900, 900, 0);
		coinsTable.add(coinsCount);
		
		menu.add(coinsTable);
		menu.add(first);
		menu.add(second);
		
		
		menu.setFillParent(true);
		stage.addActor(menu);
		Gdx.input.setInputProcessor(stage);
	}

	private ImageButton setupImage(TextureRegion textureRegion) {
		TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(textureRegion);
		return new ImageButton(myTexRegionDrawable);
	}

	public void render(float delta) {
		bar.act(delta);
		highscore.act(delta);
		coinsCount.act(delta);
		stage.act(delta);
		stage.draw();
	}

	public void setCurrentFuel(int fuel, int max) {
		bar.setValue(fuel);
		bar.setRange(0, max);
	}
	
	public void setScore (long l) {
		highscore.setText(String.valueOf(l));
	}
	
	public void setCoins(int i) {
		coinsCount.setText(String.valueOf(i));
	}
	
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}
	
}