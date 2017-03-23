package uib.teamdank.common.gui;

import java.util.function.Consumer;

import uib.teamdank.common.GameObject;

/**
 * A layer of {@link GameObject}s.
 */
public interface Layer {
	/**
	 * Loops through every game object in this layer and passes it to the
	 * specified consumer.
	 */
	public void forEachGameObject(Consumer<GameObject> consumer);

	public void addGameObject(GameObject object);

	/**
	 * @return amount of game objects in this layer
	 */
	public int getSize();

	/**
	 * @return whether this layer is checked for collision
	 */
	public boolean isSolid();

	public void setSolid(boolean solid);
}
