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
	
	private int lowestRoadY;
	
	public LoopingBackground(OrthographicCamera camera, Texture texture, float scale) {
		this.camera = camera;
		this.texture = texture;
		this.scale = scale;
	}
	
	public void render(SpriteBatch batch) {
		if (!camera.frustum.pointInFrustum(0, lowestRoadY + texture.getHeight(), 0)) {
			batch.draw(texture, 0, lowestRoadY + getHeight(), getWidth(), getHeight());
		} else {
			lowestRoadY += getHeight() / 2;
		}
		
		batch.draw(texture, 0, lowestRoadY, getWidth(), getHeight());
	}
	
	public int getWidth() {
		return (int) (texture.getHeight() * scale);
	}
	
	public int getHeight() {
		return (int) (texture.getWidth() * scale);
	}
}
