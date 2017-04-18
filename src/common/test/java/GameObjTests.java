import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import org.junit.Before;
import org.junit.Test;
import uib.teamdank.common.GameObject;

import java.util.Random;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

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
		TextureRegion texture = mock(TextureRegion.class);
        when(texture.getRegionWidth()).thenReturn(width);
        when(texture.getRegionHeight()).thenReturn(height);
		obj = new GameObject(x, y, texture);
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
		obj.markForRemoval();
		assertThat(true, is(equalTo(obj.isMarkedForRemoval())));
	}
	
	@Test
	public void changesPosition() {
		int x = r.nextInt(HIGHER);
		int y = r.nextInt(HIGHER);
		Vector2 p = new Vector2(x, y);
		obj.getPosisiton().set(p);
		assertThat(p, is(equalTo(obj.getPosisiton())));
	}
	
	@Test
	public void velocityChanges() {
		Vector2 v = new Vector2(r.nextFloat(), r.nextFloat());
		obj.getVelocity().set(v);
		assertThat(v, is(equalTo(obj.getVelocity())));
	}
}
