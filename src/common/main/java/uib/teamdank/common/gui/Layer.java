package uib.teamdank.common.gui;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import uib.teamdank.common.GameObject;

/**
 * A layer of {@link GameObject}s.
 */
public class Layer {
	private final Array<GameObject> gameObjects = new Array<>();

	private boolean solid;

	public Layer(boolean solid) {
		setSolid(solid);
	}

	public void addGameObject(GameObject object) {
		Objects.requireNonNull(object, "game object cannot be null");
		gameObjects.add(object);
	}

	/**
	 * Loops through every game object in this layer and passes it to the
	 * specified consumer.
	 */
	public void forEachGameObject(Consumer<GameObject> action) {
		gameObjects.forEach(action);
	}

	/**
	 * @return amount of game objects in this layer
	 */
	public int getSize() {
		return gameObjects.size;
	}

	/**
	 * @return whether this layer is checked for collision
	 */
	public boolean isSolid() {
		return solid;
	}
	
	/**
	 * @return returns the array of game objects in the layer
	 */
	public Array<GameObject> getAllObjects() {
		return gameObjects;
	}
	
	/**
	 * This method is called before the rendering of the game
	 * objects in this layer ({@link #render(SpriteBatch, float)}.
	 */
	protected void preRender(SpriteBatch batch, float delta) {
		// Can be overridden
	}

	/**
	 * Removes every game object in this layer that is marked for removal.
	 */
	public void removeMarkedGameObjects() {
		
		// Use iterator instead of for-loop to prevent
		// modification of list while looping through
		Iterator<GameObject> it = gameObjects.iterator();
		while (it.hasNext()) {
			GameObject obj = it.next();
			if (obj.isMarkedForRemoval()) {
				it.remove();
			}
		}
	}
	
	public void render(SpriteBatch batch, float delta) {
		preRender(batch, delta);
		forEachGameObject(gameObject -> {
			if (gameObject.getTexture() != null) {
				
				// Game objects should have an origin in the lower left
				// corner, but to correctly flip textures the origin
				// will be in the center and the game object will be
				// offset to be positioned correctly in the lower left.
				
				Vector2 pos = gameObject.getPosisiton();
				final float width = gameObject.getWidth();
				final float height = gameObject.getHeight();
				final float flipX = gameObject.getFlipHorizontally() ? -1 : 1;
				final float flipY = gameObject.getFlipVertically() ? -1 : 1;
				batch.draw(gameObject.getTexture(), pos.x, pos.y, width / 2, height / 2, width, height, flipX, flipY, gameObject.getAngle());
			}
		});
	}

	public void setSolid(boolean solid) {
		this.solid = solid;
	}
}
