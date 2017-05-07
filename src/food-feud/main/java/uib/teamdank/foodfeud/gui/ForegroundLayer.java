package uib.teamdank.foodfeud.gui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import uib.teamdank.common.gui.Layer;
import uib.teamdank.foodfeud.Level;

public class ForegroundLayer extends Layer {

    private final Level level;

    public ForegroundLayer(Level level) {
        super(false);
        this.level = level;
    }

    @Override
    public void preRender(SpriteBatch batch, float delta) {
        batch.draw(level.getForeground(), 0, 0);
    }

}
