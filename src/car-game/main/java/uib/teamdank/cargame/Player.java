package uib.teamdank.cargame;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.math.Vector2;

import uib.teamdank.common.Actor;
import uib.teamdank.common.Inventory;
import uib.teamdank.common.Score;
import uib.teamdank.common.Upgrade;
import uib.teamdank.common.Upgradeable;
import uib.teamdank.common.util.AssetManager;

/**
 * The player/the car.
 */
public class Player extends Actor implements Upgradeable {

	private static final float ZERO_SPEED_TOLERANCE = 8f;

	private static final float HORIZONTAL_ACCELERATION = 40f;
	private static final float HORIZONTAL_FRICTION = .9f;

	private static final float VERTICAL_ACCELERATION = 10f;
	private static final float VERTICAL_FRICTION = .98f;
	private static final float VERTICAL_TOP_SPEED = 512f;

	private final Inventory inventory = new Inventory();
	private final Set<String> unlockedSkins = new HashSet<>();

	private final Score score;

	private uib.teamdank.common.util.TextureAtlas carTextures;
	private AssetManager assets;

	public Player() {
		super(100, "Per");
		score = new Score(getName());
	}

	public void accelerate() {
		if (!isOutOfFuel() && getVelocity().y != VERTICAL_TOP_SPEED) {
			if (getVelocity().y > VERTICAL_TOP_SPEED) {
				getVelocity().y = VERTICAL_TOP_SPEED;
			} else {
				getVelocity().y += VERTICAL_ACCELERATION;
			}
		}
	}

	public void applyFriction() {
		final Vector2 velocity = getVelocity();

		// Horizontal
		velocity.x *= HORIZONTAL_FRICTION;
		if (velocity.epsilonEquals(0, velocity.y, ZERO_SPEED_TOLERANCE)) {
			velocity.x = 0;
		}

		// Vertical
		if (isOutOfFuel()) {
			velocity.y *= VERTICAL_FRICTION;
			if (velocity.epsilonEquals(velocity.x, 0, ZERO_SPEED_TOLERANCE)) {
				velocity.y = 0;
			}
		}

	}

	@Override
	public void applyUpgrade(Upgrade upgrade) {
		// TODO Auto-generated method stub
	}

	public Inventory getInventory() {
		return inventory;
	}

	public Score getScore() {
		return score;
	}

	public boolean hasUnlockedSkin(String name) {
		return unlockedSkins.contains(name);
	}

	public boolean isOutOfFuel() {
		return getHealth() == 0;
	}

	public void resetPosAndHealth() {
		Vector2 pos = getPosisiton();
		pos.x = 0;
		pos.y = 0;

		Vector2 vel = getVelocity();
		vel.x = 0;
		vel.y = 0;

		setHealth(getMaxHealth());
	}

	public void restrictHorizontally(int minX, int maxX) {
		if (getPosisiton().x < minX) {
			getPosisiton().x = minX;
			getVelocity().x *= -1;
		} else if (getPosisiton().x > maxX - getWidth()) {
			getPosisiton().x = (float) (maxX - getWidth());
			getVelocity().x *= -1;
		}
	}

	public void setImage(String car) {
		this.assets = new AssetManager();
		carTextures = assets.getAtlas("Images/car_sheet.json");
		setTexture(carTextures.getRegion(car));
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

	public void unlockSkin(String name) {
		this.unlockedSkins.add(name);
	}
}
