package uib.teamdank.common;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Represent an object in a game.
 */
public class GameObject {
	
	/**
	 * @return whether the given coordinates are contained within this game
	 *         object
	 */
	public boolean contains(double x, double y) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 *
	 * @return height of object
	 */
	public int getHeight() {
		// TODO Auto-generated method stub
		return -1;
	}

	/**
	 * remove this object
	 */
	public void remove(){

	}

	/**
	 *
	 * @return true if object is to be removed in the next step, false otherwise
	 */
	public boolean toBeRemoved(){

	}
	/**
	 * 
	 * @return the current position of this game object
	 */
	public Vector2 getPosisiton() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @return the image representation of this game object
	 */
	public TextureRegion getTexture() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return the current velocity of this game object
	 */
	public Vector2 getVelocity() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getWidth() {
		// TODO Auto-generated method stub
		return -1;
	}

	/**
	 * 
	 * @return whether or not this game object can move
	 */
	public boolean isMovable() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @return whether or not this game object can share space with other
	 *         objects
	 */
	public boolean isSolid() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 
	 * @param position
	 *            the new position of this game object
	 */
	public void setPosition(Vector2 position) {
		// TODO Auto-generated method stub
	}

	/**
	 * @param velocity
	 *            the new velocity of this game object
	 */
	public void setVelocity(Vector2 velocity) {
		// TODO Auto-generated method stub
	}

}
