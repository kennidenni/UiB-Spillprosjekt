package uib.teamdank.cargame;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import uib.teamdank.common.Actor;
import uib.teamdank.common.util.AudioManager;

/**
 * A pedestrian in the road. Has positive effects if driven over by the
 * {@link Player}.
 */
public class Pedestrian extends Actor implements RoadEntity {

	private static final String SOUND = "Sounds/dead_pedestrian.mp3";

	private final AudioManager audioManager;
	private boolean wasDrivenOver = false;

	private final long scoreBonus;

	public Pedestrian(AudioManager audioManager, long scoreBonus, float vx, float vy, boolean randomHorizontalDirection, TextureRegion texture) {
		this.audioManager = audioManager;
		setTexture(texture);
		setScale(.9f);

		getVelocity().set(vx, vy);
		if (randomHorizontalDirection) {
			getVelocity().x *= -1;
			setFlipHorizontally(true);
		}
		this.scoreBonus = scoreBonus;

		audioManager.preloadSounds(SOUND);
	}

	@Override
	public void drivenOverBy(Player player) {
		if (!wasDrivenOver) {
			player.getScore().addToScore(this.scoreBonus);
			audioManager.playSound(SOUND);
			wasDrivenOver = true;
		}
		this.markForRemoval();

	}
	
}
