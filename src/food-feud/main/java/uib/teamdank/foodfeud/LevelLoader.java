package uib.teamdank.foodfeud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ShortArray;
import com.google.gson.Gson;

public class LevelLoader {
	
	private static class LevelModel {
		String name;
		String background;
		String foreground;
		float gravity;
		float[] ground;
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
		
		// TODO Dele opp alle punktene i level.ground til trekanter
		/*
		EarClippingTriangulator earClipper = new EarClippingTriangulator();
		ShortArray vertixIndices = earClipper.computeTriangles(level.ground);
		
		for (int i = 0; i < vertixIndices.size; i += 6) {
			float[] vertices = {
				level.ground[vertixIndices.get(i + 0)],
				level.ground[vertixIndices.get(i + 1)],
				level.ground[vertixIndices.get(i + 2)],
				level.ground[vertixIndices.get(i + 3)],
				level.ground[vertixIndices.get(i + 4)],
				level.ground[vertixIndices.get(i + 5)],
			};
			
			PolygonShape shape = new PolygonShape();
			shape.set(vertices);
			ground.createFixture(shape, 1);
		}
		*/
		
	}
	
}
