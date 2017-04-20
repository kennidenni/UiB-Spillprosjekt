package uib.teamdank.cargame.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScrollingImage {
	private final OrthographicCamera camera;
	private final Texture texture;
	
	public ScrollingImage(OrthographicCamera camera, Texture texture) {
		this.camera = camera;
		this.texture = texture;
	}
	
	public void render(SpriteBatch batch, float delta) {
		final int width = Gdx.graphics.getWidth();
		final int height = Gdx.graphics.getHeight();
		
		batch.draw(texture, 0, 0);
	}

	public void update(float delta) {
		
	}
}
