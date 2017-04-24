package uib.teamdank.foodfeud;

import uib.teamdank.common.Item;

/**
 * Represents a weapon item. A weapon can be thrown (distance depends of on
 * weight and thrower) and inflicts damage on impact.
 */
public class Weapon extends Item {

	private double damage;
	private double weight;
	
    public Weapon(String n, String d, double dmg, double wgt) {
        super(n, d);
        this.damage = dmg;
        this.weight = wgt;
    }

    /**
	 * @return amount of damage this weapon inflicts
	 */
	public double getDamage() {
		return this.damage;
	}

	/**
	 * @return the weight of this weapon
	 */
	public double getWeight() {
		return this.weight;
	}
	
}
