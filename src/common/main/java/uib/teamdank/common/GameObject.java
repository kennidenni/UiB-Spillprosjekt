package uib.teamdank.common;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Represent an object in a game.
 */
public class GameObject {

	private boolean marked;
	private int height, width;
	private Vector2 pos, velocity;
	private TextureRegion tRegion;

	/**
	 * Creates a GameObject that spans no area, in the origin (0, 0).
	 */
	public GameObject() {
		this(0, 0);
	}

	/**
	 * Creates a GameObject in the origin, that spans the area of the given
	 * {@link TextureRegion}.
	 * 
	 * @param tRegion
	 */
	public GameObject(TextureRegion tRegion) {
		this(0, 0, tRegion);
	}

	/**
	 * Creates a GameObject that spans no area, in the position (x, y).
	 * 
	 * @param x
	 * @param y
	 */
	public GameObject(int x, int y) {
		this(x, y, 0, 0);
	}

	/**
	 * Creates a GameObject in the position (x, y), that spans the area of the
	 * given {@link TextureRegion}.
	 * 
	 * @param x
	 * @param y
	 * @param tRegion
	 */
	public GameObject(int x, int y, TextureRegion tRegion) {
		this(x, y, Math.abs(tRegion.getRegionWidth()), Math.abs(tRegion.getRegionHeight()));
	}

	/**
	 * Creates a GameObject in the position (x, y), that spans the area (width,
	 * height).
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	//Did some assumptions here.
	public GameObject(int x, int y, int width, int height) {
		if (x < 0 || y < 0) {
			throw new IllegalArgumentException("Expected positive coordinates.");
		}
		if (width < 0 || height < 0) {
			throw new IllegalArgumentException("Width and height must be positive.");
		}

		this.width = width;
		this.height = height;
		pos = new Vector2(x, y);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return Whether the given coordinates are contained within this GameObject.
	 */
	public boolean contains(double x, double y) {
		return x >= pos.x && x <= (pos.x + width) && y >= pos.y && y <= (pos.y + height);
	}

	/**
	 * 
	 * @return The width of this GameObject.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 *
	 * @return The height of this GameObject.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Mark this object for removal.
	 */
	// TODO: Rename to markForRemoval (?)
	public void remove() {
		marked = true;
	}

	/**
	 *
	 * @return Whether or not this object is marked for removal.
	 */
	// TODO: Rename to isMarkedForRemoval (?)
	public boolean toBeRemoved() {
		return marked;
	}

	/**
	 * 
	 * @return The current position of this game object.
	 */
	public Vector2 getPosisiton() {
		return pos;
	}

	/**
	 * 
	 * @param pos
	 *            The new position of this GameObject.
	 */
	public void setPosition(Vector2 pos) {
		this.pos = pos;
	}

	/**
	 * @return The current velocity of this GameObject.
	 */
	public Vector2 getVelocity() {
		return velocity;
	}

	/**
	 * @param velocity
	 *            The new velocity of this GameObject.
	 */
	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

	/**
	 * 
	 * @return The rectangular area eventually containing a texture that is the
	 *         image representation of this GameObject.
	 */
	public TextureRegion getTexture() {
		return tRegion;
	}

	/**
	 * 
	 * @return Whether or not this GameObject can move.
	 */
	// TODO: Subclasses must remember to override
	public boolean isMovable() {
		return false;
	}

	/**
	 * @return Whether or not this GameObject can share space with other
	 *         GameObject's.
	 */
	// TODO: Subclasses must remember to override
	public boolean isSolid() {
		return false;
	}

}
