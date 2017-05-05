package uib.teamdank.foodfeud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import uib.teamdank.common.Actor;
import uib.teamdank.common.Inventory;
import uib.teamdank.common.ItemHolder;
import uib.teamdank.common.util.Animation;
import uib.teamdank.common.util.AssetManager;
import uib.teamdank.common.util.TextureAtlas;

/**
 * Represents a player in the game.
 */
public class Player extends Actor implements ItemHolder, PhysicsSimulated {

	private static final float HORIZONTAL_MOVEMENT_IMPULSE = 5000f;
	private static final float JUMP_FORCE = 100000000f;

	private final TextureAtlas playerAtlas;
	private TextureRegion bodyTexture;
	private final Animation feetStillAnimation;
	private final Animation feetWalkingAnimation;
	private final Animation feetFallingAnimation;

	private Body body;
	private boolean onGround;
	private boolean walking;

	private final Team team;
	private final Inventory weapons;
	
	public Player(AssetManager assets, Team team, String name) {
		super(100, name);
		this.team = team;
		this.weapons = new Inventory();

		this.playerAtlas = assets.getAtlas("Images/player_sheet.json");
		this.bodyTexture = getBodyExpansionTexture();
		this.feetStillAnimation = assets.getAnimation(team.getStillAnimation());
		this.feetWalkingAnimation = assets.getAnimation(team.getWalkingAnimation());
		this.feetFallingAnimation = assets.getAnimation(team.getFallingAnimation());
		setScale(.5f);
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

	private TextureRegion getBodyExpansionTexture() {
		if (getHealth() == 0) {
			return playerAtlas.getRegion(team.getBodyDead());
		}
		float healthPerBody = getMaxHealth() / (float) team.getBodyExpansionCount();
		int index = (int) (getHealth() / healthPerBody) - 1;
		return playerAtlas.getRegion(team.getBodyExpansion(index));
	}

	@Override
	public int getHeight() {
		return (int) (bodyTexture.getRegionHeight() * getScale());
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

	@Override
	public Vector2 getVelocity() {
		if (body != null) {
			super.getVelocity().set(body.getLinearVelocity());
		}
		return super.getVelocity();
	}

	@Override
	public int getWidth() {
		int max = Math.max(bodyTexture.getRegionWidth(), getAnimation().getTexture().getRegionWidth());
		return (int) (max * getScale());
	}

	private boolean isMoving() {
		return !body.getLinearVelocity().equals(Vector2.Zero);
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
		moveRight(-1);
	}
	
	public void moveRight() {
		moveRight(1);
	}
	
	public void moveRight(int times) {
		body.applyLinearImpulse(HORIZONTAL_MOVEMENT_IMPULSE * times, 0, getWidth() / 2, getHeight() / 2, true);
		walking = true;
	}

	@Override
	public void render(SpriteBatch batch, float delta) {
		renderTexture(batch, delta, bodyTexture, 0, 0);
		renderTexture(batch, delta, getAnimation().getTexture(), 0, getHeight());
	}
	
	public void setBody(Body body) {
		this.body = body;
	}

	@Override
	public void setHealth(int health) {
		super.setHealth(health);
		this.bodyTexture = getBodyExpansionTexture();
	}

	public void setOnGround(boolean onGround) {
		this.onGround = onGround;
	}
	
	@Override
	public void update(float delta) {
		super.update(delta);
		
		if (isOnGround() && isMoving() && walking) {
			setAnimation(feetWalkingAnimation);
		} else if (!isOnGround()) {
			setAnimation(feetFallingAnimation);
		} else {
			setAnimation(feetStillAnimation);
		}
		setFlipHorizontally(getVelocity().x < 0);
		walking = false;
		
	}

}
