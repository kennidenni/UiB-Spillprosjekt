package uib.teamdank.common.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.google.gson.Gson;

public class TextureAtlas {

	public static void main(String[] args){
//		Gdx.files.external("/Desktop/atlas.json")
		TextureAtlas a = TextureAtlas.loadAtlas(Gdx.files.internal("/atlas.json"));
		System.out.println(TextureAtlas.class.getResourceAsStream("atlas.json"));
	}
	
	private Region[] regions;
	
	private static class Region {
		String name;
		int x;
		int y;
		int width;
		int height;
	}
	
	public static TextureAtlas loadAtlas(FileHandle handle){
		Gson gson = new Gson();
		
		TextureAtlas atlas = gson.fromJson(handle.reader(), TextureAtlas.class);
		return atlas;
	}
}
