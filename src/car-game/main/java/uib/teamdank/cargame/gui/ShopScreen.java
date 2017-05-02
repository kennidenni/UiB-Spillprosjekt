package uib.teamdank.cargame.gui;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
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
import uib.teamdank.cargame.Player;
import uib.teamdank.common.util.AssetManager;
import uib.teamdank.common.util.TextureAtlas;

public class ShopScreen extends ScreenAdapter {

	private static class CarButton extends ImageButton {
		private TextureRegion texture;
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
				System.out.println(source.unlocked);
				if (source.unlocked) {
					game.getPlayer().setTexture(source.texture);
					
				} else {
					// TODO Si at den ikke er kjøpt
				}
			}
		}
	}

	private Stage stage;
	private CarGame game;

	private AssetManager assets;

	private ImageButton backButton;

	private final List<CarButton> carButtons = new ArrayList<>();

	private Table menu;
	private Table cars;
	private TextureAtlas roadEntityTextures;
	private TextureAtlas buttonTexture;
	private ImageButton coinImage;
	private Table coinsTable;
	private BitmapFont font;
	private TextButtonStyle textButtonStyle;
	private TextButton coinsCount;
	private TextureAtlas lockedCarTextures;

	public ShopScreen(CarGame game) {
		this.game = game;
		stage = new Stage(new FitViewport(1920, 1080));

		menu = new Table();
		cars = new Table();
		coinsTable = new Table();

		this.assets = new AssetManager();

		buttonTexture = assets.getAtlas("Images/Buttons/cg_buttons.json");
		backButton = setupImage(buttonTexture.getRegion("cg_back"));

		roadEntityTextures = assets.getAtlas("Images/road_entity_sheet.json");
		lockedCarTextures = assets.getAtlas("Images/locked_car_sheet.json");

		coinImage = setupImage(roadEntityTextures.getRegion("coin"));

		font = new BitmapFont();
		textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = font;

		coinsCount = new TextButton("0", textButtonStyle);
		coinsCount.getLabel().setFontScale(10, 10);

		coinsTable = new Table();
		coinsTable.add(coinImage).pad(0, 1600, 900, 0);
		coinsTable.add(coinsCount).pad(0, 20, 900, 0);

		setupCars();
		setupScreen();
		backListener();

		menu.setFillParent(true);
		coinsTable.setFillParent(true);
		stage.addActor(menu);
		stage.addActor(coinsTable);
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

	private ImageButton setupImage(TextureRegion textureRegion) {
		TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(textureRegion);
		return new ImageButton(myTexRegionDrawable);
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

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);

		final Player player = game.getPlayer();
		coinsCount.setText(String.valueOf(player.getInventory().getGold()));
		for (CarButton button : carButtons) {
			button.unlocked = player.hasUnlockedSkin(button.getName());
			if (!button.unlocked) {
				button.getImage().setDrawable(new TextureRegionDrawable(lockedCarTextures.getRegion(button.getName())));
			}
		}
		
	}

	private void setupCars() {
		assets.getAtlas("Images/car_sheet.json").forEachRegion((name, texture) -> {
			CarButton carButton = new CarButton(name, texture);
			carButton.addListener(new CarListener(carButton));
			carButtons.add(carButton);
		});
	}

	private void setupScreen() {
		for (int i = 0; i < carButtons.size(); i++) {
			if (i % 3 == 0)
				cars.row();
			cars.add(carButtons.get(i)).height((float) (carButtons.get(i).getHeight() / 1.5))
					.width((float) (carButtons.get(i).getWidth() / 1.5)).pad(15);
		}
		menu.add(cars);
		menu.row();
		menu.add(backButton).width((float) (backButton.getWidth() / 4)).height((float) (backButton.getHeight() / 4))
				.pad(0, 0, 0, 0);
		menu.debug();
	}

	public void setCoins(int i) {
		coinsCount.setText(String.valueOf(i));
	}

}
