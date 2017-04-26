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
		Pedestrian ped = new Pedestrian(10);
		pede = ped;
	}

	@Test
	public void getscore() throws Exception {
		assertThat(pede.getscore(), is(equalTo(10)));
	}

}