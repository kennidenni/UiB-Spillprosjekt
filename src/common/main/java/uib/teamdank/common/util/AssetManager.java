package uib.teamdank.common.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Disposable;

public class AssetManager implements Disposable {
	
	private final Map<String, TextureAtlas> atlases = new HashMap<>();
	private final Map<String, Animation> animations = new HashMap<>();

	public Animation getAnimation(String fileName) {
		Objects.requireNonNull(fileName);
		
		// Load animation and cache it
		if (!animations.containsKey(fileName)) {
			Animation anim = Animation.createFromJson(Gdx.files.internal(fileName), atlases);
			animations.put(fileName, anim);
		}
		
		return animations.get(fileName);
	}
	
	public TextureAtlas getAtlas(String fileName) {
		Objects.requireNonNull(fileName);
		
		// Load atlas and cache it
		if (!atlases.containsKey(fileName)) {
			TextureAtlas atlas = TextureAtlas.createFromJson(Gdx.files.internal(fileName));
			atlases.put(fileName, atlas);
		}
		
		return atlases.get(fileName);
	}
	
	public int getLoadedAnimationCount() {
		return animations.size();
	}
	
	public int getLoadedAtlasCount() {
		return atlases.size();
	}
	
	@Override
	public void dispose() {
		atlases.forEach((file, atlas) -> atlas.dispose());
	}
}
