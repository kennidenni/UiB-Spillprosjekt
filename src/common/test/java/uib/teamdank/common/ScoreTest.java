package uib.teamdank.common;
import static org.junit.Assert.*;
import org.junit.Test;

public class ScoreTest {
	@Test
	public void scoreZeroAtStartTest(){
		Score testScore = new Score();
		long score = testScore.getScore();
		long zero = 0;
		assertEquals(score,zero);
	}
	@Test
	public void addToScoreTest(){
		Score testScore = new Score();
		testScore.addToScore(3);
		long score = testScore.getScore();
		long three = 3;
		assertEquals(score,three);
	}
	@Test
	public void setScoreTest(){
		Score testScore = new Score();
		testScore.setScore(5);
		long score = testScore.getScore();
		long three = 5;
		assertEquals(score,three);
	}
	@Test
	public void compareToTest(){
		Score score1 = new Score();
		Score score2 = new Score();
		long stor = 10;
		long liten = 1;
		score1.setScore(stor);
		score2.setScore(liten);
		int compare1 = score1.compareTo(score2);
		assertEquals(compare1, 1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void subtractScoreTest(){
		Score score = new Score();
		score.addToScore(-1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void setNegativeScoreTest(){
		Score score = new Score();
		score.setScore(-1);
	}
	
	@Test
	public void setAndGetNameTest(){
		Score s1 = new Score();
		Score s2 = new Score("test");
		assertEquals("Anonymous", s1.getName());
		assertEquals("test", s2.getName());
		
		s1.setName("test2");
		assertEquals("test2", s1.getName());
	}
	
}
