package uib.teamdank.cargame;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import uib.teamdank.common.Item;

/**
 * A fuel can in the road. Has positive effects if driven over by the
 * {@link Player}.
 */
public class Fuel extends Item {
    private static final int DEFAULT_FUEL_AMOUNT = 100;

    public Fuel(TextureRegion texture) {
        super("Fuel", "A jerry can full of delicious fuel.");
        setTexture(texture);
        setScale(.5f);
    }

    public int getFuelIncrease() {
        return DEFAULT_FUEL_AMOUNT;
    }
}