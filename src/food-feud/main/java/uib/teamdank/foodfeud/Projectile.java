package uib.teamdank.foodfeud;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import uib.teamdank.common.GameObject;

public class Projectile extends GameObject {

	private final Body body;
	private final int damage;
	
	public Projectile(Body body, int damage, float scale) {
		this.body = body;
		this.damage = damage;
		setScale(scale);
	}

	@Override
	public float getAngle() {
		return (float) Math.toDegrees(body.getAngle());
	}
	
	public Body getBody() {
		return body;
	}
	
	public int getDamage() {
		return damage;
	}
	
	@Override
	public Vector2 getPosition() {
		super.getPosition().set(body.getPosition());
		super.getPosition().sub(getWidth() / 2f, getHeight() / 2f);
		return super.getPosition();
	}
	
	@Override
	public Vector2 getVelocity() {
		return body.getLinearVelocity();
	}
	
	
}
