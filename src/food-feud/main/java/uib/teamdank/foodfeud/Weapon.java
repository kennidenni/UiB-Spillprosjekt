package uib.teamdank.foodfeud;

import uib.teamdank.common.Item;

/**
 * Represents a weapon item. A weapon can be thrown (distance depends of on
 * weight and thrower) and inflicts damage on impact.
 */
public class Weapon extends Item {

	private double dmg, weight;
	
	/**
	 * 
	 * @param name
	 * @param descr weapon description
	 * @param dmg damage
	 * @param weight
	 */
	public Weapon(String name, String descr, double dmg, double weight) {
		this(name, descr);
		this.dmg = dmg;
		this.weight = weight;
	}
	
	/**
	 * Creates a weapon that does no damage and weighs nothing.
	 * @param name
	 * @param descr weapon description
	 */
    public Weapon(String name, String descr) {
        super(name, descr);
    }

    /**
	 * @return amount of damage this weapon inflicts
	 */
	public double getDamage() {
		return dmg;
	}

	/**
	 * @return the weight of this weapon
	 */
	public double getWeight() {
		return weight;
	}
	
}