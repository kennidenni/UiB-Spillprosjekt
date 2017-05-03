package uib.teamdank.foodfeud;

import com.badlogic.gdx.math.Vector2;

import uib.teamdank.common.Actor;
import uib.teamdank.common.Inventory;
import uib.teamdank.common.ItemHolder;

/**
 * Represents a player in the game.
 */
public class Player extends Actor implements ItemHolder {
	
	private final Body body;
	
	private Inventory weapons;
	
	public Player(World world) {
		weapons = new Inventory();
	}
	
	@Override
	public Inventory getInventory() {
		return weapons;
	}
	
	@Override
	public Vector2 getPosisiton() {
		// TODO Auto-generated method stub
		return super.getPosisiton();
	}
}
