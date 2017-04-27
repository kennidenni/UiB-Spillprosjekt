package uib.teamdank.cargame;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by sel023.
 */
public class FuelTest {
	private Fuel fuel;

	@Before
	public void setUp() throws Exception {
		fuel = new Fuel(null); 
	}

	@Test
	public void getFuelIncrease() throws Exception {
		assertThat(fuel.getFuelIncrease(), is(equalTo(100)));
	}

}

