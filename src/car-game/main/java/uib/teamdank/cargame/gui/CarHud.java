package uib.teamdank.cargame.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
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
		style.background = new TextureRegionDrawable(new TextureRegion(new Texture("images/background.png")));
		//style.knob = new TextureRegionDrawable(new TextureRegion(new Texture("images/road.png")));
		//style.knobBefore = style.knob;

		bar = new ProgressBar(0, 100, 1, false, style);
		bar.setAnimateDuration(1);
		bar.setValue(fuel);

		menu = new Table();
		menu.add(fuelImage).width((float) (fuelImage.getWidth() / 4)).height((float) (fuelImage.getHeight() / 4)).pad(900, 600, 0, 0);
		menu.add(bar).width((float) (bar.getWidth() / 4)).height((float) (bar.getHeight() / 4)).pad(900, 600, 0, 0);

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
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		bar.act(delta);
		stage.draw();
	}

	public void setCurrentFuel(int fuel) {
		this.fuel = fuel;
	}

	public int getCurrentFuel() {
		return fuel;
	}
}
