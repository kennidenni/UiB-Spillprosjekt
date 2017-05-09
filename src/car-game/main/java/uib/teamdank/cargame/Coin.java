package uib.teamdank.cargame;

import uib.teamdank.common.Item;
import uib.teamdank.common.util.AudioManager;
import uib.teamdank.common.util.TextureAtlas;

public class Coin extends Item implements RoadEntity {
	
	private static final String SOUND = "Sounds/coin_sound.wav";
	private static final String TEXTURE_REGION = "coin";
	
	private static final int GOLD_AMOUNT = 1;
	
	private final AudioManager audioManager;
	private boolean wasDrivenOver = false;
	
	public Coin(AudioManager audioManager, TextureAtlas atlas) {
		super("Coin", "A precious gold coin. So shiny it hurts.");
		this.audioManager = audioManager;
        setTexture(atlas.getRegion(TEXTURE_REGION));
		setScale(.5f);
		
		audioManager.preloadSounds(SOUND);
	}

	@Override
	public void drivenOverBy(Player player) {
		if (!wasDrivenOver) {
			player.getInventory().addGold(GOLD_AMOUNT);
			audioManager.playSound(SOUND);
			wasDrivenOver = true;
		}
		this.markForRemoval();
	}
	
}
