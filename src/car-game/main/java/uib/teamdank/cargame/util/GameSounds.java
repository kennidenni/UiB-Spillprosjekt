package uib.teamdank.cargame.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class GameSounds {

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
