package uib.teamdank.common;

/**
 * For a generic controlled actor in a game (e.g. a player or an NPC).
 */
public class Actor extends GameObject {

	private final int maxHealth;
	private int currentHealth;
	private final String name;

	public Actor(){
	    this(100, 100, "Default");
    }
	public Actor (int h, String n){
	    this(h, h, n);
    }

    public Actor (int mh, int ch, String n){
	    super();
	    this.maxHealth = mh;
	    setHealth(ch);
	    this.name = n;
    }
	/**
	 * @return how much health this actor currently has (if it is {@code zero},
	 *         the actor is dead)
	 */
	public int getHealth() {
		return this.currentHealth;
	}

	/**
	 * @return the maximum health this actor can have
	 */
	public int getMaxHealth() {
		return this.maxHealth;
	}

	/**
	 * @return the name of this actor
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param amount
	 *            the amount of health to add
	 */
	public void increaseHealth(int amount) {
		setHealth(Math.min(getMaxHealth(), getHealth() + amount));
	}

	/**
	 * @param amount
	 *            the amount of health to subtract
	 */
	public void decreaseHealth(int amount) {
		setHealth(Math.max(0, getHealth() - amount));
	}

	/**
	 * Sets the health of this actor. It cannot be less than {@code zero} or
	 * larger than {@link #getMaxHealth()}.
	 * 
	 * @param health
	 *            the new health of this actor
	 */
	public void setHealth(int health) {
		if (health < 0 || health > this.maxHealth)
			throw new IllegalArgumentException("health is out of range: " + health);
		this.currentHealth = health;
	}
}
