package uib.teamdank.cargame;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import uib.teamdank.common.Item;

/**
 * A fuel can in the road. Has positive effects if driven over by the
 * {@link Player}.
 */
public class Fuel extends Item implements RoadEntity {
    private static final int DEFAULT_FUEL_AMOUNT = 15;
    
    private boolean gaveFuelToPlayer = false;

    public Fuel(TextureRegion texture) {
        super("Fuel", "A jerry can full of delicious fuel.");
        setTexture(texture);
        setScale(.25f);
    }

	@Override
	public void drivenOverBy(Player player) {
		if (!gaveFuelToPlayer) {
			player.increaseHealth(DEFAULT_FUEL_AMOUNT);
			gaveFuelToPlayer = true;
			this.markForRemoval();
		}
	}
}