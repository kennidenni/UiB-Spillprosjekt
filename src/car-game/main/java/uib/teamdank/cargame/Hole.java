package uib.teamdank.cargame;

import com.badlogic.gdx.math.Vector2;

import uib.teamdank.common.Item;
import uib.teamdank.common.util.TextureAtlas;

/**
 * A hole in the road. Has negative effects if driven over by the
 * {@link Player}.
 */
public class Hole extends Item implements RoadEntity {

	private static final String TEXTURE_REGION = "hole";

	public Hole(TextureAtlas atlas) {
		super("Hole", "A man hole that suspiciously is uncovered.");
		setTexture(atlas.getRegion(TEXTURE_REGION));
		setScale(.4f);
	}

	@Override
	public void drivenOverBy(Player player) {
		player.setHealth(0);
		player.getVelocity().set(Vector2.Zero);
	}

}
