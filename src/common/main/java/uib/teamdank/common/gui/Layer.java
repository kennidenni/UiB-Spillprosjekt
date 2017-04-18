package uib.teamdank.common.gui;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;

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
		Objects.requireNonNull(action, "action cannot be null");
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
	 * Removes every game object in this layer that is marked for removal.
	 */
	public void removeMarkedGameObjects() {
		
		// Use iterator instead of for-loop to prevent
		// modification of list while looping through
		Iterator<GameObject> it = gameObjects.iterator();
		while (it.hasNext()) {
			GameObject obj = it.next();
			if (obj.toBeRemoved()) {
				it.remove();
			}
		}
	}

	public void setSolid(boolean solid) {
		this.solid = solid;
	}
}
