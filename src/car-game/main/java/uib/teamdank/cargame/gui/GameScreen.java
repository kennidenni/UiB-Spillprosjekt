package uib.teamdank.cargame.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import uib.teamdank.cargame.Player;
import uib.teamdank.cargame.ScrollingImage;
import uib.teamdank.common.Game;
import uib.teamdank.common.gui.Layer;

/**
 * The main gameplay screen.
 */
public class GameScreen extends uib.teamdank.common.gui.GameScreen {

	private final Layer backgroundLayer = new Layer(false);
	private final Layer carLayer = new Layer(true);

	private final OrthographicCamera camera = new OrthographicCamera();
	
	private final Player player = new Player();
	
	private final Texture backgroundTexture;
	private final ScrollingImage scrollingRoad;
	
	public GameScreen(Game game) {
		super(game);
		
		// Background
		backgroundTexture = new Texture(Gdx.files.internal("Images/background.png"));
		scrollingRoad = new ScrollingImage(camera, new Texture(Gdx.files.internal("Images/road.png")));
		
		// Player
		player.setTexture(new TextureRegion(new Texture(Gdx.files.internal("Images/car.png"))));
		player.setScale(.4f);
		carLayer.addGameObject(player);
		
		addLayer(backgroundLayer);
		addLayer(carLayer);
		
		player.getVelocity().set(10, 20);
	}
	
	@Override
	public void render(float delta) {
		final int screenWidth = Gdx.graphics.getWidth();
		final int screenHeight = Gdx.graphics.getHeight();
		
		// Draw background
		SpriteBatch batch = getGame().getSpriteBatch();
		batch.begin();
		batch.draw(backgroundTexture, 0, 0, screenWidth, screenHeight);
		scrollingRoad.render(batch, delta);
		batch.end();
		
		// Camera
		Vector2 playerPos = player.getPosisiton();
		camera.position.set(playerPos.x + player.getWidth() / 2, playerPos.y + player.getHeight() / 2, 0);
		camera.viewportWidth = screenWidth;
		camera.viewportHeight = screenHeight;
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		super.render(delta);
	}
	
	@Override
	public void update(float delta) {
		super.update(delta);
		scrollingRoad.update(delta);
	}

}