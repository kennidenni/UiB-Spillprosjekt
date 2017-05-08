package uib.teamdank.foodfeud;

import java.util.Objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

public class PlayerBodyCreator {

	private static final float GROUND_DETECTOR_HEIGHT = 4;
	
	// These constants set the size of the colliders for
	// the players, which makes movement unified and not
	// different on different sized skins.
	private static final float BODY_WIDTH = 96f; 
	private static final float BODY_HEIGHT = 128f;
	private static final float FEET_RADIUS = 48f;
	
	private final World world;

	public PlayerBodyCreator(World world) {
		this.world = Objects.requireNonNull(world, "world cannot be null");
	}
	
	private void addBodyCollider(Player player, Body body) {
		PolygonShape shape = new PolygonShape();
		shape.setAsBox((BODY_WIDTH / 2f) * player.getScale(),
						(BODY_HEIGHT / 2f) * player.getScale(),
						new Vector2(0,
								(BODY_HEIGHT / 2f) * player.getScale()
								+ (FEET_RADIUS * 2f) * player.getScale()),
						0);
		body.createFixture(createPlayerFixtureDef(shape));
		shape.dispose();
	}
	
	private void addFeetCollider(Player player, Body body) {
		CircleShape shape = new CircleShape();
		final float radius = FEET_RADIUS * player.getScale();
		shape.setRadius(radius);
		shape.setPosition(new Vector2(0, radius));
		body.createFixture(createPlayerFixtureDef(shape));
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
	
	private FixtureDef createPlayerFixtureDef(Shape shape) {
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.density = 1f;
		fixtureDef.friction = 0.75f;
		fixtureDef.restitution = 0.1f;
		fixtureDef.shape = shape;
		return fixtureDef;
	}
	
	public void initializeBody(Player player) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.fixedRotation = true;
		bodyDef.linearDamping = .01f;
		Body body = world.createBody(bodyDef);

		addGroundDetector(player, body);
		addFeetCollider(player, body);
		addBodyCollider(player, body);
		player.setBody(body);
	}

}
