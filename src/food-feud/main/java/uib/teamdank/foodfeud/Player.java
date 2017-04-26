package uib.teamdank.foodfeud;

import uib.teamdank.common.Actor;
import uib.teamdank.common.Inventory;
import uib.teamdank.common.ItemHolder;

/**
 * Represents a player in the game.
 */
public class Player extends Actor implements ItemHolder {
	
	private Inventory weapons;
	
	public Player() {
		weapons = new Inventory();
	}
	
	@Override
	public Inventory getInventory() {
		return weapons;
	}
}
