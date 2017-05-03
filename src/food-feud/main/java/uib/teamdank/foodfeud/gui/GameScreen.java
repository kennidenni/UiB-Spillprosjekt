package uib.teamdank.foodfeud.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

import uib.teamdank.common.Game;
import uib.teamdank.foodfeud.Level;
import uib.teamdank.foodfeud.LevelLoader;
import uib.teamdank.foodfeud.Match;

/**
 * The main gameplay screen.
 */
public class GameScreen extends uib.teamdank.common.gui.GameScreen {
	private static final Box2DDebugRenderer WORLD_DEBUG_RENDERER = new Box2DDebugRenderer();

	private final BackgroundLayer backgroundLayer;
	
	private final OrthographicCamera camera;
	private final Level level;
	private final Match match;
	
	public GameScreen(Game game) {
		super(game);
		
		this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.level = LevelLoader.createFromJson(Gdx.files.internal("Data/field_level.json"));
		this.match = new Match(level.getWorld(), "Geir", "Bodil", "Arne", "Bertrude");	
		
		camera.position.add(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
		camera.zoom = 2f;
		
		this.backgroundLayer = new BackgroundLayer(level);
		addLayer(backgroundLayer);
	}
	
	private void checkPauseRequest() {
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			getGame().setScreen(getGame().getPauseMenuScreen());
		}
	}
	
	@Override
	public void render(float delta) {
		getGame().getSpriteBatch().setProjectionMatrix(camera.combined);
		super.render(delta);
		WORLD_DEBUG_RENDERER.render(level.getWorld(), camera.combined);
	}
	
	@Override
	public void resize(int width, int height) {
		camera.viewportWidth = width;
		camera.viewportHeight = height;
		camera.update();
	}
	
	@Override
	public void update(float delta) {
		
		checkPauseRequest();
		camera.update();
		
		// Update game objects
		super.update(delta);
		level.updateWorld();
		
	}

}