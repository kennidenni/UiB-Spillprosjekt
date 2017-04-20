package uib.teamdank.cargame.util;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * A looping image ensures that a texture always covers the screen vertically
 * by rendering the texture multiple time when needed, effectively looping it.  
 */
public class LoopingBackground {
	private final OrthographicCamera camera;
	private final Texture texture;
	private final float scale;
	
	private int horizontalPosition;
	private int lowestRoadY;
	
	public LoopingBackground(OrthographicCamera camera, Texture texture, float scale) {
		this.camera = camera;
		this.texture = texture;
		this.scale = scale;
	}
	
	public void setHorizontalPosition(int x) {
		this.horizontalPosition = x;
	}
	
	public void render(SpriteBatch batch) {
		if (!camera.frustum.pointInFrustum(0, lowestRoadY + texture.getHeight(), 0)) {
			batch.draw(texture, horizontalPosition, lowestRoadY + getHeight(), getWidth(), getHeight());
		} else {
			lowestRoadY += getHeight() / 2;
		}
		
		batch.draw(texture, horizontalPosition, lowestRoadY, getWidth(), getHeight());
	}
	
	public int getWidth() {
		return (int) (texture.getHeight() * scale);
	}
	
	private int getHeight() {
		// Method is private because the background loops
		// vertically, which makes the height obsolete
		return (int) (texture.getWidth() * scale);
	}
}
