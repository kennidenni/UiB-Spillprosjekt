package uib.teamdank.cargame.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import uib.teamdank.cargame.Player;
import uib.teamdank.cargame.util.LoopingBackground;
import uib.teamdank.cargame.util.StructureSpawner;
import uib.teamdank.common.gui.Layer;
import uib.teamdank.common.util.TextureAtlas;

public class BackgroundLayer extends Layer {

	private final OrthographicCamera playerCamera;
	private final OrthographicCamera screenCamera;
	private final Player player;
	
	private final Texture backgroundTexture;
	private final LoopingBackground scrollingRoad;
	private final StructureSpawner[] structureSpawners;
	
	public BackgroundLayer(OrthographicCamera playerCamera, OrthographicCamera screenCamera, Player player) {
		super(false);
		
		this.playerCamera = playerCamera;
		this.screenCamera = screenCamera;
		this.player = player;
		
		this.backgroundTexture = new Texture(Gdx.files.internal("Images/background.png"));
		this.scrollingRoad = new LoopingBackground(playerCamera, new Texture(Gdx.files.internal("Images/road.png")), .5f);
		
		TextureAtlas structuresAtlas = TextureAtlas.createFromJson(Gdx.files.internal("Images/structures.json")); 
		TextureRegion[] structureTextures = structuresAtlas.getAllRegions();
		this.structureSpawners = new StructureSpawner[] {
				new StructureSpawner(this, playerCamera, false, structureTextures),
				new StructureSpawner(this, playerCamera, true, structureTextures)
		};
	}
	
	@Override
	protected void preRender(SpriteBatch batch, float delta) {
		final int screenWidth = Gdx.graphics.getWidth();
		final int screenHeight = Gdx.graphics.getHeight();
		
		batch.setProjectionMatrix(screenCamera.combined);
		batch.draw(backgroundTexture, -screenWidth / 2f, -screenHeight / 2f, screenWidth, screenHeight);
		batch.setProjectionMatrix(playerCamera.combined);
		scrollingRoad.render(batch);
		for (StructureSpawner spawner : structureSpawners) {
			spawner.spawnNewStructures();
			spawner.deleteOldStructures();
		}
		structureSpawners[0].updateHorizontalPosition(-(scrollingRoad.getWidth() - player.getWidth()) / 2);
		structureSpawners[1].updateHorizontalPosition((scrollingRoad.getWidth() + player.getWidth()) / 2);
		
	}
	
}
