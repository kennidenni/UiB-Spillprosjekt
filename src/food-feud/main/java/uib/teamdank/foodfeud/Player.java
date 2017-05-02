package uib.teamdank.foodfeud;

import com.badlogic.gdx.math.Vector2;

import uib.teamdank.common.Actor;
import uib.teamdank.common.Inventory;
import uib.teamdank.common.ItemHolder;

/**
 * Represents a player in the game.
 */
public class Player extends Actor implements ItemHolder {
	
	private static final float HORIZONTAL_ACCELERATION = 40f;
    //private static final float HORIZONTAL_FRICTION = .9f;
	private static final float VERTICAL_TOP_SPEED = 512f;
	
	private Inventory weapons;
	
	public Player(int h, String name) {
		super(h, h, name);
		weapons = new Inventory();
	}
	
	@Override
	public Inventory getInventory() {
		return weapons;
	}
	
	private void turn(int direction) {
		final Vector2 velocity = getVelocity();
		float horizontalAcceleration = (velocity.y / VERTICAL_TOP_SPEED) * HORIZONTAL_ACCELERATION;
		velocity.x += (Math.signum(direction) * horizontalAcceleration);
	}
	
	public void turnLeft() {
		turn(-1);
	}
	
	public void turnRight() {
		turn(1);
	}
	
}
