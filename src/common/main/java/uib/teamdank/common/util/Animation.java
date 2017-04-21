package uib.teamdank.common.util;

import java.util.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class Animation {
	
	public static Animation createFromJson(FileHandle fileHandle) {
		Objects.requireNonNull(fileHandle, "file handle cannot be null");
		Animation animation = new Gson().fromJson(fileHandle.reader(), Animation.class);
		animation.load();
		return animation;
	}
	
	@SerializedName("atlas") private String textureAtlasFile;
	@SerializedName("speed") private float speed;
	@SerializedName("frames") private String[] atlasRegionFrames;

	private transient TextureAtlas atlas;
	private transient float time;
	
	private Animation() {
		// Hide constructor
	}
	
	private void load() {
		this.atlas = TextureAtlas.createFromJson(Gdx.files.internal(textureAtlasFile));
	}
	
	public TextureRegion getTexture() {
		final int currentIndex = (int) (time % atlasRegionFrames.length);
		return atlas.getRegion(atlasRegionFrames[currentIndex]);
	}
	
	public void update(float delta) {
		time += delta * speed;
	}
	
}
