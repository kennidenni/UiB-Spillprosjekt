package uib.teamdank.cargame.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import uib.teamdank.cargame.util.ScrollingImage;
import uib.teamdank.common.gui.Layer;

public class BackgroundLayer extends Layer {

	private final Texture backgroundTexture;
	private final ScrollingImage scrollingRoad;
	
	public BackgroundLayer(OrthographicCamera camera) {
		super(false);
		
		this.backgroundTexture = new Texture(Gdx.files.internal("Images/background.png"));
		this.scrollingRoad = new ScrollingImage(camera, new Texture(Gdx.files.internal("Images/road.png")), .5f);
	}
	
	@Override
	protected void preRender(SpriteBatch batch, float delta) {
		final int screenWidth = Gdx.graphics.getWidth();
		final int screenHeight = Gdx.graphics.getHeight();
		
		batch.draw(backgroundTexture, 0, 0, screenWidth, screenHeight);
		scrollingRoad.update(delta);
		scrollingRoad.render(batch, delta);
	}
	
}
