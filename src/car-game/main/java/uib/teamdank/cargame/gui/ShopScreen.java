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
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;

import uib.teamdank.cargame.CarGame;
import uib.teamdank.common.util.AssetManager;
import uib.teamdank.common.util.TextureAtlas;

public class ShopScreen implements Screen {
	private static final String BACK = "Images/Buttons/bs_back.png";
	
	private Stage stage;
	private CarGame game;
	private TextureAtlas carTextures;
	private Array<Button> buttons;
	private AssetManager assets;
	
	private ImageButton backButton;
	private CarButton greenCar;
	private CarButton blueCar;
	private CarButton norwayCar;
	private CarButton pinkCar;
	private CarButton redCar;
	private CarButton greyCar;
	private CarButton flameCar;
	private CarButton yellowCar;
	private CarButton splatCar;

	private Table menu;
	private Table cars;
	private Table text;
	
	public ShopScreen(CarGame game) {
		this.game = game;
		stage = new Stage(new FitViewport(1920, 1080));
		
		buttons = new Array<Button>();
		menu = new Table();
		cars = new Table();
		text = new Table();
		
		this.assets = new AssetManager();
		carTextures = assets.getAtlas("Images/car_sheet.json");
		
		Texture backStart = new Texture(Gdx.files.internal(BACK));
		TextureRegion myTextureRegion = new TextureRegion(backStart);
		TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
		backButton = new ImageButton(myTexRegionDrawable);
		
		buttons.add(greenCar = setupButton("car_forward"));
		buttons.add(blueCar = setupButton("car_forward_blue"));
		buttons.add(norwayCar = setupButton("car_forward_flag"));
		buttons.add(pinkCar = setupButton("car_forward_pink"));
		buttons.add(redCar = setupButton("car_forward_red"));
		buttons.add(greyCar = setupButton("car_forward_grey"));
		buttons.add(flameCar = setupButton("car_forward_hot"));
		buttons.add(yellowCar = setupButton("car_forward_orange"));
		buttons.add(splatCar = setupButton("car_forward_splat"));
		
		setupScreen();
		backListener();
		carListener();
		
		greenCar.setUnlocked(true);
		
		menu.setFillParent(true);
		stage.addActor(menu);
		Gdx.input.setInputProcessor(stage);
	}

	public void setupScreen() {
		for (int i = 0; i < buttons.size; i++) {
			if (i % 3 == 0) 
	    		cars.row();
		    cars.add(buttons.get(i)).height((float) (buttons.get(i).getHeight() /1.5)).width((float) (buttons.get(i).getWidth() / 1.5)).pad(15);
		}
		menu.add(cars);
		menu.row();
		menu.add(backButton).width((float) (backButton.getWidth() / 4)).height((float) (backButton.getHeight() / 4)).pad(0, 0, 0, 0);
		menu.debug();
	}

	public CarButton setupButton(String string) {
		TextureRegion textureRegion = carTextures.getRegion(string);
		TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(textureRegion);
		return new CarButton(myTexRegionDrawable, true);
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
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void pause() { }

	@Override
	public void resume() { }

	@Override
	public void dispose() { }
	
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
	
	private void carListener() {
		greenCar.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Stage myStage = event.getTarget().getStage();
				Vector2 mouse = myStage.screenToStageCoordinates(new Vector2(Gdx.input.getX(), Gdx.input.getY()));

				if (myStage.hit(mouse.x, mouse.y, true) == event.getTarget()) {
					
					if (greenCar.isUnlocked()) {
						//player will select this car
						System.out.println("green");
					}
				}
			}
		});
		
		blueCar.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Stage myStage = event.getTarget().getStage();
				Vector2 mouse = myStage.screenToStageCoordinates(new Vector2(Gdx.input.getX(), Gdx.input.getY()));

				if (myStage.hit(mouse.x, mouse.y, true) == event.getTarget()) {
					if (blueCar.isUnlocked()) {
						//player will select this car
						System.out.println("blue");
					}
				}
			}
		});
		
		norwayCar.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Stage myStage = event.getTarget().getStage();
				Vector2 mouse = myStage.screenToStageCoordinates(new Vector2(Gdx.input.getX(), Gdx.input.getY()));

				if (myStage.hit(mouse.x, mouse.y, true) == event.getTarget()) {
					if (norwayCar.isUnlocked()) {
						//player will select this car
						System.out.println("norway");
					}
				}
			}
		});
		
		pinkCar.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Stage myStage = event.getTarget().getStage();
				Vector2 mouse = myStage.screenToStageCoordinates(new Vector2(Gdx.input.getX(), Gdx.input.getY()));

				if (myStage.hit(mouse.x, mouse.y, true) == event.getTarget()) {
					if (pinkCar.isUnlocked()) {
						//player will select this car
						System.out.println("pink");
					}
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
					if (redCar.isUnlocked()) {
						//player will select this car
						System.out.println("red");
					}
				}
			}
		});
		
		greyCar.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Stage myStage = event.getTarget().getStage();
				Vector2 mouse = myStage.screenToStageCoordinates(new Vector2(Gdx.input.getX(), Gdx.input.getY()));

				if (myStage.hit(mouse.x, mouse.y, true) == event.getTarget()) {
					if (greyCar.isUnlocked()) {
						//player will select this car
						System.out.println("grey");
					}
				}
			}
		});
		
		flameCar.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Stage myStage = event.getTarget().getStage();
				Vector2 mouse = myStage.screenToStageCoordinates(new Vector2(Gdx.input.getX(), Gdx.input.getY()));

				if (myStage.hit(mouse.x, mouse.y, true) == event.getTarget()) {
					if (flameCar.isUnlocked()) {
						//player will select this car
						System.out.println("flameCar");
					}
				}
			}
		});
		
		yellowCar.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Stage myStage = event.getTarget().getStage();
				Vector2 mouse = myStage.screenToStageCoordinates(new Vector2(Gdx.input.getX(), Gdx.input.getY()));

				if (myStage.hit(mouse.x, mouse.y, true) == event.getTarget()) {
					if (yellowCar.isUnlocked()) {
						//player will select this car
						System.out.println("yellow");
					}
				}
			}
		});
		
		splatCar.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Stage myStage = event.getTarget().getStage();
				Vector2 mouse = myStage.screenToStageCoordinates(new Vector2(Gdx.input.getX(), Gdx.input.getY()));

				if (myStage.hit(mouse.x, mouse.y, true) == event.getTarget()) {
					if (splatCar.isUnlocked()) {
						//player will select this car
						System.out.println("splat");
					}
				}
			}
		});
	}

	public void goBack() {
		game.setScreen(game.getStartMenuScreen());
	}
	
	private static class CarButton extends ImageButton {
		public boolean unlocked = false;
		
		public CarButton(Drawable imageUp, boolean unlocked) {
			super(imageUp);
			this.unlocked = unlocked;
		}
		
		public void setUnlocked(boolean lock) {
			this.unlocked = lock;
		}
		
		public boolean isUnlocked() {
			return unlocked;
		}
	}
}
