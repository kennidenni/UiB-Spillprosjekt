package uib.teamdank.common;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.function.Consumer;

import uib.teamdank.common.GameObject;
import uib.teamdank.common.gui.Layer;

import org.junit.Before;
import org.junit.Test;

public class LayerTest {
	Layer l;

	@Before
	public void setUp() throws Exception {
		l = new Layer(false);
	}

	@Test
	public void testIfLayerIsSolidOrNot() {
		assertFalse(l.isSolid());

		Layer l2 = new Layer(true);
		assertTrue(l2.isSolid());
		
		l2.setSolid(false);
		assertFalse(l2.isSolid());
	}

	@Test
	public void testAddNullAsGameObjectToLayer() {
		try {
			l.addGameObject(null);
			assertTrue(false);
		} catch(NullPointerException e) {
			assertTrue(true);
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	@Test
	public void testConsumeNullToGameObjectsOnLayer() {
		try {
			l.forEachGameObject(null);
			assertTrue(false);
		} catch(NullPointerException e) {
			assertTrue(true);
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	@Test
	public void testAddGameObjectsToLayer() {
		GameObject gObj = mock(GameObject.class);
		l.addGameObject(gObj);

		assertEquals(1, l.getSize());

		for (int i = 0; i < 20; i++) {
			l.addGameObject(gObj);
		}

		assertEquals(21, l.getSize());
	}

	@Test
	public void testConsumeOnAnEmptyGameObjectListToLayer() {
		Consumer<GameObject> c = (gObj -> {
		});
		try {
			l.forEachGameObject(c);
			assertTrue(true);
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	@Test
	public void testConsumeOnGameObjectsInLayer() {
		Consumer<GameObject> c = (gObj -> {
		});
		testAddGameObjectsToLayer();
		try {
			l.forEachGameObject(c);
			assertTrue(true);
		} catch (Exception e) {
			assertTrue(false);
		}
	}


	@Test
	public void testRemovalOfMarkedGameObjectsInLayer() {
		testAddGameObjectsToLayer();

		l.forEachGameObject((gObj -> {
			when(gObj.isMarkedForRemoval()).thenReturn(false);
		}));
		l.removeMarkedGameObjects();

		assertEquals(21, l.getSize());

		l.forEachGameObject((gObj -> {
			when(gObj.isMarkedForRemoval()).thenReturn(true);
		}));
		l.removeMarkedGameObjects();
		
		assertEquals(0, l.getSize());
	}
}