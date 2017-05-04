package uib.teamdank.foodfeud;

import java.util.List;
import java.util.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;

public class Level implements Disposable {
	
	private static final int PLAYER_POSITION_MARGIN = 25;
	
	private final String name;
	private final World world;
	private final Texture background;
	private final Texture foreground;
	
	public Level(String name, World world, Texture background, Texture foreground) {
		if (background.getWidth() != foreground.getWidth()) {
			throw new IllegalArgumentException("foreground must be as wide as background");
		}
		if (background.getHeight() != foreground.getHeight()) {
			throw new IllegalArgumentException("foreground must be as tall as background");
		}
		
		this.name = Objects.requireNonNull(name, "name cannot be null");
		this.world = Objects.requireNonNull(world, "world cannot be null");
		this.background = Objects.requireNonNull(background, "background cannot be null");
		this.foreground = Objects.requireNonNull(foreground, "foreground cannot be null");
	}
	
	@Override
	public void dispose() {
		background.dispose();
		foreground.dispose();
		world.dispose();
	}
	
	public void distributePlayers(List<Player> players) {
		float stepX = (getWidth() - PLAYER_POSITION_MARGIN * 2f) / (players.size());
		for (int i = 0; i < players.size(); i++) 
		{
			final Body body = players.get(i).getBody();
			if (body == null) {
				throw new IllegalStateException("player does not have a body");
			}
			
			final Vector2 pos = body.getPosition();
			pos.y = getHeight() / 2;
			pos.x = PLAYER_POSITION_MARGIN + (stepX * (i + .5f));
			body.setTransform(pos, 0);
		}
	}
	
	public Texture getBackground() {
		return background;
	}
	
	public Texture getForeground() {
		return foreground;
	}
	
	public int getHeight() {
		return getBackground().getHeight();
	}
	
	public String getName() {
		return name;
	}
	
	public void updateWorld() {
		world.step(1 / 12f, 6, 4);
	}
	
	public int getWidth() {
		return getBackground().getWidth();
	}
	
	public World getWorld() {
		return world;
	}
	
}
