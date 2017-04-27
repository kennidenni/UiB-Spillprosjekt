package uib.teamdank.cargame;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import uib.teamdank.common.Item;

/**
 * A hole in the road. Has negative effects if driven over by the
 * {@link Player}.
 */
public class Hole extends Item implements RoadEntity {

    public Hole(TextureRegion texture) {
        super("Hole", "A man hole that suspiciously is uncovered.");
        setTexture(texture);
        setScale(.4f);
    }
    
    @Override
    public void drivenOverBy(Player player) {
    	player.setHealth(0);
    	player.getVelocity().set(Vector2.Zero);
    }

}
