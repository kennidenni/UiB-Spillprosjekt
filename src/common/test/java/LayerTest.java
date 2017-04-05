import static org.junit.Assert.*;

import java.util.function.Consumer;

import org.mockito.*;

import com.badlogic.gdx.math.Vector2;

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
		
		l = new Layer(true);
		assertTrue(l.isSolid());
	}
	
	@Test
	public void testAddNullAsGameObjectToLayer() {
		try {
			l.addGameObject(null);
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testConsumeNullToGameObjectsOnLayer() {
		try {
			l.forEachGameObject(null);
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testAddGameObjectsToLayer() {
		GameObject gObj = new GameObject();
		
		l.addGameObject(gObj);
		
		assertEquals(1, l.getSize());
		
		for(int i = 0; i<20; i++){
			l.addGameObject(gObj);
		}
		
		assertEquals(21, l.getSize());
	}

	@Test
	public void testConsumeOnAnEmptyGameObjectListToLayer() {
		Consumer<GameObject> c = (gObj -> { });
		try {
			l.forEachGameObject(c);
			assertTrue(true);
		} catch (Exception e){
			assertTrue(false);
		}
	}
	
	@Test
	public void testConsumeOnGameObjectsInLayer() {
		Consumer<GameObject> c = (gObj -> { 
			gObj.setPosition(new Vector2());
			});
		testAddGameObjectsToLayer();
		try {
			l.forEachGameObject(c);
			assertTrue(true);
		} catch (Exception e){
			assertTrue(false);
		}
		
	}
}
