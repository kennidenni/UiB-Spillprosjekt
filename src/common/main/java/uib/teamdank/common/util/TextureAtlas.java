package uib.teamdank.common.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * A texture atlas manages named regions on a larger tileset which can be
 * retrieved by calling {@link #getRegion(String)}.
 * <p>
 * <b>Note:</b>When an atlas is created the entire tileset image
 * is loaded into memory. Call {@link #dispose()} when done.
 */
public class TextureAtlas {

	private static class Region {
		String name;
		int x;
		int y;
		int w;
		int h;
	}
	
	public static TextureAtlas createFromJson(FileHandle handle){
		Objects.requireNonNull(handle, "file handle cannot be null");
		TextureAtlas atlas = new Gson().fromJson(handle.reader(), TextureAtlas.class);
		atlas.load();
		return atlas;
	}
	
	@SerializedName("tileset") private String tilesetFile;	
	@SerializedName("regions") private Region[] regions;
	
	private transient  Map<String, TextureRegion> textureCache = new HashMap<>();
	private transient Texture tileset;
	
	private TextureAtlas() {
		// Hide constructor
	}
	
	public void dispose() {
		tileset.dispose();
	}
	
	/**
	 * @return the texture region with the given name 
	 */
	public TextureRegion getRegion(String name) {
		if (tileset == null) {
			throw new IllegalStateException("texture atlas has not loaded the tileset yet, call #load()");
		}
		if (!textureCache.containsKey(name)) {
			throw new IllegalArgumentException("no such region: " + name);
		}
		return textureCache.get(name);
	}
	
	private void load() {
		FileHandle sheetFile = Gdx.files.internal(tilesetFile);
		if (sheetFile == null) {
			throw new IllegalArgumentException("no such tileset: " + tilesetFile);
		}
		this.tileset = new Texture(sheetFile);
		for (Region region : regions) {
			textureCache.put(region.name, new TextureRegion(tileset, region.x, region.y, region.w, region.h));
		}
	}
	
}
