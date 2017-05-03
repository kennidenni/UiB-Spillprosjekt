package uib.teamdank.cargame.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class GameSounds {
//	//Music/long audio files
//	private final Music CAR_DRIVE = Gdx.audio.newMusic(Gdx.files.internal("Music/car_drive.wav"));
//	private final Music BG_MUSIC = Gdx.audio.newMusic(Gdx.files.internal("Music/happy_bgmusic.wav")); 
//	
//	//Sounds
//	private final Sound CRASH_SOUND = Gdx.audio.newSound(Gdx.files.internal("Sounds/car_crash.wav"));
//	private final Sound COIN_SOUND = Gdx.audio.newSound(Gdx.files.internal("Sounds/coin_sound.wav"));
//	private final Sound PEDESTRIAN_SOUND = Gdx.audio.newSound(Gdx.files.internal("Sounds/dead_pedestrian.wav"));
//	private final Sound MANHOLE_SOUND = Gdx.audio.newSound(Gdx.files.internal("Sounds/manhole.wav"));
//	private final Sound SPLASH_SOUND = Gdx.audio.newSound(Gdx.files.internal("Sounds/splash.wav"));
	
//	private final Music CAR_DRIVE;
//	private final Music BG_MUSIC;
//	
//	private final Sound CRASH_SOUND;
//	private final Sound COIN_SOUND;
//	private final Sound PEDESTRIAN_SOUND;
//	private final Sound MANHOLE_SOUND;
//	private final Sound SPLASH_SOUND;

	private Sound sound;
	private Music music;
	
	public GameSounds() {
		
	}
	
	// Initializing the audio files
	public void addMusic(String filename) {
		String path = "Music/";
		String file = path + filename;
		music = Gdx.audio.newMusic(Gdx.files.internal(file));
		music.play();
		music.setLooping(true);
	}
	
	public void addSound(String filename) {
		String path = "Sounds/";
		String file = path + filename;
		sound = Gdx.audio.newSound(Gdx.files.internal(file));

	}
	
	// Methods for Music
	public void playMusic() {
		music.play();
	}
	
	public void pauseMusic() {
		music.pause();
	}
	
	public void loopMusic() {
		music.setLooping(true);
	}
	
	public void disposeMusic() {
		music.dispose();
	}
	
	// Methods for Sound
	public void playSound() {
		sound.play(0.9f);
	}
	
	public void pauseSound() {
		sound.pause();
	}
	
	public void loopSound() {
		sound.loop();
	}
	
	public void disposeSound() {
		sound.dispose();
	}
	
}
