package uib.teamdank.cargame;

import uib.teamdank.common.Actor;
import uib.teamdank.common.Upgrade;
import uib.teamdank.common.Upgradeable;

/**
 * The player/the car.
 */
public class Player extends Actor implements Upgradeable {

	public Player() {
		super(1000, "Per");
	}
	
	@Override
	public void applyUpgrade(Upgrade upgrade) {
		// TODO Auto-generated method stub
	}

}
