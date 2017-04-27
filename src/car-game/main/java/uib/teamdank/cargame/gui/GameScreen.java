package uib.teamdank.cargame.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

import uib.teamdank.cargame.CarGame;
import uib.teamdank.cargame.Player;
import uib.teamdank.cargame.util.RoadEntityGenerator;
import uib.teamdank.cargame.util.ScrollingSpawner;
import uib.teamdank.common.Game;
import uib.teamdank.common.gui.Layer;
import uib.teamdank.common.util.AssetManager;
import uib.teamdank.common.util.TextureAtlas;

/**
 * The main gameplay screen.
 */
public class GameScreen extends uib.teamdank.common.gui.GameScreen {
	private static final int AMOUNT_PER_SCORE = 1;
	private static final float TIME_BETWEEN_SCORE = 1f;

	private static final int AMOUNT_PER_FUEL_LOSS = 1;
	private static final float TIME_BETWEEN_FUEL_LOSS = .1f;

	private static final int CAR_VERTICAL_POSITION = 25;

	private final AssetManager assets;

	private final OrthographicCamera playerCamera;
	private final OrthographicCamera screenCamera;

	private final BackgroundLayer backgroundLayer;
	private final Layer roadEntityLayer;
	private final Layer carLayer;
	private final CarHud hud;

	private final Sound carSound;
	private float carVolume = 0.5f;
	
	private final Player player;
	private float timeSinceScore = 0;
	private float timeSinceFuelLoss = 0;
	
	private final ScrollingSpawner roadEntitySpawner;

	public GameScreen(Game game) {
		super(game);

		this.assets = new AssetManager();
		TextureAtlas carTextures = assets.getAtlas("Images/car_sheet.json");
		TextureAtlas roadEntityTextures = assets.getAtlas("Images/road_entity_sheet.json");

		// Cameras
		this.playerCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.screenCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		// Player initialization
		player = new Player();
		player.setTexture(carTextures.getRegion("car_forward_flag"));
		player.setScale(.5f);

		// Layers
		backgroundLayer = new BackgroundLayer(assets, playerCamera, screenCamera, player);
		roadEntityLayer = new Layer(true);
		carLayer = new Layer(true);
		addLayer(backgroundLayer);
		addLayer(roadEntityLayer);
		addLayer(carLayer);
		carLayer.addGameObject(player);

		// Road entity spawner initialization
		this.roadEntitySpawner = new ScrollingSpawner(roadEntityLayer,
														playerCamera,
														new RoadEntityGenerator(roadEntityTextures));
		roadEntitySpawner.setHorizontalPositionRange(backgroundLayer.getRoadLeftX(),
														backgroundLayer.getRoadRightX());
		roadEntitySpawner.setChanceOfSpawn(1f);
		roadEntitySpawner.setExtraVerticalSpaceBetweenSpawns(50);
		
		// HUD
		this.hud = new CarHud();

		// Sounds
		carSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/car_sound.wav"));
		carSound.play(carVolume);
		carSound.loop();

	}

	private boolean checkForPauseRequest() {
		final boolean pause = Gdx.input.isKeyJustPressed(Keys.ESCAPE);
		if (pause) {
			getGame().setScreen(getGame().getPauseMenuScreen());
		}
		return pause;
	}

	@Override
	public void dispose() {
		super.dispose();
		assets.dispose();
		carSound.dispose();
	}

	@Override
	public void hide() {
		carSound.pause();
	}

	@Override
	public void render(float delta) {

		// Update player camera
		Vector2 playerPos = player.getPosisiton();
		float cameraX = playerPos.x + player.getWidth() / 2;
		float cameraY = playerPos.y + Gdx.graphics.getHeight() / 2 - CAR_VERTICAL_POSITION;
		playerCamera.position.set(cameraX, cameraY, 0);
		playerCamera.update();
		getGame().getSpriteBatch().setProjectionMatrix(playerCamera.combined);

		// Render layers
		super.render(delta);

		// Render HUD
		hud.render(delta);

	}

	@Override
	public void resize(int width, int height) {
		playerCamera.viewportWidth = screenCamera.viewportWidth = width;
		playerCamera.viewportHeight = screenCamera.viewportHeight = height;
		playerCamera.update();
		screenCamera.update();
	}

	@Override
	public void show() {
		carSound.resume();
	}

	@Override
	public void update(float delta) {

		// Update HUD
		updateScore(delta);
		updateHUD();

		// Updates game objects
		super.update(delta);

		// Update player
		player.accelerate();
		player.applyFriction();
		player.restrictHorizontally(backgroundLayer.getRoadLeftX(), backgroundLayer.getRoadRightX());
		updateFuel(delta);

		// Player input
		checkForPauseRequest();
		boolean inputTurnLeft = Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT);
		boolean inputTurnRight = Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT);
		if (inputTurnLeft) {
			player.turnLeft();
		}
		if (inputTurnRight) {
			player.turnRight();
		}

		// Spawn new road entities
		roadEntitySpawner.update(delta);
		roadEntitySpawner.setHorizontalPositionRange(backgroundLayer.getRoadLeftX(), backgroundLayer.getRoadRightX());
		
		// Check for game over
		if (player.isOutOfFuel() && player.getVelocity().y == 0) {
			getGame().setScreen(new EndingScreen((CarGame) getGame()));
		}

	}
	
	private void updateFuel(float delta) {
		timeSinceFuelLoss += delta;
		if (timeSinceFuelLoss >= TIME_BETWEEN_FUEL_LOSS) {
			player.decreaseHealth(AMOUNT_PER_FUEL_LOSS);
			timeSinceFuelLoss -= TIME_BETWEEN_FUEL_LOSS;
		}
	}
	
	private void updateHUD() {
		hud.setCurrentFuel(player.getHealth(), player.getMaxHealth());
		hud.setScore(player.getScore().getScore());
	}
	
	private void updateScore(float delta) {
		timeSinceScore += delta;
		if (timeSinceScore >= TIME_BETWEEN_SCORE) {
			player.getScore().addToScore(AMOUNT_PER_SCORE);
			timeSinceScore -= TIME_BETWEEN_SCORE;
		}
	}
}