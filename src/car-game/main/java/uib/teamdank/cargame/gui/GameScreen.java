package uib.teamdank.cargame.gui;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

import uib.teamdank.cargame.CarGame;
import uib.teamdank.cargame.Coin;
import uib.teamdank.cargame.Fuel;
import uib.teamdank.cargame.Pedestrian;
import uib.teamdank.cargame.Player;
import uib.teamdank.cargame.RoadEntity;
import uib.teamdank.cargame.util.GameSounds;
import uib.teamdank.cargame.util.PedestrianGenerator;
import uib.teamdank.cargame.util.RoadEntityGenerator;
import uib.teamdank.cargame.util.ScrollingSpawner;
import uib.teamdank.common.Game;
import uib.teamdank.common.GameObject;
import uib.teamdank.common.Score;
import uib.teamdank.common.gui.Layer;
import uib.teamdank.common.util.AssetManager;
import uib.teamdank.common.util.TextureAtlas;
import uib.teamdank.common.util.TimedEvent;

/**
 * The main gameplay screen.
 */
public class GameScreen extends uib.teamdank.common.gui.GameScreen {
	private static final String SCORES = "TeamDank/Carl the Crasher/highscore.json";
	private static final int AMOUNT_NEW_HIGHSCORE_MESSAGES = 4;
	
	private static final int AMOUNT_PER_SCORE = 1;
	private static final float TIME_BETWEEN_SCORE = 1f;

	private static final int AMOUNT_PER_FUEL_LOSS = 1;
	private static final float TIME_BETWEEN_FUEL_LOSS = .2f;

	private static final int CAR_VERTICAL_POSITION = 25;

	private final AssetManager assets;

	private final OrthographicCamera playerCamera;
	private final OrthographicCamera screenCamera;

	private final BackgroundLayer backgroundLayer;
	private final Layer roadEntityLayer;
	private final Layer pedestrianLayer;
	private final Layer carLayer;
	private final CarHud hud;

	private final GameSounds bgMusic;
	private final GameSounds car_drive;
	
	private final GameSounds car_crash;
	private final GameSounds coin_sound;
	private final GameSounds dead_ped;
	private final GameSounds fuel;
	
	private final Player player;

	private final ScrollingSpawner pedestrianSpawner;
	private final ScrollingSpawner roadEntitySpawner;
	private List<Score> score;
	private int numTimesNewHighscoreMessage;
	private TimedEvent onOffNewHighscoreMessage;

