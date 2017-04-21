package uib.teamdank.cargame.util;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import uib.teamdank.common.GameObject;
import uib.teamdank.common.gui.Layer;
import uib.teamdank.common.util.Generator;

public class StructureSpawner implements Generator<TextureRegion> {
	
	private static final int SPACE_BETWEEN_STRUCTURES = 64;
	
	private static final Random random = new Random();
	
	private final Layer layer;
	private final OrthographicCamera camera;
	private final boolean flipped;
	private final TextureRegion[] structureTextures;
	
	private final Array<GameObject> structures = new Array<>();
	
	public StructureSpawner(Layer layer, OrthographicCamera camera, boolean flipped, TextureRegion[] textures) {
		this.layer = Objects.requireNonNull(layer, "layer cannot be null");
		this.camera = Objects.requireNonNull(camera, "camera cannot be null");
		this.flipped = flipped;
		Objects.requireNonNull(textures, "structure textures cannot be null");
		if (textures.length == 0) {
			throw new IllegalArgumentException("there must be at least one structure texture");
		}
		this.structureTextures = Arrays.copyOf(textures, textures.length);
	}
	
	private GameObject addNewStructure() {
		GameObject structure = new GameObject();
		structure.getPosisiton().y = camera.position.y + camera.viewportHeight / 2 + SPACE_BETWEEN_STRUCTURES;
		structure.setTexture(generate(random));
		structure.setFlipHorizontally(flipped);
		structures.add(structure);
		layer.addGameObject(structure);
		return structure;
	}
	
	public void deleteOldStructures() {
		GameObject firstStructure = structures.get(0);
		float firstY = firstStructure.getPosisiton().y;
		float firstHeight = firstStructure.getHeight();
		if (firstY + firstHeight < camera.position.y - camera.viewportHeight / 2) {
			structures.removeIndex(0);
			firstStructure.markForRemoval();
		}
	}
	
	@Override
	public TextureRegion generate(Random random) {
		return structureTextures[random.nextInt(structureTextures.length)];
	}
	
	public void spawnNewStructures() {
		if (structures.size == 0) {
			addNewStructure();
		}

		GameObject prevStructure = structures.get(structures.size - 1);
		float prevY = prevStructure.getPosisiton().y;
		float prevHeight = prevStructure.getHeight();
		if (prevY + prevHeight < camera.position.y + camera.viewportHeight / 2) {
			addNewStructure();
		}
	}
	
	public void updateHorizontalPosition(int horizontalPosition) {
		for (GameObject structure : structures) {
			Vector2 pos = structure.getPosisiton();
			pos.x = horizontalPosition - structure.getWidth();
			if (flipped) {
				pos.x += structure.getWidth();
			}
		}
	}
	
}
