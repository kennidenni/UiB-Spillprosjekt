package uib.teamdank.foodfeud;

import com.badlogic.gdx.physics.box2d.Body;

@FunctionalInterface
public interface PhysicsSimulated {
	public Body getBody();
}
