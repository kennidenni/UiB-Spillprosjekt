package uib.teamdank.cargame.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScrollingImage {
	private final OrthographicCamera camera;
	private final Texture texture;
	private final float scale;
	
	public ScrollingImage(OrthographicCamera camera, Texture texture, float scale) {
		this.camera = camera;
		this.texture = texture;
		this.scale = scale;
	}
	
	public void render(SpriteBatch batch, float delta) {
		final int width = Gdx.graphics.getWidth();
		final int height = Gdx.graphics.getHeight();
		
		batch.draw(texture, 0, 0, (int) (width * scale), (int) (height * scale));
	}

	public void update(float delta) {
		// Temporarily empty
	}
}
