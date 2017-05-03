package uib.teamdank.cargame;

import uib.teamdank.common.Item;
import uib.teamdank.common.util.AudioManager;
import uib.teamdank.common.util.TextureAtlas;
/**
 * A puddle in the road. Has negative effects if driven over by the
 * {@link Player}.
 */
public class Puddle extends Item implements RoadEntity {
	
	private static final String SOUND = "Sounds/splash.mp3";
	private static final String TEXTURE_REGION = "puddle";
	
	private static final float PLAYER_VELOCITY_MODIFIER = 1.1f;
	private static final int FUEL_PENALTY = 15;
	
	private final AudioManager audioManager;
	private boolean wasDrivenOver = false;
	
	public Puddle(AudioManager audioManager, TextureAtlas atlas) {
        super("Puddle", "A minitaure pond in the middle of the road.");
        this.audioManager = audioManager;
        setTexture(atlas.getRegion(TEXTURE_REGION));
        setScale(.4f);
        
        audioManager.preloadSounds(SOUND);
    }
    
    @Override
    public void drivenOverBy(Player player) {
    	if (!wasDrivenOver) {
    		player.decreaseHealth(getFuelPenalty());
    		audioManager.playSound(SOUND);
    		wasDrivenOver = true;
    	}
    	player.getVelocity().scl(PLAYER_VELOCITY_MODIFIER);
    }
    
    public int getFuelPenalty() {
        return FUEL_PENALTY;
    }
    
    public boolean wasDriverOven() {
		return wasDrivenOver;
	}

}
