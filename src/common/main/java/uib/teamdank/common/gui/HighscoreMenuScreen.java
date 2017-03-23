package uib.teamdank.common.gui;

import java.util.List;

import com.badlogic.gdx.Screen;

import uib.teamdank.common.Score;

/**
 * Screen and the graphical user interface for viewing a set of {@link Score}s.
 */
public interface HighscoreMenuScreen extends Screen {

	/**
	 * Returns to the previous screen.
	 */
	public void goBack();

	/**
	 * Sets the list of scores to display. The list and the scores it contains
	 * cannot be {@code null}, but the list can be empty.
	 */
	public void setScores(List<Score> scores);

}
