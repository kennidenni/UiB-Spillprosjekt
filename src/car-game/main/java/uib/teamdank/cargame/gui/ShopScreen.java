package uib.teamdank.cargame.gui;

import com.badlogic.gdx.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;

import uib.teamdank.cargame.CarGame;
import uib.teamdank.common.util.AssetManager;
import uib.teamdank.common.util.TextureAtlas;

public class ShopScreen implements Screen {
	private static final String BACK = "Images/Buttons/bs_back.png";
	
	private Stage stage;
	private Table menu;
	private CarGame game;
	private TextureAtlas carTextures;
	private Array<Button> buttons;
	private AssetManager assets;
	
	private ImageButton backButton;
	private ImageButton greenCar;
	private ImageButton blueCar;
	private ImageButton norwayCar;
	private ImageButton pinkCar;
	private ImageButton redCar;
	private ImageButton greyCar;
	private ImageButton flameCar;
	private ImageButton yellowCar;
	private ImageButton splatCar;

	private Table cars;

	private Table text;

	public ShopScreen(CarGame game) {
		this.game = game;
		stage = new Stage(new FitViewport(1920, 1080));
		
		this.assets = new AssetManager();
		Texture backStart = new Texture(Gdx.files.internal(BACK));
		TextureRegion myTextureRegion = new TextureRegion(backStart);
		TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
		backButton = new ImageButton(myTexRegionDrawable);
		
		carTextures = assets.getAtlas("Images/car_sheet.json");
		buttons = new Array<Button>();
		
		buttons.add(greenCar = setupButton("car_forward"));
		buttons.add(blueCar = setupButton("car_forward_blue"));
		buttons.add(norwayCar = setupButton("car_forward_flag"));
		buttons.add(pinkCar = setupButton("car_forward_pink"));
		buttons.add(redCar = setupButton("car_forward_red"));
		buttons.add(greyCar = setupButton("car_forward_grey"));
		buttons.add(flameCar = setupButton("car_forward_hot"));
		buttons.add(yellowCar = setupButton("car_forward_orange"));
		buttons.add(splatCar = setupButton("car_forward_splat"));
		
		menu = new Table();
		cars = new Table();
		text = new Table();
		
		for (int i = 0; i < buttons.size; i++) {
			if (i % 3 == 0) 
	    		cars.row();
		    cars.add(buttons.get(i)).height((float) (buttons.get(i).getHeight() /1.5)).width((float) (buttons.get(i).getWidth() / 1.5)).pad(15);
		}
		menu.add(cars);
		menu.row();
		menu.add(backButton).width((float) (backButton.getWidth() / 4)).height((float) (backButton.getHeight() / 4)).pad(0, 0, 0, 0);
		menu.debug();
		
		
		
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
		redCar.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Stage myStage = event.getTarget().getStage();
				Vector2 mouse = myStage.screenToStageCoordinates(new Vector2(Gdx.input.getX(), Gdx.input.getY()));

				if (myStage.hit(mouse.x, mouse.y, true) == event.getTarget()) {
					System.out.println("heri");
				}
			}
		});
	}

	public ImageButton setupButton(String string) {
		TextureRegion textureRegion = carTextures.getRegion(string);
		TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(textureRegion);
		return new ImageButton(myTexRegionDrawable);
	}
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		
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
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	
	public void goBack() {
		game.setScreen(game.getStartMenuScreen());
	}
}
