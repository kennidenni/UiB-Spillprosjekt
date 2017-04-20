package uib.teamdank.cargame.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScrollingImage {
	private final OrthographicCamera camera;
	private final Texture texture;
	private final float scale;
	
	private int lowestRoadY;
	
	public ScrollingImage(OrthographicCamera camera, Texture texture, float scale) {
		this.camera = camera;
		this.texture = texture;
		this.scale = scale;
	}
	
	public void render(SpriteBatch batch, float delta) {
		final int width = Gdx.graphics.getWidth();
		final int height = Gdx.graphics.getHeight();
		
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
