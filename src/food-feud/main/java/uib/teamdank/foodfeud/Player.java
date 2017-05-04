package uib.teamdank.foodfeud;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import uib.teamdank.common.Actor;
import uib.teamdank.common.Inventory;
import uib.teamdank.common.ItemHolder;

/**
 * Represents a player in the game.
 */
public class Player extends Actor implements ItemHolder, PhysicsSimulated {
	
	private static final float HORIZONTAL_MOVEMENT_IMPULSE = 5000f;
	private static final float JUMP_FORCE = 100000000f;
	
	private Body body;
	private boolean onGround; 
	
	private Inventory weapons;
	
	public Player(String name) {
		super(100, name);
		weapons = new Inventory();
	}
	
	@Override
	public float getAngle() {
		if (body != null) {
			setAngle((float) Math.toDegrees(body.getAngle()));
		}
		return super.getAngle();
	}
	
	public Body getBody() {
		return body;
	}
	
	@Override
	public Inventory getInventory() {
		return weapons;
	}
	
	@Override
	public Vector2 getPosition() {
		if (body != null) {
			super.getPosition().set(body.getPosition());
			super.getPosition().sub(getWidth() / 2f, getHeight() / 2f);
		}
		return super.getPosition();
	}
	
	public boolean isOnGround() {
		return onGround;
	}
	
	public void jump() {
		if (isOnGround()) {
			body.applyForceToCenter(0, JUMP_FORCE, true);
		}
	}
	
	public void moveLeft() {
		moveLeft(1);
	}
	
	public void moveLeft(int times) {
		body.applyLinearImpulse(-(HORIZONTAL_MOVEMENT_IMPULSE * times), 0, getWidth() / 2, getHeight() / 2, true);
	}
	
	public void moveRight() {
		moveRight(1);
	}
	
	public void moveRight(int times) {
		body.applyLinearImpulse(HORIZONTAL_MOVEMENT_IMPULSE * times, 0, getWidth() / 2, getHeight() / 2, true);
	}
	
	public void setBody(Body body) {
		this.body = body;
	}
	
	public void setOnGround(boolean onGround) {
		this.onGround = onGround;
	}
	
}
