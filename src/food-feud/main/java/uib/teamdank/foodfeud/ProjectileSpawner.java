package uib.teamdank.foodfeud;

import java.util.Objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.World;

import uib.teamdank.common.GameObject;
import uib.teamdank.common.gui.Layer;
import uib.teamdank.foodfeud.Weapon.Type;

public class ProjectileSpawner {

	private final Weapon weapon;
	
	public ProjectileSpawner(Weapon weapon) {
		this.weapon = Objects.requireNonNull(weapon, "weapon cannot be null");
	}
	
	private Body createBody(World world, float scale, float originX, float originY) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(originX, originY);
		
		final float width = weapon.getTexture().getRegionWidth() * scale;
		final float height = weapon.getTexture().getRegionHeight() * scale;
		
		CircleShape shape = new CircleShape();
		shape.setRadius(Math.max(width, height) / 2f);
		shape.setPosition(new Vector2(width / 2f, height / 2f));
		
		Body body = world.createBody(bodyDef);
		MassData massData = new MassData();
		massData.mass = weapon.getMass();
		body.setMassData(massData);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1f;
		fixtureDef.isSensor = true;
		body.createFixture(fixtureDef);
		
		shape.dispose();
		return body;
	}
	
	private GameObject createProjectile(World world, Vector2 force, float originX, float originY) {
		final float scale = .25f;
		Projectile projectile = new Projectile(createBody(world, scale, originX, originY), weapon.getDamage(), scale);
		projectile.getBody().getTransform().setPosition(new Vector2(originX, originY));
		projectile.getBody().applyLinearImpulse(force.x, force.y, 0, 0, true);
		projectile.setTexture(weapon.getTexture());
		return projectile;
	}
	
	public void spawn(Layer layer, World world, Vector2 dir, float originX, float originY) {
		dir.scl(10000000f);
		if (weapon.getType() == Type.LIGHT_BALLISTIC) {
			layer.addGameObject(createProjectile(world, dir, originX, originY));
		}
	}
	
}
