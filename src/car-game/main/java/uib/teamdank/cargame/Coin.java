package uib.teamdank.cargame;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import uib.teamdank.common.Item;

public class Coin extends Item implements RoadEntity {
	
	private static final int GOLD_AMOUNT = 1;
	
	public Coin(TextureRegion texture) {
		super("Coin", "A precious gold coin. So shiny it hurts.");
		setTexture(texture);
		setScale(.5f);
	}

	@Override
	public void drivenOverBy(Player player) {
		player.getInventory().addGold(GOLD_AMOUNT);
		this.markForRemoval();
	}
	
}
