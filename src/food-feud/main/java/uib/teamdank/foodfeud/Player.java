/*******************************************************************************
 * Copyright (C) 2017  TeamDank
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package uib.teamdank.foodfeud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import uib.teamdank.common.Actor;
import uib.teamdank.common.Inventory;
import uib.teamdank.common.ItemHolder;
import uib.teamdank.common.gui.Layer;
import uib.teamdank.common.util.Animation;
import uib.teamdank.common.util.AssetManager;
import uib.teamdank.common.util.TextureAtlas;

/**
 * Represents a player in the game.
 */
public class Player extends Actor implements ItemHolder, PhysicsSimulated {

	private static final float FLIP_VELOCITY_TOLERANCE = 1f;
	private static final float HORIZONTAL_MOVEMENT_IMPULSE = 1000f;
	private static final float JUMP_FORCE = 25000f;
	public static final float MAX_VEL_X = 12f;
	
	private final TextureAtlas playerAtlas;
	private final Animation feetStillAnimation;
	private final Animation feetWalkingAnimation;
	private final Animation feetFallingAnimation;
	
	private TextureRegion bodyTexture;
	private Animation currentFeetAnimation;
	
	private Body body;
	private boolean onGround;
	public boolean walking;

	private final Team team;
	private final Inventory weapons;
	private Weapon selectedWeapon;
	
	public Player(AssetManager assets, Team team, String name) {
		super(100, name);
		this.team = team;
		this.weapons = new Inventory();
		for (Weapon weapon : WeaponLoader.fromJson(assets, Gdx.files.internal("Data/weapons.json"))) {
			this.weapons.addItem(weapon);
		}
		selectedWeapon = (Weapon) weapons.getItem(0);

		this.playerAtlas = assets.getAtlas("Images/player_sheet.json");
		this.feetStillAnimation = assets.getAnimation(team.getStillAnimation());
		this.feetWalkingAnimation = assets.getAnimation(team.getWalkingAnimation());
		this.feetFallingAnimation = assets.getAnimation(team.getFallingAnimation());
		setScale(.2f);
		
		this.bodyTexture = getBodyExpansionTexture();
		currentFeetAnimation = feetStillAnimation;
	}
	
	public boolean fireWeapon(Layer layer, World world, Vector2 dir) {
		if (selectedWeapon == null || selectedWeapon.getAmount() == 0) {
			return false;
		}
		selectedWeapon.fire(selectedWeapon, this, layer, world, dir);
		
		return true;
	}
	
	@Override
	public float getAngle() {
		if (body != null) {
			setAngle((float) Math.toDegrees(body.getAngle()));
		}
		return super.getAngle();
	}
	
	@Override
	public Animation getAnimation() {
		throw new UnsupportedOperationException("player uses custom animations");
	}
	
	public Body getBody() {
		return body;
	}

	private TextureRegion getBodyExpansionTexture() {
		if (team == null) {
			return null;
		}
		float healthPerBody = getMaxHealth() / (float) team.getBodyExpansionCount();
		int index = team.getBodyExpansionCount() - (int) (getHealth() / healthPerBody);
		return playerAtlas.getRegion(team.getBodyExpansion(index));
	}

	@Override
	public int getHeight() {
		int height = bodyTexture.getRegionHeight();
		height += currentFeetAnimation.getAverageHeight();
		return (int) (height * getScale());
	}

	@Override
	public Inventory getInventory() {
		return weapons;
	}
	
	@Override
	public Vector2 getPosition() {
		if (body != null) {
			super.getPosition().set(body.getPosition());
			super.getPosition().sub(getWidth() / 2f, 0);
		}
		return super.getPosition();
	}

	public Weapon getSelectedWeapon() {
		return selectedWeapon;
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
		return (int) (playerAtlas.getRegion(team.getBodyExpansion(0)).getRegionWidth() * getScale());
	}

	public boolean isDead() {
		return getHealth() == 0;
			
	}
	
	private boolean isMoving() {
		return Math.abs(body.getLinearVelocity().x) > 0.001;
	}
	
	public boolean isOnGround() {
		return onGround;
	}
	
	public void jump() {
		if (isOnGround()) {
			body.applyLinearImpulse(body.getLinearVelocity().x, JUMP_FORCE, getWidth() / 2f, getHeight() / 2f, true);
		}
	}
	
	public void moveLeft() {
		moveLeft(1);
	}
	
	public void moveLeft(int times) {
		moveRight(-1 * times);
	}

	public void moveRight() {
		moveRight(1);
	}
	
	public void moveRight(int times) {
		body.applyLinearImpulse(HORIZONTAL_MOVEMENT_IMPULSE * times, 0, getWidth() / 2, getHeight() / 2, true);
	}

	@Override
	public void render(SpriteBatch batch, float delta) {
		
		final float bodyWidth = bodyTexture.getRegionWidth() * getScale();
		final float bodyHeight = bodyTexture.getRegionHeight() * getScale();
		
		final TextureRegion feetTexture = currentFeetAnimation.getTexture();
		final Vector2 feetOffset = currentFeetAnimation.getUserPoint();
		final float feetWidth = feetTexture.getRegionWidth() * getScale();
		final float feetHeight = feetTexture.getRegionHeight() * getScale();
		float feetOffsetX = feetWidth / 2f + -feetOffset.x * getScale();
		if(this.getFlipHorizontally())
			feetOffsetX-=160*getScale()*0.1f;
		
		final float feetOffsetY = -feetOffset.y * getScale();

		renderTexture(batch, delta, bodyTexture, bodyWidth, bodyHeight, 0, feetHeight + feetOffsetY);
		renderTexture(batch, delta, feetTexture, feetWidth, feetHeight, feetOffsetX, 0);
		
	}

	@Override
	public void setAnimation(Animation animation) {
		throw new UnsupportedOperationException("player uses custom animations");
	}
	
	public void setBody(Body body) {
		this.body = body;
	}

	@Override
	public void setHealth(int health) {
		boolean wasAlive = !isDead();
		super.setHealth(health);
		if (isDead() && !wasAlive) {
			return;
		}
		
		if (isDead()) {
			this.bodyTexture = playerAtlas.getRegion(team.getBodyDead());
			body.setFixedRotation(false);
			body.applyAngularImpulse(1f, true);
		} else {
			this.bodyTexture = getBodyExpansionTexture();
		}
	}
	
	public void setOnGround(boolean onGround) {
		this.onGround = onGround;
	}
	
	@Override
	public void update(float delta) {
		if (!isDead()) {
			if (isOnGround() && isMoving() && walking) {
				currentFeetAnimation = feetWalkingAnimation;
			} else if (!isOnGround()) {
				currentFeetAnimation = feetFallingAnimation;
			} else {
				currentFeetAnimation = feetStillAnimation;
			}
			if (getVelocity().x < -FLIP_VELOCITY_TOLERANCE) {
				setFlipHorizontally(true);
			} else if (getVelocity().x > FLIP_VELOCITY_TOLERANCE){
				setFlipHorizontally(false);
			}
			walking = false;
			currentFeetAnimation.update(delta);
		}
	}
}
