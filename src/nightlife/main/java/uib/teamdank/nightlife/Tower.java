package uib.teamdank.nightlife;

import uib.teamdank.common.Item;
import uib.teamdank.common.Upgrade;
import uib.teamdank.common.Upgradeable;

/**
 * A player-owned tower.
 */
public class Tower extends Item implements Upgradeable {
	
	@Override
	public void applyUpgrade(Upgrade upgrade) {
		// TODO Auto-generated method stub
	}

	/**
	 * @return amount of damage a projectile from this tower inflicts
	 */
	public double getDamage() {
		// TODO Auto-generated method stub
		return -1;
	}

	/**
	 * @return the amount of damage this tower will do per second
	 */
	public double getDamagePerSecond() {
		// TODO Auto-generated method stub
		return -1;
	}

	/**
	 * @return the firing speed of this tower in seconds
	 */
	public double getFiringSpeed() {
		// TODO Auto-generated method stub
		return -1;
	}

	/**
	 * @return the initial cost of this tower
	 */
	public int getInitialCost() {
		// TODO Auto-generated method stub
		return -1;
	}

}
