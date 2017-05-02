package uib.teamdank.cargame;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import uib.teamdank.common.Actor;


/**
 * A pedestrian in the road. Has positive effects if driven over by the
 * {@link Player}.
 */
public class Pedestrian extends Actor implements RoadEntity {
	private final long score;
	private final float VERTICAL_TOP_SPEED;
	private final float VERTICAL_ACCELERATION;
	private final float HORIZONTAL_TOP_SPEED;
	private final float HORIZONTAL_ACCELERATION;
	
	private boolean gaveGoldToPlayer = false;

	public Pedestrian() {
		super();
		this.score = 1;
		this.VERTICAL_ACCELERATION = 40f;
		this.VERTICAL_TOP_SPEED = 400f;
		this.HORIZONTAL_ACCELERATION = 20f;
		this.HORIZONTAL_TOP_SPEED = 100f;
	}

	public Pedestrian(float vertSpd, float horiSpd, long sc, boolean goLeft, TextureRegion texture) {
		super();
		setTexture(texture);
		setScale(.9f);
		this.score = sc;
		this.isMovable();
		if (goLeft) {
			this.VERTICAL_ACCELERATION = -50f;
			this.VERTICAL_TOP_SPEED = -vertSpd;
			this.HORIZONTAL_ACCELERATION = -20f;
			this.HORIZONTAL_TOP_SPEED = -horiSpd;
		} else {
			this.VERTICAL_ACCELERATION = 50f;
			this.VERTICAL_TOP_SPEED = vertSpd;
			this.HORIZONTAL_ACCELERATION = 20f;
			this.HORIZONTAL_TOP_SPEED = horiSpd;
		}
	}

	public long getScore() {
		return score;

	}

	@Override
	public void drivenOverBy(Player player) {
		if (!gaveGoldToPlayer) {
			player.getScore().addToScore(this.score);
			gaveGoldToPlayer = true;
			this.markForRemoval();
		}

	}

	public void accelerate() {
		accelerateVertical();
		accelerateHorizontal();
	}
	private void accelerateVertical(){
		if (getVelocity().y != VERTICAL_TOP_SPEED) {
			if (getVelocity().y > VERTICAL_TOP_SPEED) {
				getVelocity().y = VERTICAL_TOP_SPEED;
			} else {
				getVelocity().y += VERTICAL_ACCELERATION;
			}
		}
	}
	private void accelerateHorizontal(){
		if (HORIZONTAL_TOP_SPEED > 0) {
			if (getVelocity().x != HORIZONTAL_TOP_SPEED) {
				if (getVelocity().x > HORIZONTAL_TOP_SPEED) {
					getVelocity().x = HORIZONTAL_TOP_SPEED;
				} else {
					getVelocity().x += HORIZONTAL_ACCELERATION;
				}
			}
		} else {
			if (getVelocity().x != HORIZONTAL_TOP_SPEED) {
				if (getVelocity().x > HORIZONTAL_TOP_SPEED) {
					getVelocity().x = HORIZONTAL_TOP_SPEED;
				} else {
					getVelocity().x -= HORIZONTAL_ACCELERATION;
				}
			}
		}
	}
}
