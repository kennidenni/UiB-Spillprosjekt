package uib.teamdank.foodfeud.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

import uib.teamdank.common.Game;
import uib.teamdank.common.GameObject;
import uib.teamdank.common.gui.Layer;
import uib.teamdank.foodfeud.Level;
import uib.teamdank.foodfeud.LevelLoader;
import uib.teamdank.foodfeud.Match;
import uib.teamdank.foodfeud.PhysicsSimulated;
import uib.teamdank.foodfeud.Player;

/**
 * The main gameplay screen.
 */
public class GameScreen extends uib.teamdank.common.gui.GameScreen {
	private static final Box2DDebugRenderer WORLD_DEBUG_RENDERER = new Box2DDebugRenderer();

	private final BackgroundLayer backgroundLayer;
	private final Layer playerLayer;

	private final OrthographicCamera camera;
	private final Level level;
	private final Match match;

	public GameScreen(Game game) {
		super(game);

		this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.level = LevelLoader.createFromJson(Gdx.files.internal("Data/field_level.json"));
		this.match = new Match("Geir", "Bodil", "Arne", "Bertrude");

		camera.position.set(level.getWidth() / 2f, level.getHeight() / 2f, 0);
		camera.zoom = 1f;

		this.backgroundLayer = new BackgroundLayer(level);
		addLayer(backgroundLayer);
		this.playerLayer = new Layer(true);
		addLayer(playerLayer);

		TextureRegion playerTexture = new TextureRegion(new Texture("Images/food_sheet.png"), 53, 48, 57, 57); // Temporary
		for (Player player : match.getPlayers()) {
			player.setTexture(playerTexture);
			playerLayer.addGameObject(player);
		}
		level.distributePlayers(match.getPlayers());
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
	
	@Override
	protected void onUpdateGameObject(float delta, Layer layer, GameObject gameObject) {
		
		// Dispose of physics bodies on deleted objects
		if (gameObject.isMarkedForRemoval() && gameObject instanceof PhysicsSimulated) {
			level.getWorld().destroyBody(((PhysicsSimulated) gameObject).getBody());
		}
		
	}

}