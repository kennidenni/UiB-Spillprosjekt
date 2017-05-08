package uib.teamdank.common.util;

import java.util.Map;
import java.util.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class Animation {

	public static Animation createFromJson(FileHandle fileHandle) {
		return createFromJson(fileHandle, null);
	}

	/**
	 * Creates a new animation from the specification in the given JSON-file.
	 * <p>
	 * If the given map of texture atlases is not {@code null}, the animation
	 * will look for a texture atlas in there instead of creating and loading
	 * a new one. However, if it doesn't find it, the atlas will be loaded
	 * and added to the map.
	 * 
	 */
	public static Animation createFromJson(FileHandle fileHandle, Map<String, TextureAtlas> atlases) {
		Objects.requireNonNull(fileHandle, "file handle cannot be null");
		Animation animation = new Gson().fromJson(fileHandle.reader(), Animation.class);
		if (atlases == null) {
			animation.initialize(null);
		} else {
			if (!atlases.containsKey(animation.textureAtlasFile)) {
				animation.initialize(null);
				atlases.put(fileHandle.path(), animation.atlas);
			} else {
				animation.initialize(atlases.get(animation.textureAtlasFile));
			}
		}
		
		return animation;
	}
	
	/**
	 * Creates a single-framed animation from the specified region on
	 * the given texture atlas.
	 */
	public static Animation createSingleFramed(TextureRegion texture) {
		Objects.requireNonNull(texture, "texture cannot be null");
		Animation anim = new Animation();
		anim.textureAtlasFile = null;
		anim.speed = 0;
		anim.atlasRegionFrames = new String[] { };
		anim.currentTexture = texture;
		anim.averageWidth = texture.getRegionWidth();
		anim.averageHeight = texture.getRegionHeight();
		return anim;
	}

	@SerializedName("atlas")
	private String textureAtlasFile;
	@SerializedName("speed")
	private float speed;
	@SerializedName("frames")
	private String[] atlasRegionFrames;

	private transient TextureAtlas atlas;
	private transient TextureRegion currentTexture;
	private transient float time;
	private transient float averageWidth;
	private transient float averageHeight;

	private Animation() {
		// Hide constructor
	}

	private void initialize(TextureAtlas atlas) {
		if (atlas == null) {
			this.atlas = TextureAtlas.createFromJson(Gdx.files.internal(textureAtlasFile));
		} else {
			this.atlas = atlas;
		}
		
		averageWidth = 0;
		averageHeight = 0;
		for (String region : atlasRegionFrames) {
			averageWidth += atlas.getRegion(region).getRegionWidth();
			averageHeight += atlas.getRegion(region).getRegionHeight();
		}
		averageWidth /= atlasRegionFrames.length;
		averageHeight /= atlasRegionFrames.length;
	}

	public float getAverageHeight() {
		return averageHeight;
	}
	
	public float getAverageWidth() {
		return averageWidth;
	}
	
	public Vector2 getUserPoint() {
		if (atlas != null) {
			return atlas.getUserPoint(atlasRegionFrames[getCurrentIndex()]);
		}
		return Vector2.Zero;
	}
	
	public int getCurrentIndex() {
		return (int) (time % atlasRegionFrames.length);
	}
	
	/**
	 * @return the texture atlas, or {@code null} if this
	 * animation does not use one
	 */
	public TextureAtlas getTextureAtlas() {
		return atlas;
	}
	
	public TextureRegion getTexture() {
		if (atlas != null) {
			currentTexture = atlas.getRegion(atlasRegionFrames[getCurrentIndex()]);
		}
		return currentTexture; 
	}

	public void update(float delta) {
		if (speed >= 0) {
			time += delta * speed;
		}
	}

}
