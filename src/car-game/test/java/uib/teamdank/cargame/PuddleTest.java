package uib.teamdank.cargame;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by sel023.
 */
public class PuddleTest {

	private Puddle pudd;

	@Before
	public void setUp() throws Exception {
		Puddle pud = new Puddle("name", "desc");
		pudd = pud;
	}

	@Test
	public void getFuelPenalty() throws Exception {
		assertThat(pudd.getFuelPenalty(), is(equalTo(100)));
	}

	@Test
	public void isMoveable() throws Exception {
		assertThat(pudd.isMovable(), is(equalTo(false)));
	}

}