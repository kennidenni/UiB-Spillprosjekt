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
	private int lowestY;
	
	public LoopingBackground(OrthographicCamera camera, Texture texture, float scale) {
		this.camera = camera;
		this.texture = texture;
		this.scale = scale;
		
		this.lowestY = -30;
	}
	
	public void updateHorizontalPosition(int x) {
		this.horizontalPosition = x;
	}
	
	public void render(SpriteBatch batch) {
		if (lowestY + getHeight() < camera.position.y - camera.viewportHeight / 2) {
			lowestY += getHeight();
		}
		for (int y = lowestY; y < camera.position.y + camera.viewportHeight / 2; y += getHeight()) {
			batch.draw(texture, horizontalPosition, y, getWidth(), getHeight());
		}
		
	}
	
	public int getWidth() {
		return (int) (texture.getWidth() * scale);
	}
	
	private int getHeight() {
		// Method is private because the background loops
		// vertically, which makes the height obsolete
		return (int) (texture.getHeight() * scale);
	}
}
