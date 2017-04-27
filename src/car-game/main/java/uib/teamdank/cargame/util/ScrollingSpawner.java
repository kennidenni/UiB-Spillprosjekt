package uib.teamdank.cargame.util;

import java.util.Objects;
import java.util.Random;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Array;

import uib.teamdank.common.GameObject;
import uib.teamdank.common.gui.Layer;
import uib.teamdank.common.util.Generator;

public class ScrollingSpawner implements Generator<GameObject> {
	
	private static final Random random = new Random();
	
	private final Layer layer;
	private final OrthographicCamera camera;
	private final Generator<GameObject> generator;
	
	private boolean flipTexture;
	private int extraVerticalSpaceBetweenSpawns;
	private int minHorizontalPosition;
	private int maxHorizontalPosition;
	
	private float timeBetweenSpawns;
	private float chanceOfSpawn;
	
	private final Array<GameObject> spawns = new Array<>();
	private float timeSinceSpawn = 0;
	
	public ScrollingSpawner(Layer layer, OrthographicCamera camera, Generator<GameObject> generator) {
		this.layer = Objects.requireNonNull(layer, "layer cannot be null");
		this.camera = Objects.requireNonNull(camera, "camera cannot be null");
		this.generator = Objects.requireNonNull(generator, "generator cannot be null");
		
		setFlipTexture(false);
		setExtraVerticalSpaceBetweenSpawns(300);
		setTimeBetweenSpawns(0f);
		setChanceOfSpawn(1f);
	}
	
	private GameObject createNewSpawn() {
		GameObject spawn = generator.generate(random);
		spawn.getPosisiton().y = camera.position.y + camera.viewportHeight / 2 + extraVerticalSpaceBetweenSpawns;
		spawn.getPosisiton().x = getNewHorizontalPosition(spawn.getWidth());
		spawn.setFlipHorizontally(flipTexture);
		spawns.add(spawn);
		layer.addGameObject(spawn);
		return spawn;
	}
	
	public void deleteOldStructures() {
		GameObject firstSpawn = spawns.get(0);
		if (firstSpawn.isMarkedForRemoval()) {
			// Object has already been removed elsewhere
			spawns.removeIndex(0);
		} else {
			float firstY = firstSpawn.getPosisiton().y;
			float firstHeight = firstSpawn.getHeight();
			if (firstY + firstHeight < camera.position.y - camera.viewportHeight / 2) {
				spawns.removeIndex(0);
				firstSpawn.markForRemoval();
			}
		}
	}
	
	@Override
	public GameObject generate(Random random) {
		return generator.generate(random);
	}
	
	private int getNewHorizontalPosition(int width) {
		final int min = minHorizontalPosition;
		final int max = maxHorizontalPosition - width;
		
		int position;
		if (min == max) {
			position = min;
		} else {
			position = random.nextInt(max - min) + min;
		}
		if (flipTexture) {
			position -= width;
		}
		return position;
	}
	
	private boolean previousSpawnHasBeenVisible() {
		if (spawns.size == 0) {
			return false;
		}
		final GameObject prevSpawn = spawns.get(spawns.size - 1);
		final float prevY = prevSpawn.getPosisiton().y;
		final float prevHeight = prevSpawn.getHeight();
		return prevY + prevHeight < camera.position.y + camera.viewportHeight / 2;
	}
	
	public void setChanceOfSpawn(float chanceOfSpawn) {
		this.chanceOfSpawn = chanceOfSpawn;
	}
	
	public void setExtraVerticalSpaceBetweenSpawns(int extraVerticalSpaceBetweenSpawns) {
		this.extraVerticalSpaceBetweenSpawns = extraVerticalSpaceBetweenSpawns;
	}
	
	public void setFlipTexture(boolean flipTexture) {
		this.flipTexture = flipTexture;
	}
	
	public void setHorizontalPositionRange(int min, int max) {
		this.minHorizontalPosition = min;
		this.maxHorizontalPosition = max;
	}
	
	public void setTimeBetweenSpawns(float timeBetweenSpawns) {
		this.timeBetweenSpawns = timeBetweenSpawns;
	}
	
	public void spawnNewGameObjects(float delta) {
		timeSinceSpawn += delta;
		if (timeSinceSpawn >= timeBetweenSpawns
							&& (spawns.size == 0 || previousSpawnHasBeenVisible())
							&& random.nextFloat() < chanceOfSpawn) {
			layer.addGameObject(createNewSpawn());
			timeSinceSpawn -= timeBetweenSpawns;
		}
	}
}
