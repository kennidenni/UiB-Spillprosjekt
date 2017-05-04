package uib.teamdank.foodfeud;

import java.util.Objects;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class PhysicsContactListener implements ContactListener {

	private final Level level;
	private final Match match;

	public PhysicsContactListener(Level level, Match match) {
		this.level = Objects.requireNonNull(level, "level cannot be null");
		this.match = Objects.requireNonNull(match, "match canont be null");
	}

	@Override
	public void beginContact(Contact contact) {
		updateActivePlayerGroundStatus(contact.getFixtureA(), contact.getFixtureB(), true);
	}

	@Override
	public void endContact(Contact contact) {
		updateActivePlayerGroundStatus(contact.getFixtureA(), contact.getFixtureB(), false);
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// Not necessary
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// Not necessary
	}

	private void updateActivePlayerGroundStatus(Fixture fixtureA, Fixture fixtureB, boolean onGround) {
		Object userDataA = fixtureA.getUserData();
		Object userDataB = fixtureB.getUserData();
		if (userDataA == match.getActivePlayer() || userDataB == match.getActivePlayer()) {
			match.getActivePlayer().setOnGround(onGround);
		}
	}

}
