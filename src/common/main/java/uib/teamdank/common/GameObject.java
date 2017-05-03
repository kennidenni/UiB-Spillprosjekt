package uib.teamdank.common;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import uib.teamdank.common.util.Animation;

/**
 * Represent an object in a game.
 */
public class GameObject {

	private boolean marked;
	private final Vector2 pos = new Vector2();
    private final Vector2 velocity = new Vector2();
	private Animation animation;
	private float scale;
	private float angle;
	private boolean flipHorizontally;
	private boolean flipVertically;

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
	    this.setTexture(tRegion);
	    this.setScale(1);
	    this.setFlipHorizontally(false);
	    this.setFlipVertically(false);
	}

	/**
	 * Set the scaling of the item.
	 * @param s
	 */
	public void setScale(float s){
		this.scale = s;
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
		if (getTexture() == null) {
			return 0;
		}
		return (int) (getTexture().getRegionWidth()*scale);
	}

	/**
	 *
	 * @return The height of this GameObject.
	 */
	public int getHeight() {
		if (getTexture() == null) {
			return 0;
		}
		return (int) (getTexture().getRegionHeight()*scale);
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

	public Animation getAnimation() {
		return animation;
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
	 * @return The rectangular area eventually containing a texture that is the
	 *         image representation of this GameObject.
	 */
	public TextureRegion getTexture() {
		return animation == null ? null : animation.getTexture();
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
	}
	
	/**
	 * Sets this game object's texture to be flipped along the vertical
	 * axis when rendered.
	 */
	public void setFlipHorizontally(boolean flipHorizontally) {
		this.flipHorizontally = flipHorizontally;
	}
	
	public boolean getFlipHorizontally() {
		return flipHorizontally;
	}
	
	/**
	 * Sets this game object's texture to be flipped along the horizontal
	 * axis when rendered.
	 */
	public void setFlipVertically(boolean flipVertically) {
		this.flipVertically = flipVertically;
	}
	
	public boolean getFlipVertically() {
		return flipVertically;
	}
	
	public void setAngle(float angle) {
		this.angle = angle;
	}
	
	public float getAngle() {
		return angle;
	}
	
    /**
     * Sets the texture of this object
     * @param texture
     */
	public void setTexture(TextureRegion texture){
		if (texture == null) {
			this.animation = null;
		} else {
			this.animation = Animation.createSingleFramed(texture);
		}
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
