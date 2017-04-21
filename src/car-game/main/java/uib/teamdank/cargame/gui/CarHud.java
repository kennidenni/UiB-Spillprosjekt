package uib.teamdank.cargame.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class CarHud {
	private static final String FUELTANK = "images/game/gastank.png";

	private int fuel;
	private Stage stage;
	private Table menu;
	private ImageButton fuelImage;

	private ProgressBar bar;

	public CarHud() {
		stage = new Stage(new FitViewport(1920, 1080));

		fuelImage = setupButton(FUELTANK);

		ProgressBar.ProgressBarStyle style = new ProgressBar.ProgressBarStyle();
		style.background = new TextureRegionDrawable(new TextureRegion(new Texture("images/road.png")));
		//style.knob = new TextureRegionDrawable(new TextureRegion(new Texture("images/road.png")));
		//style.knobBefore = style.knob;

		bar = new ProgressBar(0, 100, 1, false, style);
		bar.setRange(1, 100);
		bar.setValue(fuel);

		menu = new Table();
		menu.add(fuelImage).width((float) (fuelImage.getWidth() / 2)).height((float) (fuelImage.getHeight() / 2)).pad(900, 1650, 0, 0);
		menu.add(bar).width(bar.getWidth() / 2).pad(900, 10, 100, 50);

		menu.setFillParent(true);
		stage.addActor(menu);
		Gdx.input.setInputProcessor(stage);
	}

	public ImageButton setupButton(String imageString) {
		Texture myTexture = new Texture(Gdx.files.internal(imageString));
		TextureRegion myTextureRegion = new TextureRegion(myTexture);
		TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
		return new ImageButton(myTexRegionDrawable);
	}

	public void render(float delta) {
		bar.act(delta);
		stage.act(delta);
		stage.draw();
	}

	public void setCurrentFuel(int fuel) {
		this.fuel = fuel;
		bar.setValue(fuel);
		//for debuggging
		System.out.println(fuel);
	}

	public int getCurrentFuel() {
		return fuel;
	}
	
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}
}