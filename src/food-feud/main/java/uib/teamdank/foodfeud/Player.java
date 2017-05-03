package uib.teamdank.foodfeud;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;

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
		
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.fixedRotation = true;
		this.body = world.createBody(bodyDef);
	}

	public Body getBody() {
		return body;
	}
	
	@Override
	public Inventory getInventory() {
		return weapons;
	}
	
	@Override
	public Vector2 getPosisiton() {
		if (body != null) {
			super.getPosisiton().set(body.getPosition());
		}
		return super.getPosisiton();
	}
}
