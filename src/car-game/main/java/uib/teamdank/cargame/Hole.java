package uib.teamdank.cargame;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import uib.teamdank.common.Item;

/**
 * A hole in the road. Has negative effects if driven over by the
 * {@link Player}.
 */
public class Hole extends Item {

    public Hole(TextureRegion texture) {
        super("Hole", "A man hole that suspiciously is uncovered.");
        setTexture(texture);
        setScale(.4f);
    }

}
