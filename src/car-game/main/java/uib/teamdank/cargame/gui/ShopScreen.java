package uib.teamdank.cargame.gui;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
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
import uib.teamdank.cargame.Player;
import uib.teamdank.common.util.AssetManager;

public class ShopScreen extends ScreenAdapter {

	private static class CarButton extends ImageButton {
		private final TextureRegion texture;
		private boolean unlocked;

		public CarButton(String name, TextureRegion texture) {
			super(new TextureRegionDrawable(texture));
			this.texture = texture;
			setName(name);
			setUnlocked(false);
		}

		public void setUnlocked(boolean unlocked) {
			this.unlocked = unlocked;
		}
	}

	private class CarListener extends InputListener {
		private final CarButton source;

		public CarListener(CarButton source) {
			this.source = source;
		}

		@Override
		public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
			return true;
		}

		@Override
		public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
			Stage myStage = event.getTarget().getStage();
			Vector2 mouse = myStage.screenToStageCoordinates(new Vector2(Gdx.input.getX(), Gdx.input.getY()));

			if (myStage.hit(mouse.x, mouse.y, true) == event.getTarget()) {
				if (source.unlocked) {
					game.getPlayer().setTexture(source.texture);
					// TODO Marker som knapp som valgt
				} else {
					System.out.println("Player pressed (locked): " + source.getName()); // TODO Replace
				}
			}
		}
	}

	private static final String BACK = "Images/Buttons/bs_back.png";
	private Stage stage;
	private CarGame game;
	
	private AssetManager assets;

	private ImageButton backButton;

	private final List<CarButton> carButtons = new ArrayList<>();
	
	private Table menu;
	private Table cars;
	
	public ShopScreen(CarGame game) {
		this.game = game;
		stage = new Stage(new FitViewport(1920, 1080));

		menu = new Table();
		cars = new Table();

		this.assets = new AssetManager();

		Texture backStart = new Texture(Gdx.files.internal(BACK));
		TextureRegion myTextureRegion = new TextureRegion(backStart);
		TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
		backButton = new ImageButton(myTexRegionDrawable);

		assets.getAtlas("Images/car_sheet.json").forEachRegion((name, texture) -> {
			CarButton carButton = new CarButton(name, texture);
			carButton.addListener(new CarListener(carButton));
			carButtons.add(carButton);
		});
		
		setupScreen();
		backListener();

		menu.setFillParent(true);
		stage.addActor(menu);
		Gdx.input.setInputProcessor(stage);
	}
	
	private void backListener() {
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

	@Override
	public void dispose() {
		assets.dispose();
	}

	public void goBack() {
		game.setScreen(game.getStartMenuScreen());
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
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

	private void setupScreen() {
		for (int i = 0; i < carButtons.size(); i++) {
			if (i % 3 == 0) cars.row();
			cars.add(carButtons.get(i)).height((float) (carButtons.get(i).getHeight() / 1.5)).width((float) (carButtons.get(i).getWidth() / 1.5)).pad(15);
		}
		menu.add(cars);
		menu.row();
		menu.add(backButton).width((float) (backButton.getWidth() / 4)).height((float) (backButton.getHeight() / 4)).pad(0, 0, 0, 0);
		menu.debug();
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		
		final Player player = game.getPlayer();
		for (CarButton button : carButtons) {
			button.unlocked = player.hasUnlockedSkin(button.getName());
		}
	}

}
