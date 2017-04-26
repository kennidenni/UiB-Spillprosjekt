package uib.teamdank.common;

import java.util.Objects;

import com.badlogic.gdx.files.FileHandle;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

public class Highscore {

	@SerializedName("score") private Score[] scores;
	
	public static Highscore createFromJson(FileHandle handle){
		Objects.requireNonNull(handle, "file handle cannot be null");
		return new Gson().fromJson(handle.reader(), Highscore.class);
	}
	
	public static void writeToJson(FileHandle handle, Score[] scores){
		Gson gson = new GsonBuilder().create();
		handle.writeString(gson.toJson(new Highscore(scores)), false);
	}

	// Hide constructor
	private Highscore(Score[] scores) {
		this.scores = scores;
	}
	
	public Score[] getScores(){
		return scores;
	}
}
