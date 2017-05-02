package uib.teamdank.cargame;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by sel023.
 */
public class PedestrianTest {

	private Pedestrian pede;

	@Before
	public void setUp() throws Exception {
		pede = new Pedestrian();
	}

	@Test
	public void getScore() throws Exception {
		assertThat(pede.getScore(), is(equalTo(1)));
	}

}