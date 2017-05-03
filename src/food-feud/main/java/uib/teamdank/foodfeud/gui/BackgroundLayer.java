package uib.teamdank.foodfeud.gui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import uib.teamdank.common.gui.Layer;
import uib.teamdank.foodfeud.Level;

public class BackgroundLayer extends Layer {

	private final Level level;

	public BackgroundLayer(Level level) {
		super(false);
		this.level = level;
	}

	@Override
	protected void preRender(SpriteBatch batch, float delta) {
		batch.draw(level.getBackground(), 0, 0);
		batch.draw(level.getForeground(), 0, 0);
	}

}
