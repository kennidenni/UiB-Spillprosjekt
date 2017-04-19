package uib.teamdank.cargame;

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
		batch.draw(texture, 0, 0);
	}

	public void update(float delta) {
		
	}
}
