package uib.teamdank.cargame;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import uib.teamdank.common.Item;
/**
 * A puddle in the road. Has negative effects if driven over by the
 * {@link Player}.
 */
public class Puddle extends Item implements RoadEntity {
	private static final float PLAYER_VELOCITY_MODIFIER = 1.1f;
	private static final int FUEL_PENALTY = 15;

	private boolean tookFuelFromPlayer = false;
	
    public Puddle(TextureRegion texture) {
        super("Puddle", "A minitaure pond in the middle of the road.");
        setTexture(texture);
        setScale(.4f);
    }
    
    public int getFuelPenalty() {
        return FUEL_PENALTY;
    }
    
    @Override
    public void drivenOverBy(Player player) {
    	if (!tookFuelFromPlayer) {
    		player.decreaseHealth(getFuelPenalty());
    		tookFuelFromPlayer = true;
    	}
    	player.getVelocity().scl(PLAYER_VELOCITY_MODIFIER);
    }

}
