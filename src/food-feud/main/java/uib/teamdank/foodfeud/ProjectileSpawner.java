package uib.teamdank.foodfeud;

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
import uib.teamdank.common.util.CType;
import uib.teamdank.common.util.TimedEvent;
import uib.teamdank.foodfeud.Weapon.Type;
import uib.teamdank.foodfeud.gui.GameScreen;

public class ProjectileSpawner {
	
	Weapon wep;

	private Body createBody(Weapon weapon, World world, float scale) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		
		wep = weapon;
		
		final float width = wep.getTexture().getRegionWidth() * scale;
		final float height = wep.getTexture().getRegionHeight() * scale;
		
		CircleShape shape = new CircleShape();
		shape.setRadius(Math.max(width, height) / 2f);
		shape.setPosition(new Vector2(0, 0));

		Body body = world.createBody(bodyDef);
		MassData massData = new MassData();
		massData.mass = wep.getMass();
		body.setMassData(massData);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1f;
		fixtureDef.filter.categoryBits = CType.CATEGORY_PROJECTILE.getValue();
		fixtureDef.filter.maskBits = (short) (CType.CATEGORY_PLAYER.getValue() | CType.CATEGORY_WORLD.getValue());
		body.createFixture(fixtureDef);

		shape.dispose();
		return body;
	}

	private GameObject createProjectile(Weapon weapon, World world, Player player, Vector2 force, float originX, float originY) {
		float scale;
		if(wep.getType() == Type.BURST_BALLISTIC)
			scale = .03f;
		else if(wep.getType() == Type.HEAVY_BALLISTIC)
			scale = .125f;
		else if(wep.getType() == Type.LIGHT_BALLISTIC)
			scale = 0.6f;
		else
			scale = 0.4f;
		
		Projectile projectile = new Projectile(createBody(weapon, world, scale), player, weapon.getDamage(), scale);
		projectile.getBody().setTransform(projectile.getBody().getPosition().set(originX + projectile.getWidth() / 2f,
				originY + projectile.getHeight() / 2f), 0);

		projectile.getBody().applyLinearImpulse(force.x, force.y, originX + projectile.getWidth() / 2f,
				originY + projectile.getHeight() / 2f, true);
		projectile.getBody().applyAngularImpulse(10000f, true);
		projectile.getBody().setUserData(projectile);
		projectile.setTexture(weapon.getTexture());
		return projectile;
	}

	public void spawn(GameScreen game, Weapon wep, Layer layer, World world, Player player, Vector2 dir, float originX, float originY,
			long elapsedTime) {
		dir.scl((float) elapsedTime);
		if (wep.getType() == Type.LIGHT_BALLISTIC) {
			layer.addGameObject(createProjectile(wep, world, player, dir, originX, originY));
		}
		if (wep.getType() == Type.HEAVY_BALLISTIC) {
			layer.addGameObject(createProjectile(wep, world, player, dir.add(dir), originX, originY));
		}
		if (wep.getType() == Type.BURST_BALLISTIC) {
			for(int i = 0; i<3; i++){
				layer.addGameObject(createProjectile(wep, world, player, dir, originX, originY));
				game.addTimedEvent(new TimedEvent(0.5f, true, () -> {
				}));
		
			}
		}
		if (wep.getType() == Type.SPRAY_BALLISTIC) {
			for (int i = 0; i < 2; i++) {
				layer.addGameObject(createProjectile(wep, world, player, dir.add(0, +i), originX, originY));
				layer.addGameObject(createProjectile(wep, world, player, dir.add(0, -i), originX, originY));
			}
			layer.addGameObject(createProjectile(wep, world, player, dir, originX, originY));
		}
	}

}
