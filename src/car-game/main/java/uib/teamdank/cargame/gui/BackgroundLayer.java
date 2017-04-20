package uib.teamdank.cargame.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import uib.teamdank.cargame.util.LoopingBackground;
import uib.teamdank.common.gui.Layer;

public class BackgroundLayer extends Layer {

	private final OrthographicCamera playerCamera;
	private final OrthographicCamera screenCamera;
	
	private final Texture backgroundTexture;
	private final LoopingBackground scrollingRoad;
	
	public BackgroundLayer(OrthographicCamera playerCamera, OrthographicCamera screenCamera) {
		super(false);
		
		this.playerCamera = playerCamera;
		this.screenCamera = screenCamera;
		
		this.backgroundTexture = new Texture(Gdx.files.internal("Images/background.png"));
		this.scrollingRoad = new LoopingBackground(playerCamera, new Texture(Gdx.files.internal("Images/road.png")), .5f);
	}
	
	@Override
	protected void preRender(SpriteBatch batch, float delta) {
		final int screenWidth = Gdx.graphics.getWidth();
		final int screenHeight = Gdx.graphics.getHeight();
		
		batch.setProjectionMatrix(screenCamera.combined);
		batch.draw(backgroundTexture, -screenWidth / 2f, -screenHeight / 2f, screenWidth, screenHeight);
		batch.setProjectionMatrix(playerCamera.combined);
		scrollingRoad.render(batch);
	}
	
}
