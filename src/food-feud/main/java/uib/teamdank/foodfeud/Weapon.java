package uib.teamdank.foodfeud;

import uib.teamdank.common.Item;

/**
 * Represents a weapon item. A weapon can be thrown (distance depends of on
 * weight and thrower) and inflicts damage on impact.
 */
public class Weapon extends Item {
	
	public enum weaponProjectile{
		Reg_Ballistic,
		Burst_Ballistic,
		//rpg etc..
		Heavy_Ballistic,
		Spray_Ballistic;
		
	}
	
	private weaponProjectile proj;
	
	/**
	 * Creates a weapon that does no damage and weighs nothing.
	 * @param name
	 * @param descr weapon description
	 */
    public Weapon(String name, String descr, weaponProjectile p) {
        super(name, descr);
    }
	
	public weaponProjectile getProjectileType(){
		return proj;
	}
	
}