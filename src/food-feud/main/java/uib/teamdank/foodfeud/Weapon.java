package uib.teamdank.foodfeud;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import uib.teamdank.common.Item;

/**
 * Represents a weapon item. A weapon can be thrown (distance depends of on
 * weight and thrower) and inflicts damage on impact.
 */
public class Weapon extends Item {
	
	public enum Type {
		// light bullets like guns
		LIGHT_BALLISTIC,
		// like uzi, HK416.. etc
		BURST_BALLISTIC,
		// arching ballistics like rpg and grenades..(only exploding one)
		HEAVY_BALLISTIC,
		// shotgun etc
		SPRAY_BALLISTIC;
	}

	private final int damage;
	private final Type type;

	private float mass;
	private int amount;
	private boolean damagedPlayer = false;


	/**
	 * Creates a weapon that does no damage and weighs nothing.
	 * 
	 * @param name
	 * @param descr
	 *            weapon description
	 */
	public Weapon(String name, String descr, TextureRegion texture, int damage, float mass, int amount, Type type) {
		super(name, descr);
		setTexture(texture);
		this.damage = damage;
		this.mass = mass;
		this.amount = amount;
		this.type = type;
	}

	public void fire() {
		if (amount > 0) {
			amount--;
		}
	}
	
	public int getAmount() {
		return amount;
	}
	
	public int getDamage() {
		return damage;
	}

	public float getMass() {
		return mass;
	}
	
	public Type getType() {
		return type;
	}

	public void hitPlayer(Player player) {
		if (!damagedPlayer) {
			player.decreaseHealth(getDamage());
			this.markForRemoval();
			damagedPlayer = true;
		}
	}

}
