package uib.teamdank.foodfeud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.google.gson.Gson;

public class LevelLoader {
	
	private static class LevelModel {
		String name;
		String background;
		String foreground;
		float gravity;
		float[][] ground;
	}
	
	private LevelLoader() {
		// Hide constructor
	}
	
	public static Level createFromJson(FileHandle handle) {
		LevelModel model = new Gson().fromJson(handle.reader("UTF-8"), LevelModel.class);
		
		Texture background = new Texture(model.background);
		Texture foreground = new Texture(model.foreground);
		
		World world = new World(new Vector2(0, model.gravity), true);
		createGroundFixtures(world, model);
		
		return new Level(model.name, world, background, foreground);
	}
	
	private static void createGroundFixtures(World world, LevelModel level) {
		BodyDef groundDef = new BodyDef();
		groundDef.type = BodyType.StaticBody;
		Body ground = world.createBody(groundDef);

		float[] groundHighs = new float[level.ground.length];
		float[] groundLows = new float[level.ground.length];
		for (int i = 0; i < level.ground.length; i++) {
			groundLows[i] = level.ground[i][1];
			for (int j = 1; j < level.ground[i].length; j += 2) {
				groundHighs[i] = Math.max(level.ground[i][j], groundHighs[i]);
				groundLows[i] = Math.min(level.ground[i][j], groundLows[i]);
			}
		}
		
		for (int i = 0; i < level.ground.length; i++) {
			for (int j = 0; j < level.ground[i].length; j++) {
				if (j % 2 == 1) {
					level.ground[i][j] = Gdx.graphics.getHeight()
											- level.ground[i][j]
											+ (groundHighs[i] - groundLows[i]);
				}
			}

			PolygonShape shape = new PolygonShape();
			shape.set(level.ground[i]);
			ground.createFixture(shape, 1);
			shape.dispose();
		}
		
	}
	
}
