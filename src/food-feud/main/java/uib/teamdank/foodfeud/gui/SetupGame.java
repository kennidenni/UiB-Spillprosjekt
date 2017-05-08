package uib.teamdank.foodfeud.gui;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import uib.teamdank.common.Game;
import uib.teamdank.common.gui.MenuScreen;
import uib.teamdank.common.util.AssetManager;
import uib.teamdank.common.util.TextureAtlas;
import uib.teamdank.foodfeud.FoodFeud;

public class SetupGame extends MenuScreen implements Screen {

	private static class CarButton extends ImageButton {
		private boolean unlocked;

		public CarButton(String name, Drawable unlockedImage, Drawable lockedImage) {
			super(lockedImage, lockedImage, unlockedImage);
			setDisabled(true);
			setName(name);
			setUnlocked(false);
		}

		public void setUnlocked(boolean unlocked) {
			this.unlocked = unlocked;
		}

		public void updateTexture() {
			if (unlocked && isChecked()) {
				setDisabled(true);
			} else if (unlocked) {
				setDisabled(false);
			}
		}
	}

	private class ButtonListener extends InputListener {
		private final Button source;

		public ButtonListener(Button source) {
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
				if (source.isDisabled()) {
					// something
				} else {
					// noe
				}
			}
		}
	}

	private final List<CarButton> carButtons = new ArrayList<>();
	private static final int CAR_COST = 10;

	private Game game;

	private Table menu;
	private Table cars;
	private Table coinsTable;

	private AssetManager assets;
	private TextureAtlas roadEntityTextures;
	private TextureAtlas buttonTexture;

	private BitmapFont font;
	private ImageButton coinImage;
	private ImageButton backButton;
	private TextButtonStyle textButtonStyle;
	private TextButton coinsCount;
	private TextButton helpText;

	public SetupGame(Game game) {
		super();
		this.game = game;

		menu = new Table();
		cars = new Table();
		coinsTable = new Table();

		this.assets = new AssetManager();

		font = new BitmapFont();
		textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = font;

		helpText = new TextButton("hei", textButtonStyle);
		helpText.getLabel().setFontScale(3, 3);
		helpText.pad(0, 0, 0, 0);
		menu.add(helpText);
		menu.row();
		
		for(int i = 1; i < 5; i++) {
			TextButton playerNumber = new TextButton(String.valueOf(i), textButtonStyle);
			playerNumber.getLabel().setFontScale(3, 3);
			playerNumber.addListener(new ButtonListener(playerNumber));
			menu.add(playerNumber);
		}

		menu.setFillParent(true);
		coinsTable.setFillParent(true);
		getStage().addActor(menu);
		getStage().addActor(coinsTable);
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
	public void render(float delta) {
//		coinsCount.act(delta);
		super.render(delta);
	}

	@Override
	public void show() {
		super.show();
	}

}
