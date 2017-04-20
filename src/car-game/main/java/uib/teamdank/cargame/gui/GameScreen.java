package uib.teamdank.cargame.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import uib.teamdank.cargame.Player;
import uib.teamdank.common.Game;
import uib.teamdank.common.gui.Layer;

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
	
	private final Layer backgroundLayer;
	private final Layer carLayer;
	
	private final Player player;
	
	public GameScreen(Game game) {
		super(game);
		
		// Cameras
		this.playerCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.screenCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		// Player initialization
		player = new Player();
		player.setTexture(new TextureRegion(new Texture(Gdx.files.internal("Images/car.png"))));
		player.setScale(.4f);
		player.getVelocity().y = CAR_VERTICAL_SPEED;
		
		// Layers
		backgroundLayer = new BackgroundLayer(playerCamera, screenCamera, player);
		carLayer = new Layer(true);
		addLayer(backgroundLayer);
		addLayer(carLayer);
		carLayer.addGameObject(player);
		
	}
	
	@Override
	public void render(float delta) {
		final int screenWidth = Gdx.graphics.getWidth();
		final int screenHeight = Gdx.graphics.getHeight();
		
		// Update player camera
		Vector2 playerPos = player.getPosisiton();
		float cameraX = playerPos.x + player.getWidth() / 2;
		float cameraY = playerPos.y + screenHeight / 2 - CAR_VERTICAL_POSITION;
		playerCamera.position.set(cameraX, cameraY, 0);
		playerCamera.viewportWidth = screenWidth;
		playerCamera.viewportHeight = screenHeight;
		playerCamera.update();
		getGame().getSpriteBatch().setProjectionMatrix(playerCamera.combined);
		
		// Render layers
		super.render(delta);
		
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
		
	}

}