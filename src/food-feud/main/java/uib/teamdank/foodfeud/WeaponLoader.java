package uib.teamdank.foodfeud;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.google.gson.Gson;

import uib.teamdank.common.util.AssetManager;

public class WeaponLoader {
	
	private static class WeaponModel {
		String name;
		String description;
		String texture;
		int calories;
		float mass;
		int amount;
		Weapon.Type type;
	}
	
	private static WeaponModel[] cachedModels;
	
	public static Weapon[] fromJson(AssetManager assets, FileHandle file) {
		if (cachedModels == null) {
			cachedModels = new Gson().fromJson(file.reader(), WeaponModel[].class);
		}
		Weapon[] weapons = new Weapon[cachedModels.length];
		for (int i = 0; i < cachedModels.length; i++) {
			final WeaponModel w = cachedModels[i];
			final TextureRegion tex = assets.getAtlas("Images/food_sheet.json").getRegion(w.texture);
			weapons[i] = new Weapon(w.name, w.description, tex, (int) (w.calories * w.mass), w.mass,w.type);
		}
		return weapons;
	}
	
	private WeaponLoader() {
		// Hide constructor
	}
	
}
