package uib.teamdank.cargame;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import uib.teamdank.common.Item;
/**
 * A puddle in the road. Has negative effects if driven over by the
 * {@link Player}.
 */
public class Puddle extends Item {
	private static final int FUEL_PENALTY = 100;

    public Puddle(TextureRegion texture) {
        super("Puddle", "A minitaure pond in the middle of the road.");
        setTexture(texture);
        setScale(.5f);
    }
    
    public int getFuelPenalty() {
        return FUEL_PENALTY;
    }

}
