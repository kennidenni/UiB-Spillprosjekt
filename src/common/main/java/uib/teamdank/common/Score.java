package uib.teamdank.common;

/**
 *
 * Represent an accumulated amount of points.
 */
public class Score implements Comparable<Score> {
	
	long score = 0;
	
	public void addToScore(long score) {
			this.score += score;
	}

	@Override
	public int compareTo(Score o) {
		if (this.getScore() == o.getScore()){
			return 0;
		}
		else if (this.getScore() < o.getScore()){
			return 1;
		}
		else {
			return -1;
		}
	}

	public long getScore(){
		return this.score;
	}
	
	/**
	 * Sets the new score. The score cannot be less than zero.
	 */
	public void setScore(long score){
		this.score = score;
	}
}