	public GameScreen(Game game) {
		super(game);

		this.assets = new AssetManager();
		TextureAtlas carTextures = assets.getAtlas("Images/car_sheet.json");
		TextureAtlas roadEntityTextures = assets.getAtlas("Images/road_entity_sheet.json");
		TextureAtlas pedestrianTextures = assets.getAtlas("Images/Game/walkers.json");

		// Cameras
		this.playerCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.screenCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		// Player initialization
		player = ((CarGame) game).getPlayer();
		player.reset();
		if (player.getTexture() == null) {
			player.unlockSkin("car_forward");
			player.setTexture(carTextures.getRegion("car_forward"));
		}
		player.setScale(.5f);
		addTimedEvent(new TimedEvent(TIME_BETWEEN_FUEL_LOSS, true, () -> {
			player.decreaseHealth(AMOUNT_PER_FUEL_LOSS);
		}));
		addTimedEvent(new TimedEvent(TIME_BETWEEN_SCORE, true, () -> {
			player.getScore().addToScore(AMOUNT_PER_SCORE);
		}));

		// Layers
		backgroundLayer = new BackgroundLayer(assets, playerCamera, screenCamera, player);
		roadEntityLayer = new Layer(true);
		pedestrianLayer = new Layer(true);
		carLayer = new Layer(true);
		addLayer(backgroundLayer);
		addLayer(roadEntityLayer);
		addLayer(pedestrianLayer);
		addLayer(carLayer);
		carLayer.addGameObject(player);

		// Road entity spawner initialization
		this.roadEntitySpawner = new ScrollingSpawner(roadEntityLayer, playerCamera,
				new RoadEntityGenerator(roadEntityTextures));
		roadEntitySpawner.setHorizontalPositionRange(backgroundLayer.getRoadLeftX(), backgroundLayer.getRoadRightX());
		roadEntitySpawner.setChanceOfSpawn(.01f);
		roadEntitySpawner.setExtraVerticalSpaceBetweenSpawns(50);

		// pedestrian spawner initialization
		this.pedestrianSpawner = new ScrollingSpawner(pedestrianLayer, playerCamera,
				new PedestrianGenerator(pedestrianTextures));
		pedestrianSpawner.setHorizontalPositionRange(backgroundLayer.getRoadLeftX(), backgroundLayer.getRoadRightX());
		pedestrianSpawner.setChanceOfSpawn(.01f);
		pedestrianSpawner.setExtraVerticalSpaceBetweenSpawns(50);

		// HUD
		this.hud = new CarHud();
		FileHandle handle = Gdx.files.external(SCORES);
		if(!handle.exists())
			handle = Gdx.files.internal("Data/highscore.json");
		score = new LinkedList<>(Arrays.asList(Score.createFromJson(handle)));
		numTimesNewHighscoreMessage = 0;
		onOffNewHighscoreMessage = new TimedEvent(0.5f, true, () -> {
			if(player.getScore().getScore() > score.get(0).getScore()-290) {
				hud.setVisibleNewHighscore(!hud.isVisibleNewHighscore());
				numTimesNewHighscoreMessage++;
				if(numTimesNewHighscoreMessage >= AMOUNT_NEW_HIGHSCORE_MESSAGES*2) {
					onOffNewHighscoreMessage.setLoop(false);
					hud.setVisibleNewHighscore(false);
				}
			}
		});
		addTimedEvent(onOffNewHighscoreMessage);
		

		// Music/long audio files
		bgMusic = new GameSounds();
		car_drive = new GameSounds();
		
		bgMusic.addMusic("happy_bgmusic.wav");
		car_drive.addMusic("car_drive.wav");
		
		// Sounds
		car_crash = new GameSounds();
		car_crash.addSound("car_crash.mp3");
		
		dead_ped = new GameSounds();
		dead_ped.addSound("dead_pedestrian.mp3");
		
		coin_sound = new GameSounds();
		coin_sound.addSound("coin_sound.wav");
		
		fuel = new GameSounds();
		fuel.addSound("fuel.wav");
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

		bgMusic.disposeMusic();
		car_drive.disposeMusic();
		
		car_crash.disposeSound();
		dead_ped.disposeSound();
		coin_sound.disposeSound();
		fuel.disposeSound();
	}

	@Override
	public void hide() {
		bgMusic.pauseMusic();
		car_drive.pauseMusic();
		
		car_crash.pauseSound();
		dead_ped.pauseSound();
		coin_sound.pauseSound();
		fuel.pauseSound();
	}

	private void itemDrivenOver(GameObject gameObject) {
		if (gameObject instanceof Coin) {
			coin_sound.playSound();
		}
		if (gameObject instanceof Fuel) {
			fuel.playSound();
		}
		if (gameObject instanceof Pedestrian) {
			dead_ped.playSound();
		}
	}

	@Override
	protected void onUpdateGameObject(float delta, Layer layer, GameObject gameObject) {
	
		if (layer == roadEntityLayer || layer == pedestrianLayer) {
			if (gameObject instanceof RoadEntity
					&& player.contains(gameObject)) {
				((RoadEntity) gameObject).drivenOverBy(player);
				itemDrivenOver(gameObject);
			}
		}
		
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
		bgMusic.playMusic();
		car_drive.playMusic();
		
	}
	
	@Override
	public void update(float delta) {

		// Update HUD
		updateHUD();

		// Updates game objects
		super.update(delta);
		player.restrictHorizontally(backgroundLayer.getRoadLeftX(), backgroundLayer.getRoadRightX());
		
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

		// Spawn new pedestrians
		pedestrianSpawner.update(delta);
		pedestrianSpawner.setHorizontalPositionRange(backgroundLayer.getRoadLeftX(), backgroundLayer.getRoadRightX());

		// Check for game over
		if (player.isOutOfFuel() && player.getVelocity().y == 0) {
			getGame().setScreen(new EndingScreen((CarGame) getGame()));
			car_crash.playSound();
		}

	}
	
	private void updateHUD() {
		hud.setCurrentFuel(player.getHealth(), player.getMaxHealth());
		hud.setScore(player.getScore().getScore());
		hud.setCoins(player.getInventory().getGold());
	}
	
}