package uib.teamdank.common;

/**
 * For a generic controlled actor in a game (e.g. a player or an NPC).
 */
public class Actor extends GameObject {
	/**
	 * @param amount
	 *            the amount of health to subtract
	 */
	public void decreaseHealth(int amount) {
		// TODO Auto-generated method stub
	}

	/**
	 * @return how much health this actor currently has (if it is {@code zero},
	 *         the actor is dead)
	 */
	public int getHealth() {
		// TODO Auto-generated method stub
		return -1;
	}

	/**
	 * @return the maximum health this actor can have
	 */
	public int getMaxHealth() {
		// TODO Auto-generated method stub
		return -1;
	}

	/**
	 * @return the name of this actor
	 */
	public String getName() {
		return null;
	}

	/**
	 * @param amount
	 *            the amount of health to add
	 */
	public void increaseHealth(int amount) {
		// TODO Auto-generated method stub
	}

	/**
	 * Sets the health of this actor. It cannot be less than {@code zero} or
	 * larger than {@link #getMaxHealth()}.
	 * 
	 * @param health
	 *            the new health of this actor
	 */
	public void setHealth(int health) {
		// TODO Auto-generated method stub
	}
}
