package uib.teamdank.common;

/**
 *
 * Represent an accumulated amount of points.
 */
public interface Score extends Comparable<Score> {
	public default void addToScore(long score) {
		// TODO Auto-generated method stub
	}

	@Override
	public default int compareTo(Score o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public long getScore();
	
	/**
	 * Sets the new score. The score cannot be less than zero.
	 */
	public void setScore(long score);
}
