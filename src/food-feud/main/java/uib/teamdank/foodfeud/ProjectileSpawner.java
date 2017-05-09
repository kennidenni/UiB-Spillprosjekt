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
	

	private Body createBody(Weapon weapon, World world, float scale) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		
		final float width = weapon.getTexture().getRegionWidth() * scale;
		final float height = weapon.getTexture().getRegionHeight() * scale;
		
		CircleShape shape = new CircleShape();
		shape.setRadius(Math.max(width, height) / 2f);
		shape.setPosition(new Vector2(0, 0));
		
		Body body = world.createBody(bodyDef);
		MassData massData = new MassData();
		massData.mass = weapon.getMass();
		body.setMassData(massData);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1f;
		body.createFixture(fixtureDef);
		
		shape.dispose();
		return body;
	}
	
	private GameObject createProjectile(Weapon weapon, World world, Vector2 force, float originX, float originY) {
		final float scale = .25f;
		Projectile projectile = new Projectile(createBody(weapon, world, scale), weapon.getDamage(), scale);
		projectile.getBody().setTransform(projectile.getBody().getPosition().set(originX + projectile.getWidth() / 2f, originY + projectile.getHeight() / 2f), 0);
		
		projectile.getBody().applyLinearImpulse(force.x, force.y, originX + projectile.getWidth() / 2f, originY + projectile.getHeight() / 2f, true);
		projectile.getBody().applyAngularImpulse(10000f, true);
		projectile.setTexture(weapon.getTexture());
		return projectile;
	}
	
	public void spawn(Weapon wep, Layer layer, World world, Vector2 dir, float originX, float originY) {
		dir.scl(1000f);
		if (wep.getType() == Type.LIGHT_BALLISTIC) {
			layer.addGameObject(createProjectile(wep, world, dir, originX, originY));
		}
	}
	
}
