package uib.teamdank.foodfeud;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;

public class Level implements Disposable {
	
	private final String name;
	private final World world;
	private final Texture background;
	private final Texture foreground;
	
	public Level(String name, World world, Texture background, Texture foreground) {
		this.name = name;
		this.world = world;
		this.background = background;
		this.foreground = foreground;
	}
	
	@Override
	public void dispose() {
		background.dispose();
		foreground.dispose();
		world.dispose();
	}
	
	public Texture getBackground() {
		return background;
	}
	
	public Texture getForeground() {
		return foreground;
	}
	
	public String getName() {
		return name;
	}
	
	public void updateWorld() {
		world.step(1 / 12f, 6, 4);
	}
	
	public World getWorld() {
		return world;
	}
	
}
