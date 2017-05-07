package uib.teamdank.foodfeud;

import java.util.Objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class PlayerBodyCreator {

	private static final float GROUND_DETECTOR_HEIGHT = 6;
	
	private final World world;

	public PlayerBodyCreator(World world) {
		this.world = Objects.requireNonNull(world, "world cannot be null");
	}

	private void addGroundCollider(Player player, Body body) {
		CircleShape shape = new CircleShape();
		final float radius = player.getWidth() / 2f;
		shape.setRadius(radius);
		shape.setPosition(new Vector2(0, radius + 0));
		body.createFixture(shape, 1);
		shape.dispose();
	}
	
	private void addGroundDetector(Player player, Body body) {
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(player.getWidth() / 3, GROUND_DETECTOR_HEIGHT, new Vector2(0f, 0), 0);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.isSensor = true;
		body.createFixture(fixtureDef).setUserData(player);
		shape.dispose();
	}
	
	public void initializeBody(Player player) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.fixedRotation = true;
		bodyDef.linearDamping = .01f;
		Body body = world.createBody(bodyDef);

		addGroundCollider(player, body);
		addGroundDetector(player, body);
		player.setBody(body);
	}

}
