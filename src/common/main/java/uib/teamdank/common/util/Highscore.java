package uib.teamdank.common.util;

import java.util.Objects;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import uib.teamdank.common.Score;

public class Highscore {

	@SerializedName("name") private String[] names;
	@SerializedName("score") private Score[] scores;
//	private Score[] scores;
	
	public static void main(String[] args){
		Gson test = new GsonBuilder().create();
		Score s = new Score();
		System.out.println(test.toJson(s));
	}
	
	public static Highscore createFromJson(FileHandle handle){
		Objects.requireNonNull(handle, "file handle cannot be null");
		return new Gson().fromJson(handle.reader(), Highscore.class);
	}
	
	public static void writeToJson(FileHandle handle, String[] names, Score[] scores){
		Gson gson = new GsonBuilder().create();
		handle.writeString(gson.toJson(new Highscore(names, scores)), false);
	}

	// Hide constructor
	private Highscore(String[] names, Score[] scores) {
		this.names = names;
		this.scores = scores;
	}
	
//	public List<String> getNames(){
//		List<String> nameList = new List<>((Skin) null);
//		nameList.setItems(names);
//		return nameList;
//	}
	public String[] getNames(){
		return names;
	}
	
//	public List<Score> getScore(){
//		List<Score> scoreList = new List<>((Skin) null);
//		scoreList.setItems(scoresNum);
//		return scoreList;
//	}
	public Score[] getScore(){
		return scores;
	}

//	private void setScores() {
//		scores = new Score[scoresNum.length];
//		for(int i = 0; i < scoresNum.length; i++){
//			Score score = new Score();
//			score.setScore(scoresNum[i]);
//			scores[i] = score;
//		}
//	}
	
}
