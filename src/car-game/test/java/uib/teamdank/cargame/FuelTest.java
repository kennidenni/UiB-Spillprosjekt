package uib.teamdank.cargame;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by sel023.
 */
public class FuelTest {
    private Fuel fuel;
	@org.junit.Before
    public void setUp() throws Exception {
    Fuel f = new Fuel("name","desc");
    Fuel fuel = f;
    }

    @org.junit.Test
    public void getFuelIncrease() throws Exception {
    asserThat(fuel.getFuelIncrease(), is(equalTo(100)));
    }

} 