import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.math.Vector2;


import uib.teamdank.common.GameObject;

/**
 * TODO: With actual textures?
 *
 */
public class GameObjTests {
	
	private final Random r = new Random();
	
	//To have some padding around the objects
	private final int LOWER = 50, HIGHER = 100;
	
	private GameObject obj;
	
	private Vector2 pos;
	private int width, height;
	
	@Before
	public void setUp() throws Exception {
		int x = r.nextInt(HIGHER - LOWER) + LOWER;
		int y = r.nextInt(HIGHER - LOWER) + LOWER;
		width = r.nextInt(HIGHER - LOWER) + LOWER;
		height = r.nextInt(HIGHER - LOWER) + LOWER;
		
		pos = new Vector2(x, y);
		obj = new GameObject(pos, width, height);
	}
	
	
	@Test
	public void containsCoordinate() {
		int x = r.nextInt(HIGHER + 50);
		int y = r.nextInt(HIGHER + 50);
		boolean bx = x < obj.getPosisiton().x || x > (obj.getPosisiton().x + obj.getWidth());
		boolean by = y < obj.getPosisiton().y || y > (obj.getPosisiton().y + obj.getHeight());
		if(bx || by) {
			assertThat(false, is(equalTo(obj.contains(x, y))));
		} else {
			assertThat(true, is(equalTo(obj.contains(x, y))));
		}
	}

	
	@Test
	public void marksForRemoval() {
		obj.remove();
		assertThat(true, is(equalTo(obj.toBeRemoved())));
	}
	
	@Test
	public void changesPosition() {
		int x = r.nextInt(HIGHER);
		int y = r.nextInt(HIGHER);
		Vector2 p = new Vector2(x, y);
		obj.setPosition(p);
		assertThat(p, is(equalTo(obj.getPosisiton())));
	}
	
	@Test
	public void velocityChanges() {
		Vector2 v = new Vector2(r.nextFloat(), r.nextFloat());
		obj.setVelocity(v);
		assertThat(v, is(equalTo(obj.getVelocity())));
	}
	
	@Test
	public void failsWithNegativePos() {
		try {
			float x = -pos.x;
			float y = -pos.y;
			new GameObject(new Vector2(x, y), width, height);
			fail("Position with negative coordinate passed through");
		} catch (IllegalArgumentException i) {}
	}
	
	@Test
	public void failsWithNegativeSize() {
		try {
			new GameObject(pos, -width, -height);
			fail("GameObject with negative size passed through");
		} catch (IllegalArgumentException i) {}
	}
	
	@Test
	public void hasNoTexture() {
		assertThat(null, is(equalTo(obj.getTexture())));
	}
	

}
