package uib.teamdank.foodfeud;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class PhysicsContactListener implements ContactListener {

	private final Match match;

	private final Map<String, Integer> activePlayerGroundContactCounts = new HashMap<>();

	public PhysicsContactListener(Match match) {
		this.match = Objects.requireNonNull(match, "match canont be null");
		match.getPlayers().forEach(player -> {
			activePlayerGroundContactCounts.put(player.getName(), 0);
		});
	}

	@Override
	public void beginContact(Contact contact) {
		updatePlayerGroundStatus(contact.getFixtureA(), contact.getFixtureB(), false);
	}

	@Override
	public void endContact(Contact contact) {
		updatePlayerGroundStatus(contact.getFixtureA(), contact.getFixtureB(), true);
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
	 * Updates the every player's "touching the ground" status by examining the
	 * potential contact between the player and the given fixtures. Nothing will
	 * happen if none of the fixtures belong to the player.
	 * <p>
	 * Since the player's ground sensor can collide with more than fixture at
	 * the same time, a count ({@link #activePlayerGroundContactCount}) is kept
	 * to properly update the player.
	 */
	private void updatePlayerGroundStatus(Fixture fixtureA, Fixture fixtureB, boolean endContact) {
		final Object userDataA = fixtureA.getUserData();
		final Object userDataB = fixtureB.getUserData();
		if (!(userDataA instanceof Player) && !(userDataB instanceof Player)) {
			return;
		}
		Player player = (Player) (userDataA instanceof Player ? userDataA : userDataB);
		final Map<String, Integer> counts = activePlayerGroundContactCounts;
		counts.put(player.getName(), counts.get(player.getName()) + (endContact ? -1 : 1));
		player.setOnGround(counts.get(player.getName()) != 0);
	}

}
