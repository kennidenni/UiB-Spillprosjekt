package uib.teamdank.cargame.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

import uib.teamdank.cargame.Player;
import uib.teamdank.common.Game;
import uib.teamdank.common.gui.Layer;
import uib.teamdank.common.util.TextureAtlas;

/**
 * The main gameplay screen.
 */
public class GameScreen extends uib.teamdank.common.gui.GameScreen {
	private static final int CAR_VERTICAL_POSITION = 25;

	private static final float CAR_HORIZONTAL_ACCELERATION = 50f;
	private static final float CAR_VERTICAL_SPEED = 512f;
	private static final float CAR_HORIZONTAL_FRICTION = .9f;

	private final OrthographicCamera playerCamera;
	private final OrthographicCamera screenCamera;

	private final BackgroundLayer backgroundLayer;
	private final Layer carLayer;
	private final CarHud hud;

	private final Player player;

	public GameScreen(Game game) {
		super(game);

		TextureAtlas gameObjectTextures = TextureAtlas.createFromJson(Gdx.files.internal("Images/game_objects.json"));

		// Cameras
		this.playerCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.screenCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		// Player initialization
		player = new Player();
		player.setTexture(gameObjectTextures.getRegion("car_forward"));
		player.setScale(.5f);
		player.getVelocity().y = CAR_VERTICAL_SPEED;

		// Layers
		backgroundLayer = new BackgroundLayer(playerCamera, screenCamera, player);
		carLayer = new Layer(true);
		addLayer(backgroundLayer);
		addLayer(carLayer);
		carLayer.addGameObject(player);
		
		this.hud = new CarHud();

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

		// Update game objects
		super.update(delta);

		// Player movement
		boolean left = Gdx.input.isKeyPressed(Keys.A);
		boolean right = Gdx.input.isKeyPressed(Keys.D);
		if (left) player.getVelocity().x -= CAR_HORIZONTAL_ACCELERATION;
		if (right) player.getVelocity().x += CAR_HORIZONTAL_ACCELERATION;
		player.getVelocity().x *= CAR_HORIZONTAL_FRICTION;
		if (player.getPosisiton().x < backgroundLayer.getRoadLeftX()) {
			player.getPosisiton().x = backgroundLayer.getRoadLeftX();
			player.getVelocity().x *= -1;
		} else if (player.getPosisiton().x > backgroundLayer.getRoadRightX() - player.getWidth()) {
			player.getPosisiton().x = backgroundLayer.getRoadRightX() - player.getWidth();
			player.getVelocity().x *= -1;
		}
		
		// Update HUD
		hud.setCurrentFuel(player.getHealth());
		player.setHealth(player.getMaxHealth() - (int) (player.getPosisiton().y / 100));

	}

}