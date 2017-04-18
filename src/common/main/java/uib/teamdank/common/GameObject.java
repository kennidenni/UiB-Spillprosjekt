package uib.teamdank.common;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Represent an object in a game.
 */
public class GameObject {

	private boolean marked;
	private final Vector2 pos = new Vector2();
    private final Vector2 velocity = new Vector2();
	private TextureRegion tRegion;

	/**
	 * Creates a GameObject that spans no area, in the origin (0, 0).
	 */
	public GameObject() {
		this(null);
	}

	/**
	 * Creates a GameObject in the origin, that spans the area of the given
	 * {@link TextureRegion}.
	 * 
	 * @param tRegion
	 */
	public GameObject(TextureRegion tRegion) {
		this(0,0, tRegion);
	}

	/**
	 * Creates a GameObject that spans no area, in the position 
	 * ({@link Vector2#x}, {@link Vector2#y}).
	 * 
	 * @param x
	 * @param y
	 */
	public GameObject(float x, float y) {
		this(x, y, null);
	}

	/**
	 * Creates a GameObject in the position ({@link Vector2#x}, {@link Vector2#y}),
	 * that spans the area of the given {@link TextureRegion}.
	 * 
	 * @param x
	 * @param y
	 * @param tRegion
	 */
	public GameObject(float x, float y, TextureRegion tRegion) {
	    this.pos.set(x, y);
	    setTexture(tRegion);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return Whether the given coordinates are contained within this GameObject.
	 */
	public boolean contains(double x, double y) {
		return x >= pos.x && x <= (pos.x + getWidth()) && y >= pos.y && y <= (pos.y + getHeight());
	}

	/**
	 * 
	 * @return The width of this GameObject.
	 */
	public int getWidth() {
		return tRegion.getRegionWidth();
	}

	/**
	 *
	 * @return The height of this GameObject.
	 */
	public int getHeight() {
		return tRegion.getRegionHeight();
	}

	/**
	 * Mark this object for removal.
	 */
	public void markForRemoval() {
		marked = true;
	}

	/**
	 *
	 * @return Whether or not this object is marked for removal.
	 */
	public boolean isMarkedForRemoval() {
		return marked;
	}

	/**
	 * 
	 * @return The current position of this GameObject.
	 */
	public Vector2 getPosisiton() {
		return pos;
	}

	/**
	 * @return The current velocity of this GameObject.
	 */
	public Vector2 getVelocity() {
		return velocity;
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
     * Sets the texture of this object
     * @param texture
     */
	public void setTexture(TextureRegion texture){
	    this.tRegion = texture;
    }

	/**
	 * 
	 * @return Whether or not this GameObject can move.
	 */
	public boolean isMovable() {
		return true;
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
