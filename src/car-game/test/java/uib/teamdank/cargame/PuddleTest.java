package uib.teamdank.cargame;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by sel023.
 */
public class PuddleTest {
    
	private Puddle pudd;
    
	@Before
    public void setUp() throws Exception {
	Puddle pud = new Puddle("name","desc");
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