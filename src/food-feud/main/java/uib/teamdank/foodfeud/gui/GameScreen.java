/*******************************************************************************
 * Copyright (C) 2017  TeamDank
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package uib.teamdank.foodfeud.gui;

import java.util.Date;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

import uib.teamdank.common.Game;
import uib.teamdank.common.GameObject;
import uib.teamdank.common.gui.Layer;
import uib.teamdank.common.util.AssetManager;
import uib.teamdank.common.util.TimedEvent;
import uib.teamdank.foodfeud.FoodFeud;
import uib.teamdank.foodfeud.Level;
import uib.teamdank.foodfeud.LevelLoader;
import uib.teamdank.foodfeud.Match;
import uib.teamdank.foodfeud.PhysicsContactListener;
import uib.teamdank.foodfeud.PhysicsSimulated;
import uib.teamdank.foodfeud.Player;
import uib.teamdank.foodfeud.PlayerBodyCreator;

/**
 * The main gameplay screen.
 */
public class GameScreen extends uib.teamdank.common.gui.GameScreen {
	private static final Box2DDebugRenderer WORLD_DEBUG_RENDERER = new Box2DDebugRenderer();

	private final BackgroundLayer backgroundLayer;
	private final Layer playerLayer;
	private final ForegroundLayer foregroundLayer;

	private final OrthographicCamera camera;
	private final Level level;
	private final Match match;

	private static final float TIME_BETWEEN_TIME = 1f;
	private static final int AMOUNT_PER_TIME = 1;

	private static final int FINAL_TIME = 30;
	private int time;

	private final FoodHud hud;
	private final AssetManager assets;

	private boolean touched = false;
	private long startTime;
	private long elapsedTime;
	
	public GameScreen(Game game) {
		super(game);

		this.assets = ((FoodFeud) game).getSetupGame().getAssets();
		time = FINAL_TIME;

		this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.level = LevelLoader.createFromJson(Gdx.files.internal("Data/field_level.json"));
		
		this.match = ((FoodFeud) game).getSetupGame().getMatch();

		level.getWorld().setContactListener(new PhysicsContactListener(match));

		camera.position.set(level.getWidth() / 2f, level.getHeight() / 2f, 0);
		camera.zoom = .5f; // .5f

		this.backgroundLayer = new BackgroundLayer(level);
		addLayer(backgroundLayer);
		this.playerLayer = new Layer(true);
		addLayer(playerLayer);
		this.foregroundLayer = new ForegroundLayer(level);
		addLayer(foregroundLayer);

		// HUD
		this.hud = new FoodHud();
		hud.setGame((FoodFeud) game);
		
		addTimedEvent(new TimedEvent(TIME_BETWEEN_TIME, true, () -> {
			time -= AMOUNT_PER_TIME;
		}));

		PlayerBodyCreator playerBodyCreator = new PlayerBodyCreator(level.getWorld());
		for (Player player : match.getPlayers()) {
			playerBodyCreator.initializeBody(player);
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

		// Render HUD
		hud.render(delta);	
		
		WORLD_DEBUG_RENDERER.render(level.getWorld(), camera.combined);
	}
	
	@Override
	public void show() {
		hud.setAsInputProcessor();
	}

	@Override
	public void resize(int width, int height) {
		camera.viewportWidth = width;
		camera.viewportHeight = height;
		camera.update();
	}

	@Override
	public void update(float delta) {

		final Player activePlayer = match.getActivePlayer();

		camera.position.set(activePlayer.getX(), activePlayer.getY(), 0f);
		camera.position.add(activePlayer.getWidth() / 2f, activePlayer.getHeight() / 2f, 0f);
		camera.update();

		// Update game objects
		super.update(delta);
		level.updateWorld();

		checkForMute();
		// User input
		checkPauseRequest();
		if (!activePlayer.isDead()) {
			movement(activePlayer);
		}
		// Prevent players exiting world
		for (Player player : match.getPlayers()) {
			if (player.getX() < 0) {
				player.moveRight(3);
			} else if (player.getX() > level.getWidth() - player.getWidth()) {
				player.moveLeft(3);
			}
		}

		checkTimeorDead(activePlayer);

		checkVictory();

		// Temporary
		if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			Vector3 aim3D = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
			Vector2 aim = new Vector2(aim3D.x, aim3D.y);
			aim.sub(activePlayer.getPosition());
			activePlayer.fireWeapon(this, playerLayer, level.getWorld(), aim.nor(), 10000);
		}
		if (Gdx.input.justTouched()) {
			
			startTime = System.currentTimeMillis();
			elapsedTime = 0;
			touched = true;
		}
			
		if(!Gdx.input.isTouched() && touched){
				elapsedTime = (new Date()).getTime() -startTime;
				Vector3 aim3D = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
				Vector2 aim = new Vector2(aim3D.x, aim3D.y);
				aim.sub(activePlayer.getPosition());
				System.out.println(elapsedTime * 100);
				activePlayer.fireWeapon(this, playerLayer, level.getWorld(), aim.nor(), elapsedTime * 100);
				touched = false;
				
			}
		
		if (Gdx.input.isKeyJustPressed(Keys.N)) {
			time = FINAL_TIME;
			match.nextTurn();
		}
		if (Gdx.input.isKeyJustPressed(Keys.M)) {
			activePlayer.decreaseHealth(20);
		}

	}

	private void movement(Player active) {
		if (active.isOnGround() && (Gdx.input.isKeyJustPressed(Keys.W) || Gdx.input.isKeyJustPressed(Keys.UP))) {
			active.jump();
		}
		if ((Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT))) {
			if ((active.getBody().getLinearVelocity().x) > (-Player.MAX_VEL_X)) {
				active.moveLeft();
			}
			active.walking = true;
		}
		if ((Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT))) {
			if ((active.getBody().getLinearVelocity().x) < Player.MAX_VEL_X) {
				active.moveRight();
			}
			active.walking = true;
		}
	}
	
	@Override
	protected void onUpdateGameObject(float delta, Layer layer, GameObject gameObject) {

		// Dispose of physics bodies on deleted objects
		if (gameObject.isMarkedForRemoval() && gameObject instanceof PhysicsSimulated) {
			level.getWorld().destroyBody(((PhysicsSimulated) gameObject).getBody());
		}
	}

	public void setStartAudio(boolean isMuted) {
		hud.setMute(isMuted);
	}

	private void checkForMute() {
		if (hud.isMuted()) {
			assets.getAudio().mute();
		} else {
			assets.getAudio().unmute();
		}
	}

	public boolean isMuted() {
		return hud.isMuted();
	}

	/**
	 * checks if time has run out, forces new round if true
	 */
	public void checkTimeorDead(Player active) {
		hud.setTime(time);
		if (time == 0) {
			time = FINAL_TIME;
			match.nextTurn();
		}
		if (active.isDead()) {
			time = FINAL_TIME;
			match.nextTurn();
		}
	}

	public Match getMatch() {
		return match;
	}

	public void checkVictory() {
		Player player = match.getWinner();
		if (player != null)
			getGame().setScreen(new EndingScreen((FoodFeud) getGame()));
	}
}