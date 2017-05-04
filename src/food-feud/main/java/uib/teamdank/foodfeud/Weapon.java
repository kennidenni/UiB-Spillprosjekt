package uib.teamdank.foodfeud;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import uib.teamdank.common.Item;

/**
 * Represents a weapon item. A weapon can be thrown (distance depends of on
 * weight and thrower) and inflicts damage on impact.
 */
public class Weapon extends Item {

	private final int dmg, weight;
	private final weaponProjectile proj;

	private boolean damagedPlayer = false;

	public enum weaponProjectile {
		// light bullets like guns
		LIGHT_BALLISTIC,
		// like uzi, HK416.. etc
		BURST_BALLISTIC,
		// arching ballistics like rpg and grenades..(only exploding one)
		HEAVY_BALLISTIC,
		// shotgun etc
		SPRAY_BALLISTIC;

	}

	/**
	 * Creates a weapon that does no damage and weighs nothing.
	 * 
	 * @param name
	 * @param descr
	 *            weapon description
	 */
	public Weapon(String name, String descr, int d, int w, TextureRegion texture, weaponProjectile p) {
		super(name, descr);
		this.dmg = d;
		this.weight = w;
		setTexture(texture);
		proj = p;
	}

	public int getDamage() {
		return dmg;
	}

	public double getWeight() {
		return weight;
	}

	public weaponProjectile getProjectileType() {
		return proj;
	}

	public void hitPlayer(Player player) {
		if (!damagedPlayer) {
			player.decreaseHealth(getDamage());
			this.markForRemoval();
			damagedPlayer = true;
		}
	}

}
