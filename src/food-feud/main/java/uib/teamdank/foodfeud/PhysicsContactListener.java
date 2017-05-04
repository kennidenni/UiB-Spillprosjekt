package uib.teamdank.foodfeud;

import java.util.Objects;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class PhysicsContactListener implements ContactListener {

	private final Match match;

	private int activePlayerGroundContactCount = 0;

	public PhysicsContactListener(Match match) {
		this.match = Objects.requireNonNull(match, "match canont be null");
	}

	@Override
	public void beginContact(Contact contact) {
		updateActivePlayerGroundStatus(contact.getFixtureA(), contact.getFixtureB(), false);
	}

	@Override
	public void endContact(Contact contact) {
		updateActivePlayerGroundStatus(contact.getFixtureA(), contact.getFixtureB(), true);
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// Not necessary
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// Not necessary
	}

	/**
	 * Updates the active player's "touching the ground" status by examining the
	 * potential contact between the player and the given fixtures. Nothing will
	 * happen if none of the fixtures belong to the player.
	 * <p>
	 * Since the player's ground sensor can collide with more than fixture at
	 * the same time, a count ({@link #activePlayerGroundContactCount}) is kept
	 * to properly update the player.
	 */
	private void updateActivePlayerGroundStatus(Fixture fixtureA, Fixture fixtureB, boolean endContact) {
		Object userDataA = fixtureA.getUserData();
		Object userDataB = fixtureB.getUserData();
		if (userDataA == match.getActivePlayer() || userDataB == match.getActivePlayer()) {
			activePlayerGroundContactCount += endContact ? -1 : 1;
			assert (activePlayerGroundContactCount >= 0);
			match.getActivePlayer().setOnGround(activePlayerGroundContactCount != 0);
		}
	}

}
