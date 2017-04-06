package uib.teamdank.foodfeud;

import uib.teamdank.common.Item;

/**
 * Represents a weapon item. A weapon can be thrown (distance depends of on
 * weight and thrower) and inflicts damage on impact.
 */
public class Weapon extends Item {

    public Weapon(String n, String d) {
        super(n, d);
    }

    /**
	 * @return amount of damage this weapon inflicts
	 */
	public double getDamage() {
		// TODO Auto-generated method stub
		return -1;
	}

	/**
	 * @return the weight of this weapon
	 */
	public double getWeight() {
		// TODO Auto-generated method stub
		return -1;
	}
	
}
