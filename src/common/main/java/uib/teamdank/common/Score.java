package uib.teamdank.common;

/**
 *
 * Represent an accumulated amount of points.
 */
public class Score implements Comparable<Score> {
	
	private long score;
	private String name;
	
	public Score(long score, String name){
		this.score = score;
		this.name = name;
	}
	
	public Score(long score){
		this(score, "Anonymous");
	}
	
	public Score(String name){
		this(0, name);
	}
	
	public Score(){
		this(0, "Anonymous");
	}
	
	public void addToScore(long score) {
		if(score < 0)
			throw new IllegalArgumentException("Score can not be negative: " + score);
		this.score += score;
	}
	/**
	 *
	 * Compares the two scores.
	 */
	@Override
	public int compareTo(Score o) {
		return (int) Math.signum((score - o.getScore()));
	}

	public long getScore(){
		return this.score;
	}
	
	public String getName(){
		return name;
	}
	
	/**
	 * Sets the new score. The score cannot be less than zero.
	 */
	public void setScore(long score){
		if(score < 0)
			throw new IllegalArgumentException("Score can not be negative: " + score);
		this.score = score;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
}
