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
	private final static float CAR_HORIZONTAL_ACCELERATION = 50f;
	private final static float CAR_VERTICAL_SPEED = 512f;
	private final static float CAR_HORIZONTAL_FRICTION = .9f;
	
	private final OrthographicCamera camera = new OrthographicCamera();
	
	private final Layer backgroundLayer = new BackgroundLayer(camera);
	private final Layer carLayer = new Layer(true);
	
	private final Player player = new Player();
	
	public GameScreen(Game game) {
		super(game);
				
		// Player initialization
		player.setTexture(new TextureRegion(new Texture(Gdx.files.internal("Images/car.png"))));
		player.setScale(.4f);
		player.getVelocity().y = CAR_VERTICAL_SPEED;
		carLayer.addGameObject(player);
		
		// Layers
		addLayer(backgroundLayer);
		addLayer(carLayer);
		
	}
	
	@Override
	public void render(float delta) {
		final int screenWidth = Gdx.graphics.getWidth();
		final int screenHeight = Gdx.graphics.getHeight();
		
		// Update camera
		Vector2 playerPos = player.getPosisiton();
		camera.position.set(playerPos.x + player.getWidth() / 2, playerPos.y + player.getHeight() / 2 + 200, 0);
		camera.viewportWidth = screenWidth;
		camera.viewportHeight = screenHeight;
		camera.update();
		getGame().getSpriteBatch().setProjectionMatrix(camera.combined);
		
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