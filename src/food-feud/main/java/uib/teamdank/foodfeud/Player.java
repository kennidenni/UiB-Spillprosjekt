package uib.teamdank.foodfeud;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import uib.teamdank.common.Actor;
import uib.teamdank.common.Inventory;
import uib.teamdank.common.ItemHolder;

/**
 * Represents a player in the game.
 */
public class Player extends Actor implements ItemHolder {
	
	private Body body;
	
	private Inventory weapons;
	
	public Player(String name) {
		super(100, name);
		weapons = new Inventory();
		setTexture(new TextureRegion(new Texture("Images/Matvarer.png"), 53, 48, 57, 57));
	}
	
	public void setBody(Body body) {
		this.body = body;
	}
	
	public Body getBody() {
		return body;
	}
	
	@Override
	public Inventory getInventory() {
		return weapons;
	}
	
	@Override
	public float getAngle() {
		if (body != null) {
			setAngle((float) Math.toDegrees(body.getAngle()));
		}
		return super.getAngle();
	}
	
	@Override
	public Vector2 getPosisiton() {
		if (body != null) {
			super.getPosisiton().set(body.getPosition());
		}
		return super.getPosisiton();
	}
	
}
