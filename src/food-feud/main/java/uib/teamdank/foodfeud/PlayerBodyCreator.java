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

	private final World world;

	public PlayerBodyCreator(World world) {
		this.world = Objects.requireNonNull(world, "world cannot be null");
	}

	private void addGroundCollider(Player player, Body body) {
		CircleShape shape = new CircleShape();
		shape.setRadius(player.getWidth() / 2);
		body.createFixture(shape, 1);
	}
	
	private void addGroundDetector(Player player, Body body) {
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(5, 5, new Vector2(0f, -player.getHeight() / 2f), 0);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.isSensor = true;
		body.createFixture(fixtureDef).setUserData(player);
	}
	
	public void initializeBody(Player player) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.fixedRotation = true;
		Body body = world.createBody(bodyDef);

		addGroundCollider(player, body);
		addGroundDetector(player, body);
		player.setBody(body);
	}

}
