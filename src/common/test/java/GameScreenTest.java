import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import uib.teamdank.common.Game;
import uib.teamdank.common.gui.GameScreen;
import uib.teamdank.common.gui.Layer;

public class GameScreenTest {
	Game g;
	GameScreen gs;

	@Before
	public void setUp() throws Exception {
		g = mock(Game.class);
		gs = new GameScreen(g);
		
	}

	@Test
	public void testAddedLayerCannotBeNull() {
		try {
			gs.addLayer(null);
			assertTrue(false);
		} catch(Exception e) {
			assertTrue(true);
		}
	}

}
