package uib.teamdank.cargame.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

import uib.teamdank.cargame.CarGame;
import uib.teamdank.cargame.Player;
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
	
	private static final int CAR_VERTICAL_POSITION = 25;

	private static final float CAR_HORIZONTAL_ZERO_SPEED_TOLERANCE = 4f;
	private static final float CAR_HORIZONTAL_ACCELERATION = 50f;
	private static final float CAR_HORIZONTAL_FRICTION = .9f;
	
	private static final float CAR_VERTICAL_ZERO_SPEED_TOLERANCE = 48f;
	private static final float CAR_VERTICAL_ACCELERATION = 10f;
	private static final float CAR_VERTICAL_MAX_SPEED = 512f;
	private static final float CAR_VERTICAL_FRICTION = .985f;

	private final AssetManager assets;

	private final OrthographicCamera playerCamera;
	private final OrthographicCamera screenCamera;

	private final BackgroundLayer backgroundLayer;
	private final Layer carLayer;
	private final CarHud hud;

	private final Sound carSound;

	private final Player player;
	private float timeSinceScore;
	
	private final EndingScreen endScreen;

	public GameScreen(Game game) {
		super(game);

		this.assets = new AssetManager();
		TextureAtlas carTextures = assets.getAtlas("Images/car_sheet.json");
		// TextureAtlas gameObjectTextures = assets.getAtlas("Images/game_object_sheet.json");

		// Cameras
		this.playerCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.screenCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		// Player initialization
		player = new Player();
		player.setTexture(carTextures.getRegion("car_forward_flag"));
		player.setScale(.5f);
		player.getVelocity().y = CAR_VERTICAL_MAX_SPEED;

		// Layers
		backgroundLayer = new BackgroundLayer(assets, playerCamera, screenCamera, player);
		carLayer = new Layer(true);
		addLayer(backgroundLayer);
		addLayer(carLayer);
		carLayer.addGameObject(player);

		this.hud = new CarHud();

		// Sounds
		carSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/car_sound.wav"));
		carSound.play(0.5f); // 0.5f er volumet

		endScreen = new EndingScreen((CarGame) game);

	}

	@Override
	public void resize(int width, int height) {
		playerCamera.viewportWidth = screenCamera.viewportWidth = width;
		playerCamera.viewportHeight = screenCamera.viewportHeight = height;
		playerCamera.update();
		screenCamera.update();
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
	public void update(float delta) {
		
		// Update score
		timeSinceScore += delta;
		if (timeSinceScore >= TIME_BETWEEN_SCORE) {
			player.getScore().addToScore(AMOUNT_PER_SCORE);
			timeSinceScore -= TIME_BETWEEN_SCORE;
		}

		// Update HUD
		hud.setCurrentFuel(player.getHealth(), player.getMaxHealth());
		hud.setScore(player.getScore().getScore());

		// Update game objects
		super.update(delta);
		
		final Vector2 playerVelocity = player.getVelocity();
		
		// Player vertical movement
		if (player.getVelocity().y == 0) {
			getGame().setScreen(endScreen);
		} else if (player.getHealth() == 0) {
			player.getVelocity().y *= CAR_VERTICAL_FRICTION;
		} else {
			player.decreaseHealth(1);
			
			if (playerVelocity.y != CAR_VERTICAL_MAX_SPEED) {
				playerVelocity.y += CAR_VERTICAL_ACCELERATION;
				if (playerVelocity.y > CAR_VERTICAL_MAX_SPEED) {
					playerVelocity.y = CAR_VERTICAL_MAX_SPEED;
				}
			}
		}
		if (playerVelocity.epsilonEquals(playerVelocity.x, 0, CAR_VERTICAL_ZERO_SPEED_TOLERANCE)) {
			playerVelocity.y = 0;
		}
		
		// Player horizontal movement
		boolean left = Gdx.input.isKeyPressed(Keys.A);
		boolean right = Gdx.input.isKeyPressed(Keys.D);
		float playerHorizontalAcceleration = (playerVelocity.y / CAR_VERTICAL_MAX_SPEED)
												* CAR_HORIZONTAL_ACCELERATION;
		if (left)
			player.getVelocity().x -= playerHorizontalAcceleration;
		if (right)
			player.getVelocity().x += playerHorizontalAcceleration;
		if (player.getPosisiton().x < backgroundLayer.getRoadLeftX()) {
			player.getPosisiton().x = backgroundLayer.getRoadLeftX();
			player.getVelocity().x *= -1;
		} else if (player.getPosisiton().x > backgroundLayer.getRoadRightX() - player.getWidth()) {
			player.getPosisiton().x = backgroundLayer.getRoadRightX() - player.getWidth();
			player.getVelocity().x *= -1;
		}
		playerVelocity.x *= CAR_HORIZONTAL_FRICTION;
		if (playerVelocity.epsilonEquals(0, playerVelocity.y, CAR_HORIZONTAL_ZERO_SPEED_TOLERANCE)) {
			playerVelocity.x = 0;
		}
		
	}

	@Override
	public void dispose() {
		super.dispose();
		assets.dispose();
		carSound.dispose();
	}
}