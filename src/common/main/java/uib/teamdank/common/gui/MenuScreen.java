package uib.teamdank.common.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public abstract class MenuScreen {
    public ImageButton setupButton(String imageString) {
        Texture myTexture = new Texture(Gdx.files.internal(imageString));
        TextureRegion myTextureRegion = new TextureRegion(myTexture);
        TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        ImageButton logo = new ImageButton(myTexRegionDrawable);
        return logo;
    }

    public void addButtonListener(Button button, Runnable lambda) {
        button.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Stage stage = event.getTarget().getStage();
                Vector2 mouse = stage.screenToStageCoordinates(new Vector2(Gdx.input.getX(), Gdx.input.getY()));

                if (stage.hit(mouse.x, mouse.y, true) == event.getTarget()) {
                    lambda.run();
                }
            }
        });
    }
}
